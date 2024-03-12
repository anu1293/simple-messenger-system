package org.riomoney.service;

import org.riomoney.model.CreateGroupRequest;
import org.riomoney.model.CreteGroupRequest;

import java.util.List;

public interface GroupService {
    boolean createGroup(CreateGroupRequest createGroupRequest);
    boolean addMembersToGroup(List<Integer> userIds, int groupId);

    boolean removeMembersFromGroup(List<Integer> userIds, int groupId);
}
