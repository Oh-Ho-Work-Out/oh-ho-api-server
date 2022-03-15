package ohho.backend.spring.domain.partner.model.request;

import java.time.LocalDate;
import lombok.Data;

@Data
public class UpdateDdayAndGoalRequestDto {

    private LocalDate dDay;
    private String goal;
}
