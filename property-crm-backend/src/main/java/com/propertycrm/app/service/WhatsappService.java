package com.propertycrm.app.service;

import com.propertycrm.app.entity.WhatsappMessageType;

public interface WhatsappService {

    void sendMessage(
            String mobile,
            String message,
            WhatsappMessageType Type);
}