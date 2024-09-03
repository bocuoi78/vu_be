package com.example.demo.service.impl;

import com.example.demo.mapper.UniformMapper;
import com.example.demo.model.dto.ApiResponseDto;
import com.example.demo.model.dto.UniformDto;
import com.example.demo.model.entity.Uniform;
import com.example.demo.repository.UniformRepository;
import com.example.demo.service.UniformService;
import com.example.demo.util.CommonConstants;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UniformServiceImpl implements UniformService {
    private UniformRepository uniformRepository;

    @Override
    public ApiResponseDto getAllUniform(HttpServletRequest request) {
        ApiResponseDto apiResponseDto;
        List<UniformDto> uniformDtoList = uniformRepository.findAll().stream()
                .map(uniform -> UniformMapper.getInstance().toDto(uniform))
                .toList();
        if(!uniformDtoList.isEmpty()) {
            apiResponseDto = ApiResponseDto.builder()
                    .code(String.format(HttpStatus.OK.toString()))
                    .message("Request successfully")
                    .data(uniformDtoList)
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
    public ApiResponseDto getUniform(HttpServletRequest request, String uniformId) {
        ApiResponseDto apiResponseDto;
        if(uniformRepository.findById(uniformId).isPresent()) {
            Uniform uniform = uniformRepository.findById(uniformId).get();
            apiResponseDto = ApiResponseDto.builder()
                    .code(String.format(HttpStatus.OK.toString()))
                    .message("Request successfully")
                    .data(UniformMapper.getInstance().toDto(uniform))
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
    public ApiResponseDto addUniform(HttpServletRequest request, UniformDto uniformDto) {
        ApiResponseDto apiResponseDto;
        try {
            Uniform uniform = uniformRepository.save(UniformMapper.getInstance().toEntity(uniformDto));
            apiResponseDto = ApiResponseDto.builder()
                    .code(String.format(HttpStatus.OK.toString()))
                    .message("Created successfully")
                    .data(UniformMapper.getInstance().toDto(uniform))
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
    public ApiResponseDto updateUniform(HttpServletRequest request, UniformDto uniformDto) {
        ApiResponseDto apiResponseDto;
        try {
            Uniform updateUniform = UniformMapper.getInstance().toEntity(uniformDto);
            Uniform uniform = uniformRepository.save(updateUniform);
            apiResponseDto = ApiResponseDto.builder()
                    .code(String.format(HttpStatus.OK.toString()))
                    .message("Updated successfully")
                    .data(UniformMapper.getInstance().toDto(uniform))
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
    public ApiResponseDto deleteUniform(HttpServletRequest request, String uniformId) {
        ApiResponseDto apiResponseDto;
        if (uniformRepository.findById(uniformId).isPresent()) {
            uniformRepository.deleteById(uniformId);
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
