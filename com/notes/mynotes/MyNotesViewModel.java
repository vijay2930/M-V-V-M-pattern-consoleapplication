package com.notes.mynotes;

import com.notes.dto.Notes;
import com.notes.repository.Repository;

import java.util.List;

public class MyNotesViewModel {
    private MyNotesView myNotesView;
    private Repository repository;
    public MyNotesViewModel(MyNotesView myNotesView) {
        this.myNotesView =myNotesView;
        this.repository=Repository.getInstance();
    }

    public void deleteAllNotes(String user, String folderName) {
        List<Notes> notes=repository.deleteAllNotes(user,folderName);
        if(notes.isEmpty()){
            myNotesView.deletedAllNotesSuccessfully();
        }else {
            myNotesView.deletionOfAllNotesFailed("Something went wrong.Try again");
        }
    }

    public void getAllNotesNameForCreating(String user, String folderName) {
        List<String> notesTitle=repository.getAllNotesName(user,folderName);
        myNotesView.getNewNotesDetails(notesTitle);
    }

    public void addNewNotes(String user, String folderName, Notes notes) {
        Notes note=repository.addNewNotes(user,folderName,notes);
        if(note==null){
            myNotesView.addNewNotesFailed("Something went wrong.Try again");
        }else {
            myNotesView.newNotesAddedSuccessfully();
        }
    }

    public void getAllNotesNameForUpdating(String user, String folderName) {
        List<String> notesTitle=repository.getAllNotesName(user,folderName);
        myNotesView.getUpdateNotesDetail(notesTitle);
    }

    public void getUpdatingNotes(String user, String folderName, String title) {
        Notes notes=repository.getNotes(user,folderName,title);
        if(notes==null){
            myNotesView.getUpdatingNotesFailed("Something went wrong.Try again");
        }else {
            myNotesView.getUpdateNotesContent(notes);
        }
    }

    public void updateNotes(String user, String folderName, Notes updateNotes, Notes notes) {
        Notes updatednotes=repository.updateNotes(user,folderName,updateNotes,notes);
        if(updatednotes==null){
            myNotesView.updateNotesFailed("Something went wrong.Try again");
        }else {
            myNotesView.updateNotesSuccessfully();
        }
    }

    public void getAllNotesNameForViewing(String user, String folderName) {
        List<String> notesList=repository.getAllNotesName(user,folderName);
        myNotesView.getViewNotesDetails(notesList);
    }

    public void getViewNotes(String user, String folderName, String notesTitle) {
        Notes notes=repository.getNotes(user,folderName,notesTitle);
        if(notes==null){
            myNotesView.getViewNotesFailed("Something went wrong");
        }else {
            myNotesView.getViewNotesSuccessfully(notes);
        }
    }

    public void getAllNotesNameForDeleting(String user, String folderName) {
        List<String> notesList=repository.getAllNotesName(user,folderName);
        myNotesView.getDeletingNotesDetail(notesList);
    }

    public void deleteNotes(String user, String folderName, String notesTitle) {
        Notes notes=repository.deleteNotes(user,folderName,notesTitle);
        if(notes==null){
            myNotesView.deleteNotesFailed("Something went wrong");
        }else {
            myNotesView.deleteNotesSuccessfully();
        }
    }
}
