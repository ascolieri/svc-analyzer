package com.scolieri.ml.analizer.controllers;

import com.scolieri.ml.analizer.models.MutantRequest;
import com.scolieri.ml.analizer.services.MutantService;
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

    private MutantService mutantService;

    @Autowired
    public MutantController(final MutantService mutantService) {
        this.mutantService = mutantService;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity IsMutant(@Valid @RequestBody MutantRequest request){
        boolean isMutant = mutantService.isMutant(request.getDna());
        if(isMutant){
           return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
