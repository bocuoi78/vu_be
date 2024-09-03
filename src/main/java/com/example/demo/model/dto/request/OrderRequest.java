package com.example.demo.model.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequest {
    String keyword;
    Boolean uniformGender;
    String UniformSize;
    Date fromDate;
    Date toDate;
    Long currentPage;
    Long maxSize;
}
