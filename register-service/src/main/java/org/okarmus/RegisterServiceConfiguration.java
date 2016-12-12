package org.okarmus;

import feign.codec.ErrorDecoder;
import org.okarmus.service.client.response.BodyExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by mateusz on 29.11.16.
 */
@Configuration
public class RegisterServiceConfiguration {

    @Bean
    public BodyExtractor bodyExtractor() {
        return new BodyExtractor();
    }

    @Bean
    public ErrorDecoder.Default defaultDecoder() {
        return new ErrorDecoder.Default();
    }
}
