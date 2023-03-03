package com.calculator.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class History {
    private String date;
    private List<Expression> expressionList;
    public History(){

    }

    public History(String date) {
        this.date=date;
        this.expressionList=new ArrayList<>();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Expression> getExpressionList() {
        return expressionList;
    }

    public void setExpressionList(List<Expression> expressionList) {
        this.expressionList = expressionList;
    }
}
