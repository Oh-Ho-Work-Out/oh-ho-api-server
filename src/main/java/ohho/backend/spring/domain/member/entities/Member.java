package ohho.backend.spring.domain.member.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import ohho.backend.spring.common.entities.BaseEntity;
import ohho.backend.spring.domain.exerciseHistory.entities.ExerciseHistory;
import ohho.backend.spring.domain.group.entities.Group;
import ohho.backend.spring.domain.partner.entities.Partner;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@ToString(of = {"id"})
@EqualsAndHashCode(of = "id")
@EntityListeners(AuditingEntityListener.class)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String email;

    private String password;

    private String nickName;

    private String interestList;

    /* 연관관계 */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "partner_id")
    private Partner partner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<ExerciseHistory> exerciseHistories = new ArrayList<>();

    /* date 컬럼 */

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    protected Member() {
    }

    public static Member of(String email, String password, String nickName) {
        Member member = new Member();
        member.email = email;
        member.password = password;
        member.nickName = nickName;
        return member;
    }
}
