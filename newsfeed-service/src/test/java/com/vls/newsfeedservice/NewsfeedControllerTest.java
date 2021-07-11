package com.vls.newsfeedservice;

import DTO.CategoryDTO;
import DTO.PostDTO;
import DTO.ThingDTO;
import DTO.UserAccountDTO;
import com.vls.newsfeedservice.controller.NewsfeedController;
import com.vls.newsfeedservice.converter.Converter;
import com.vls.newsfeedservice.model.Category;
import com.vls.newsfeedservice.model.Post;
import com.vls.newsfeedservice.model.Thing;
import com.vls.newsfeedservice.model.UserAccount;
import com.vls.newsfeedservice.repository.CategoryRepository;
import com.vls.newsfeedservice.repository.PostRepository;
import com.vls.newsfeedservice.repository.ThingRepository;
import com.vls.newsfeedservice.repository.UserAccountRepository;
import com.vls.newsfeedservice.service.CategoryService;
import com.vls.newsfeedservice.service.PostService;
import com.vls.newsfeedservice.service.ThingService;
import com.vls.newsfeedservice.service.UserAccountService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class NewsfeedControllerTest extends AbstractTest {
    private NewsfeedController newsfeedController;
    private PostService postService;
    private ThingService thingService;
    private CategoryService categoryService;
    private UserAccountService userAccountService;
    private Converter converter;

    @Mock
    private PostRepository postRepository;
    @Mock
    private ThingRepository thingRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private UserAccountRepository userAccountRepository;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        converter = new Converter();
        categoryService = new CategoryService(categoryRepository);
        userAccountService = new UserAccountService(userAccountRepository);
        thingService = new ThingService(thingRepository);
        postService = new PostService(postRepository, thingService, categoryService, userAccountService, converter);
        newsfeedController = new NewsfeedController(postService);
    }

    @Test
    public void newsFeed() {
        UserAccount user = new UserAccount(UUID.randomUUID(), "email", false);
        Category category = new Category(UUID.randomUUID(), "name");
        Thing thing = new Thing(UUID.randomUUID(), "name", "origin", 1000, 1, "used time",
                "image", user.getId(), category.getId(), UUID.randomUUID());
        Post post = new Post(thing.getPost_id(), "description", new Date(), true, false,
                "contact", thing.getId(), "Free", "Mở");
        List<Post> posts = new ArrayList<>();
        posts.add(post);
        posts.add(post);

        UserAccountDTO userAccountDTO = new UserAccountDTO(user.getId(), user.getEmail(), user.isBlock());
        CategoryDTO categoryDTO = new CategoryDTO(category.getId(), category.getCategory_name());
        ThingDTO thingDTO = new ThingDTO(thing.getId(), thing.getThing_name(), thing.getOrigin(),
                thing.getPrice(), thing.getQuantity(), thing.getUsed_time(), thing.getImage(),
                thing.getUser_id(), thing.getCategory_id(), thing.getPost_id());
        thingDTO.setCategory(categoryDTO);
        thingDTO.setUser(userAccountDTO);
        PostDTO postDTO = new PostDTO(post.getId(), post.getDescription(), post.getCreated_time(), post.isVisible(),
                post.isDeletion(), post.getContact(), post.getExchange_method(), post.getStatus(),
                post.getThing_id());
        postDTO.setThing(thingDTO);
        List<PostDTO> postDTOList = new ArrayList<>();
        postDTOList.add(postDTO);
        postDTOList.add(postDTO);


        Mockito.when(postRepository.findAllNewPost()).thenReturn(posts);
        Mockito.when(thingRepository.findById(thing.getId())).thenReturn(Optional.of(thing));
        Mockito.when(categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));
        Mockito.when(userAccountRepository.findById(user.getId())).thenReturn(Optional.of(user));

        ResponseEntity<List<PostDTO>> response = newsfeedController.newsFeed();
        Assert.assertEquals(200, response.getStatusCodeValue());
        List<PostDTO> result = response.getBody();
        for (int i = 0; i < postDTOList.size(); i++) {
            PostDTO expected = postDTOList.get(i);
            PostDTO actual = result.get(i);
            Assert.assertEquals(expected.getId(), actual.getId());
            Assert.assertEquals(expected.getDescription(), actual.getDescription());
            Assert.assertEquals(expected.getCreated_time(), actual.getCreated_time());
            Assert.assertEquals(expected.isVisible(), actual.isVisible());
            Assert.assertEquals(expected.isDeletion(), actual.isDeletion());
            Assert.assertEquals(expected.getExchange_method(), actual.getExchange_method());
            Assert.assertEquals(expected.getContact(), actual.getContact());
            Assert.assertEquals(expected.getStatus(), actual.getStatus());
            Assert.assertEquals(expected.getThing_id(), actual.getThing_id());
        }
    }

    @Test
    public void details() {
        UserAccount user = new UserAccount(UUID.randomUUID(), "email", false);
        Category category = new Category(UUID.randomUUID(), "name");
        Thing thing = new Thing(UUID.randomUUID(), "name", "origin", 1000, 1, "used time",
                "image", user.getId(), category.getId(), UUID.randomUUID());
        Post post = new Post(thing.getPost_id(), "description", new Date(), true, false,
                "contact", thing.getId(), "Free", "Mở");

        UserAccountDTO userAccountDTO = new UserAccountDTO(user.getId(), user.getEmail(), user.isBlock());
        CategoryDTO categoryDTO = new CategoryDTO(category.getId(), category.getCategory_name());
        ThingDTO thingDTO = new ThingDTO(thing.getId(), thing.getThing_name(), thing.getOrigin(),
                thing.getPrice(), thing.getQuantity(), thing.getUsed_time(), thing.getImage(),
                thing.getUser_id(), thing.getCategory_id(), thing.getPost_id());
        thingDTO.setCategory(categoryDTO);
        thingDTO.setUser(userAccountDTO);
        PostDTO postDTO = new PostDTO(post.getId(), post.getDescription(), post.getCreated_time(), post.isVisible(),
                post.isDeletion(), post.getContact(), post.getExchange_method(), post.getStatus(),
                post.getThing_id());
        postDTO.setThing(thingDTO);


        Mockito.when(postRepository.findById(post.getId())).thenReturn(Optional.of(post));
        Mockito.when(thingRepository.findById(thing.getId())).thenReturn(Optional.of(thing));
        Mockito.when(categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));
        Mockito.when(userAccountRepository.findById(user.getId())).thenReturn(Optional.of(user));

        ResponseEntity<PostDTO> response = newsfeedController.postDetails(post.getId());
        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(postDTO.getId(), response.getBody().getId());
        Assert.assertEquals(postDTO.getDescription(), response.getBody().getDescription());
        Assert.assertEquals(postDTO.getCreated_time(), response.getBody().getCreated_time());
        Assert.assertEquals(postDTO.isVisible(), response.getBody().isVisible());
        Assert.assertEquals(postDTO.isDeletion(), response.getBody().isDeletion());
        Assert.assertEquals(postDTO.getExchange_method(), response.getBody().getExchange_method());
        Assert.assertEquals(postDTO.getContact(), response.getBody().getContact());
        Assert.assertEquals(postDTO.getStatus(), response.getBody().getStatus());
        Assert.assertEquals(postDTO.getThing_id(), response.getBody().getThing_id());

        Mockito.when(postRepository.findById(post.getId())).thenReturn(Optional.empty());
        ResponseEntity<PostDTO> responseNotFound = newsfeedController.postDetails(post.getId());
        Assert.assertEquals(404, responseNotFound.getStatusCodeValue());
    }
}




