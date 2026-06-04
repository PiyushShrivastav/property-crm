package com.propertycrm.app.service;

import com.propertycrm.app.dto.request.UnitHoldRequest;

public interface UnitHoldService {

    Object holdUnit(UnitHoldRequest request);

    Object releaseExpiredHolds();

    Object getActiveHolds();
}