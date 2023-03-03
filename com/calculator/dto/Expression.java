package com.calculator.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.SplittableRandom;

public class Expression {
    private String time;
    private String exp;
    private String res;

    public Expression() {
    }

    public Expression(String exp, String result, String time) {
        this.exp=exp;
        this.res=result;
        this.time=time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public void display() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Time: "+this.time);
        System.out.println("Expression: "+this.exp);
        System.out.println("Result:"+this.res);
        System.out.println("---------------------------------------------------------------");
    }
}
