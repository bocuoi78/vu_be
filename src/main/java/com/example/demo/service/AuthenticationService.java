package com.example.demo.service;

import com.example.demo.model.dto.ApiResponseDto;
import com.example.demo.model.dto.request.AuthenticationRequest;
import com.example.demo.model.dto.request.IntrospectRequest;
import com.nimbusds.jose.JOSEException;
import jakarta.servlet.http.HttpServletRequest;

import java.text.ParseException;

public interface AuthenticationService {
    ApiResponseDto authenticate(HttpServletRequest request, AuthenticationRequest authenticationRequest);
    ApiResponseDto introspect(HttpServletRequest request, IntrospectRequest introspectRequest) throws JOSEException, ParseException;
}
