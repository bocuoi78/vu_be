package com.example.demo.model.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
    String id;
    String studentId;
    String studentName;
    String studentClazz;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Ho_Chi_Minh")
    Date birthday;
    Boolean studentGender;
    String studentPhone;
    String studentEmail;
    Boolean uniformGender;
    String uniformSize;
    Boolean paid;
    Boolean transfer;
    Boolean received;
}
