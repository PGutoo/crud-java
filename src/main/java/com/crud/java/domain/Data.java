package com.crud.java.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Data<T> {

    @JsonProperty("statusCode")
    private Integer status;

    @JsonProperty("data")
    private T data;

    @JsonProperty("message")
    private String message;



}
