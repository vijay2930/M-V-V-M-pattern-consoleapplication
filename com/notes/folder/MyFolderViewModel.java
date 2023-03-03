package com.notes.folder;

import com.notes.dto.Folder;
import com.notes.repository.Repository;

import java.util.List;

public class MyFolderViewModel {
    private MyFolderView myFolderView;
    private Repository repository;
    public MyFolderViewModel(MyFolderView myFolderView) {
        this.myFolderView =myFolderView;
        this.repository=Repository.getInstance();
    }
    public void createNewFolder(String user, String folderName) {
        Folder myFolder=repository.createFolder(user,folderName);
        if(myFolder==null){
            myFolderView.newFolderCreationFailed("Folder Name Already Exits.Try Another name.");
        }else {
            myFolderView.newFolderCreatedSuccessfully(myFolder.getName());
        }
    }

    public void getUpdateFolderNames(String user) {
        List<String> folderNames=repository.getFolderNames(user);
        myFolderView.getNewFolderName(folderNames);
    }

    public void updateFolderName(String user, String oldFolderName, String newFolderName) {
        Folder myFolder=repository.updateFolderName(user,oldFolderName,newFolderName);
        if(myFolder==null){
            myFolderView.updateFolderNameFailed("Something went wrong.");
        }else {
            myFolderView.folderNameUpdatedSuccessfully(myFolder.getName());
        }
    }

    public void getDeleteFolderNames(String user) {
        List<String> folderNames=repository.getFolderNames(user);
        myFolderView.getDeleteFolderName(folderNames);
    }

    public void deleteFolder(String user, String folderName) {
        Folder myFolder=repository.deleteFolder(user,folderName);
        if(myFolder==null){
            myFolderView.folderDeletionFailed("Something went Wrong.");
        }else {
            myFolderView.folderDeletedSuccessfully(myFolder.getName());
        }

    }

    public void viewAllFolders(String user) {
        List<Folder> folders=repository.getFolders(user);
        myFolderView.listAllFolders(folders);
    }

    public void deleteAllFolders(String user) {
        List<Folder> folders=repository.deleteAllFolders(user);
        if(folders.isEmpty()){
            myFolderView.deleteAllFoldersSuccessfully();
        }else {
            myFolderView.deleteAllFoldersFailed("Something went wrong");
        }
    }

    public void getSelectedFolderNames(String user) {
        List<String> folders=repository.getFolderNames(user);
        myFolderView.getSelectedFolderName(folders);
    }
}
