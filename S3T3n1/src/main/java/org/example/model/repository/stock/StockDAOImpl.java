package org.example.model.repository.stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.model.domain.entity.Decoration;
import org.example.model.domain.entity.Flower;
import org.example.model.domain.entity.Product;
import org.example.model.domain.entity.Tree;
import org.example.model.repository.DatabaseConnection;
import org.example.model.repository.interfaces.StockDAO;

public class StockDAOImpl implements StockDAO {
	
	MongoCollection collStock = MongoDatabase.getCollection("Stock");
	
	/*Document sampleDoc = new Document("id","1").append("field name(like id,name,color,etc", "value of each field");
	collStock.insertOne(sampleDoc);
	
	//or to run thru collection list
	
	DBCursor cursor = collInvoice.find();
	while (cursor.hasNext()) {
		int i = 1;
		System.out.println(cursor.next());
		i++;
	}*/
	

    public Product checkTypeRS(ResultSet rs, Product p) throws SQLException {

        String type = rs.getString(4);
        //p.setInvoiceId(rs.getInt(8));    //need this variable		//or string param invoice_id

        switch (type) {
            case "Tree":
                p = new Tree(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getFloat(5));
                break;
            case "Flower":
                p = new Flower(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(6));
                break;
            case "Decoration":
                p = new Decoration(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getBoolean(7));
                break;
        }
        p.setInvoiceId(rs.getInt(8));

        return p;
    }

    public Product checkTypePS(PreparedStatement ps, Product p) throws SQLException {

        Product product = null;

        ps.setInt(1, p.getId());
        ps.setString(2, p.getName());
        ps.setDouble(3, p.getPrice());
        ps.setString(4, p.getType());
        ps.setInt(8, p.getInvoiceId());

        switch (p.getType()) {
            case "Tree":
                Tree tree = (Tree) p;
                ps.setFloat(5, tree.getHeight());
                ps.setString(6, null);
                ps.setString(7, "false");
                product = tree;
                break;
            case "Flower":
                Flower flower = (Flower) p;
                ps.setFloat(5, 0f);
                ps.setString(6, flower.getColor());
                ps.setString(7, "false");
                product = flower;
                break;
            case "Decoration":
                Decoration decoration = (Decoration) p;
                ps.setFloat(5, 0f);
                ps.setString(6, null);
                ps.setBoolean(7, decoration.isMaterialIsWood());
                product = decoration;
                break;
        }

        return product;
    }

