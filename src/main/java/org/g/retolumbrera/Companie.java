package org.g.retolumbrera;


import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Companie extends PanacheEntity {

    public int id;
    public String name;

    @OneToMany(mappedBy = "companies_id", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    public List<Product> products;

    public Companie(){
    }

    public Companie(int id, String name){
        this.id = id;
        this.name = name;
    }
}
