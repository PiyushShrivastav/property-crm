package com.propertycrm.app.service;

import com.propertycrm.app.dto.request.UnitRequest;
import com.propertycrm.app.dto.response.UnitResponse;

import java.util.List;

public interface UnitService {

    UnitResponse create(UnitRequest request);

    List<UnitResponse> getAll();

    List<UnitResponse> getByProject(Long projectId);

    UnitResponse getById(Long id);
}