package com.distributed.MusicAppProject.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import java.io.IOException;

@Component
public class SIMHRestTemplate {


    private IHTTPConnection httpConnection;

    /**
     * Instantiates a new rest template.
     *
     * @param httpConnection the http connection
     */
    public SIMHRestTemplate(IHTTPConnection httpConnection) {
        this.httpConnection = httpConnection;
    }

    /**
     * Get t.
     *
     * @param <T>    /*the type parameter e.g. <p>Artist,Album,Track....</p>
     * @param url    the url
     * @param target the variable name
     * @return the t
     * @throws Exception the exception
     */
    public <T> T get(final String url, Class<T> target) throws Exception {
        return getRequest(url, target);
    }


    private <T> T getRequest(final String url, Class<T> target) throws Exception {

        try {
            final String response = httpConnection.getConnect(url);
            if (containsError(response)) {
                throw new RestClientException("Error " + response);
            } else {
                return convertJson(httpConnection.getConnect(url), target);
            }
        } catch (IOException ex) {
            throw new RestClientException("There is an exception for url " + url, ex);
        }
    }

    private <T> T convertJson(final String content, Class<T> target) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, target);


    }

    private Boolean containsError(final String response) {
        return response.startsWith("{\"error");
    }
}