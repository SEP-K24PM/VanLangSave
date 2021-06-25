package com.vls.postsearchservice.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.util.Date;
import java.util.UUID;

@Document(indexName = "post", createIndex = false)
public class Post {
    @Id
    private UUID id;

    @Field(type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Text)
    private String exchange_methods;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
//    @Field(type = FieldType.Date)
    private Date created_time;

    @Field(type = FieldType.Nested, includeInParent = true)
    private Thing thing;
}
