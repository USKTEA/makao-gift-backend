package com.ahastudio.makaoGift.repositories;

import com.ahastudio.makaoGift.models.Member;
import com.ahastudio.makaoGift.models.MemberName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("unchecked")
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByMemberName(MemberName memberName);

    List<Member> findAllByMemberName(MemberName memberName);
}
