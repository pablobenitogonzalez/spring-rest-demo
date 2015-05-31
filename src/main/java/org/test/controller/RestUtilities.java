package org.test.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class RestUtilities {

    public static HttpHeaders getHttpHeaders(UriComponentsBuilder uriComponentsBuilder, String path, String id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        URI locationURI = uriComponentsBuilder.path(path).path("/"+id).build().toUri();
        httpHeaders.setLocation((locationURI));
        return httpHeaders;
    }
}
