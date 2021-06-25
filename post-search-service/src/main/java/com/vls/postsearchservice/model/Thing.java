package com.vls.postsearchservice.model;

import org.springframework.data.annotation.*;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.UUID;

public class Thing {
        @Id
    private UUID id;

    @Field(type = FieldType.Text)
    private String thing_name;

    @Field(type = FieldType.Text)
    private String origin;

    private int price;
    private int quantity;

    @Field(type = FieldType.Text)
    private String used_time;

}
