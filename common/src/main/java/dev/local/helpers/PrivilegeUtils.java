package dev.local.helpers;

import dev.local.domain.Privilege;
import dev.local.domain.Role;

import java.util.Arrays;
import java.util.List;

public class PrivilegeUtils {

    public static Role createDefaultUserRole() {
        return new Role("ROLE_USER", createUserPrivileges());
    }

    private static List<Privilege> createUserPrivileges() {
        final Privilege readProjectPrivilege = new Privilege("READ_PROJECT");
        final Privilege readTaskListPrivilege = new Privilege("READ_TASKLIST");
        final Privilege readTaskPrivilege = new Privilege("READ_TASK");
        final Privilege writeProjectPrivilege = new Privilege("WRITE_PROJECT");
        final Privilege writeTaskListPrivilege = new Privilege("WRITE_TASKLIST");
        final Privilege writeTaskPrivilege = new Privilege("WRITE_TASK");
        return Arrays.asList(
                readProjectPrivilege,
                readTaskListPrivilege,
                readTaskPrivilege,
                writeProjectPrivilege,
                writeTaskListPrivilege,
                writeTaskPrivilege);
    }

}
