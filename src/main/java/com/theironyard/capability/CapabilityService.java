package com.theironyard.capability;

import com.theironyard.word.WordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by doug on 5/19/16.
 */
@Service
public class CapabilityService {

    private final Capability[] capabilities;

    @Autowired
    public CapabilityService(WordGenerator wordGenerator) {
        capabilities = new Capability[]{
                new HorizontalCapability(wordGenerator),
                new VerticalCapability(wordGenerator),
                new AngleDownCapability(wordGenerator),
                new AngleUpCapability(wordGenerator),
                new HorizontalBackwardsCapability(wordGenerator),
                new AngleBackwardsUpCapability(wordGenerator),
                new AngleBackwardsDownCapability(wordGenerator)
        };
    }

    public List<Capability> getAllCapabilities(){
        return Arrays.asList(capabilities);
    }

    public List<Capability> getCapabilities(ArrayList<String> keywords){
        ArrayList<Capability> capabilities = new ArrayList<>();

        for(String keyword : keywords){
            Capability capability = getCapability(keyword);
            if(capability != null) capabilities.add(capability);
        }

        return capabilities;
    }

    private Capability getCapability(String keyword){
        for(Capability capability : capabilities){
            if(capability.getKeyword().equals(keyword)){
                return capability;
            }
        }
        return null;
    }

    public ArrayList<String> getAllCapabilityKeywords() {
        ArrayList<String> capsList = new ArrayList<>();

        for(Capability cap : capabilities){
            capsList.add(cap.getKeyword());
        }

        return capsList;
    }
}
