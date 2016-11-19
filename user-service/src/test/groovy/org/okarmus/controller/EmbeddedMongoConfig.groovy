package org.okarmus.controller

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration
import org.springframework.context.annotation.Configuration

/**
 * Created by mateusz on 17.11.16.
 */
@Configuration
@EnableAutoConfiguration(exclude = [EmbeddedMongoAutoConfiguration.class])
class EmbeddedMongoConfig {}
