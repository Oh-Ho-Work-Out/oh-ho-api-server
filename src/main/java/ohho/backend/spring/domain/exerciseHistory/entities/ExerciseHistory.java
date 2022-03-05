package ohho.backend.spring.domain.exerciseHistory.entities;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import ohho.backend.spring.common.entities.BaseEntity;
import ohho.backend.spring.domain.exercise.entities.Exercise;
import ohho.backend.spring.domain.member.entities.Member;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Getter
public class ExerciseHistory extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    /* 연관관계 */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    private int count;

    /* date 컬럼 */

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
