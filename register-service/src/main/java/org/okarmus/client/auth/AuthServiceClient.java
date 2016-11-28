package org.okarmus.client.auth;

import feign.Headers;
import org.okarmus.domain.Account;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by mateusz on 26.11.16.
 */

@FeignClient("auth-service")
public interface AuthServiceClient {

    @RequestMapping(method = POST, value = "/uaa/account")
    @Headers("Content-Type: application/json")
    String createAccount(Account account);

    @RequestMapping(method = GET, value = "/uaa/account")
    String getHello();
}
