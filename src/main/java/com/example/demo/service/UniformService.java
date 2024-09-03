package com.example.demo.service;

import com.example.demo.model.dto.ApiResponseDto;
import com.example.demo.model.dto.StudentDto;
import com.example.demo.model.dto.UniformDto;
import jakarta.servlet.http.HttpServletRequest;

public interface UniformService {
    ApiResponseDto getAllUniform(HttpServletRequest request);
    ApiResponseDto getUniform(HttpServletRequest request, String uniformId);
    ApiResponseDto addUniform(HttpServletRequest request, UniformDto uniformDto);
    ApiResponseDto updateUniform(HttpServletRequest request, UniformDto uniformDto);
    ApiResponseDto deleteUniform(HttpServletRequest request, String uniformId);
}
