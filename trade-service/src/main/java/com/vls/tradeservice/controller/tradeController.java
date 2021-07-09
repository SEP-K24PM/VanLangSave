package com.vls.tradeservice.controller;

import com.vls.tradeservice.model.PostRegistration;
import com.vls.tradeservice.repository.PostRegistrationRepo;
import com.vls.tradeservice.repository.PostRepo;
import com.vls.tradeservice.repository.ThingRepo;
import com.vls.tradeservice.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class tradeController {
    private final PostRegistrationRepo postRegistrationRepo;
    private final ThingRepo thingRepo;
    private final PostRepo postRepo;
    private final UserRepo userRepo;

    @Autowired
    public tradeController(PostRegistrationRepo postRegistrationRepo,ThingRepo thingRepo,
                           PostRepo postRepo,UserRepo userRepo) {
        this.postRegistrationRepo = postRegistrationRepo;
        this.thingRepo = thingRepo;
        this.postRepo = postRepo;
        this.userRepo = userRepo;
    }
    @RequestMapping(value = "/Registation/{email}")
    public ResponseEntity<List<PostRegistration>> getAllPostRegist(@PathVariable("email") String email) {
        //List<PostRegistration> listRegisted
        return new ResponseEntity<>(null, HttpStatus.OK);
    }


}
