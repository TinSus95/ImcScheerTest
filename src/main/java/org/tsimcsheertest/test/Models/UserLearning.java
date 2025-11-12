package org.tsimcsheertest.test.Models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Optional;

@Entity
public class UserLearning {
    @EmbeddedId
    private UserLearningKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("componentId")
    @JoinColumn(name = "component_id")
    private LearningComponent component;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private UserStatus status;

    @Column
    private Date startDate;

    @Column
    private Date endDate;

    public User getUser(){
        return this.user;
    }

    public LearningComponent getComponent(){
        return this.component;
    }

    public UserStatus getStatus(){
        return this.status;
    }

    public UserLearningKey getId(){
        return this.id;
    }

    public Optional<Date> getStartDate(){
        return Optional.ofNullable(this.startDate);
    }

    public Optional<Date> getEndDate(){
        return Optional.ofNullable(this.endDate);
    }

    public void setUser(User user){
        this.user = user;
    }

    public void setComponent(LearningComponent component){
        this.component = component;
    }

    public void setStatus(UserStatus status){
        this.status = status;
    }

    public void setId(UserLearningKey id){
        this.id = id;
    }

    public void setStartDate(Optional<Date> startDate){
        this.startDate = startDate.orElse(null);
    }

    public void setEndDate(Optional<Date> endDate){
        this.endDate = endDate.orElse(null);
    }
}
