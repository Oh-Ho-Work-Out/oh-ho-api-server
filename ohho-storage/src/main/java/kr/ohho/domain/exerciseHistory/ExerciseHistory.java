package kr.ohho.domain.exerciseHistory;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import kr.ohho.common.entities.BaseEntity;
import kr.ohho.domain.exercise.Exercise;
import kr.ohho.domain.member.Member;
import lombok.Getter;

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
