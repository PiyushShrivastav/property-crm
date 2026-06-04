package com.propertycrm.app.controller;

import com.propertycrm.app.service.ReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/receipts")
@RequiredArgsConstructor
public class ReceiptController {

    private final ReceiptService receiptService;

    @GetMapping("/{collectionId}")
    public ResponseEntity<byte[]> downloadReceipt(
            @PathVariable Long collectionId) {

        byte[] pdf =
                receiptService.generateReceipt(
                        collectionId);

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=receipt.pdf")
                .contentType(
                        MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}