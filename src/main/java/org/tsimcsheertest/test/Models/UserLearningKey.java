package org.tsimcsheertest.test.Models;

import jakarta.persistence.*;
import java.io.Serializable;

@Embeddable
public class UserLearningKey implements Serializable {
    @Column(name = "user_id")
    public Long userId;

    @Column(name = "component_id")
    public Long componentId;

    public Long getUserId() {
        return this.userId;
    }

    public Long getComponentId(){
        return componentId;
    }

    public void setUserId(Long id){
        this.userId = id;
    }

    public void setComponentId(Long id){
        this.componentId = id;
    }

    //  Limits integer size to total 8 digits between both IDs
    //  For Int (Long) type component ID
    public int hashCode() {
        return Integer.parseInt(Integer.toString(this.userId.intValue()).concat("0").concat(Integer.toString(this.componentId.intValue())));
    }

    /* If we wish to use more than total of 8 digits between both IDs, use following hasCode method, which isn't as precise with its hash, but allows for greater data input range
    //  For Int (Long) type component ID
    public int hashCode() {
        return this.userId.intValue()+this.componentId.intValue();
    }
     */

    /* Used before transferred LearningComponent ID from String to long
    //  For String type component ID
    public int hashCode() {
        return (int)(this.userId.intValue() + this.getAsciiSumValue(this.componentId));
    }
     */

    /* Higher change of overflow
    //  For String type component ID
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

    /* Used before transferred LearningComponent ID from String to long
    //  For String type component ID
    public boolean equals(Object object) {
        if (object instanceof UserLearningKey) {
            UserLearningKey otherId = (UserLearningKey) object;
            return (otherId.getUserId() == this.userId)
                    && (otherId.getComponentId().equals(this.getComponentId()));
        }
        return false;
    }
     */

    //  For Int (Long) type component ID
    public boolean equals(Object object) {
        if (object instanceof UserLearningKey) {
            UserLearningKey otherId = (UserLearningKey) object;
            return (otherId.getUserId() == this.userId)
                    && (otherId.getComponentId() == this.componentId);
        }
        return false;
    }
}
