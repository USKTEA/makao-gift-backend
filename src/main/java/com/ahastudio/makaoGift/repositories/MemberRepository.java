package com.ahastudio.makaoGift.repositories;

import com.ahastudio.makaoGift.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("unchecked")
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByMemberName(String memberName);

    List<Member> findAllByMemberName(String memberName);
}
