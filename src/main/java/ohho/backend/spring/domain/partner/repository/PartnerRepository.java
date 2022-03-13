package ohho.backend.spring.domain.partner.repository;

import ohho.backend.spring.domain.partner.entities.Partner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerRepository extends JpaRepository<Partner, Long>, PartnerCustomRepository {

}
