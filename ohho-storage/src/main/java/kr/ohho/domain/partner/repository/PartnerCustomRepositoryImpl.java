//package kr.ohho.domain.partner.repository;
//
//import static ohho.backend.spring.domain.partner.entities.QPartner.partner;
//
//import com.querydsl.core.dml.UpdateClause;
//import com.querydsl.core.util.StringUtils;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import com.querydsl.jpa.impl.JPAUpdateClause;
//import java.time.LocalDate;
//import javax.persistence.EntityManager;
//import org.springframework.stereotype.Repository;
//import org.springframework.util.ObjectUtils;
//
//@Repository
//public class PartnerCustomRepositoryImpl implements PartnerCustomRepository {
//
//    private final JPAQueryFactory queryFactory;
//
//    public PartnerCustomRepositoryImpl(EntityManager em) {
//        this.queryFactory = new JPAQueryFactory(em);
//    }
//
//    @Override
//    public Long updateDdayAndGoal(Long id, LocalDate dDay, String goal) {
//        UpdateClause<JPAUpdateClause> updateClauseUpdateClause = queryFactory.update(partner);
//
//        if (!ObjectUtils.isEmpty(dDay)) {
//            updateClauseUpdateClause.set(partner.dDay, dDay);
//        }
//
//        if (!StringUtils.isNullOrEmpty(goal)) {
//            updateClauseUpdateClause.set(partner.goal, goal);
//        }
//
//        return updateClauseUpdateClause
//            .where(partner.id.eq(id))
//            .execute();
//    }
//}
