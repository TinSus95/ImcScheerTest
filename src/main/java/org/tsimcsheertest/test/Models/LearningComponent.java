package org.tsimcsheertest.test.Models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class LearningComponent {
    @Id
    private String id;

    @Column
    private String name;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private LearningComponentType type;

    @ManyToMany
    @JoinTable(
            name = "component_tag",
            joinColumns = @JoinColumn(name = "component_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<MetaTag> metaTags;

    @Column
    private String imageUrl;

    @Column
    private long duration;

    @Column
    private String category;

    @OneToMany(mappedBy = "component")
    private Set<UserLearning> subscribedUser;

    public String getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public LearningComponentType getType(){
        return this.type;
    }

    public String getImageUrl(){
        return this.imageUrl;
    }

    public Set<MetaTag> getMetaTags(){
        return this.metaTags;
    }

    public String getCategory(){
        return this.category;
    }

    public Set<UserLearning> getSubscribedUser(){
        return this.subscribedUser;
    }

    public long getDuration(){
        return this.duration;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setType(LearningComponentType type){
        this.type = type;
    }

    public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }

    public void setMetaTags(Set<MetaTag> metaTags){
        this.metaTags = metaTags;
    }

    public void setDuration(long duration){
        this.duration = duration;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public void setSubscribedUser(Set<UserLearning> subscribedUser){
        this.subscribedUser = subscribedUser;
    }
}
