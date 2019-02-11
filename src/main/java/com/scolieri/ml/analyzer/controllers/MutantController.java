package com.scolieri.ml.analyzer.controllers;

import com.scolieri.ml.analyzer.models.transport.MutantRequest;
import com.scolieri.ml.analyzer.services.SequenceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/mutant")
@Api(value="mutant", description="Operations pertaining to DNA analysis")
public class MutantController {

    private SequenceService sequenceService;

    @Autowired
    public MutantController(final SequenceService sequenceService) {
        this.sequenceService = sequenceService;
    }

    @ApiOperation(value = "Verify if the DNA sequence corresponds to a mutant")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The DNA corresponds to a mutant"),
            @ApiResponse(code = 403, message = "The DNA corresponds to a single human"),
            @ApiResponse(code = 400, message = "The DNA sequence contains invalid characters")
    })
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Void> IsMutant(@Valid @RequestBody MutantRequest request){
        boolean isMutant = sequenceService.validateSequence(request.getDna());
        if(isMutant){
           return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
