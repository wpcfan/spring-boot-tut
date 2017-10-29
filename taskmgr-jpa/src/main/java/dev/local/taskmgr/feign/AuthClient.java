package dev.local.taskmgr.feign;

import com.netflix.hystrix.HystrixObservable;
import dev.local.taskmgr.dto.UserDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("http://auth")
public interface AuthResource {
    @RequestMapping(value = "/user/register", method = RequestMethod.POST, consumes = "application/json")
    HystrixObservable<UserDTO> register(UserDTO user);
}
