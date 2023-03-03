package com.notes.mynotes;

import com.notes.dto.Notes;
import com.notes.folder.MyFolderView;
import com.notes.login.LoginView;
import com.notes.util.Check;
import com.notes.util.Read;

import java.util.List;

public class MyNotesView {
    private final String user;
    private final String folderName;
    private MyNotesViewModel myNotesViewModel;
    public MyNotesView(String user, String folderName) {
        myNotesViewModel =new MyNotesViewModel(this);
        this.user=user;
        this.folderName=folderName;
    }
    public void init() {
        this.start();
    }
    private void start() {
        System.out.println("\nMy Notes Page");
        System.out.println("-------------");
        System.out.println("1. Add Notes");
        System.out.println("2. Edit Notes");
        System.out.println("3. View Notes");
        System.out.println("4. Delete Notes");
        System.out.println("5. Delete All Notes");
        System.out.println("6. Go Back");
        System.out.println("7. Logout");
        int choice=0;
        try{
            choice= Read.changeToInt(Read.input("Enter your Choice: "));
        }catch (Exception e){}
        this.checkValidChoice(choice);
    }
    private void checkValidChoice(int choice) {
        switch (choice){
        case 1:
            myNotesViewModel.getAllNotesNameForCreating(this.user,this.folderName);
        case 2:
            myNotesViewModel.getAllNotesNameForUpdating(this.user,this.folderName);
        case 3:
            myNotesViewModel.getAllNotesNameForViewing(this.user,this.folderName);
        case 4:
            myNotesViewModel.getAllNotesNameForDeleting(this.user,this.folderName);
        case 5:
            this.getConfirmationOnDeleteAllNotes();
        case 6:
            this.goBack();
        case 7:
            this.logout();
        default:
            System.out.println("Please enter only the Valid Choice...");
            this.init();
        }
    }

    private void getConfirmationOnDeleteAllNotes() {
        System.out.println("Delete All Notes Page");
        System.out.println("---------------------");
        String confirm=Read.input("Enter [CONFIRM](in full caps) to proceed: ");
        if(confirm.equals("CONFIRM")){
            myNotesViewModel.deleteAllNotes(this.user,this.folderName);
        }else {
            System.out.println("Abort Deleting All Notes");
            System.out.println("Returning to My Notes Page");
            this.init();
        }
    }


    private void goBack() {
        new MyFolderView(this.user);
    }

    private void logout() {
        new LoginView().init();
    }

    public void deletedAllNotesSuccessfully() {
        System.out.println("All Notes Deleted Successfully.");
        System.out.println("Returning to the My Notes Page");
        this.init();
    }

    public void deletionOfAllNotesFailed(String msg) {
        System.out.println("All Notes Deletion Failed.");
        System.out.println(msg);
        System.out.println("Returning to the My Notes Page");
        this.init();
    }

    public void getNewNotesDetails(List<String> notesTitle) {
        System.out.println("Add New Notes Page");
        String title;
        while (true){
            title=Read.input("Enter Notes Title: ");
            if(!Check.isMatchFound(title,notesTitle)){
                break;
            }
        }
        List<String> content=Read.getContent("Enter Content: ");
        myNotesViewModel.addNewNotes(this.user,this.folderName , new Notes(title,content,Read.getCurrentDate()));
    }

    public void newNotesAddedSuccessfully() {
        System.out.println("New Notes Added Successfully");
        System.out.println("Returning to the My Notes Page");
        this.init();
    }

    public void addNewNotesFailed(String msg) {
        System.out.println("New Notes insertion Failed");
        System.out.println(msg);
        System.out.println("Returning to the My Notes Page");
        this.init();
    }

