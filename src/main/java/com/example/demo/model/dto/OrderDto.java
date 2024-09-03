package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto implements Serializable {
    private String id;
    private String studentId;
    private Boolean uniformGender = Boolean.TRUE;
    private String uniformSizeName;
    private Boolean paid = Boolean.FALSE;
    private Boolean transfer = Boolean.FALSE;
    private Boolean received  = Boolean.FALSE;
}
