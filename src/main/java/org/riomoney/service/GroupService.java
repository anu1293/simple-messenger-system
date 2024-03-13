package org.riomoney.service;

import org.riomoney.exceptions.GroupNotFoundException;
import org.riomoney.exceptions.UserNotFoundException;
import org.riomoney.model.CreateGroupRequest;
import org.riomoney.model.CreateGroupResponse;
import org.riomoney.model.GroupMembersAddOrRemoveResponse;

import java.util.Set;

public interface GroupService {
    CreateGroupResponse createGroup(String token, CreateGroupRequest createGroupRequest) throws UserNotFoundException, GroupNotFoundException;
    GroupMembersAddOrRemoveResponse addMembersToGroup(String token, Set<Integer> userIds, int groupId) throws UserNotFoundException, GroupNotFoundException;

    GroupMembersAddOrRemoveResponse removeMembersFromGroup(String token,Set<Integer> userIds, int groupId) throws GroupNotFoundException, UserNotFoundException;
}
