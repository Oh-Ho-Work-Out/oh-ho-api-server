package kr.ohho.domain.exercise;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import kr.ohho.domain.exercise.converter.PartConverter;
import kr.ohho.domain.exerciseHistory.ExerciseHistory;
import lombok.Getter;

@Entity
@Getter
public class Exercise {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Convert(converter = PartConverter.class)
    private Part part;

    /* 연관관계 */

    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL)
    private List<ExerciseHistory> exerciseHistories = new ArrayList<>();
}
