package ohho.backend.spring.domain.notification.entities;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ohho.backend.spring.common.entities.BaseEntity;

@Entity
@Getter
@NoArgsConstructor
public class Notification extends BaseEntity {

    private Long senderId;

    private Long receiverId;

    public Notification(Long senderId, Long receiverId) {
        this.senderId = senderId;
        this.receiverId = receiverId;
    }
}
