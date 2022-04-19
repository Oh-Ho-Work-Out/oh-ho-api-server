package kr.ohho.application.notification.model.response;

import java.time.LocalDateTime;

import kr.ohho.common.utils.LocalDateTimeToArray;
import kr.ohho.domain.notification.Notification;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NotificationResponseDto {

    /**
     * 알림 id
     */
    private Long id;

    /**
     * 알림이 생성된 날짜(몇일 전 계산 위함)
     */
    private Integer[] createdAt;

    @Builder
    public NotificationResponseDto(Long id, LocalDateTime createdAt) {
        this.id = id;
        this.createdAt = LocalDateTimeToArray.convert(createdAt);
    }

    public static NotificationResponseDto from(Notification notification) {
        return builder()
            .id(notification.getId())
            .createdAt(notification.getCreatedAt())
            .build();
    }
}
