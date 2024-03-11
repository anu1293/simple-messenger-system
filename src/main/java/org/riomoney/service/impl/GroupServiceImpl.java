package org.riomoney.service.impl;

import org.riomoney.entities.GroupEntity;
import org.riomoney.entities.UserEntity;
import org.riomoney.entities.UserGroupEntity;
import org.riomoney.entities.UserGroupId;
import org.riomoney.repositories.GroupRepository;
import org.riomoney.repositories.UserGroupsRepository;
import org.riomoney.repositories.UserRepository;
import org.riomoney.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class GroupServiceImpl implements GroupService {
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserGroupsRepository userGroupsRepository;
    @Override
    public boolean createGroup(String groupName) {
        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setName(groupName);
        groupRepository.save(groupEntity);
        return false;
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
}
