package com.example.demo.service;

import com.example.demo.dto.member.CreateOrUpdateMemberDTO;
import com.example.demo.dto.member.MemberDTO;
import com.example.demo.entity.Member;
import com.example.demo.exception.ResourceNotFound;
import com.example.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public void createNewMember(CreateOrUpdateMemberDTO createOrUpdateMemberDTO) {
        Member member = new Member()
                .setName(createOrUpdateMemberDTO.getName())
                .setAddress(createOrUpdateMemberDTO.getAddress())
                .setAge(createOrUpdateMemberDTO.getAge());

        memberRepository.save(member);
    }

    public void updateMemberById(long id, CreateOrUpdateMemberDTO createOrUpdateMemberDTO) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new ResourceNotFound(String.format("Member not found %d", id)));

        member
                .setName(createOrUpdateMemberDTO.getName())
                .setAddress(createOrUpdateMemberDTO.getAddress())
                .setAge(createOrUpdateMemberDTO.getAge());

        memberRepository.save(member);
    }

    public MemberDTO findById(final Long id) {
        final Member member = memberRepository.findById(id).orElseThrow(() -> new ResourceNotFound(String.format("Member not found %d", id)));

        return convertMemberToMemberDTO(member);
    }

    public List<MemberDTO> getAllMembersList() {
        List<Member> members = memberRepository.findAll();

        return members.stream().map(this::convertMemberToMemberDTO).collect(Collectors.toList());
    }

    private MemberDTO convertMemberToMemberDTO(Member member) {
        return new MemberDTO()
                .setId(member.getId())
                .setCreatedAt(member.getCreatedAt().toString())
                .setUpdatedAt(member.getUpdatedAt().toString())
                .setAddress(member.getAddress())
                .setAge(member.getAge())
                .setName(member.getName());
    }
}
