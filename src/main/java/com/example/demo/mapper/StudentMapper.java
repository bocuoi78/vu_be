package com.example.demo.mapper;

import com.example.demo.model.dto.StudentDto;
import com.example.demo.model.entity.Student;

public class StudentMapper {
    private static StudentMapper INSTANCE;
    public static StudentMapper getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new StudentMapper();
        }
        return INSTANCE;
    }

    public StudentDto toDto (Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setFullname(student.getFullname());
        studentDto.setClazz(student.getClazz());
        studentDto.setBirthday(student.getBirthday());
        studentDto.setGender(student.getGender());
        studentDto.setPhone(student.getPhone());
        studentDto.setEmail(student.getEmail());
        return studentDto;
    }

    public Student toEntity (StudentDto studentDto) {
        Student student = new Student();
        student.setId(studentDto.getId());
        student.setFullname(studentDto.getFullname());
        student.setClazz(studentDto.getClazz());
        student.setBirthday(studentDto.getBirthday());
        student.setGender(studentDto.getGender());
        student.setPhone(studentDto.getPhone());
        student.setEmail(studentDto.getEmail());
        return student;
    }
}
