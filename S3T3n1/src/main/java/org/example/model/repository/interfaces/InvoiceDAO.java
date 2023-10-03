package org.example.model.repository.interfaces;

import org.example.model.domain.Invoice;

import java.util.List;

public interface InvoiceDAO {

    static void insertInvoice(Invoice invoice) {

    }

    static List<Invoice> invoicesRetriever() {
        return null;
    }
    void updateInvoice(Invoice invoice);
}
