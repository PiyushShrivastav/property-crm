package com.propertycrm.app.controller;

import com.propertycrm.app.dto.request.UnitHoldRequest;
import com.propertycrm.app.service.UnitHoldService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/unit-holds")
@RequiredArgsConstructor
public class UnitHoldController {

    private final UnitHoldService unitHoldService;

    @PostMapping
    public Object holdUnit(
            @RequestBody UnitHoldRequest request) {

        return unitHoldService.holdUnit(request);
    }

    @GetMapping
    public Object getActiveHolds() {

        return unitHoldService.getActiveHolds();
    }

    @PostMapping("/release-expired")
    public Object releaseExpired() {

        return unitHoldService.releaseExpiredHolds();
    }
}