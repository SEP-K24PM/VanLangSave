package com.vls.manager_statisticservice.service;

import com.vls.manager_statisticservice.model.Post;
import com.vls.manager_statisticservice.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class PostService  {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findByStatusAndMonth() {
        Date date = new Date();
        Calendar currentMonth = Calendar.getInstance();
        currentMonth.setTime(date);
        int month = currentMonth.get(Calendar.MONTH);

        List<Post> list = new ArrayList<>();
        postRepository.findByStatusAndMonth("Hoàn tất", month).forEach(list::add);
        return list;
    }
}
