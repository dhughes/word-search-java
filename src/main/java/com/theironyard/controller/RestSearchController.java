package com.theironyard.controller;

import com.theironyard.Puzzle;
import com.theironyard.bean.Configuration;
import com.theironyard.capability.Capability;
import com.theironyard.capability.CapabilityService;
import com.theironyard.word.WordGenerator;
import com.theironyard.exception.CouldNotGeneratePuzzleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

/**
 * Created by doug on 5/19/16.
 */
@RestController
public class RestSearchController {

    @Autowired
    CapabilityService capabilityService;

    @Autowired
    WordGenerator wordGenerator;

    @RequestMapping(path = "/puzzle", method = RequestMethod.POST, consumes = "application/json")
    public Puzzle puzzle(@RequestBody Configuration configuration) throws CouldNotGeneratePuzzleException {

        if(configuration.getSeed() == null){
            configuration.setSeed(new Random().nextLong());
        }
        Puzzle puzzle = new Puzzle(configuration, wordGenerator, capabilityService.getCapabilities(configuration.getCapabilities()));

        //System.out.println(puzzle);
        return puzzle;
    }

    @RequestMapping(path = "/capabilities", method = RequestMethod.GET)
    public List<Capability> capabilities(){

        return capabilityService.getAllCapabilities();
    }

}
