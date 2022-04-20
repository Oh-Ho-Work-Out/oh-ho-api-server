package kr.ohho.domain.organization;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import kr.ohho.common.entities.BaseEntity;
import kr.ohho.domain.member.Member;
import lombok.Getter;

@Entity
@Getter
public class Organization extends BaseEntity {

    /* 연관관계 */

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private List<Member> members = new ArrayList<>();
}
