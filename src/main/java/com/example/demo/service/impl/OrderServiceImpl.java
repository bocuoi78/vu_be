package com.example.demo.service.impl;

import com.example.demo.mapper.OrderMapper;
import com.example.demo.model.dto.ApiResponseDto;
import com.example.demo.model.dto.OrderDto;
import com.example.demo.model.dto.request.OrderRequest;
import com.example.demo.model.dto.response.OrderPagingResponse;
import com.example.demo.model.dto.response.OrderResponse;
import com.example.demo.model.entity.Order;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.UniformRepository;
import com.example.demo.service.OrderService;
import com.example.demo.util.CommonConstants;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private StudentRepository studentRepository;
    private UniformRepository uniformRepository;

    @Override
    public ApiResponseDto getAllOrders(HttpServletRequest request) {
        ApiResponseDto apiResponseDto;
        List<OrderDto> orderDtoList = orderRepository.findAll().stream()
                .map(order -> OrderMapper.getInstance().toDto(order))
                .toList();
        if(!orderDtoList.isEmpty()) {
            apiResponseDto = ApiResponseDto.builder()
                    .code(String.format(HttpStatus.OK.toString()))
                    .message("Request successfully")
                    .data(orderDtoList)
                    .status(CommonConstants.ApiStatus.STATUS_OK)
                    .build();
        } else {
            apiResponseDto = ApiResponseDto.builder()
                    .code(String.format(HttpStatus.NOT_FOUND.toString()))
                    .message("Empty")
                    .data(null)
                    .status(CommonConstants.ApiStatus.STATUS_ERROR)
                    .build();
        }
        return apiResponseDto;
    }

    @Override
    public ApiResponseDto getListOfOrders(OrderRequest orderRequest) {
        Pageable pageable = PageRequest.of(
                orderRequest.getCurrentPage().intValue(),
                orderRequest.getMaxSize().intValue(),
                Sort.by("createdOn").descending()
        );
        Page<Order> orders = orderRepository.findOrderByCriteria(
                orderRequest.getKeyword(),
                orderRequest.getUniformGender(),
                orderRequest.getUniformSize(),
                orderRequest.getFromDate(),
                orderRequest.getToDate(),
                pageable
        );
        OrderPagingResponse orderPagingResponse = OrderPagingResponse.builder()
                .dataPaging(
                        orders.stream().map(
                                order -> OrderResponse.builder()
                                        .id(order.getId())
                                        .studentId(order.getStudent().getId())
                                        .studentName(order.getStudent().getFullname())
                                        .studentClazz(order.getStudent().getClazz())
                                        .birthday(order.getStudent().getBirthday())
                                        .studentGender(order.getStudent().getGender())
                                        .studentPhone(order.getStudent().getPhone())
                                        .studentEmail(order.getStudent().getEmail())
                                        .uniformGender(order.getUniform().getGender())
                                        .uniformSize(order.getUniform().getSizeName())
                                        .paid(order.getPaid())
                                        .transfer(order.getTransfer())
                                        .received(order.getReceived())
                                        .build()
                        ).toList()
                )
                .total(orders.getTotalElements())
                        .build();
        return ApiResponseDto.builder()
                .code(String.valueOf(HttpStatus.OK))
                .message("Successfully")
                .data(orderPagingResponse)
                .status(CommonConstants.ApiStatus.STATUS_OK)
                .build();
    }

    @Override
    public ApiResponseDto getOrder(HttpServletRequest request, String orderId) {
        ApiResponseDto apiResponseDto;
        if(orderRepository.findById(orderId).isPresent()) {
            Order order = orderRepository.findById(orderId).get();
            apiResponseDto = ApiResponseDto.builder()
                    .code(String.format(HttpStatus.OK.toString()))
                    .message("Request successfully")
                    .data(OrderMapper.getInstance().toDto(order))
                    .status(CommonConstants.ApiStatus.STATUS_OK)
                    .build();
        } else {
            apiResponseDto = ApiResponseDto.builder()
                    .code(String.format(HttpStatus.NOT_FOUND.toString()))
                    .message("Empty")
                    .data(null)
                    .status(CommonConstants.ApiStatus.STATUS_ERROR)
                    .build();
        }
        return apiResponseDto;
    }

    @Override
    public ApiResponseDto addOrder(HttpServletRequest request, OrderDto orderDto) {
//        ApiResponseDto apiResponseDto;
//        try {
//            Order order = new Order();
//            order.setStudent(studentRepository.findById(orderDto.getStudentId())
//                    .orElseThrow(()-> new EntityNotFoundException(String.format(
//                            "Student with id [%s] was not found!",
//                            orderDto.getStudentId()
//                    )))
//            );
//            order.setUniform(uniformRepository.findById(orderDto.getUniformId())
//                    .orElseThrow(()-> new EntityNotFoundException(String.format(
//                            "Uniform with id [%s] was not found!",
//                            orderDto.getUniformId()
//                    )))
//            );
//            order.setPaid(orderDto.getPaid());
//            order.setReceived(orderDto.getReceived());
//            orderRepository.save(order);
//            apiResponseDto = ApiResponseDto.builder()
//                    .code(String.format(HttpStatus.OK.toString()))
//                    .message("Created successfully")
//                    .data(OrderMapper.getInstance().toDto(order))
//                    .status(CommonConstants.ApiStatus.STATUS_OK)
//                    .build();
//        } catch (Exception e) {
//            apiResponseDto = ApiResponseDto.builder()
//                    .code(String.format(HttpStatus.BAD_REQUEST.toString()))
//                    .message(e.getLocalizedMessage())
//                    .data(null)
//                    .status(CommonConstants.ApiStatus.STATUS_ERROR)
//                    .build();
//        }
//        return apiResponseDto;
        return null;
    }

    @Override
    public ApiResponseDto updateOrder(HttpServletRequest request, OrderDto orderDto) {
        try {
            if (Objects.equals(orderDto.getId(), "")) {
                Order order = new Order();
                order.setStudent(studentRepository.findById(orderDto.getStudentId()).get());
                order.setUniform(uniformRepository.findByGenderAndAndSizeName(
                        orderDto.getUniformGender(),
                        orderDto.getUniformSizeName()
                ).get());
                order.setPaid(orderDto.getPaid());
                order.setReceived(orderDto.getReceived());
                orderRepository.save(order);
                return ApiResponseDto.builder()
                        .code(String.format(HttpStatus.OK.toString()))
                        .message("Create successfully")
                        .data(OrderMapper.getInstance().toDto(order))
                        .status(CommonConstants.ApiStatus.STATUS_OK)
                        .build();
            } else if (orderRepository.findById(orderDto.getId()).isPresent()) {
                Order order = orderRepository.findById(orderDto.getId()).get();
                order.setStudent(studentRepository.findById(orderDto.getStudentId()).get());
                order.setUniform(uniformRepository.findByGenderAndAndSizeName(
                        orderDto.getUniformGender(),
                        orderDto.getUniformSizeName()
                ).get());
                order.setPaid(orderDto.getPaid());
                order.setReceived(orderDto.getReceived());
                orderRepository.save(order);
                return ApiResponseDto.builder()
                        .code(String.format(HttpStatus.OK.toString()))
                        .message("Updated successfully")
                        .data(OrderMapper.getInstance().toDto(order))
                        .status(CommonConstants.ApiStatus.STATUS_OK)
                        .build();
            } else {
                return ApiResponseDto.builder()
                        .code(String.format(HttpStatus.BAD_REQUEST.toString()))
                        .message("Order not found")
                        .data(null)
                        .status(CommonConstants.ApiStatus.STATUS_ERROR)
                        .build();
            }
        } catch (Exception e) {
            return ApiResponseDto.builder()
                    .code(String.format(HttpStatus.BAD_REQUEST.toString()))
                    .message(e.getLocalizedMessage())
                    .data(null)
                    .status(CommonConstants.ApiStatus.STATUS_ERROR)
                    .build();
        }
    }

    @Override
    public ApiResponseDto deleteOrder(HttpServletRequest request, String orderId) {
        ApiResponseDto apiResponseDto;
        if (orderRepository.findById(orderId).isPresent()) {
            Order order = orderRepository.findById(orderId).get();
            order.setIsDeleted(true);
            orderRepository.save(order);
            apiResponseDto = ApiResponseDto.builder()
                    .code(String.format(HttpStatus.OK.toString()))
                    .message("Deleted successfully")
                    .data(null)
                    .status(CommonConstants.ApiStatus.STATUS_OK)
                    .build();
        } else {
            apiResponseDto = ApiResponseDto.builder()
                    .code(String.format(HttpStatus.NOT_FOUND.toString()))
                    .message("Empty")
                    .data(null)
                    .status(CommonConstants.ApiStatus.STATUS_ERROR)
                    .build();
        }
        return apiResponseDto;
    }
}
