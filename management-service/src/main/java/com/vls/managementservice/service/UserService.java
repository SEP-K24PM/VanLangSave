package com.vls.managementservice.service;

import DTO.PostDTO;
import DTO.ThingDTO;
import DTO.UserRatingDTO;
import com.vls.managementservice.model.Account;
import com.vls.managementservice.model.Post;
import com.vls.managementservice.model.Thing;
import com.vls.managementservice.model.UserRating;
import com.vls.managementservice.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserService(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    public Post getPost(UUID thingId) {
        return postRepository.findByThingIdEquals(thingId);
    }

    public List<PostDTO> getListPostByUser(Account user) {
        List<PostDTO> postDTOList = new ArrayList<>();
        List<Thing> things = user.getThings();
        for (Thing thing : things) {
            Post temp = getPost(thing.getId());
            if (temp != null) {
                PostDTO tempDTO = modelMapper.map(temp, PostDTO.class);
                tempDTO.setThing(modelMapper.map(thing, ThingDTO.class));
                postDTOList.add(tempDTO);
            }
        }
        return postDTOList;
    }

    public List<UserRatingDTO> setRatingList(List<UserRating> ratings) {
        List<UserRatingDTO> userRatingDTOS = new ArrayList<>();
        for (UserRating userRating : ratings) {
            userRatingDTOS.add(modelMapper.map(userRating, UserRatingDTO.class));
        }
        return userRatingDTOS;
    }
}
