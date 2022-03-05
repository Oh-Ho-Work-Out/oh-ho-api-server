package ohho.backend.spring.domain.exercise.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import ohho.backend.spring.common.entities.BaseEntity;
import ohho.backend.spring.domain.exercise.enums.ExerciseType;
import ohho.backend.spring.domain.exercise.enums.Part;
import ohho.backend.spring.domain.exercise.enums.converter.ExerciseTypeConverter;
import ohho.backend.spring.domain.exercise.enums.converter.PartConverter;
import ohho.backend.spring.domain.exerciseHistory.entities.ExerciseHistory;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Getter
public class Exercise extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Convert(converter = ExerciseTypeConverter.class)
    private ExerciseType exerciseType;

    @Convert(converter = PartConverter.class)
    private Part part;

    /* 연관관계 */

    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL)
    private List<ExerciseHistory> exerciseHistories = new ArrayList<>();

    /* date 컬럼 */

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
