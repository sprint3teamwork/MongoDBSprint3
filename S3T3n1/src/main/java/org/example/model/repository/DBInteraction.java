package org.example.model.repository;

import org.example.model.domain.Invoice;
import org.example.model.domain.entity.Product;
import org.example.model.repository.invoice.InvoiceDAOImpl;
import org.example.model.repository.stock.StockMongoDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DBInteraction implements org.example.model.repository.interfaces.DBInteraction {

    private static StockMongoDAO stockDAO = new StockMongoDAO();
    private static InvoiceDAOImpl invoiceDAO = new InvoiceDAOImpl();


    @Override
    public boolean stockDataIsEmpty() {
        return stockDAO.findAll().isEmpty();
    }

    @Override
    public boolean invoiceDataIsEmpty() {
        return invoiceDAO.findAll().isEmpty();
    }

    @Override
    public List<Product> getStockData() {
        List<Product> stockList = new ArrayList<>();

        for(Product p : stockDAO.findAll()){
            if(p.getInvoiceId() == 0){
                stockList.add(p);
            }
        }
        return stockList;
    }

    @Override
    public List<Product> getProductsSoldData() {
        List<Product> soldProductsList = new ArrayList<>();

        for(Product p : stockDAO.findAll()){
            if(p.getInvoiceId() != 0){
                soldProductsList.add(p);
            }
        }
        return soldProductsList;
    }

    @Override
    public List<Invoice> getInvoiceData() {
        return invoiceDAO.findAll();
    }

    @Override
    public void insertTree(Product tree) {
        stockDAO.insertProduct(tree);
    }

    @Override
    public void insertFlower(Product flower) {
        stockDAO.insertProduct(flower);
    }

    @Override
    public void insertDecoration(Product decoration) {
        stockDAO.insertProduct(decoration);
    }

    @Override
    public void removeTree(Product tree) {
        stockDAO.deleteProduct(tree.getId());
    }

    @Override
    public void removeFlower(Product flower) {
        stockDAO.deleteProduct(flower.getId());
    }

    @Override
    public void removeDecoration(Product decoration) {
        stockDAO.deleteProduct(decoration.getId());
    }

    @Override
    public Map<String, Integer> getProductMapData() {
        return null;
    }//Not sure if we need it if we compile stocklist in flowershop class product by product

    @Override
    public void insertInvoice(Invoice invoice) {
        invoiceDAO.insertInvoice(invoice);
    }

    @Override
    public void updateProduct(Product product) {
        stockDAO.updateProduct(product);
    }

    public void updateInvocie(Invoice invoice){
        invoiceDAO.updateInvoice(invoice);
    }
}
