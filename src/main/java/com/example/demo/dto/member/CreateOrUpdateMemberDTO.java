package com.example.demo.dto.member;

import lombok.Data;

@Data
public class CreateOrUpdateMemberDTO {
    private String name;
    private String address;
    private Integer age;
}