    //this method needed to add all products on first connection?
    //also is another constructor needed to include the invoiceID?
    //flowershop needed to receive addstock and removestock methods?
    //using FIND_ALL static variable from above on line 83 from 24
    @Override
    public List<Product> findAll() {

        List<Product> stockList = new ArrayList();

        try (Connection newConnect = DatabaseConnection.getConnection();
             PreparedStatement ps = newConnect.prepareStatement(FIND_ALL);
             ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {
                Product p = null;
                p = checkTypeRS(rs, p);
                stockList.add(p);
                //flowerShopp.setStockValue += p.getprice when?
                //flowerShopp.putProductMap put.p
            }
        } catch (SQLException e) {// | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return stockList;
    }

    //using FINDBYID static variable
    @Override
    public Product findById(int id) {

        Product p = new Product();
        p.setId(id);

        try (Connection newConnect = DatabaseConnection.getConnection();
             PreparedStatement ps = newConnect.prepareStatement(FIND_BY_ID);
             ResultSet rs = ps.executeQuery()
        ) {

            rs.next();

            p = checkTypeRS(rs, p);

        } catch (SQLException e) {// | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return p;
    }

    @Override
    public List<Product> findByName(String name) {

        List<Product> stockList = new ArrayList();

        try (Connection newConnect = DatabaseConnection.getConnection();
             PreparedStatement ps = newConnect.prepareStatement(FIND_BY_NAME);
             ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                Product p = null;
                p = checkTypeRS(rs, p);
                stockList.add(p);
                //flowerShopp.setStockValue += p.getprice when?
                //flowerShopp.putProductMap put.p
            }

        } catch (SQLException e) {// | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return stockList;
    }

    @Override
    public List<Product> findByPrice(double price) {

        List<Product> stockList = new ArrayList();

        try (Connection newConnect = DatabaseConnection.getConnection();
             PreparedStatement ps = newConnect.prepareStatement(FIND_BY_PRICE);
             ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                Product p = null;
                p = checkTypeRS(rs, p);
                stockList.add(p);
                //flowerShopp.setStockValue += p.getprice when?
                //flowerShopp.putProductMap put.p
            }

        } catch (SQLException e) {// | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return stockList;
    }

    @Override
    public List<Product> findByType(String productType) {

        List<Product> stockList = new ArrayList();

        try (Connection newConnect = DatabaseConnection.getConnection();
             PreparedStatement ps = newConnect.prepareStatement(FIND_BY_TYPE);
             ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                Product p = null;
                p = checkTypeRS(rs, p);
                stockList.add(p);
                //flowerShopp.setStockValue += p.getprice when?
                //flowerShopp.putProductMap put.p
            }

        } catch (SQLException e) {// | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return stockList;
    }

    @Override
    public List<Product> findByHeight(float height) {

        List<Product> stockList = new ArrayList();

        try (Connection newConnect = DatabaseConnection.getConnection();
             PreparedStatement ps = newConnect.prepareStatement(FIND_BY_HEIGHT);
             ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                Product p = null;
                p = checkTypeRS(rs, p);
                stockList.add(p);
                //flowerShopp.setStockValue += p.getprice when?
                //flowerShopp.putProductMap put.p
            }

        } catch (SQLException e) {// | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return stockList;
    }

    @Override
    public List<Product> findByColor(String color) {

        List<Product> stockList = new ArrayList();

        try (Connection newConnect = DatabaseConnection.getConnection();
             PreparedStatement ps = newConnect.prepareStatement(FIND_BY_COLOR);
             ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                Product p = null;
                p = checkTypeRS(rs, p);
                stockList.add(p);
                //flowerShopp.setStockValue += p.getprice when?
                //flowerShopp.putProductMap put.p
            }

        } catch (SQLException e) {// | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return stockList;
    }

    @Override
    public List<Product> findByMaterial(boolean material) {

        List<Product> stockList = new ArrayList();

        try (Connection newConnect = DatabaseConnection.getConnection();
             PreparedStatement ps = newConnect.prepareStatement(FIND_BY_MATERIAL);
             ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                Product p = null;
                p = checkTypeRS(rs, p);
                stockList.add(p);
                //flowerShopp.setStockValue += p.getprice when?
                //flowerShopp.putProductMap put.p
            }

        } catch (SQLException e) {// | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return stockList;
    }

    @Override
    public boolean insertProduct(Product p) {

        try (Connection newConnect = DatabaseConnection.getConnection();
             PreparedStatement ps = newConnect.prepareStatement(INSERT);
        ) {

            Product product = checkTypePS(ps, p);
            ps.setNull(8, java.sql.Types.INTEGER);//all inserted products will have a null invoice-id
            ps.executeUpdate();

            System.out.println("Product with following details was saved in DB: " + product.toString());

            return true;
        } catch (SQLException e) {// | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateProduct(Product p) {

        try (Connection newConnect = DatabaseConnection.getConnection();
             PreparedStatement ps = newConnect.prepareStatement(UPDATE);
        ) {

            Product product = checkTypePS(ps, p);
            ps.setInt(9, p.getId());

            ps.executeUpdate();

            System.out.println("User with id " + product.getId() + " was updated in DB with following details: " + product.toString());

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteProduct(int id) {

        try (Connection newConnect = DatabaseConnection.getConnection();
             PreparedStatement ps = newConnect.prepareStatement(DELETE);
        ) {

            ps.setInt(1, id);

            ps.executeUpdate();

            System.out.println("Product with id: " + id + " was sucesfully deleted from DB.");

        } catch (SQLException e) {// | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

}
