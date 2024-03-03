package com.example.Challenge;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ChallengeService {
    //this here manages all the addition and deletion basically CRUD operation in the application.
    private List<Challenge> challenges = new ArrayList<>();
    private int nextId = 1;

    public ChallengeService() {
        //this.challenges= new Challenge(); //dont add anything it will ask for 3 arguments SB will mamnage
    }

    public List<Challenge> getAllChallenges() {
        return challenges;
    }

    public boolean addChallenge(@RequestBody Challenge challenge) {
        if (challenge != null) { //validation check
            challenge.setId(nextId++);            //from challenge class object call setId and increment nextId (Automated id even if you dont give in id )
            challenges.add(challenge);
            challenges.sort(Comparator.comparing(Challenge::getId));  //will return sorted every time
            return true;
        } else {
            return false;
        }
    }

    public Challenge getChallenge(String month) {
        for (Challenge challenge : challenges) {
            if (challenge.getMonth().equalsIgnoreCase(month)) { //equalignore case will ignore if first letter in month are capital or not
                return challenge;
            }
        }
        return null;
    }


    public boolean updateChallenge(int id, Challenge updateChallenge) {
        for(Challenge challenge:challenges){
            if(challenge.getId()==id){
                challenge.setMonth(updateChallenge.getMonth());
                challenge.setDescription(updateChallenge.getDescription());   //we are just replacing the values here like description and month - first it will fetch if the month we are finding exits change the other attributes by updating them.
                return true;
            }
        }
        return false;
    }

    public boolean deleteChallenge(int id) {
       return challenges.removeIf(challenge -> challenge.getId()==id); //this is a lambda expression
        //how does it work ?
        /*this above expression means remove the challenge is the provided id is eqal to the id we have in challenge class, if no element found then return false.*/
    }
}

