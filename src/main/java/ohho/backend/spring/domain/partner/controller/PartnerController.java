package ohho.backend.spring.domain.partner.controller;


import lombok.RequiredArgsConstructor;
import ohho.backend.spring.common.ApiResponse;
import ohho.backend.spring.domain.partner.entities.Partner;
import ohho.backend.spring.domain.partner.exception.PartnerNotFoundException;
import ohho.backend.spring.domain.partner.model.request.AcceptPartnerRequestDto;
import ohho.backend.spring.domain.partner.model.request.RequestPartnerRequestDto;
import ohho.backend.spring.domain.partner.model.request.UpdateDdayAndGoalRequestDto;
import ohho.backend.spring.domain.partner.service.PartnerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/partners")
@RequiredArgsConstructor
public class PartnerController {

    private final PartnerService partnerService;

    @PostMapping("/request")
    public ApiResponse<String> requestPartner(@ModelAttribute("memberId") Long memberId,
        @RequestBody RequestPartnerRequestDto requestPartnerRequestDto) {
        return ApiResponse.success(
            partnerService.requestPartner(memberId, requestPartnerRequestDto));
    }

    @PostMapping("/accept")
    public ApiResponse<Partner> acceptPartner(@ModelAttribute("memberId") Long memberId,
        @RequestBody AcceptPartnerRequestDto acceptPartnerRequestDto) {
        return ApiResponse.success(
            partnerService.acceptPartner(memberId, acceptPartnerRequestDto));
    }

    @GetMapping("/{id}")
    public ApiResponse<Partner> getPartner(@PathVariable("id") Long id) {
        return ApiResponse.success(partnerService.getPartner(id));
    }

    @PatchMapping("/{id}")
    public ApiResponse<String> updateDdayAndGoalRequest(
        @PathVariable("id") Long id,
        @RequestBody UpdateDdayAndGoalRequestDto updateDdayAndGoalRequest) {
        return ApiResponse.success(partnerService.updateDdayAndGoal(id, updateDdayAndGoalRequest));
    }

}
