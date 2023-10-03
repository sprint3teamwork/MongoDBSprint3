package org.example.model.domain.entity;

public class Product {
    protected static int idNextNumber = 1;
    protected int id;
    protected String name;
    protected double price;
    protected String type;
    protected int invoiceId;

    public Product(int id,String name, double price){
        this.id = id;
        idNextNumber = id +1;
        this.name = name;
        this.price = price;
    }

    public Product(String name, double price) {
        id = idNextNumber;
        idNextNumber++;
        this.name = name;
        this.price = price;
    }
    
    public Product() {}

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
    	this.id = id;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
		this.name = name;
	}

    public double getPrice() {
        return price;
    }

	public void setPrice(double price) {
		this.price = price;
	}

	public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }
}
