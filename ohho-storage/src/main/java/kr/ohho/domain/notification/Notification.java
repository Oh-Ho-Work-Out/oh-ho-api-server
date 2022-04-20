package kr.ohho.domain.notification;

import javax.persistence.Entity;
import kr.ohho.common.entities.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
