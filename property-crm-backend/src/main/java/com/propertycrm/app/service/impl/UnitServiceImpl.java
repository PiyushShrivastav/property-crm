package com.propertycrm.app.service.impl;

import com.propertycrm.app.dto.request.UnitRequest;
import com.propertycrm.app.dto.response.UnitResponse;
import com.propertycrm.app.entity.Project;
import com.propertycrm.app.entity.Unit;
import com.propertycrm.app.entity.UnitStatus;
import com.propertycrm.app.repository.ProjectRepository;
import com.propertycrm.app.repository.UnitRepository;
import com.propertycrm.app.service.UnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UnitServiceImpl implements UnitService {

    private final UnitRepository unitRepository;
    private final ProjectRepository projectRepository;

    @Override
    public UnitResponse create(UnitRequest request) {

        if(unitRepository.existsByUnitNo(
                request.getUnitNo())) {

            throw new RuntimeException(
                    "Unit already exists");
        }

        Project project =
                projectRepository.findById(
                        request.getProjectId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Project not found"));

        Unit unit = Unit.builder()
                .project(project)
                .unitNo(request.getUnitNo())
                .tower(request.getTower())
                .floorNo(request.getFloorNo())
                .area(request.getArea())
                .basePrice(request.getBasePrice())
                .finalPrice(request.getFinalPrice())
                .unitType(request.getUnitType())
                .status(UnitStatus.AVAILABLE)
                .build();

        unit = unitRepository.save(unit);

        return map(unit);
    }

    @Override
    public List<UnitResponse> getAll() {

        return unitRepository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public List<UnitResponse> getByProject(Long projectId) {

        return unitRepository.findByProjectId(projectId)
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public UnitResponse getById(Long id) {

        Unit unit =
                unitRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Unit not found"));

        return map(unit);
    }

    private UnitResponse map(Unit unit) {

        return UnitResponse.builder()
                .id(unit.getId())
                .project(unit.getProject()
                        .getProjectName())
                .unitNo(unit.getUnitNo())
                .tower(unit.getTower())
                .area(unit.getArea())
                .finalPrice(unit.getFinalPrice())
                .status(unit.getStatus())
                .build();
    }
}