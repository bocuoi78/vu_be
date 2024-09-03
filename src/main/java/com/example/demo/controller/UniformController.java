package com.example.demo.controller;

import com.example.demo.model.dto.ApiResponseDto;
import com.example.demo.model.dto.StudentDto;
import com.example.demo.model.dto.UniformDto;
import com.example.demo.service.StudentService;
import com.example.demo.service.UniformService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/uniform")
public class UniformController {
    @Autowired
    private UniformService uniformService;

    @GetMapping("/get-all")
    ResponseEntity<ApiResponseDto> getAllUniform (
            HttpServletRequest request
    ) {
        return new ResponseEntity<>(uniformService.getAllUniform(request), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<ApiResponseDto> getUniform (
            HttpServletRequest request,
            @PathVariable(name = "id") String uniformId
    ) {
        return new ResponseEntity<>(uniformService.getUniform(request, uniformId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto> addUniform(
            HttpServletRequest request,
            @RequestBody UniformDto uniformDto
    ) {
        return new ResponseEntity<>(uniformService.addUniform(request, uniformDto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ApiResponseDto> updateUniform(
            @RequestBody UniformDto uniformDto,
            HttpServletRequest request
    ) {
        return new ResponseEntity<>(uniformService.updateUniform(request, uniformDto), HttpStatus.OK);
    }

    @DeleteMapping("/{uniformId}")
    public ResponseEntity<ApiResponseDto> remove(
            @PathVariable(name = "uniformId") String uniformId,
            HttpServletRequest request
    ) {
        return new ResponseEntity<>(uniformService.deleteUniform(request, uniformId), HttpStatus.OK);
    }
}
