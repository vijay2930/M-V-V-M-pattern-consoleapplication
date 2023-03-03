package com.notes.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Folder {
    private String name;
    private String dateOfCreation;

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

    private String modifiedDate;
    private List<Notes> notes;
    public Folder(){}
    public Folder(String name,String dateOfCreation) {
        this.name = name;
        this.dateOfCreation=dateOfCreation;
        this.modifiedDate=dateOfCreation;
        this.notes = new ArrayList<>();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Notes> getNotes() {
        return notes;
    }

    public void setNotes(List<Notes> notes) {
        this.notes = notes;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Folder folder = (Folder) o;
        return Objects.equals(name, folder.name) && Objects.equals(notes, folder.notes);
    }
}
