package org.tsimcsheertest.test.Templates;

import org.tsimcsheertest.test.Models.LearningComponentType;
import org.tsimcsheertest.test.Models.UserLearning;
import org.tsimcsheertest.test.Models.UserStatus;

public class MyLearningResponse {
    public String id;
    public String name;
    public String type;
    public AssignedDates assignedDates;
    public String userStatus;
    public String imageUrl;

    public MyLearningResponse(String id, String name, String type, AssignedDates assignedDates,
                              String userStatus, String imageUrl){
        this.id = id;
        this.name = name;
        this.type = type;
        this.assignedDates = assignedDates;
        this.userStatus = userStatus;
        this.imageUrl = imageUrl;
    }

    public MyLearningResponse(String id, String name, LearningComponentType type, AssignedDates assignedDates,
                              UserStatus userStatus, String imageUrl){
        this.id = id;
        this.name = name;
        this.type = type.getName();
        this.assignedDates = assignedDates;
        this.userStatus = userStatus.getName();
        this.imageUrl = imageUrl;
    }

    public MyLearningResponse(UserLearning userLearning, AssignedDates assignedDates){
        this.id = userLearning.getComponent().getId();
        this.name = userLearning.getComponent().getName();
        this.type = userLearning.getComponent().getType().getName();
        this.assignedDates = assignedDates;
        this.userStatus = userLearning.getStatus().getName();
        this.imageUrl = userLearning.getComponent().getImageUrl();
    }
}
