package org.example.model.repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import com.mongodb.*;
import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.Document;
import org.bson.conversions.Bson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class DatabaseConnection {
    private static Properties properties = new Properties();

    static {								//if we rename inner structure we need to change  SQLsprint3\\S3T3n1
        try (FileInputStream inputStream = new FileInputStream("SQLsprint3\\S3T3n1\\src\\main\\resources\\application.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }//Made the properties loading static so it only loads once, when the DatabaseConnection class is called
    }

    public static MongoDatabase getConnection() {//we have to handle this exception in upper level wit try-with-resources
        
    	String url = properties.getProperty("db.url");
        String port = properties.getProperty("db.port");
        
        MongoClient client = new MongoClient(url, port);
        
        MongoDatabase db = client.getDatabase("FlowerShop");
        
        return db;
    }
}


    /*
     * Singleton pattern needed for connection or is it already in use with the first null?
     *
     * private DatabaseConnection() {}
     *
     * public static Connection getConnection() {
		Connection result = connection;
		if (result == null) {								//first null instance is to avoid multithreading issue, if instance exists already skips creation process below
			synchronized (DatabaseConnection.class) {
				result = instance;
				if (result == null) {						//if instance is null, ie new, it will go within method
					instance = result = new DatabaseConnection();
				}
			}
		}
		return result;
	}
     *
     *
     */
