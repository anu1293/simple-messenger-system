package org.riomoney.service.impl;

import org.riomoney.entities.GroupEntity;
import org.riomoney.entities.UserEntity;
import org.riomoney.entities.UserGroupEntity;
import org.riomoney.entities.UserGroupId;
import org.riomoney.model.CreateGroupRequest;
import org.riomoney.repositories.GroupRepository;
import org.riomoney.repositories.UserGroupsRepository;
import org.riomoney.repositories.UserRepository;
import org.riomoney.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserGroupsRepository userGroupsRepository;
    @Override
    public boolean createGroup(CreateGroupRequest creteGroupRequest) {
        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setName(creteGroupRequest.getGroupName());
        groupRepository.save(groupEntity);
        addMembersToGroup(creteGroupRequest.getMembers(),groupEntity.getId());
        return true;
    }

    @Override
    public boolean addMembersToGroup(List<Integer> userIds, int groupId) {
        GroupEntity groupEntity = groupRepository.findById(groupId);
        List<UserEntity> users = userRepository.listUsersById(userIds);
        List<UserGroupEntity> userGroupEntities = new ArrayList<>();
        users.forEach(user-> {
            UserGroupId id = new UserGroupId();
            id.setGroup(groupEntity);
            id.setUser(user);
            UserGroupEntity userGroup = new UserGroupEntity();
            userGroup.setId(id);
            userGroupEntities.add(userGroup);
        });

        userGroupsRepository.saveAll(userGroupEntities);
        return true;
    }

    @Override
    public boolean removeMembersFromGroup(List<Integer> userIds, int groupId) {
        GroupEntity groupEntity = groupRepository.findById(groupId);
        List<UserEntity> users = userRepository.listUsersById(userIds);
        List<UserGroupEntity> userGroupEntities = new ArrayList<>();
        users.forEach(user-> {
            UserGroupId id = new UserGroupId();
            id.setGroup(groupEntity);
            id.setUser(user);
            UserGroupEntity userGroup = new UserGroupEntity();
            userGroup.setId(id);
            userGroupEntities.add(userGroup);
        });
        userGroupsRepository.deleteAll(userGroupEntities);
        return true;
    }

}
