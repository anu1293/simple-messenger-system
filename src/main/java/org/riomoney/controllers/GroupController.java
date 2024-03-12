package org.riomoney.controllers;

import org.riomoney.model.*;
import org.riomoney.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class GroupController implements GroupsApi{
    @Autowired
    GroupService groupService;
    @Override
    public ResponseEntity<Boolean> addMembersToGroup(GroupOperation operation, GroupMembersAddOrRemoveRequest request) {
        return  switch(operation) {
            case ADD -> ResponseEntity.ok(groupService.addMembersToGroup(request.getMembers(), request.getGroupId()));
            case REMOVE -> ResponseEntity.ok(groupService.removeMembersFromGroup(request.getMembers(), request.getGroupId()));
        };

    }

    @Override
    public ResponseEntity<Boolean> createGroup(CreateGroupRequest createGroupRequest) {
        return ResponseEntity.ok(groupService.createGroup(createGroupRequest));
    }
}
