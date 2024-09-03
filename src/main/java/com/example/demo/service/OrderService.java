package com.example.demo.service;

import com.example.demo.model.dto.ApiResponseDto;
import com.example.demo.model.dto.OrderDto;
import com.example.demo.model.dto.request.OrderRequest;
import jakarta.servlet.http.HttpServletRequest;

public interface OrderService {
    ApiResponseDto getAllOrders(HttpServletRequest request);
    ApiResponseDto getListOfOrders(OrderRequest orderRequest);
    ApiResponseDto getOrder(HttpServletRequest request, String orderId);
    ApiResponseDto addOrder(HttpServletRequest request, OrderDto orderDto);
    ApiResponseDto updateOrder(HttpServletRequest request, OrderDto orderDto);
    ApiResponseDto deleteOrder(HttpServletRequest request, String orderId);
}
