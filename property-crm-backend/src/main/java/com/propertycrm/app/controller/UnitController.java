package com.propertycrm.app.controller;

import com.propertycrm.app.dto.request.UnitRequest;
import com.propertycrm.app.service.UnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/units")
@RequiredArgsConstructor
public class UnitController {

    private final UnitService unitService;

    @PostMapping
    public Object create(
            @RequestBody UnitRequest request) {

        return unitService.create(request);
    }

    @GetMapping
    public Object getAll() {

        return unitService.getAll();
    }

    @GetMapping("/project/{projectId}")
    public Object getByProject(
            @PathVariable Long projectId) {

        return unitService.getByProject(projectId);
    }
}