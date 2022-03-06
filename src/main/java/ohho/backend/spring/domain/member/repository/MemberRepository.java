package ohho.backend.spring.domain.member.repository;

import java.util.Optional;
import ohho.backend.spring.domain.member.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);
}
