package dev.local.taskmgr.feign;

import dev.local.taskmgr.dto.UserDTO;

public class AuthClientFallback implements AuthClient{
    @Override
    public UserDTO register(UserDTO user) {
        return null;
    }
}
