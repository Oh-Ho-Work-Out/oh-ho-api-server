package ohho.backend.spring.domain.exerciseHistory.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import lombok.Getter;
import ohho.backend.spring.common.entities.BaseEntity;
import ohho.backend.spring.domain.exercise.entities.Exercise;
import ohho.backend.spring.domain.member.entities.Member;

@Entity
@Getter
public class ExerciseHistory extends BaseEntity {

    private int count;

    /* 연관관계 */

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private Exercise exercise;
}
