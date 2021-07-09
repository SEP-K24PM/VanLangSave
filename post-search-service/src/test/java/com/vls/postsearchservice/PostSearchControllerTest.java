package com.vls.postsearchservice;

import com.vls.postsearchservice.controller.PostSearchController;
import com.vls.postsearchservice.dto.postelastic;
import com.vls.postsearchservice.repository.PostRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class PostSearchControllerTest {
    @InjectMocks
    private PostSearchController postSearchController;

    @Mock
    private PostRepository postRepository;

    @Test
    public void testPostSearch() {
        List<postelastic> posts = new ArrayList<>();
        postelastic post = new postelastic(
                "3f405f65-7c23-441f-a2da-6fff76eae14a",
                "description",
                "exchange method",
                new Date(),
                true,
                "thing name",
                "origin",
                "category name");
        posts.add(post);
        posts.add(post);
        posts.add(post);
        posts.add(post);
        posts.add(post);

        String name = "name";

        //Mockito.when(postRepository.findBy(name)).thenReturn(posts);

        //List<postelastic> result = postSearchController.posts(name);

        //Assert.assertEquals(5, result.size());
    }
}
