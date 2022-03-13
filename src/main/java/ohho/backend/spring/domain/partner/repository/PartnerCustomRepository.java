package ohho.backend.spring.domain.partner.repository;

import java.time.LocalDate;

public interface PartnerCustomRepository {

    Long updateDdayAndGoal(Long id, LocalDate dDay, String goal);
}
