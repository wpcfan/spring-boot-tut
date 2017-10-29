package dev.local.taskmgr.feign;

import dev.local.taskmgr.dto.UserDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "http://auth", fallback = AuthClientFallback.class)
public interface AuthClient {
    @RequestMapping(value = "/user/register", method = RequestMethod.POST, consumes = "application/json")
    UserDTO register(UserDTO user);
}
