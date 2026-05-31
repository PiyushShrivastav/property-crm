package com.propertycrm.app.service;

import com.propertycrm.app.dto.request.ProjectRequest;
import com.propertycrm.app.dto.response.ProjectResponse;

import java.util.List;

public interface ProjectService {

    ProjectResponse create(ProjectRequest request);

    List<ProjectResponse> getAll();

    ProjectResponse getById(Long id);
}