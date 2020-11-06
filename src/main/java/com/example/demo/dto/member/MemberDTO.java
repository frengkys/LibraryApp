package com.example.demo.dto.member;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MemberDTO {
    private Long id;
    private String createdAt;
    private String updatedAt;
    private String name;
    private int age;
    private String address;
}
