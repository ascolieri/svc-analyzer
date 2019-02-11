package com.scolieri.ml.analyzer.controllers;

import com.scolieri.ml.analyzer.models.transport.StatsResponse;
import com.scolieri.ml.analyzer.services.StatsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats/")
@Api(value="stats", description="Operations pertaining to DNA analysis stats")
public class StatsController {

    private StatsService statsService;

    @Autowired
    public StatsController(final StatsService statsService) {
        this.statsService = statsService;
    }

    @ApiOperation(value = "Return the number of mutants vs number of humans stats",response = StatsResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The stats based on DNA analysed"),
    })
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<StatsResponse> getStats(){
        StatsResponse response = statsService.getStats();
        return ResponseEntity.ok(response);
    }
}
