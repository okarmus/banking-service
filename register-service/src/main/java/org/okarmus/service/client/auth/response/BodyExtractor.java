package org.okarmus.service.client.auth.response;

import feign.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * Created by mateusz on 29.11.16.
 */
public class BodyExtractor {

    private static final String DEFAULT_RESPONSE = "";

    public String extract(Response response) {
        try {
            InputStream stream  = response.body().asInputStream();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(stream));
            return buffer.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace(); //TODO change to logging
            return DEFAULT_RESPONSE;
        }
    }

}
