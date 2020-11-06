package com.example.demo.dto.member;

import lombok.Data;

@Data
public class GetMemberByIdDTO {
    private String name;
    private String address;
    private Integer age;
}
