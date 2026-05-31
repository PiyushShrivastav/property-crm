package com.propertycrm.app.service.impl;

import com.propertycrm.app.dto.request.ProjectRequest;
import com.propertycrm.app.dto.response.ProjectResponse;
import com.propertycrm.app.entity.Project;
import com.propertycrm.app.repository.ProjectRepository;
import com.propertycrm.app.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public ProjectResponse create(ProjectRequest request) {

        if(projectRepository.existsByProjectCode(
                request.getProjectCode())) {

            throw new RuntimeException(
                    "Project code already exists");
        }

        Project project = Project.builder()
                .projectCode(request.getProjectCode())
                .projectName(request.getProjectName())
                .builderName(request.getBuilderName())
                .city(request.getCity())
                .location(request.getLocation())
                .totalArea(request.getTotalArea())
                .totalUnits(request.getTotalUnits())
                .active(true)
                .createdAt(LocalDateTime.now())
                .build();

        project = projectRepository.save(project);

        return map(project);
    }

    @Override
    public List<ProjectResponse> getAll() {

        return projectRepository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public ProjectResponse getById(Long id) {

        Project project =
                projectRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Project not found"));

        return map(project);
    }

    private ProjectResponse map(Project project) {

        return ProjectResponse.builder()
                .id(project.getId())
                .projectCode(project.getProjectCode())
                .projectName(project.getProjectName())
                .city(project.getCity())
                .location(project.getLocation())
                .totalUnits(project.getTotalUnits())
                .build();
    }
}