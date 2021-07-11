package com.vls.newsfeedservice.converter;

import DTO.CategoryDTO;
import DTO.PostDTO;
import DTO.ThingDTO;
import DTO.UserAccountDTO;
import com.vls.newsfeedservice.model.Category;
import com.vls.newsfeedservice.model.Post;
import com.vls.newsfeedservice.model.Thing;
import com.vls.newsfeedservice.model.UserAccount;
import org.springframework.stereotype.Service;

@Service
public class Converter {
    public Converter() {

    }

    public PostDTO convertToPostDTO(Post post) {
        PostDTO postDTO = new PostDTO(post.getId(), post.getDescription(), post.getCreated_time(), post.isVisible(),
                post.isDeletion(), post.getContact(), post.getExchange_method(), post.getStatus(),
                post.getThing_id());
        return postDTO;
    }

    public ThingDTO convertToThingDTO(Thing thing, Category category, UserAccount user) {
        ThingDTO thingDTO = new ThingDTO(thing.getId(), thing.getThing_name(), thing.getOrigin(),
                thing.getPrice(), thing.getQuantity(), thing.getUsed_time(), thing.getImage(),
                thing.getUser_id(), thing.getCategory_id(), thing.getPost_id());
        CategoryDTO categoryDTO = convertToCategoryDTO(category);
        UserAccountDTO userAccountDTO = convertToUserAccountDTO(user);
        thingDTO.setCategory(categoryDTO);
        thingDTO.setUser(userAccountDTO);
        return thingDTO;
    }

    public CategoryDTO convertToCategoryDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO(category.getId(), category.getCategory_name());
        return categoryDTO;
    }

    public UserAccountDTO convertToUserAccountDTO(UserAccount user) {
        UserAccountDTO userAccountDTO = new UserAccountDTO(user.getId(), user.getEmail(), user.isBlock());
        return userAccountDTO;
    }
}
