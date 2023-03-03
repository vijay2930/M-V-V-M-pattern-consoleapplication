package com.notes.folder;

import com.notes.dto.Folder;
import com.notes.login.LoginView;
import com.notes.mynotes.MyNotesView;
import com.notes.util.Check;
import com.notes.util.Read;

import java.util.List;

public class MyFolderView {
    private final String user;
    private MyFolderViewModel myFolderViewModel;

    public MyFolderView(String user) {
        myFolderViewModel = new MyFolderViewModel(this) {
        };
        this.user = user;
    }

    public void init() {
        this.start();
    }

    private void start() {
        System.out.println("\nMy Folder Page");
        System.out.println("-------------");
        System.out.println("1. Create Folder");
        System.out.println("2. Edit Folder Name");
        System.out.println("3. Delete Folder");
        System.out.println("4. View All Folder");
        System.out.println("5. Delete All Folder");
        System.out.println("6. Select Folder");
        System.out.println("7. Logout");
        int choice=0;
        try{
            choice = Read.changeToInt(Read.input("Enter your Choice: "));
        }catch (NumberFormatException e){}
        this.checkValidChoice(choice);
    }
    private void checkValidChoice(int choice) {
        switch (choice) {
        case 7:
            this.logout();
        case 1:
            this.createNewFolder();
//            code here
            break;
        case 2:
            myFolderViewModel.getUpdateFolderNames(user);
//            this.getUpdateNotesDetail();
        case 3:
            myFolderViewModel.getDeleteFolderNames(user);
//            this.getViewNotesDetail();
        case 4:
            myFolderViewModel.viewAllFolders(user);
        case 5:
            this.getConfirmOnDeleteAllFolder();
        case 6:
            myFolderViewModel.getSelectedFolderNames(user);
        default:
            System.out.println("Please enter only the Valid Choice...");
            this.init();
        }
    }

    private void getConfirmOnDeleteAllFolder() {
        String confirm=Read.input("Enter [CONFIRM](in full caps) to Proceed: ");
        if(confirm.equals("CONFIRM")){
            myFolderViewModel.deleteAllFolders(user);
        }else {
            System.out.println("Cancel Deleting All Folders");
            System.out.println("Returning to the My Folder Page");
        }
    }


    private void createNewFolder() {
        System.out.println("\nCreate New Folder");
        System.out.println("-----------------");
        String newFolderName = Read.input("Enter the Folder Name: ");
        myFolderViewModel.createNewFolder(user, newFolderName);
    }

    private void logout() {
        System.out.println("Logout Successfully.");
        new LoginView().init();
    }

    public void newFolderCreationFailed(String msg) {
        System.out.println("\nCan't Create New Folder");
        System.out.println(msg);
        System.out.println("Returning to the My Folders Page.");
        this.init();
    }

    public void newFolderCreatedSuccessfully(String folderName) {
        System.out.println("\nFolder Created Successfully");
        System.out.println("Folder Name: " + folderName);
        System.out.println("Returning to the My Folders Page");
        this.init();
    }
    public void getNewFolderName(List<String> folderNames) {
        System.out.println("\nUpdate Folder Name Page");
        System.out.println("-----------------------");
        if(folderNames.isEmpty()){
            System.out.println("You haven't Created any folder");
            System.out.println("Returning to the My Folder Page");
            this.init();
        }
        System.out.println("The Folder are "+folderNames);
        String oldFolderName,newFolderName;
        while (true) {
            oldFolderName = Read.input("Enter the Folder Name: ");
            System.out.println(Check.isMatchFound(oldFolderName,folderNames));
            if (Check.isMatchFound(oldFolderName, folderNames)) {
                break;
            } else {
                System.out.println("\nNo Match found");
            }
        }
        while (true) {
            newFolderName = Read.input("Enter the new Folder Name: ");
            if (Check.isMatchFound(newFolderName, folderNames)) {
                System.out.println("\nFile Name Already exits try another name.");
            } else {
                break;
            }
        }
        myFolderViewModel.updateFolderName(user,oldFolderName,newFolderName);
    }

    public void updateFolderNameFailed(String msg) {
        System.out.println("Update Folder Name Failed");
        System.out.println(msg);
        this.init();
    }

    public void folderNameUpdatedSuccessfully(String name) {
        System.out.println("\nFolder Name Updated Successfully");
        System.out.println("Folder Name: " + name);
        System.out.println("Returning to the My Folders Page");
        this.init();
    }

    public void getDeleteFolderName(List<String> folderNames) {
        System.out.println("\nDelete Folder Page");
        System.out.println("------------------");
        if(folderNames.isEmpty()){
            System.out.println("You haven't Created any folder");
            System.out.println("Returning to the My Folder Page");
            this.init();
        }
        System.out.println("The Folder are "+folderNames);
        while (true){
            String folderName=Read.input("Enter Folder Name:");
            if(Check.isMatchFound(folderName,folderNames)){
                myFolderViewModel.deleteFolder(user,folderName);
                break;
            }else {
                System.out.println("No Match found");
            }
        }
    }
    public void folderDeletionFailed(String msg) {
        System.out.println("\nFolder Deletion Failed");
        System.out.println(msg);
        System.out.println("Returning to the My Folder Page");
        this.init();
    }
    public void folderDeletedSuccessfully(String name) {
        System.out.println("\nFolder Deleted Successfully");
        System.out.println("Returning to the My Folder Page.");
        this.init();
    }
    public void listAllFolders(List<Folder> folders) {
        System.out.println("\nList All Folder Page");
        System.out.println("--------------------");
        if(folders.isEmpty()){
            System.out.println("Nothing to Show");
        }else {
            for (Folder folder:folders) {
                System.out.println("Folder Name: "+folder.getName()+" It Contains "+folder.getNotes().size()+" Notes");
            }
        }
        System.out.println("Returning to the My Folder Page");
        this.init();
    }
    public void deleteAllFoldersFailed(String msg) {
        System.out.println("Delete All Folders Failed");
        System.out.println(msg);
        System.out.println("Returning to the My Folders Page");
        this.init();
    }
    public void deleteAllFoldersSuccessfully() {
        System.out.println("All Folders Deleted SuccessFully");
        System.out.println("Returning to the My Folders Page");
        this.init();
    }

    public void getSelectedFolderName(List<String> folders) {
        System.out.println("\nSelect Folder Page");
        System.out.println("------------------");
        if(folders.isEmpty()){
            System.out.println("You haven't Created any folder");
            System.out.println("Returning to the My Folders page");
            this.init();
        }
        System.out.println("The Folders are "+folders);
        while (true){
            String folderName=Read.input("Enter the Folder name: ");
            if(Check.isMatchFound(folderName,folders)){
                new MyNotesView(user,folderName).init();
            }
            System.out.println("You hava enter the Invalid Folder Name:");
        }
    }
}
