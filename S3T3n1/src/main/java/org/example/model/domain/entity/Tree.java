package org.example.model.domain.entity;

import org.example.model.domain.entity.Product;

public class Tree extends Product {
    private float height;

    public Tree(String name, double price, float height) {
        super(name, price);
        super.setType("Tree");
        this.height = height;
    }

    public Tree(int id, String name, double price, float height) {
        super(id, name, price);
        super.setType("Tree");
        this.height = height;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public String toString(){
        return "Id = " + this.getId() + " | Name = " + this.getName() + " | Price = " + ((float) Math.round(this.getPrice() * 100) / 100) +
                " | Height = " + this.getHeight() + " | Type = " + this.getType();
    }
}
