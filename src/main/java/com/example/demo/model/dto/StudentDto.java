package com.example.demo.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto implements Serializable {
    private String id;
    private String fullname;
    private String clazz;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Ho_Chi_Minh")
    private Date birthday;
    private Boolean gender;
    private String phone;
    private String email;
}
