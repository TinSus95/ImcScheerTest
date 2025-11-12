package org.tsimcsheertest.test.Models;

import jakarta.persistence.*;

@Entity
@Cacheable
public class UserStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    public long getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public void setId(long id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }
}
