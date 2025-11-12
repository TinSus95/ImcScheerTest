package org.tsimcsheertest.test.Templates;

import org.tsimcsheertest.test.Models.MetaTag;
import org.tsimcsheertest.test.Models.UserLearning;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LearningComponentDetails {
    public String id;
    public String name;
    public String description;
    public String type;
    public AssignedDates availableDates;
    public String userStatus;
    public String imageUrl;
    public List<String> metaTags;
    public String duration;
    public String category;

    public LearningComponentDetails(UserLearning userLearning, AssignedDates assignedDates){
        this.id = userLearning.getComponent().getId();
        this.name = userLearning.getComponent().getName();
        this.type = userLearning.getComponent().getType().getName();
        this.description = userLearning.getComponent().getDescription();
        this.availableDates = assignedDates;
        this.userStatus = userLearning.getStatus().getName();
        this.imageUrl = userLearning.getComponent().getImageUrl();
        this.duration = String.format("%d hours", userLearning.getComponent().getDuration());
        this.category = userLearning.getComponent().getCategory();
        this.metaTags = new ArrayList<String>();

        Iterator<MetaTag> iterator = userLearning.getComponent().getMetaTags().iterator();

        while(iterator.hasNext()){
            this.metaTags.add(iterator.next().getName());
        }
    }
}
