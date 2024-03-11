package org.riomoney.service;

import java.util.List;

public interface GroupService {
    boolean createGroup(String groupName);
    boolean addMembersToGroup(List<Integer> userIds, int groupId);
}
