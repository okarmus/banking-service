package org.okarmus.service.client.user;

import feign.Headers;
import org.okarmus.domain.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by mateusz on 28.11.16.
 */
@FeignClient("user-service")
public interface UserServiceClient {

    @RequestMapping(method = POST, value = "/users/user")
    @Headers("Content-Type: application/json")
    String addUser(User user);
}
