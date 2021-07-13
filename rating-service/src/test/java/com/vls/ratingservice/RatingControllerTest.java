package com.vls.ratingservice;

import com.vls.ratingservice.controller.RatingController;
import com.vls.ratingservice.model.UserRating;
import com.vls.ratingservice.repository.UserRatingRepository;
import com.vls.ratingservice.service.RatingService;
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
import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class RatingControllerTest extends AbstractTest {
    private RatingController ratingController;
    private RatingService ratingService;

    @Mock
    private UserRatingRepository userRatingRepository;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        ratingService = new RatingService(userRatingRepository);
        ratingController = new RatingController(ratingService);
    }

    @Test
    public void testList() {
        UUID userId = UUID.randomUUID();
        UserRating rating = new UserRating(UUID.randomUUID(), "description", 5, UUID.randomUUID(), userId, UUID.randomUUID());
        UserRating rating1 = new UserRating(UUID.randomUUID(), "description", 5, UUID.randomUUID(), userId, UUID.randomUUID());
        List<UserRating> list = new ArrayList<>();
        list.add(rating);
        list.add(rating1);

        Mockito.when(userRatingRepository.findAllByUser(userId)).thenReturn(list);

        ResponseEntity<List<UserRating>> response = ratingController.listRatings(userId);
        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(list, response.getBody());
    }

    @Test
    public void testCreate() {
        UserRating rating = new UserRating("description",
                5, UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
        UserRating savedRating = new UserRating(UUID.randomUUID(), "saved description",
                5, rating.getRated_user_id(), rating.getRater_id(), rating.getPost_id());

        Mockito.when(userRatingRepository.save(rating)).thenReturn(savedRating);

        ResponseEntity<UserRating> response = ratingController.createRating(rating);
        Assert.assertEquals(201, response.getStatusCodeValue());
        Assert.assertEquals(savedRating, response.getBody());
    }
}
