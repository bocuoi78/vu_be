package com.example.demo.controller;

import com.example.demo.model.dto.ApiResponseDto;
import com.example.demo.model.dto.request.AuthenticationRequest;
import com.example.demo.model.dto.request.IntrospectRequest;
import com.example.demo.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<ApiResponseDto> login(
            HttpServletRequest request,
            @RequestBody AuthenticationRequest authenticationRequest
            ) {
        return new ResponseEntity<>(authenticationService.authenticate(request, authenticationRequest), HttpStatus.OK);
    }

    @PostMapping("/introspect")
    public ResponseEntity<ApiResponseDto> introspect(
            HttpServletRequest request,
            @RequestBody IntrospectRequest introspectRequest
    ) throws ParseException, JOSEException {
        return new ResponseEntity<>(authenticationService.introspect(request, introspectRequest), HttpStatus.OK);
    }
}
