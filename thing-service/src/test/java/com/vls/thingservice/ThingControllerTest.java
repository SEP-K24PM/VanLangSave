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
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        Assert.assertEquals(listWithCateName, allThingsResult);
    }

    @Test
    public void addThingShouldReturnRightThing() {
        Thing newThing = new Thing("thing name 1", "origin 1", 10000, 1,
                "used time 1", "image1.png",
                UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
        Thing savedThing = new Thing(UUID.randomUUID(), newThing.getThing_name(), newThing.getOrigin(),
                newThing.getPrice(), newThing.getQuantity(), newThing.getUsed_time(), newThing.getImage(),
                newThing.getUserid(), newThing.getCategory_id(), newThing.getPost_id());

        Mockito.when(thingService.addThing(newThing)).thenReturn(savedThing);

        Thing result = thingController.addThing(newThing);
        Assert.assertEquals(result, savedThing);
    }

}
