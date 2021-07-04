package com.vls.thingservice;

import com.vls.thingservice.controller.ThingController;
import com.vls.thingservice.model.Thing;
import com.vls.thingservice.service.CategoryService;
import com.vls.thingservice.service.ThingService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class ThingControllerTest extends AbstractTest {

    @InjectMocks
    private ThingController thingController;

    @Mock
    private ThingService thingService;

    @Mock
    private CategoryService categoryService;

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void getListThingShouldReturnOkStatus() throws Exception {
        String userId = "c77bd9d9-9177-4bca-bf15-62a071e866e9";
        String uri = "/list/{userId}";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri, userId)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
    }

    @Test
    public void getListThingShouldReturnRightList() {
        UUID userId = UUID.randomUUID();

        List<Thing> results = new ArrayList<>();
        results.add(new Thing(UUID.randomUUID(),"thing name 1", "origin 1", 10000, 1,
                "used time 1", "image1.png",
                userId, UUID.randomUUID(), UUID.randomUUID()));
        results.add(new Thing(UUID.randomUUID(),"thing name 2", "origin 2", 10000, 1,
                "used time 2", "image2.png",
                userId, UUID.randomUUID(), UUID.randomUUID()));

        List<Thing> listWithCateName = new ArrayList<>();
        listWithCateName.addAll(results);
        for (Thing t: listWithCateName) {
            t.setCategory_name("catename");
        }

        Mockito.when(thingService.getListThings(userId)).thenReturn(results);
        Mockito.when(categoryService.addCategoryNameToThing(results)).thenReturn(listWithCateName);

        List<Thing> allThingsResult = thingController.getAllThings(userId.toString());
        Assert.assertEquals(listWithCateName.size(), allThingsResult.size());
    }
}
