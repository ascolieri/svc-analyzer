package com.scolieri.ml.analyzer.controllers;

import com.scolieri.ml.analyzer.models.transport.MutantRequest;
import com.scolieri.ml.analyzer.services.SequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/mutant/*")
public class MutantController {

    private SequenceService sequenceService;

    @Autowired
    public MutantController(final SequenceService sequenceService) {
        this.sequenceService = sequenceService;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity IsMutant(@Valid @RequestBody MutantRequest request){
        boolean isMutant = sequenceService.validateSequence(request.getDna());
        if(isMutant){
           return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
