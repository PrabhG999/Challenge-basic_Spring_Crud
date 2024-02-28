package com.example.Challenge;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
@RestController
public class ChallengeAppController {
    private List<Challenge> challenges = new ArrayList<>();

    //define endpoint
    public ChallengeAppController(){
        Challenge challenge1 = new Challenge(1,"February",
                "My first Challenge- Learning Springboot");
        challenges.add(challenge1);
    }
    @GetMapping("/challenges")   //hey SB this is a Controller and this is to get the challneges
    public List<Challenge> getAllChallenges(){  //method to return challenges need to map to an endpoint
        return challenges;
    }

    @PostMapping("/challenges")
    public String addChallenge(@RequestBody Challenge challenge){
        challenges.add(challenge);
        challenges.sort(Comparator.comparing(Challenge::getId));  //will return sorted every time
        return "Challenge Added Successfully";
    }
}
