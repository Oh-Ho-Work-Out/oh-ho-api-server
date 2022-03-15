package ohho.backend.spring.domain.partner.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import lombok.Getter;
import ohho.backend.spring.common.entities.BaseEntity;
import ohho.backend.spring.domain.member.entities.Member;

@Entity
@Getter
public class Partner extends BaseEntity {

    private LocalDate dDay;

    private String goal;

    /* 연관관계 */

    @OneToMany(mappedBy = "partner", cascade = CascadeType.ALL)
    private List<Member> members = new ArrayList<>();

    public void addMember(Member member) {
        this.members.add(member);
    }
}
