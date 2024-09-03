package com.example.demo.service.impl;

import com.example.demo.mapper.StudentMapper;
import com.example.demo.model.dto.ApiResponseDto;
import com.example.demo.model.dto.StudentDto;
import com.example.demo.model.entity.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import com.example.demo.util.CommonConstants;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;

    @Override
    public ApiResponseDto getAllStudents(HttpServletRequest request) {
        ApiResponseDto apiResponseDto;
        List<StudentDto> studentDtoList = studentRepository.findAll().stream()
                .map(student -> StudentMapper.getInstance().toDto(student))
                .toList();
        if(!studentDtoList.isEmpty()) {
            apiResponseDto = ApiResponseDto.builder()
                    .code(String.format(HttpStatus.OK.toString()))
                    .message("Request successfully")
                    .data(studentDtoList)
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
    public ApiResponseDto getListOfStudents(HttpServletRequest request) {
        return null;
    }

    @Override
    public ApiResponseDto getStudent(HttpServletRequest request, String studentId) {
        ApiResponseDto apiResponseDto;
        if(studentRepository.findById(studentId).isPresent()) {
            Student student = studentRepository.findById(studentId).get();
            apiResponseDto = ApiResponseDto.builder()
                    .code(String.format(HttpStatus.OK.toString()))
                    .message("Request successfully")
                    .data(StudentMapper.getInstance().toDto(student))
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
    public ApiResponseDto addStudent(HttpServletRequest request, StudentDto studentDto) {
        ApiResponseDto apiResponseDto;
        try {
            Student student = studentRepository.save(StudentMapper.getInstance().toEntity(studentDto));
            apiResponseDto = ApiResponseDto.builder()
                    .code(String.format(HttpStatus.OK.toString()))
                    .message("Created successfully")
                    .data(StudentMapper.getInstance().toDto(student))
                    .status(CommonConstants.ApiStatus.STATUS_OK)
                    .build();
        } catch (Exception e) {
            apiResponseDto = ApiResponseDto.builder()
                    .code(String.format(HttpStatus.BAD_REQUEST.toString()))
                    .message(e.getLocalizedMessage())
                    .data(null)
                    .status(CommonConstants.ApiStatus.STATUS_ERROR)
                    .build();
        }
        return apiResponseDto;
    }

    @Override
    public ApiResponseDto updateStudent(HttpServletRequest request, StudentDto studentDto) {
        ApiResponseDto apiResponseDto;
        try {
            if (studentRepository.findById(studentDto.getId()).isPresent()) {
                Student student = Student.builder()
                        .id(studentDto.getId())
                        .fullname(studentDto.getFullname())
                        .clazz(studentDto.getClazz())
                        .birthday(studentDto.getBirthday())
                        .gender(studentDto.getGender())
                        .phone(studentDto.getPhone())
                        .email(studentDto.getEmail())
                        .build();
                studentRepository.save(student);
                return ApiResponseDto.builder()
                        .code(String.format(HttpStatus.OK.toString()))
                        .message("Updated successfully")
                        .data(StudentMapper.getInstance().toDto(student))
                        .status(CommonConstants.ApiStatus.STATUS_OK)
                        .build();
            }
            return ApiResponseDto.builder()
                    .code(String.format(HttpStatus.BAD_REQUEST.toString()))
                    .message("Student not found")
                    .data(null)
                    .status(CommonConstants.ApiStatus.STATUS_ERROR)
                    .build();
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
    public ApiResponseDto deleteStudent(HttpServletRequest request, String studentId) {
        ApiResponseDto apiResponseDto;
        if (studentRepository.findById(studentId).isPresent()) {
            Student student = studentRepository.findById(studentId).get();
            student.setIsDeleted(true);
            studentRepository.save(student);
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
