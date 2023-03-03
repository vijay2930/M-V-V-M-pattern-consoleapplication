package com.notes.dto;

import java.util.List;

public class Notes {
    private String title;
    private List<String> content;
    private String dateOfCreation;
    private String modifiedDate;
    public Notes(){}
    public Notes(String title, List<String> content,String dateOfCreation) {
        this.title = title;
        this.content = content;
        this.dateOfCreation=dateOfCreation;
        this.modifiedDate=dateOfCreation;
    }

    public String getTitle() {
        return title;
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(String dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public List<String> getContent() {
        return content;
    }
    public void setContent(List<String> content) {
        this.content = content;
    }
    public void display(){
        System.out.println("------------------------------------------------------");
        System.out.println("Created Date: "+this.getDateOfCreation());
        System.out.println("Last date Of Accessed:"+this.getModifiedDate());
        System.out.println("Title :"+this.getTitle());
        System.out.println("Content:");
        for (String str:this.getContent()) {
            System.out.println(str);
        }
        System.out.println("-------------------------------------------------------");
    }
    @Override
    public String toString() {
        return "Notes{" +
                "\n title='" + title + '\'' +
                ", Content='" + content +"\n}";
    }

}
