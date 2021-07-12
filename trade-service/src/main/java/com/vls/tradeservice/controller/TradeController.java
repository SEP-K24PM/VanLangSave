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

import java.util.List;
import java.util.Optional;
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
    //duyệt đăng ký
    @RequestMapping(value = "/postRegist", method = RequestMethod.PUT)
    public ResponseEntity<PostRegistration> choosenRegister(@RequestBody PostRegistration postRegistration){
        PostRegistration temp = postRegistration;
        try {
            if (temp.getId() != null){
                PostRegistration _getUpdate = temp;
                _getUpdate.setPost_id(temp.getPost_id());
                _getUpdate.setId(temp.getId());
                _getUpdate.setChoosen(true);
                _getUpdate.setDescription(temp.getDescription());
                _getUpdate.setThing_id(temp.getThing_id());
                _getUpdate.setUser_id(temp.getUser_id());
                var saved = postRegistrationRepo.save(_getUpdate);
                return new ResponseEntity<>(_getUpdate, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            }
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // gửi yêu cầu đổi đồ
    @RequestMapping(value = "/GivePostRegist", method = RequestMethod.POST)
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
    //show toàn bộ yêu cầu đổi đồ của 1 bài post
    @RequestMapping(value = "/details/{postID}",method = RequestMethod.GET)
    public ResponseEntity<PostRegistration> LoadListRegister(@PathVariable("postID") UUID postID) {
        try {
            List<PostRegistration> ListRegister = postRegistrationRepo.giveListRegister(postID);
            if (ListRegister.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity(ListRegister, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}