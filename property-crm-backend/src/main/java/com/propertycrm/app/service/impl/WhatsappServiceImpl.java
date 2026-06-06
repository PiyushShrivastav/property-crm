package com.propertycrm.app.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.propertycrm.app.entity.WhatsappLog;
import com.propertycrm.app.entity.WhatsappMessageType;
import com.propertycrm.app.repository.WhatsappLogRepository;
import com.propertycrm.app.service.WhatsappService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WhatsappServiceImpl implements WhatsappService {

	private final WhatsappLogRepository whatsappLogRepository;

	@Override
	public void sendMessage(String mobile, String message, WhatsappMessageType type) {

		// FUTURE:
		// Actual WhatsApp API Call Here

		System.out.println("WhatsApp Sent To : " + mobile);

		System.out.println(message);

		WhatsappLog log = WhatsappLog.builder().mobile(mobile).message(message).type(type).sent(true)
				.sentAt(LocalDateTime.now()).build();

		whatsappLogRepository.save(log);
	}
}