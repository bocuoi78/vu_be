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
public class UniformDto implements Serializable {
    private String id;
    private Boolean gender;
    private Long sizeCode;
    private String sizeName;
    private Long quantityInStock;
}
