package org.example.model.repository.interfaces;

import org.example.model.domain.Invoice;

import java.util.List;

public interface InvoiceDAO {

    List<Invoice> findAll();
    
    Invoice findById(int id);
    
    Invoice findByTotalSale(int totalSale);
    
    boolean insertInvoice(Invoice i);
    
    boolean updateInvoice(Invoice i);
    
    boolean deleteInvoice(int id);
}
