package com.example.demo.service.impl;

import com.example.demo.model.dto.ApiResponseDto;
import com.example.demo.model.dto.request.AuthenticationRequest;
import com.example.demo.model.dto.request.IntrospectRequest;
import com.example.demo.model.dto.response.AuthenticationResponse;
import com.example.demo.model.dto.response.IntrospectResponse;
import com.example.demo.model.entity.Account;
import com.example.demo.repository.AccountRepository;
import com.example.demo.service.AuthenticationService;
import com.example.demo.util.CommonConstants;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationServiceImpl.class);
    AccountRepository accountRepository;

    @NonFinal
    @Value("${JWT.SIGNER_KEY}")
    protected String SIGNER_KEY;

    @Override
    public ApiResponseDto authenticate(HttpServletRequest request, AuthenticationRequest authenticationRequest) {
        if (accountRepository.findByUsername(authenticationRequest.getUsername()).isPresent()) {
            Account account = accountRepository.findByUsername(authenticationRequest.getUsername()).get();
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
            boolean authenticated = passwordEncoder.matches(authenticationRequest.getPassword(), account.getPassword());
            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
            String token = generateToken(authenticationRequest.getUsername(), account.getFullname());
            authenticationResponse.setToken(token);
            if (authenticated) {
                return ApiResponseDto.builder()
                        .code(String.format(HttpStatus.OK.toString()))
                        .message("Authentication success")
                        .data(authenticationResponse)
                        .status(CommonConstants.ApiStatus.STATUS_AUTHENTICATED)
                        .build();
            }
            return ApiResponseDto.builder()
                    .code(String.format(HttpStatus.OK.toString()))
                    .message("Password is incorrect")
                    .data(null)
                    .status(CommonConstants.ApiStatus.STATUS_INVALID_PASSWORD)
                    .build();
        }
        return ApiResponseDto.builder()
                .code(String.format(HttpStatus.OK.toString()))
                .message("Username is incorrect")
                .data(null)
                .status(CommonConstants.ApiStatus.STATUS_INVALID_USERNAME)
                .build();
    }

    @Override
    public ApiResponseDto introspect(HttpServletRequest request, IntrospectRequest introspectRequest) throws JOSEException, ParseException {
        String token = introspectRequest.getToken();
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);
        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        boolean verified = signedJWT.verify(verifier);
        if (verified && expiryTime.after(new Date())) {
            return ApiResponseDto.builder()
                    .code(String.format(HttpStatus.OK.toString()))
                    .message("Token is valid")
                    .data(new IntrospectResponse(true))
                    .status(CommonConstants.ApiStatus.STATUS_VALID)
                    .build();
        }
        return ApiResponseDto.builder()
                .code(String.format(HttpStatus.OK.toString()))
                .message("Token is invalid")
                .data(new IntrospectResponse(false))
                .status(CommonConstants.ApiStatus.STATUS_INVALID)
                .build();
    }

    private String generateToken(String username, String fullname) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .issuer("ssm.yu.vku.udn.vn")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(24, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("fullname", fullname)
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Could not sign JWT", e);
            throw new RuntimeException(e);
        }
    }
}
