package kr.ohho.domain.partner.repository;

import java.time.LocalDate;

public interface PartnerCustomRepository {

    Long updateDdayAndGoal(Long id, LocalDate dDay, String goal);
}
