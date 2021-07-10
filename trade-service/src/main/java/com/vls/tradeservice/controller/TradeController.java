package com.vls.tradeservice.controller;

import com.vls.tradeservice.model.PostRegistration;
import com.vls.tradeservice.repository.PostRegistrationRepo;
import com.vls.tradeservice.repository.PostRepo;
import com.vls.tradeservice.repository.ThingRepo;
import com.vls.tradeservice.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class TradeController {
    private final PostRegistrationRepo postRegistrationRepo;
    private final ThingRepo thingRepo;
    private final PostRepo postRepo;
    private final UserRepo userRepo;

    @Autowired
    public TradeController(PostRegistrationRepo postRegistrationRepo, ThingRepo thingRepo,
                           PostRepo postRepo, UserRepo userRepo) {
        this.postRegistrationRepo = postRegistrationRepo;
        this.thingRepo = thingRepo;
        this.postRepo = postRepo;
        this.userRepo = userRepo;
    }


    @RequestMapping(value = "/postRegist", method = RequestMethod.POST)
    public ResponseEntity<PostRegistration> GetInforPost(@RequestBody PostRegistration postRegistration){
        return new ResponseEntity<>( postRegistration, HttpStatus.OK);
    }


    //save với dữ liệu mẫu
    @RequestMapping(value = "/postRegist/do", method = RequestMethod.POST)
    public ResponseEntity<PostRegistration> SavePostRegistration(@RequestBody PostRegistration postRegistration){
        try {

            //UUID _userID = UUID.fromString("14551453-4e68-4e40-9aac-fda12a7b11bc");
            //UUID _thingID = UUID.fromString("5fad1a4e-e14f-4a7a-a85d-4c1c6547a9c7");
            //UUID _postID = UUID.fromString("3f552bf8-0bb7-4d5d-b1e2-179844bcd338");
            //PostRegistration _postRegistration = new PostRegistration(_thingID,_userID,_postID);

            var saved = postRegistrationRepo.save(postRegistration);
            return new ResponseEntity<>(saved,HttpStatus.CREATED);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }



    }

}