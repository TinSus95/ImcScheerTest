package org.tsimcsheertest.test.Models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Cacheable
public class MetaTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @ManyToMany (mappedBy = "metaTags")
    private Set<LearningComponent> learningComponents;

    public long getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public Set<LearningComponent> getLearningComponents(){
        return this.learningComponents;
    }

    public void setId(long id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setLearningComponents(Set<LearningComponent> learningComponents){
        this.learningComponents = learningComponents;
    }
}