    public void getUpdateNotesDetail(List<String> notesTitle) {
        System.out.println("\nUpdate Notes Page");
        System.out.println("-----------------");
        if(notesTitle.isEmpty()){
            System.out.println("This Folder is Empty");
            System.out.println("Returning to the My Notes Page");
            this.init();
        }
        System.out.println("If you don't want to edit any file then type [#None?]");
        System.out.println("The Notes are "+notesTitle);
        while (true){
            String title=Read.input("Enter the title: ");
            if(title.equals("#None?")){
                System.out.println("Returning to the My Notes Page");
                this.init();
            }else if(Check.isMatchFound(title,notesTitle)){
                myNotesViewModel.getUpdatingNotes(this.user,this.folderName,title);
            }else {
                System.out.println("No Match found.Try again...");
            }
        }

    }

    public void getUpdatingNotesFailed(String msg) {
        System.out.println("Failed to get the Updating Notes");
        System.out.println(msg);
        System.out.println("Returning to the My Notes Page");
        this.init();
    }

    public void getUpdateNotesContent(Notes notes) {
        System.out.println("Update Notes Title and Content");
        String title=Read.inputWithDefault("Enter the New Title or press Enter:",notes.getTitle());
        List<String> content=Read.getUpdateContent(notes.getContent());
        myNotesViewModel.updateNotes(this.user,this.folderName,new Notes(title,content,Read.getCurrentDate()),notes);
    }

    public void updateNotesFailed(String msg) {
        System.out.println("Update Notes Failed");
        System.out.println(msg);
        System.out.println("Returning to the My Notes Page");
        this.init();
    }

    public void updateNotesSuccessfully() {
        System.out.println("Update Notes Successfully");
        System.out.println("Returning to the My Notes Page.");
        this.init();
    }

    public void getViewNotesDetails(List<String> notesList) {
        System.out.println("\nView Notes Page");
        System.out.println("---------------");
        if(notesList.isEmpty()){
            System.out.println("The folder is Empty");
            System.out.println("Returning to the My Notes Page");
            this.init();
        }
        System.out.println("The Notes are "+notesList);
        while (true){
            String notesTitle=Read.input("Enter the Note title: ");
            if(Check.isMatchFound(notesTitle,notesList)){
                myNotesViewModel.getViewNotes(user,folderName,notesTitle);
            }
            System.out.println("You have enter the wrong file name.Try again");
        }
    }

    public void getViewNotesFailed(String msg) {
        System.out.println("Failed to get View Notes");
        System.out.println(msg);
        System.out.println("Returning to the My Notes Page");
        this.init();
    }

    public void getViewNotesSuccessfully(Notes notes) {
        System.out.println("View Notes get Successfully");
        notes.display();
        System.out.println("Returning to the My Notes Page");
    }

    public void getDeletingNotesDetail(List<String> notesList) {
        System.out.println("\nDelete Notes Page");
        System.out.println("-----------------");
        if(notesList.isEmpty()){
            System.out.println("This folder is Empty");
            System.out.println("Returning to the My Notes Page");
            this.init();
        }
        System.out.println(notesList);
        while (true){
            String notesTitle=Read.input("Enter the Notes Title: ");
            if(Check.isMatchFound(notesTitle,notesList)){
                this.getConfirmationOnDeleteNotes(notesTitle);
            }
            System.out.println("No Match found.");
        }
    }

    public void deleteNotesFailed(String msg) {
        System.out.println("Notes Deletion Failed");
        System.out.println(msg);
        System.out.println("Returning to the My Notes Page");
        this.init();
    }

    public void deleteNotesSuccessfully() {
        System.out.println("Notes Deleted Successfully");
        System.out.println("Returning to the My Notes Page");
        this.init();
    }

    private void getConfirmationOnDeleteNotes(String notesTitle) {
        String confirm=Read.input("Enter [CONFIRM](in full caps) to proceed: ");
        if(confirm.equals("CONFIRM")){
            myNotesViewModel.deleteNotes(user,folderName,notesTitle);
        }
        System.out.println("Abort Deleting Notes");
        System.out.println("Returning to the Notes Page.");
    }
}
