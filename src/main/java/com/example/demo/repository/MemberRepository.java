package com.example.demo.repository;

import com.example.demo.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByName(String name); // select * from member where name = ?

    @Query("select m.name, m.address, m.age from Member m where m.member_id = :memberId")
    List<Member> findByMemberId(@Param("member_id") long memberId);
}
