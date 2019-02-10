package com.scolieri.ml.analizer.controllers;

import com.scolieri.ml.analizer.models.transport.StatsResponse;
import com.scolieri.ml.analizer.services.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats/*")
public class StatsController {

    private StatsService statsService;

    @Autowired
    public StatsController(final StatsService statsService) {
        this.statsService = statsService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<StatsResponse> getStats(){
        StatsResponse response = statsService.getStats();
        return ResponseEntity.ok(response);
    }
}
