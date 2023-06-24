package com.bootcamp.observability.service;

import io.micrometer.observation.annotation.Observed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Service
public class HttpBinService {

    @Autowired
    private RestTemplate restTemplate;

    @Observed(name = "http.bin.service.retrieve.data")
    public String retrieveData() {
        String sampleResponse = null;
        try {
            sampleResponse = restTemplate.getForObject("https://httpbin.org/get", String.class);
        } catch (HttpServerErrorException httpServerErrorException) {
            System.out.println("Received HTTP server error exception while fetching the details. Error Message: " + httpServerErrorException.getMessage());
            throw httpServerErrorException;
        } catch (HttpClientErrorException httpClientErrorException) {
            System.out.println("Received HTTP client error exception while fetching the details. Error Message: " + httpClientErrorException.getMessage());
            throw httpClientErrorException;
        } catch (ResourceAccessException resourceAccessException) {
            System.out.println("Received Resource Access exception while fetching the details.");
            throw resourceAccessException;
        }
        return sampleResponse;
    }
}
