package org.example.model.repository.invoice;

import org.example.model.domain.Invoice;
import org.example.model.domain.entity.Product;
import org.example.model.repository.DatabaseConnection;
import org.example.model.repository.interfaces.InvoiceDAO;

import java.util.ArrayList;
import java.util.List;

public class InvoiceDAOImpl implements InvoiceDAO {
	
	MongoCollection collInvoice = MongoDatabase.getCollection("Invoice");
	
	/*Document sampleDoc = new Document("id","1").append("field name(like id,name,color,etc", "value of each field");
	collStock.insertOne(sampleDoc);
	
	//or to run thru collection list
	
	DBCursor cursor = collInvoice.find();
	while (cursor.hasNext()) {
		int i = 1;
		System.out.println(cursor.next());
		i++;
	}*/
	
    public void insertInvoice(Invoice invoice) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String insertInvoiceSQL = "INSERT INTO `flowershop`.`invoices` (id, Total_Sale) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(insertInvoiceSQL);
            statement.setInt(1, invoice.getId());
            statement.setDouble(2, invoice.getTotalSale());
            statement.executeUpdate();

            // Insert associated products
           /* for (Product product : invoice.getProductList()) {
                product.setInvoiceId(invoice.getId());
            }*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Invoice> invoicesRetriever() {
        List<Invoice> invoceLog = new ArrayList<>();
        String query = "SELECT id AS invoice_id, Total_Sale AS invoice_totalSale FROM flowershop.invoices";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {

                int id = resultSet.getInt("invoice_id");
                double totalSale = resultSet.getDouble("invoice_totalSale");
                Invoice invoice = new Invoice(id, totalSale);

                invoceLog.add(invoice);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return invoceLog;
    }

    public void updateInvoice(Invoice invoice) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String updateInvoiceSQL = "UPDATE `flowershop`.`invoices` SET Total_Sale = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(updateInvoiceSQL);
            statement.setDouble(1, invoice.getTotalSale());
            statement.setInt(2, invoice.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
