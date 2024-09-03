package com.example.demo.controller;

import com.example.demo.model.dto.AccountDto;
import com.example.demo.model.dto.ApiResponseDto;
import com.example.demo.model.dto.OrderDto;
import com.example.demo.model.dto.StudentDto;
import com.example.demo.model.dto.request.OrderRequest;
import com.example.demo.service.AccountService;
import com.example.demo.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/get-all")
    ResponseEntity<ApiResponseDto> getAllOrders (
            HttpServletRequest request
    ) {
        return new ResponseEntity<>(orderService.getAllOrders(request), HttpStatus.OK);
    }

    @PostMapping("/get-list")
    ResponseEntity<ApiResponseDto> getAllOrders (
            @RequestBody OrderRequest orderRequest
    ) {
        return new ResponseEntity<>(orderService.getListOfOrders(orderRequest), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<ApiResponseDto> getOrder (
            HttpServletRequest request,
            @PathVariable(name = "id") String orderId
    ) {
        return new ResponseEntity<>(orderService.getOrder(request, orderId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto> updateOrder(
            HttpServletRequest request,
            @RequestBody OrderDto orderDto
    ) {
        return new ResponseEntity<>(orderService.updateOrder(request, orderDto), HttpStatus.CREATED);
    }

//    @GetMapping("orderId")
//    public ResponseEntity<ApiResponseDto> updatePaid (
//            @PathVariable(name = "orderId") String orderId
//    ) {
//        return new ResponseEntity<>(orderUp.getOrder(request, orderId), HttpStatus.OK);
//    }

//    @PutMapping
//    public ResponseEntity<ApiResponseDto> updateOrder(
//            @RequestBody OrderDto orderDto,
//            HttpServletRequest request
//    ) {
//        return new ResponseEntity<>(orderService.updateOrder(request, orderDto), HttpStatus.OK);
//    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<ApiResponseDto> remove(
            @PathVariable(name = "orderId") String orderId,
            HttpServletRequest request
    ) {
        return new ResponseEntity<>(orderService.deleteOrder(request, orderId), HttpStatus.OK);
    }
}
