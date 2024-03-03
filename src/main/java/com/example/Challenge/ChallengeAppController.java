package com.example.Challenge;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/*In Spring Boot, a bean refers to an object that is instantiated, assembled, and managed by the Spring IoC (Inversion of Control) container Spring IOC is also called as dependency injection which follows the process of Encapsulation.
 These beans are typically Java objects that form the backbone of an application's
functionality. Beans in Spring Boot are defined and configured using annotations such as @Component, @Service, @Repository, @Controller, etc., or through XML configuration files.
The Spring IoC container manages the lifecycle of these beans, including instantiation, dependency injection, and destruction, making it easier to manage and maintain complex applications.*/
@RestController
@RequestMapping("/challenges") //we just made the challenges endpoint on the class URL
public class ChallengeAppController {
    private ChallengeService challengeService; //object of challenge service (encapsulation again)

    public ChallengeAppController() {
        this.challengeService = new ChallengeService();
    }

    //dependency injection of challenge service , we usually don't need to do this springboot manages dependencies for us!

    //define endpoint
    @GetMapping  //hey SB this is a Controller and this is to get the challenges
    public ResponseEntity<List<Challenge>> getAllChallenges() {                          //method to return challenges need to map to an endpoint
        return new ResponseEntity<>(challengeService.getAllChallenges(), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<String> addChallenge(@RequestBody Challenge challenge) {
        boolean isChallengeAdded = challengeService.addChallenge(challenge);
        if (isChallengeAdded) {
            return new ResponseEntity<>("Challenge Added Successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Challenge Not Added !", HttpStatus.NOT_FOUND);         //what response entity class will do here is that it will provide the right status code if data added or not , helps the developer to configure the https sttaus code message!
        }
    }

    @GetMapping("/{month}")
    public ResponseEntity<Challenge> getChallenge(@PathVariable String month) {
        //this particular method in controller will need an input for which challenge you want according to your month
        Challenge challenge = challengeService.getChallenge((month)); //get challenge method needs to be defined in our service
        if (challenge != null) {
            return new ResponseEntity<>(challenge, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //will be making put and delete endpoints now

    @PutMapping("/{id}")  //putmapping is used to update the response body of the data we saves
    public ResponseEntity<String> updateChallenge(@PathVariable int id,@RequestBody Challenge updateChallenge) {    //my find use the @request body in order to change the data of the attributes called by that specific id
        boolean isChallengeUpdated = challengeService.updateChallenge(id, updateChallenge); //create the same method updateChallenge in Service
        if (isChallengeUpdated) {
            return new ResponseEntity<>("Challenge updated Successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Challenge is not updated Successfully", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")//delete mapping will delete the challenge
    public ResponseEntity<String> deleteChallenge(@PathVariable int id){
        boolean isChallengeDeleted= challengeService.deleteChallenge(id);
        if(isChallengeDeleted){
            return new ResponseEntity<>("Challenge Deleted Successfully",HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>("Challenge not deleted Successfully- Check the challenge ID",HttpStatus.NOT_FOUND);
        }
    }
}
