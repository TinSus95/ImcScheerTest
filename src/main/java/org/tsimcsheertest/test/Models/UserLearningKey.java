package org.tsimcsheertest.test.Models;

import jakarta.persistence.*;
import java.io.Serializable;

@Embeddable
public class UserLearningKey implements Serializable {
    @Column(name = "user_id")
    public Long userId;

    @Column(name = "component_id")
    public String componentId;

    public Long getUserId() {
        return this.userId;
    }

    public String getComponentId(){
        return componentId;
    }

    public void setUserId(Long id){
        this.userId = id;
    }

    public void setComponentId(String id){
        this.componentId = id;
    }

    public int hashCode() {
        return (int)(this.userId.intValue() + this.getAsciiSumValue(this.componentId));
    }

    /* Higher change of overflow
    public int hashCode() {
        return (int)(this.userId.intValue() + this.getAsciiConcatValue(this.componentId));
    }
     */

    public int getAsciiSumValue(String input){
        int total = 0;
        for(int i=0;i<input.length();i++){
            char ch = input.charAt(i);
            total = total + (int)ch;
        }
        return total;
    }

    public int getAsciiConcatValue(String input){
        String full = "";
        for(int i=0;i<input.length();i++){
            char ch = input.charAt(i);
            full.concat(Integer.toString((int)ch));
        }
        return Integer.parseInt(full);
    }

    public boolean equals(Object object) {
        if (object instanceof UserLearningKey) {
            UserLearningKey otherId = (UserLearningKey) object;
            return (otherId.getUserId() == this.userId)
                    && (otherId.getComponentId().equals(this.getComponentId()));
        }
        return false;
    }
}
