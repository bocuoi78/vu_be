package com.example.demo.mapper;

import com.example.demo.model.dto.OrderDto;
import com.example.demo.model.entity.Order;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.UniformRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderMapper {
    StudentRepository studentRepository;
    UniformRepository uniformRepository;
    private static OrderMapper INSTANCE;
    public static OrderMapper getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new OrderMapper();
        }
        return INSTANCE;
    }

    public OrderDto toDto (Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setStudentId(order.getStudent().getId());
        orderDto.setUniformGender(order.getUniform().getGender());
        orderDto.setUniformSizeName(order.getUniform().getSizeName());
        orderDto.setPaid(order.getPaid());
        orderDto.setTransfer(order.getTransfer());
        orderDto.setReceived(order.getReceived());
        return orderDto;
    }

    public Order toEntity (OrderDto orderDto) {
//        System.out.println(orderDto.getStudentId());
//        Order order = new Order();
//        order.setId(orderDto.getId());
//        order.setStudent(studentRepository.findById(orderDto.getStudentId())
//                .orElseThrow(()-> new EntityNotFoundException(String.format(
//                        "Student with id [%s] was not found!",
//                        orderDto.getStudentId()
//                )))
//        );
//        order.setUniform(uniformRepository.findById(orderDto.getUniformId())
//                .orElseThrow(()-> new EntityNotFoundException(String.format(
//                        "Uniform with id [%s] was not found!",
//                        orderDto.getUniformId()
//                )))
//        );
//        order.setPaid(orderDto.getPaid());
//        order.setReceived(orderDto.getReceived());
//        return order;
        return null;
    }
}
