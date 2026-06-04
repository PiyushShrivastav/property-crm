package com.propertycrm.app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "whatsapp_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WhatsappLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mobile;

    @Column(length = 5000)
    private String message;

    @Enumerated(EnumType.STRING)
    private WhatsappMessageType type;

    private Boolean sent;

    private LocalDateTime sentAt;
}