package org.example.model.repository.invoice;

import org.bson.Document;
import org.example.model.domain.Invoice;
import org.example.model.repository.DatabaseConnection;
import org.example.model.repository.interfaces.InvoiceDAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.List;

public class InvoiceDAOImpl implements InvoiceDAO {
	
	private static final String COLLECTION_NAME = "invoiceLog";
	private MongoCollection<Document> invoiceCollection;
	
	public InvoiceDAOImpl() {
		MongoDatabase database = DatabaseConnection.getConnection();//Connection pooling is a standard leaving connection open in mongo
		invoiceCollection = database.getCollection(COLLECTION_NAME);
	}

	@Override
	public List<Invoice> findAll() {
		
		List<Invoice> invoiceLog = new ArrayList();
		MongoCursor<Document> cursor = invoiceCollection.find().iterator();
		
		try {
			while (cursor.hasNext()) {
				Document invoiceDoc = cursor.next();
				invoiceLog.add(mapDocumentToInvoice(invoiceDoc));
			}
		} finally {
			cursor.close();
		}
		return invoiceLog;
	}
	
	@Override
	public Invoice findById(int id) {
		Document query = new Document("id", id);
        Document invoiceDoc = invoiceCollection.find(query).first();
        if (invoiceDoc != null) {
            return mapDocumentToInvoice(invoiceDoc);
        }
        return null;
	}
	
	@Override
	public Invoice findByTotalSale(int totalSale) {
		Document query = new Document("totalSale", totalSale);
        Document invoiceDoc = invoiceCollection.find(query).first();
        if (invoiceDoc != null) {
            return mapDocumentToInvoice(invoiceDoc);
        }
        return null;
	}
	
	@Override
    public boolean insertInvoice(Invoice i) {
    	Document invoiceDoc = mapInvoiceToDocument(i);
    	invoiceCollection.insertOne(invoiceDoc);
        return true;
    }

	@Override
    public boolean updateInvoice(Invoice i) {
    	Document query = new Document("id", i.getId());
    	Document invoiceDoc = mapInvoiceToDocument(i);
    	invoiceCollection.updateOne(query, new Document("$set", invoiceDoc)); //$set operator to set the fields in the document to the values provided in invoiceDoc
    	return true;
    }
    
	@Override
    public boolean deleteInvoice(int id) {
    	Document query = new Document("id", id);
    	invoiceCollection.deleteOne(query);
    	return true;
    }
    
    private Invoice mapDocumentToInvoice(Document invoiceDoc) {
        int id = invoiceDoc.getInteger("id");
        double totalSale = invoiceDoc.getDouble("price"); 
        return new Invoice(id, totalSale);
    }

    private Document mapInvoiceToDocument(Invoice i) {
    	return new Document("id", i.getId())
                .append("totalSale", i.getTotalSale());
    }
        

}
