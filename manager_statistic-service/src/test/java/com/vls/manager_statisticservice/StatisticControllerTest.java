package com.vls.manager_statisticservice;

import com.vls.manager_statisticservice.controller.StatisticController;
import com.vls.manager_statisticservice.model.Post;
import com.vls.manager_statisticservice.model.Category;
import com.vls.manager_statisticservice.repository.PostRepository;
import com.vls.manager_statisticservice.repository.CategoryRepository;
import com.vls.manager_statisticservice.service.PostService;
import com.vls.manager_statisticservice.service.CategoryService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.UUID;
import java.util.Date;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManagerStatisticServiceApplication.class)
@WebAppConfiguration
public class StatisticControllerTest extends AbstractTest {
    private StatisticController statisticController;
    private PostService postService;
    private CategoryService categoryService;
    private ModelMapper modelMapper;

    @Mock
    private RestTemplate restTemplate;
    @Mock
    private PostRepository postRepository;
    @Mock
    private CategoryRepository categoryRepository;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        postService = new PostService(postRepository);
        categoryService = new CategoryService(categoryRepository);
        modelMapper = new ModelMapper();
        statisticController = new StatisticController(postService, categoryService);
    }

    @Test
    public void poststatistic() {
        UUID thingId = UUID.randomUUID();

        List<Post> posts = new ArrayList<>();
        posts.add(new Post(UUID.randomUUID(), "description", new Date(), thingId,
                true, false, "Hoàn tất", "Free", "contact"));
        posts.add(new Post(UUID.randomUUID(), "description", new Date(), thingId,
                true, false, "Mở", "Free", "contact"));

        Mockito.when(postRepository.findByStatus("Hoàn tất")).thenReturn(posts);

        ResponseEntity<List<Post>> response = statisticController.getSucessPost();
        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(posts, response.getBody());
    }

    @Test
    public void categoryStatistic() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(UUID.randomUUID(), "name"));
        Mockito.when(categoryRepository.countAllCategory()).thenReturn(categories);

        ResponseEntity<List<Category>> response = statisticController.getAllCategory();
        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(categories, response.getBody());
    }

}