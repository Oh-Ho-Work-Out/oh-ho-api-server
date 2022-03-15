package ohho.backend.spring.domain.notification.repository;

import java.util.List;
import ohho.backend.spring.domain.notification.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findAllByReceiverId(Long id);
}
