package ohho.backend.spring.domain.partner.controller;


import lombok.RequiredArgsConstructor;
import ohho.backend.spring.common.ApiResponse;
import ohho.backend.spring.domain.partner.entities.Partner;
import ohho.backend.spring.domain.partner.model.request.UpdateDdayAndGoalRequest;
import ohho.backend.spring.domain.partner.service.PartnerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/partners")
@RequiredArgsConstructor
public class PartnerController {

    private final PartnerService partnerService;

    @GetMapping("/{id}")
    public ApiResponse<Partner> getPartner(@PathVariable("id") Long id) {
        return ApiResponse.success(partnerService.getPartner(id));
    }

    @PatchMapping("/{id}")
    public ApiResponse<String> updateDdayAndGoalRequest(
        @PathVariable("id") Long id,
        @RequestBody UpdateDdayAndGoalRequest updateDdayAndGoalRequest) {
        return ApiResponse.success(partnerService.updateDdayAndGoal(id, updateDdayAndGoalRequest));
    }

}
