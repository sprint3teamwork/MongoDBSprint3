package org.example.model.domain.entity;

public class Decoration extends Product {

    private boolean materialIsWood;
    public Decoration(String name, double price, boolean materialIsWood) {
        super(name, price);
        super.setType("Decoration");
        this.materialIsWood = materialIsWood;
    }

    public Decoration(int id, String name, double price, boolean materialIsWood) {
        super(id, name, price);
        super.setType("Decoration");
        this.materialIsWood = materialIsWood;
    }

    public boolean isMaterialIsWood() {
        return materialIsWood;
    }
    
    public void setMaterialIsWood(boolean materialIsWood) {
        this.materialIsWood = materialIsWood;
    }

    public String toString(){
        return "Id = " + this.getId() + " | Name = " + this.getName() + " | Price = " + ((float) Math.round(this.getPrice() * 100) / 100) +
                " | Is wood? = " + this.isMaterialIsWood() + " | Type = " + this.getType();
    }

}
