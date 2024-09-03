package com.example.demo.mapper;

import com.example.demo.model.dto.UniformDto;
import com.example.demo.model.entity.Uniform;

public class UniformMapper {
    private static UniformMapper INSTANCE;
    public static UniformMapper getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new UniformMapper();
        }
        return INSTANCE;
    }

    public UniformDto toDto (Uniform uniform) {
        UniformDto uniformDto = new UniformDto();
        uniformDto.setId(uniform.getId());
        uniformDto.setGender(uniform.getGender());
        uniformDto.setSizeCode(uniform.getSizeCode());
        uniformDto.setSizeName(uniform.getSizeName());
        uniformDto.setQuantityInStock(uniform.getQuantityInStock());
        return uniformDto;
    }

    public Uniform toEntity (UniformDto uniformDto) {
        Uniform uniform = new Uniform();
        uniform.setId(uniformDto.getId());
        uniform.setGender(uniformDto.getGender());
        uniform.setSizeCode(uniformDto.getSizeCode());
        uniform.setSizeName(uniformDto.getSizeName());
        uniform.setQuantityInStock(uniformDto.getQuantityInStock());
        return uniform;
    }
}
