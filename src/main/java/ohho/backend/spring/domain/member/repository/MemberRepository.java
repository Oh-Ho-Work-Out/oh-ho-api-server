package ohho.backend.spring.domain.member.repository;

import java.util.Optional;
import ohho.backend.spring.domain.member.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    Optional<Member> findByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);

    boolean existsByNickName(String nickname);
}
