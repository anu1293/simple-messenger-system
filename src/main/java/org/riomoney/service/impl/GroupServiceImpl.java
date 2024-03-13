package org.riomoney.service.impl;

import org.riomoney.entities.GroupEntity;
import org.riomoney.entities.UserEntity;
import org.riomoney.entities.UserGroupEntity;
import org.riomoney.entities.UserGroupId;
import org.riomoney.exceptions.GroupNotFoundException;
import org.riomoney.exceptions.UserNotFoundException;
import org.riomoney.model.CreateGroupRequest;
import org.riomoney.model.CreateGroupResponse;
import org.riomoney.model.GroupMembersAddOrRemoveResponse;
import org.riomoney.repositories.GroupRepository;
import org.riomoney.repositories.UserGroupsRepository;
import org.riomoney.repositories.UserRepository;
import org.riomoney.service.GroupService;
import org.riomoney.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserGroupsRepository userGroupsRepository;
    @Autowired
    JwtService jwtService;
    @Override
    public CreateGroupResponse createGroup(String token, CreateGroupRequest creteGroupRequest) throws UserNotFoundException, GroupNotFoundException {
        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setName(creteGroupRequest.getGroupName());
        groupRepository.save(groupEntity);
        addMembersToGroup(token,new HashSet<>(creteGroupRequest.getMembers()),groupEntity.getId());
        return new CreateGroupResponse().status("SUCCESS").message("GROUP CREATED SUCCESSFULLY!!!");
    }

    @Override
    public GroupMembersAddOrRemoveResponse addMembersToGroup(String token, Set<Integer> userIds, int groupId) throws UserNotFoundException, GroupNotFoundException {
        String userName = jwtService.extractUserName(token.substring(7));
        int userId = userRepository.findByUserName(userName).getId();
        userIds.add(userId);
        Optional<GroupEntity> groupEntity = groupRepository.findById(groupId);
        if(groupEntity.isEmpty()) {
            throw new GroupNotFoundException("invalid group id :"+groupId);
        }
        List<UserEntity> users = userRepository.listUsersById(userIds);
        if(users.stream().map(UserEntity::getId).collect(Collectors.toSet()).size() != userIds.size())
            throw new UserNotFoundException("user id provided does not exist");
        Set<UserGroupEntity> userGroupEntities = userGroupsRepository.findUsersByGroupId(groupEntity.get());
        Set<UserGroupEntity> newUserGroup = new HashSet<>();
        for (UserEntity user : users) {
            UserGroupId id = new UserGroupId();
            id.setGroup(groupEntity.get());
            id.setUser(user);
            UserGroupEntity userGroup = new UserGroupEntity();
            userGroup.setId(id);
            if (!userGroupEntities.contains(userGroup))
                newUserGroup.add(userGroup);
        }
        userGroupsRepository.saveAll(newUserGroup);
        return new GroupMembersAddOrRemoveResponse().status("SUCCESS").message("MEMBERS ADDED SUCCESSFULLY");
    }

    @Override
    public GroupMembersAddOrRemoveResponse removeMembersFromGroup(String token,Set<Integer> userIds, int groupId) throws GroupNotFoundException, UserNotFoundException {
        Optional<GroupEntity> groupEntity = groupRepository.findById(groupId);
        if(groupEntity.isEmpty()) {
            throw new GroupNotFoundException("invalid group id :"+groupId);
        }
        List<UserEntity> users = userRepository.listUsersById(userIds);

        if(users.contains(null))
            throw new UserNotFoundException("user id provided does not exist");

        Set<UserGroupEntity> userGroupEntities = userGroupsRepository.findUsersByGroupId(groupEntity.get());
        Set<UserGroupEntity> userGroupsToDelete = new HashSet<>();
        users.forEach(user-> {
            UserGroupId id = new UserGroupId();
            id.setGroup(groupEntity.get());
            id.setUser(user);
            UserGroupEntity userGroup = new UserGroupEntity();
            userGroup.setId(id);
            if(userGroupEntities.contains(userGroup))
                userGroupsToDelete.add(userGroup);
        });
        userGroupsRepository.deleteAll(userGroupsToDelete);
        return new GroupMembersAddOrRemoveResponse().status("SUCCESS").message("MEMBERS REMOVED SUCCESSFULLY");
    }
}
