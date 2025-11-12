package org.tsimcsheertest.test.Templates;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.Page;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomPage<T>{
    @Nullable
    public List<T> data;
    @Nullable
    public CustomPageable pagination;
    @Nullable
    public String error;
    @Nullable
    public T details;

    public CustomPage(Page<T> page){
        this.data = page.getContent();
        this.pagination = new CustomPageable(page.getPageable().getPageNumber(),
                page.getPageable().getPageSize(),
                page.getTotalPages());
    }

    public CustomPage(List<T> list){
        this.data = list;
        this.pagination = null;
    }

    public CustomPage(List<T> list, Integer pageNum, Integer pageSize, Integer total){
        this.data = list;
        this.pagination = new CustomPageable(pageNum, pageSize, total);
    }

    public CustomPage(String errorMessage){
        this.error = errorMessage;
    }

    public CustomPage(T obj){
        this.details = obj;
    }
}
