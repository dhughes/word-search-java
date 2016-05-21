package com.theironyard.controller;

import com.theironyard.Puzzle;
import com.theironyard.bean.Configuration;
import com.theironyard.capability.CapabilityService;
import com.theironyard.exception.CouldNotGeneratePuzzleException;
import com.theironyard.word.WordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Random;

/**
 * Created by doug on 5/20/16.
 */
@Controller
public class HtmlSearchController {

    @Autowired
    WordGenerator wordGenerator;

    @Autowired
    CapabilityService capabilityService;

    @RequestMapping("/")
    public String configurePuzzle(Model model){

        // do we have a configuration object in the model already? (IE via a flash attribute)
        if(!model.containsAttribute("configuration")){
            // nope, let's add one!
            model.addAttribute("configuration", new Configuration(
                    20, 20, 10, 3, 8, capabilityService.getAllCapabilityKeywords(), new Random().nextLong()
            ));
        }

        // add our capabilities collection to the model too
        model.addAttribute("capabilities", capabilityService.getAllCapabilities());

        return "home";
    }

    @RequestMapping(path = "/puzzle", method = {RequestMethod.POST, RequestMethod.GET})
    public String generatePuzzle(@Valid Configuration configuration, BindingResult bindingResult, RedirectAttributes redirectAttrs, Model model){
        if(bindingResult.hasErrors()) {
            redirectAttrs.addFlashAttribute("configuration", configuration);
            redirectAttrs.addFlashAttribute("bindingResult", bindingResult);
            return "redirect:/";
        }

        // create a puzzle
        Puzzle puzzle;
        try {
            model.addAttribute("puzzle", new Puzzle(configuration, wordGenerator, capabilityService.getCapabilities(configuration.getCapabilities())));
        } catch (CouldNotGeneratePuzzleException e) {
            model.addAttribute("configuration", configuration);
            model.addAttribute("capabilities", capabilityService.getAllCapabilities());

            return "home";
        }

        return "puzzle";
    }

}
