package kr.ohho.application.notification;

import java.io.IOException;
import java.util.Map;
import kr.ohho.application.notification.model.response.NotificationResponseDto;
import kr.ohho.domain.notification.Notification;
import kr.ohho.domain.notification.repository.EmitterRepository;
import kr.ohho.domain.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);
    private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60;

    private final EmitterRepository emitterRepository;
    private final NotificationRepository notificationRepository;

    @Override
    @Transactional
    public SseEmitter subscribe(Long memberId, String lastEventId) {
        String id = memberId + "_" + System.currentTimeMillis();
        SseEmitter emitter = emitterRepository.save(id, new SseEmitter(DEFAULT_TIMEOUT));

        emitter.onCompletion(() -> emitterRepository.deleteById(id));
        emitter.onTimeout(() -> emitterRepository.deleteById(id));

        // 503 에러를 방지하기 위한 더미 이벤트 전송
        sendToClient(emitter, id, "EventStream Created. [memberId=" + memberId + "]");

        // 클라이언트가 미수신한 Event 목록이 존재할 경우 전송하여 Event 유실을 예방
        if (!lastEventId.isEmpty()) {
            Map<String, Object> events = emitterRepository.findAllEventCacheStartWithId(
                String.valueOf(memberId));
            events.entrySet().stream()
                .filter(entry -> lastEventId.compareTo(entry.getKey()) < 0)
                .forEach(entry -> sendToClient(emitter, entry.getKey(), entry.getValue()));
        }

        return emitter;
    }

    private void sendToClient(SseEmitter emitter, String id, Object data) {
        try {
            emitter.send(SseEmitter.event()
                .id(id)
                .name("sse")
                .data(data));
        } catch (IOException exception) {
            emitterRepository.deleteById(id);
            log.error("SSE 연결 오류!", exception);
        }
    }

    @Override
    @Transactional
    public void send(Long senderId, Long receiverId) {
        Notification notification = new Notification(senderId, receiverId);
        String id = String.valueOf(receiverId);
        notificationRepository.save(notification);
        Map<String, SseEmitter> sseEmitters = emitterRepository.findAllStartWithById(id);
        sseEmitters.forEach(
            (key, emitter) -> {
                emitterRepository.saveEventCache(key, notification);
                sendToClient(emitter, key, NotificationResponseDto.from(notification));
            }
        );
    }

//    @Transactional
//    public NotificationsResponse findAllById(LoginMember loginMember) {
//        List<NotificationResponse> responses = notificationRepository.findAllByReceiverId(
//                loginMember.getId()).stream()
//            .map(NotificationResponse::from)
//            .collect(Collectors.toList());
//        long unreadCount = responses.stream()
//            .filter(notification -> !notification.isRead())
//            .count();
//
//        return NotificationsResponse.of(responses, unreadCount);
//    }

//    @Transactional
//    public void readNotification(Long id) {
//        Notification notification = notificationRepository.findById(id)
//            .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 알림입니다."));
//        notification.read();
//    }
}
