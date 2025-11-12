package org.tsimcsheertest.test.Templates;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AssignedDates {
    @Nullable
    public String startDate;
    @Nullable
    public String endDate;

    public AssignedDates(){
        this.startDate = null;
        this.endDate = null;
    }

    public AssignedDates(Date startDate, Date endDate){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if(Objects.isNull(startDate)){
            this.startDate = null;
        }
        else{
            this.startDate = dateFormat.format(startDate);
        }
        if(Objects.isNull(endDate)){
            this.endDate = null;
        }
        else{
            this.endDate = dateFormat.format(endDate);
        }
    }

    public AssignedDates(Date startDate){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if(Objects.isNull(startDate)){
            this.startDate = null;
        }
        else{
            this.startDate = dateFormat.format(startDate);
        }
    }
}
