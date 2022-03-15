package ohho.backend.spring.domain.notification.service;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface NotificationService {

    SseEmitter subscribe(Long memberId, String lastEventId);

    void send(Long senderId, Long receiverId);
}
