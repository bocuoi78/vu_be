package com.example.demo.service;

import com.example.demo.model.dto.ApiResponseDto;
import com.example.demo.model.dto.StudentDto;
import jakarta.servlet.http.HttpServletRequest;

public interface StudentService {
    ApiResponseDto getAllStudents(HttpServletRequest request);
    ApiResponseDto getListOfStudents(HttpServletRequest request);
    ApiResponseDto getStudent(HttpServletRequest request, String studentId);
    ApiResponseDto addStudent(HttpServletRequest request, StudentDto studentDto);
    ApiResponseDto updateStudent(HttpServletRequest request, StudentDto studentDto);
    ApiResponseDto deleteStudent(HttpServletRequest request, String studentId);
}
