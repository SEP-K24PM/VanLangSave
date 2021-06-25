package com.vls.searchservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.UUID;

public class Post {
    private UUID id;
    private String description;
    private String exchange_methods;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date created_time;
    private Thing thing;
}
