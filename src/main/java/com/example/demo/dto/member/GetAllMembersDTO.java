package com.example.demo.dto.member;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
//@Builder
public class GetAllMembersDTO {
    private String name;
    private String address;
    private Integer age;
}
