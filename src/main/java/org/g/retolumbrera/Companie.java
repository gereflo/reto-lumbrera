package org.g.retolumbrera;


import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.Entity;

@Entity
public class Companie extends PanacheEntity {

    public int id;
    public String name;

    public Companie(){
    }

    public Companie(int id, String name){
        this.id = id;
        this.name = name;
    }
}
