package org.example.model.repository.interfaces;

import java.util.List;
import org.example.model.domain.entity.Product;

public interface StockDAO {
	
	List<Product> findAll();
	Product findById(int id);
	List<Product> findByName(String name);
	List<Product> findByPrice(double price);
	List<Product> findByType(String type);
	List<Product> findByHeight(float height);
	List<Product> findByColor(String color);
	List<Product> findByMaterial(boolean material);
	boolean insertProduct(Product p);
	boolean updateProduct(Product p);
	boolean deleteProduct(int id);

}
