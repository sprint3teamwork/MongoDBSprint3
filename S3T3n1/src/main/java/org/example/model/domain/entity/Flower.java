package org.example.model.domain.entity;

public class Flower extends Product {

    private String color;
    public Flower(String name, double price, String color) {
        super(name, price);
        super.setType("Flower");
        this.color = color;
    }

    public Flower(int id,String name, double price, String color) {
        super(id, name, price);
        super.setType("Flower");
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String toString(){
        return "Id = " + this.getId() + " | Name = " + this.getName() + " | Price = " + ((float) Math.round(this.getPrice() * 100) / 100) +
                " | Color = " + this.getColor() + " | Type = " + this.getType();
    }
}
