package org.riomoney.controllers;

import org.riomoney.exceptions.GroupNotFoundException;
import org.riomoney.exceptions.UserNotFoundException;
import org.riomoney.model.*;
import org.riomoney.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;

@RestController
public class GroupController implements GroupsApi {
    @Autowired
    GroupService groupService;
    @Override
    public ResponseEntity<GroupMembersAddOrRemoveResponse> addMembersToGroup(String token, GroupOperation operation, GroupMembersAddOrRemoveRequest request) {
        GroupMembersAddOrRemoveResponse response = new GroupMembersAddOrRemoveResponse();
           try {
               return switch (operation) {
                   case ADD -> ResponseEntity.ok(groupService.addMembersToGroup(token, new HashSet<>(request.getMembers()), request.getGroupId()));
                   case REMOVE -> ResponseEntity.ok(groupService.removeMembersFromGroup(token, new HashSet<>(request.getMembers()), request.getGroupId()));
               };
           } catch(UserNotFoundException e) {
              return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response.status("USER_NOT_FOUND").message("ONE OR MORE INVALID USERS"));
           } catch(GroupNotFoundException e) {
               return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response.status("GROUP_NOT_FOUND").message("INVALID GROUP ID"));
           } catch(Exception e) {
               return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response.status("SERVER ERROR").message("something went wrong"));
           }

    }

    @Override
    public ResponseEntity<CreateGroupResponse> createGroup(String token,CreateGroupRequest createGroupRequest) {
        CreateGroupResponse response = new CreateGroupResponse();

        try {
            return ResponseEntity.ok(groupService.createGroup(token, createGroupRequest));
        } catch(UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response.status("USER_NOT_FOUND").message("ONE OR MORE INVALID USERS"));
        } catch(GroupNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response.status("GROUP_NOT_FOUND").message("INVALID GROUP ID"));
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response.status("SERVER ERROR").message("something went wrong"));
        }
    }
}
