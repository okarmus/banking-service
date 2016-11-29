package org.okarmus.service.create

import org.okarmus.repository.AccountRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

/**
 * Created by mateusz on 28.11.16.
 */
@Configuration
@ComponentScan(basePackages = ["org.okarmus.service.create"])
class AccountCreateServiceTestConfiguration {

    @Bean
    public AccountRepository accountRepository() {
        return null
    }
}
