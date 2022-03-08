package ohho.backend.spring.domain.organization.entities;

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
public class Organization extends BaseEntity {

    /* 연관관계 */

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private List<Member> members = new ArrayList<>();
}
