package org.tsimcsheertest.test.Templates;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.tsimcsheertest.test.Constants.LearningComponentType;
import org.tsimcsheertest.test.Models.UserLearning;

@JsonPropertyOrder(value = {"id", "name", "type", "assignedDates", "userStatus", "imageUrl"})
public class MyLearningResponse {
    public Long id;
    public String name;
    public LearningComponentType type;
    public AssignedDates assignedDates;
    public String userStatus;
    public String imageUrl;

    public MyLearningResponse(UserLearning userLearning, AssignedDates assignedDates){
        this.id = userLearning.getComponent().getId();
        this.name = userLearning.getComponent().getName();
        this.type = userLearning.getComponent().getType();
        this.assignedDates = assignedDates;
        this.userStatus = userLearning.getStatus().getName();
        this.imageUrl = userLearning.getComponent().getImageUrl();
    }
}
