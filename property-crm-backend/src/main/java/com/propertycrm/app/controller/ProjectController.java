package com.propertycrm.app.controller;

import com.propertycrm.app.dto.request.ProjectRequest;
import com.propertycrm.app.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public Object create(
            @RequestBody ProjectRequest request) {

        return projectService.create(request);
    }

    @GetMapping
    public Object getAll() {

        return projectService.getAll();
    }

    @GetMapping("/{id}")
    public Object getById(
            @PathVariable Long id) {

        return projectService.getById(id);
    }
}