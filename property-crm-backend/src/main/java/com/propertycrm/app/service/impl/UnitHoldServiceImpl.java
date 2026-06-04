package com.propertycrm.app.service.impl;

import com.propertycrm.app.dto.request.UnitHoldRequest;
import com.propertycrm.app.dto.response.UnitHoldResponse;
import com.propertycrm.app.entity.*;
import com.propertycrm.app.repository.UnitHoldRepository;
import com.propertycrm.app.repository.UnitRepository;
import com.propertycrm.app.service.UnitHoldService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UnitHoldServiceImpl
        implements UnitHoldService {

    private final UnitRepository unitRepository;
    private final UnitHoldRepository unitHoldRepository;

    @Override
    public UnitHoldResponse holdUnit(
            UnitHoldRequest request) {

        Unit unit =
                unitRepository.findById(
                        request.getUnitId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Unit not found"));

        if(unit.getStatus() != UnitStatus.AVAILABLE) {

            throw new RuntimeException(
                    "Unit not available");
        }

        unit.setStatus(UnitStatus.HOLD);

        unitRepository.save(unit);

        LocalDateTime now =
                LocalDateTime.now();

        UnitHold hold =
                UnitHold.builder()
                        .customerName(
                                request.getCustomerName())
                        .mobile(
                                request.getMobile())
                        .tokenAmount(
                                request.getTokenAmount())
                        .holdTime(now)
                        .expiryTime(
                                now.plusHours(
                                        request.getHoldHours()))
                        .active(true)
                        .unit(unit)
                        .build();

        hold = unitHoldRepository.save(hold);

        return map(hold);
    }

    @Override
    public List<UnitHoldResponse> getActiveHolds() {

        return unitHoldRepository.findByActiveTrue()
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public String releaseExpiredHolds() {

        List<UnitHold> expiredHolds =
                unitHoldRepository
                        .findByExpiryTimeBeforeAndActiveTrue(
                                LocalDateTime.now());

        for(UnitHold hold : expiredHolds) {

            hold.setActive(false);

            Unit unit = hold.getUnit();

            unit.setStatus(UnitStatus.AVAILABLE);

            unitRepository.save(unit);

            unitHoldRepository.save(hold);
        }

        return expiredHolds.size()
                + " expired holds released";
    }

    @Scheduled(fixedRate = 300000)
    public void autoReleaseExpiredHolds() {

        releaseExpiredHolds();
    }

    private UnitHoldResponse map(UnitHold hold) {

        return UnitHoldResponse.builder()
                .id(hold.getId())
                .customerName(hold.getCustomerName())
                .mobile(hold.getMobile())
                .unitNo(hold.getUnit().getUnitNo())
                .tokenAmount(hold.getTokenAmount())
                .holdTime(hold.getHoldTime())
                .expiryTime(hold.getExpiryTime())
                .active(hold.getActive())
                .build();
    }
}