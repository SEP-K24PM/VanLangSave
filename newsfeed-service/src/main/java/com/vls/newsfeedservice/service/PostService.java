package com.vls.newsfeedservice.service;

import DTO.PostDTO;
import DTO.ThingDTO;
import com.vls.newsfeedservice.converter.Converter;
import com.vls.newsfeedservice.model.Category;
import com.vls.newsfeedservice.model.Post;
import com.vls.newsfeedservice.model.Thing;
import com.vls.newsfeedservice.model.UserAccount;
import com.vls.newsfeedservice.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final ThingService thingService;
    private final CategoryService categoryService;
    private final UserAccountService userAccountService;
    private final Converter converter;

    @Autowired
    public PostService(PostRepository postRepository, ThingService thingService, CategoryService categoryService, UserAccountService userAccountService, Converter converter) {
        this.postRepository = postRepository;
        this.thingService = thingService;
        this.categoryService = categoryService;
        this.userAccountService = userAccountService;
        this.converter = converter;
    }

    public List<Post> getAllPost() {
        List<Post> list = new ArrayList<>();
        postRepository.findAllNewPost().forEach(list::add);
        return list;
    }

    public Optional<Post> getPost(UUID postId) {
        return postRepository.findById(postId);
    }

    public PostDTO getPostDetailsDTO(Post post) {
        Optional<Thing> thing = thingService.getThing(post.getThing_id());
        Optional<Category> category = categoryService.getCategory(thing.get().getCategory_id());
        Optional<UserAccount> userAccount = userAccountService.getUserAccount(thing.get().getUser_id());

        ThingDTO thingDTO = converter.convertToThingDTO(thing.get(), category.get(), userAccount.get());
        PostDTO postDetails = converter.convertToPostDTO(post);
        postDetails.setThing(thingDTO);

        return postDetails;
    }

    public List<PostDTO> convertToListPostDTO(List<Post> list) {
        List<PostDTO> postDTOList = new ArrayList<>();
        for (Post post : list) {
            postDTOList.add(getPostDetailsDTO(post));
        }
        return postDTOList;
    }
}
