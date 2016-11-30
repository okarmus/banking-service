package org.okarmus.service.client.auth;

import feign.Headers;
import org.okarmus.domain.Account;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by mateusz on 26.11.16.
 */

@FeignClient(value = "auth-service", configuration = {AuthErrorDecoder.class})
public interface FeignAuthClient {

    @RequestMapping(method = POST, value = "/uaa/account")
    @Headers("Content-Type: application/json")
    ResponseEntity<String> createAccount(Account account);
}
