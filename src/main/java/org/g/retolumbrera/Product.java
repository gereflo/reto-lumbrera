package org.g.retolumbrera;

import java.util.List;
import java.util.Set;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;

//@Entity
public class Product extends PanacheEntity {

    public int id;
    public String name;
    public int stock;
    public double cost;
    public double price;
    public boolean has_iva;

    @ManyToOne
    @JsonbTransient
    public int companies_id;
    //public int companies_id;

    //@OneToMany(mappedBy = "Product_id", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    public Set<Variation> variations;

    public Product() {
    }

    public Product(int id) {
        this.id = id;
    }

    public Product(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Product(int id, String name, int stock) {
        this.id = id;
        this.name = name;
        this.stock = stock;
    }

    public Product(int id, String name, int stock, double cost) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.cost = cost;
    }

    public Product(int id, String name, int stock, double cost, double price) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.cost = cost;
        this.price = price;
    }

    public Product(int id, String name, int stock, double cost, double price, boolean has_iva) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.cost = cost;
        this.price = price;
        this.has_iva = has_iva;
    }

    public Product(int id, String name, int stock, double cost, double price, boolean has_iva, int companies_id) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.cost = cost;
        this.price = price;
        this.has_iva = has_iva;
        //this.companies_id = companies_id;
    }

    public Product(int id, String name, int stock, double cost, double price, boolean has_iva, int companies_id, Set<Variation> variation) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.cost = cost;
        this.price = price;
        this.has_iva = has_iva;
        this.companies_id = companies_id;
        this.variations = variation;
    }

    public Product(String name, int stock, double cost, double price, boolean has_iva, int companies_id, Set<Variation> variation) {
        this.name = name;
        this.stock = stock;
        this.cost = cost;
        this.price = price;
        this.has_iva = has_iva;
        this.companies_id = companies_id;
        this.variations = variation;
    }

    public Product(String name, int stock, double cost, double price, boolean has_iva, Set<Variation> variation) {
        this.name = name;
        this.stock = stock;
        this.cost = cost;
        this.price = price;
        this.has_iva = has_iva;
        this.variations = variation;
    }

}
