package kr.ohho.ui.notification;

import kr.ohho.application.notification.NotificationService;
import kr.ohho.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    /* 로그인 한 멤버 sse 연결 */
    @GetMapping(value = "/connection", produces = "text/event-stream")
    public ApiResponse<SseEmitter> subscribe(@ModelAttribute("memberId") Long memberId,
        @RequestParam(value = "lastEventId", required = false, defaultValue = "") String lastEventId) {
        return ApiResponse.success(notificationService.subscribe(memberId, lastEventId));
    }

    /* 로그인 한 멤버의 모든 알림 조회 */
//    @GetMapping("/notifications")
//    public ResponseEntity<NotificationsResponse> notifications(@Login LoginMember loginMember) {
//        return ResponseEntity.ok().body(notificationService.findAllById(loginMember));
//    }

    /* 알림 읽음 상태 변경 */
//    @PatchMapping("/notifications/{id}")
//    public ResponseEntity<Void> readNotification(@PathVariable Long id) {
//        notificationService.readNotification(id);
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//    }
}
