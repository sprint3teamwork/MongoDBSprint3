package org.example.model.repository.interfaces;

import org.example.model.domain.Invoice;
import org.example.model.domain.entity.Decoration;
import org.example.model.domain.entity.Flower;
import org.example.model.domain.entity.Product;
import org.example.model.domain.entity.Tree;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Flow;

public interface DBInteraction {

     boolean stockDataIsEmpty();
     boolean invoiceDataIsEmpty();
     List<Product> getStockData();
     List<Product> getProductsSoldData();
     List<Invoice> getInvoiceData();
     void insertTree(Product tree);
     void insertFlower(Product flower);
     void insertDecoration(Product decoration);
     void removeTree(Product tree);
     void removeFlower(Product flower);
     void removeDecoration(Product decoration);
     Map<String,Integer> getProductMapData();//finByType.size for every value or load stocklist in flowershop for totalEranings etc.
     void insertInvoice(Invoice invoice);
     void tablesInitializer();
     void updateProduct(Product product);

}
