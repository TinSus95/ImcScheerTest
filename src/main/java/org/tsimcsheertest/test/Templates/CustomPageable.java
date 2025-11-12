package org.tsimcsheertest.test.Templates;

public class CustomPageable {
    public int pageNum;
    public int pageSize;
    public long total;

    public CustomPageable(int pageNum, int pageSize, long total){
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
    }
}
