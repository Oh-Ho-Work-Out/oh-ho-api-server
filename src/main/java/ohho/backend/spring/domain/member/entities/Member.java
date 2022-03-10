package ohho.backend.spring.domain.member.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Getter;
import ohho.backend.spring.common.entities.BaseEntity;
import ohho.backend.spring.domain.exercise.enums.converter.ExerciseTypeConverter;
import ohho.backend.spring.domain.exerciseHistory.entities.ExerciseHistory;
import ohho.backend.spring.domain.member.enums.Age;
import ohho.backend.spring.domain.member.enums.Gender;
import ohho.backend.spring.domain.member.enums.converter.AgeConverter;
import ohho.backend.spring.domain.member.enums.converter.GenderConverter;
import ohho.backend.spring.domain.organization.entities.Organization;
import ohho.backend.spring.domain.partner.entities.Partner;

@Entity
@Getter
public class Member extends BaseEntity {

    private String email;

    private String password;

    private String nickName;

    private String interestList;

    @Convert(converter = GenderConverter.class)
    private Gender gender;

    @Convert(converter = AgeConverter.class)
    private Age age;

    /* 연관관계 */

    @ManyToOne(fetch = FetchType.LAZY)
    private Partner partner;

    @ManyToOne(fetch = FetchType.LAZY)
    private Organization organization;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<ExerciseHistory> exerciseHistories = new ArrayList<>();

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
