package com.propertycrm.app.service.impl;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.propertycrm.app.entity.Collection;
import com.propertycrm.app.repository.CollectionRepository;
import com.propertycrm.app.service.ReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
@RequiredArgsConstructor
public class ReceiptServiceImpl
        implements ReceiptService {

    private final CollectionRepository collectionRepository;

    @Override
    public byte[] generateReceipt(Long collectionId) {

        Collection collection =
                collectionRepository.findById(collectionId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Collection not found"));

        try {

            ByteArrayOutputStream outputStream =
                    new ByteArrayOutputStream();

            Document document =
                    new Document();

            PdfWriter.getInstance(
                    document,
                    outputStream);

            document.open();

            Font headingFont =
                    new Font(Font.HELVETICA,
                            18,
                            Font.BOLD);

            Paragraph heading =
                    new Paragraph(
                            "PAYMENT RECEIPT",
                            headingFont);

            heading.setAlignment(
                    Element.ALIGN_CENTER);

            document.add(heading);

            document.add(new Paragraph(" "));

            document.add(new Paragraph(
                    "Customer Name : "
                    + collection.getPaymentSchedule()
                    .getBooking()
                    .getCustomer()
                    .getCustomerName()
            ));

            document.add(new Paragraph(
                    "Project : "
                    + collection.getPaymentSchedule()
                    .getBooking()
                    .getProject()
                    .getProjectName()
            ));

            document.add(new Paragraph(
                    "Unit No : "
                    + collection.getPaymentSchedule()
                    .getBooking()
                    .getUnit()
                    .getUnitNo()
            ));

            document.add(new Paragraph(
                    "Amount : ₹ "
                    + collection.getAmount()
            ));

            document.add(new Paragraph(
                    "Payment Mode : "
                    + collection.getPaymentMode()
            ));

            document.add(new Paragraph(
                    "Reference Number : "
                    + collection.getReferenceNumber()
            ));

            document.add(new Paragraph(
                    "Payment Date : "
                    + collection.getPaymentDate()
            ));

            document.close();

            return outputStream.toByteArray();

        } catch (Exception ex) {

            throw new RuntimeException(
                    "Error generating receipt");
        }
    }
}