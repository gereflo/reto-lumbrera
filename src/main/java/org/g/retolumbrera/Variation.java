package org.g.retolumbrera;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Variation extends PanacheEntity{

    public int id;
    public String name;
    public String brand;
    public String sku;
    public int stock;
    //public int Products_id;
    @ManyToOne
    @JsonbTransient
    public Product product;

    public Variation() {
    }

    /*public Variation(int id, String name, String brand, String sku, int stock, int products_id) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.sku = sku;
        this.stock = stock;
        Products_id = products_id;
    }

    public Variation(int id, String name, String brand, String sku, int stock) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.sku = sku;
        this.stock = stock;
    }

    public Variation(String name, String brand, String sku, int stock) {
        this.name = name;
        this.brand = brand;
        this.sku = sku;
        this.stock = stock;
    }*/
}
