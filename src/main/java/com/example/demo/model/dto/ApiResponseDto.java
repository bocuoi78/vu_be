package com.example.demo.model.dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor
public class ApiResponseDto {
    private String code;
    private String message;
    private Object data;
    private String status;
}
