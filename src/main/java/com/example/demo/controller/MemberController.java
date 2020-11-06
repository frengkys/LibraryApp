package com.example.demo.controller;

import com.example.demo.dto.member.CreateOrUpdateMemberDTO;
import com.example.demo.dto.member.MemberDTO;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberController {
    @Autowired
    private MemberService memberService;

    // bikin member baru
    @PostMapping("/member")
    public void createNewMember(@RequestBody CreateOrUpdateMemberDTO createOrUpdateMemberDTO) {
        memberService.createNewMember(createOrUpdateMemberDTO);
    }

    // update one member
    @PutMapping("/member/{id}")
    public void updateMemberById(@PathVariable final long id, @RequestBody CreateOrUpdateMemberDTO createOrUpdateMemberDTO) {
        memberService.updateMemberById(id, createOrUpdateMemberDTO);
    }

    // get member by given id
    @GetMapping("/member/{id}")
    public MemberDTO getMemberById(@PathVariable final Long id) {
        return memberService.findById(id);
    }

    // get all members
    @GetMapping("/members")
    public List<MemberDTO> getAllMembersList() {
        return memberService.getAllMembersList();
    }
}
