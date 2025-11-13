package org.tsimcsheertest.test.Templates;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.tsimcsheertest.test.Constants.LearningComponentType;
import org.tsimcsheertest.test.Models.LearningComponentCategory;
import org.tsimcsheertest.test.Models.MetaTag;
import org.tsimcsheertest.test.Models.UserLearning;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@JsonPropertyOrder(value = {"id", "name", "description", "type", "assignedDates", "userStatus", "metaTags", "category", "duration", "imageUrl"})
public class LearningComponentDetails {
    public Long id;
    public String name;
    public String description;
    public LearningComponentType type;
    public AssignedDates availableDates;
    public String userStatus;
    public String imageUrl;
    public List<String> metaTags;
    public String duration;
    public String category;

    public LearningComponentDetails(UserLearning userLearning, AssignedDates assignedDates){
        this.id = userLearning.getComponent().getId();
        this.name = userLearning.getComponent().getName();
        this.type = userLearning.getComponent().getType();
        this.description = userLearning.getComponent().getDescription();
        this.availableDates = assignedDates;
        this.userStatus = userLearning.getStatus().getName();
        this.imageUrl = userLearning.getComponent().getImageUrl();
        this.duration = String.format("%d hours", userLearning.getComponent().getDuration());
        this.category = userLearning.getComponent().getCategory().getName();
        this.metaTags = new ArrayList<String>();

        Iterator<MetaTag> iterator = userLearning.getComponent().getMetaTags().iterator();

        while(iterator.hasNext()){
            this.metaTags.add(iterator.next().getName());
        }
    }
}
