package kr.ohho.domain.notification.repository;

import java.util.List;
import kr.ohho.domain.notification.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findAllByReceiverId(Long id);
}
