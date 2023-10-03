package org.example.model.repository.stock;

import org.bson.codecs.pojo.annotations.BsonId;

public class ProductDocument {
	@BsonId
	private int id;
	private String name;
	private double price;
	private String type;
	private float height;
	private String color;
	private boolean materialIsWood;
	private int invoiceId;

}
