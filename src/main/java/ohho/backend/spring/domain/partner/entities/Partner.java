package ohho.backend.spring.domain.partner.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ohho.backend.spring.common.entities.BaseEntity;
import ohho.backend.spring.domain.member.entities.Member;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Partner extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDate dDay;

    private String goal;

    /* 연관관계 */

    @OneToMany(mappedBy = "partner", cascade = CascadeType.ALL)
    private List<Member> members = new ArrayList<>();

    /* date 컬럼 */

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
