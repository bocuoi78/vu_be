package com.example.demo.model.dto.response;

import com.example.demo.model.dto.OrderDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderPagingResponse {
    List<OrderResponse> dataPaging;
    Long total;
}
