package kr.ohho.domain.member.repository;

import java.util.Optional;
import kr.ohho.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    Optional<Member> findByEmailAndPassword(String email, String password);

    Optional<Member> findByNickname(String nickname);

    Optional<Member> findByGoogleUserId(String googleUserId);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);
}
