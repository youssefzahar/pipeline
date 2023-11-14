package tn.esprit.devops_project.services.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.entities.InvoiceDetail;
import tn.esprit.devops_project.entities.Supplier;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class InvoiceTests {

    @Test
    public void testInvoiceAttributes() {
        // Create an Invoice
        Invoice invoice = new Invoice();
        invoice.setIdInvoice(1L);
        invoice.setAmountDiscount(10.0f);
        invoice.setAmountInvoice(100.0f);
        invoice.setDateCreationInvoice(new Date());
        invoice.setDateLastModificationInvoice(new Date());
        invoice.setArchived(false);

        // Test attribute values
        assertEquals(1L, invoice.getIdInvoice());
        assertEquals(10.0f, invoice.getAmountDiscount());
        assertEquals(100.0f, invoice.getAmountInvoice());
        assertNotNull(invoice.getDateCreationInvoice());
        assertNotNull(invoice.getDateLastModificationInvoice());
        assertEquals(false, invoice.getArchived());

        // Test relationships
        Set<InvoiceDetail> invoiceDetails = new HashSet<>();
        InvoiceDetail invoiceDetail = new InvoiceDetail();
        // Set attributes for InvoiceDetail
        invoiceDetails.add(invoiceDetail);
        invoice.setInvoiceDetails(invoiceDetails);

        // Test the relationship with InvoiceDetail
        assertEquals(1, invoice.getInvoiceDetails().size());
        Assertions.assertTrue(invoice.getInvoiceDetails().contains(invoiceDetail));

        // Test the relationship with Supplier (assuming it's a ManyToOne relationship)
        Supplier supplier = new Supplier();
        invoice.setSupplier(supplier);
        assertEquals(supplier, invoice.getSupplier());
    }

    // Add more test cases for specific methods and edge cases as needed
}
