package com.lyuban.m4banktask.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDTO {
    private int code;
    private String description;
}
