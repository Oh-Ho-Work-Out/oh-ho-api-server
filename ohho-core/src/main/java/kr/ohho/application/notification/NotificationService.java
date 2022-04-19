package kr.ohho.application.notification;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface NotificationService {

    SseEmitter subscribe(Long memberId, String lastEventId);

    void send(Long senderId, Long receiverId);
}
