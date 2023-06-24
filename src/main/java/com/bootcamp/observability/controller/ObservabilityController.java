package com.bootcamp.observability.controller;

import com.bootcamp.observability.service.HttpBinService;
import io.micrometer.observation.annotation.Observed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Observed(name = "observability.controller")
public class ObservabilityController {

    @Autowired
    private HttpBinService httpBinService;

    @GetMapping("/data")
    public ResponseEntity<String> retrieveSampleData() {
        String sampleData = httpBinService.retrieveData();
        return ResponseEntity.ok(sampleData);
    }
}
