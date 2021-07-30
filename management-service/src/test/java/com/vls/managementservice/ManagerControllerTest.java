package com.vls.managementservice;

import com.vls.managementservice.controller.ManagerController;
import com.vls.managementservice.model.Category;
import com.vls.managementservice.model.PostWT;
import com.vls.managementservice.model.Thing;
import com.vls.managementservice.repository.CategoryRepository;
import com.vls.managementservice.repository.PostRepository;
import com.vls.managementservice.repository.PostWTRepository;
import com.vls.managementservice.service.CategoryService;
import com.vls.managementservice.service.PostService;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class ManagerControllerTest extends AbstractTest {
    private ManagerController managerController;
    private PostService postService;
    private CategoryService categoryService;

    @Mock
    private PostRepository postRepository;
    @Mock
    private PostWTRepository postWTRepository;
    @Mock
    private CategoryRepository categoryRepository;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        postService = new PostService(postRepository, postWTRepository);
        categoryService = new CategoryService(categoryRepository);
        managerController = new ManagerController(postService, categoryService);
    }

    private PostWT getPostWT() {
        return new PostWT(UUID.randomUUID(), "description", new Date(),
        true, false, "Hoàn tất", "Free", "contact", UUID.randomUUID(), UUID.randomUUID());
    }

    private Category getCategory() {
        return new Category(UUID.randomUUID(), "name");
    }

    @Test
    public void getSucessPostTest() {
        PostWT postWT = getPostWT();
        List<PostWT> list = new ArrayList<>();
        list.add(postWT);
        list.add(postWT);

        Mockito.when(postWTRepository.findByStatus("Hoàn tất")).thenReturn(list);

        ResponseEntity<List<PostWT>> response = managerController.getSucessPost();
        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(list, response.getBody());
    }

    @Test
    public void getAllCategoryTest() {
        Category category = getCategory();
        category.setThings(new ArrayList<Thing>());
        List<Category> list = new ArrayList<>();
        list.add(category);
        list.add(category);

        Mockito.when(categoryRepository.countAllCategory()).thenReturn(list);

        ResponseEntity<List<Category>> response = managerController.getAllCategory();
        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(list.size(), response.getBody().size());
    }
}
