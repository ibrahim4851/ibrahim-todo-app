package com.felece.todoapp.dto;

import java.util.Date;

public class FilterTodoDto {

    private Date date1;
    private Date date2;

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    @Override
    public String toString() {
        return "FilterTodoDto{" +
                "date1=" + date1 +
                ", date2=" + date2 +
                '}';
    }
}
