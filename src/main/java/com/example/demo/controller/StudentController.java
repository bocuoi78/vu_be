package com.example.demo.controller;

import com.example.demo.model.dto.ApiResponseDto;
import com.example.demo.model.dto.StudentDto;
import com.example.demo.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/get-all")
    ResponseEntity<ApiResponseDto> getAllStudent (
            HttpServletRequest request
    ) {
        return new ResponseEntity<>(studentService.getAllStudents(request), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<ApiResponseDto> getStudent (
            HttpServletRequest request,
            @PathVariable(name = "id") String studentId
    ) {
        return new ResponseEntity<>(studentService.getStudent(request, studentId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto> updateStudent(
            HttpServletRequest request,
            @RequestBody StudentDto studentDto
    ) {
        return new ResponseEntity<>(studentService.updateStudent(request, studentDto), HttpStatus.CREATED);
    }

//    @PutMapping
//    public ResponseEntity<ApiResponseDto> updateStudent(
//            @RequestBody StudentDto studentDto,
//            HttpServletRequest request
//    ) {
//        return new ResponseEntity<>(studentService.updateStudent(request, studentDto), HttpStatus.OK);
//    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<ApiResponseDto> remove(
            @PathVariable(name = "studentId") String studentId,
            HttpServletRequest request
    ) {
        return new ResponseEntity<>(studentService.deleteStudent(request, studentId), HttpStatus.OK);
    }
}
