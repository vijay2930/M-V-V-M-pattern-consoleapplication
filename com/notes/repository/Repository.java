package com.notes.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.notes.dto.Folder;
import com.notes.dto.Notes;
import com.notes.dto.User;
import com.notes.util.Read;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Repository {
    private static Repository repository;
    private Data data;
    private Map<String,User> userList=new HashMap<>();
    private Repository(){
        this.readFile();
    }
    public static Repository getInstance(){
        if(repository==null){
            repository=new Repository();
        }
        return repository;
    }
    private void readFile() {
        try{
            ObjectMapper mapper=new ObjectMapper();
            data=(Data) mapper.readValue(new File("src\\com\\notes" +
                    "\\repository\\data.json").getAbsoluteFile(),
                    Data.class);
            for(User user:data.getUsers()){
                this.userList.put(user.getEmail(),user);
            }
        }catch (Exception e){
            System.out.println(e);
//            e.printStackTrace();
        }
    }
    private void writeFile(){
        try{
            ObjectMapper mapper=new ObjectMapper();
            mapper.writeValue(new File("src\\com\\notes" +
                    "\\repository\\data.json").getAbsoluteFile(),data);
        }catch (Exception e){e.printStackTrace();}
    }
    public void save(){this.writeFile();}
    public User getUser(String email, String password) {
        if(this.userList.containsKey(email) && this.userList.get(email).getPassword().equals(password)){
            return this.userList.get(email);
        }
        return null;
    }
    public User addUser(User user) {
        if(this.userList.containsKey(user.getEmail())){
            return null;
        }else {
            this.userList.put(user.getEmail(),user);
            data.getUsers().add(user);
            return this.userList.get(user.getEmail());
        }
    }

    public Folder getFolder(String user, String folderName) {
        for (Folder folder:this.userList.get(user).getFolders()) {
            if(folder.getName().equals(folderName)){
                return folder;
            }
        }
        return null;
    }
    public List<String> getFolderNames(String user){
        List<String> folderNames=new ArrayList<>();
        for (Folder folder:this.userList.get(user).getFolders()) {
            folderNames.add(folder.getName());
        }
        return folderNames;
    }
    public Folder createFolder(String user, String name) {
        Folder myFolder=getFolder(user,name);
        if(myFolder==null){
            myFolder=new Folder(name,Read.getCurrentDate());
            this.userList.get(user).getFolders().add(myFolder);
            return myFolder;
        }
        return null;
    }

    public Folder updateFolderName(String user, String oldFolderName, String newFolderName) {
        for(Folder folder:this.userList.get(user).getFolders()){
            if(folder.getName().equals(oldFolderName)){
                folder.setName(newFolderName);
                folder.setModifiedDate(Read.getCurrentDate());
                return folder;
            }
        }
        return null;
    }

    public Folder deleteFolder(String user, String folderName) {
        for (Folder folder:this.userList.get(user).getFolders()) {
            if(folder.getName().equals(folderName)){
                this.userList.get(user).getFolders().remove(folder);
                return folder;
            }
        }
        return null;
    }
    public List<Folder> getFolders(String user){
        return this.userList.get(user).getFolders();
    }

    public List<Folder> deleteAllFolders(String user) {
        this.userList.get(user).getFolders().clear();
        return this.userList.get(user).getFolders();
    }

    public List<Notes> deleteAllNotes(String user, String folderName) {
        for (Folder folder:this.userList.get(user).getFolders()) {
            if(folder.getName().equals(folderName)){
                folder.getNotes().clear();
                return folder.getNotes();
            }
        }
        return null;
    }

    public List<String> getAllNotesName(String user, String folderName) {
        List<String> notes=new ArrayList<>();
        for (Folder folder:this.userList.get(user).getFolders()){
            if(folder.getName().equals(folderName)){
                for (Notes note:folder.getNotes()){
                    notes.add(note.getTitle());
                }
                break;
            }
        }
        return notes;
    }

    public Notes addNewNotes(String user, String folderName, Notes notes) {
        for (Folder folder:this.userList.get(user).getFolders()){
            if(folder.getName().equals(folderName)){
                folder.getNotes().add(notes);
                return notes;
            }
        }
        return null;
    }

    public Notes getNotes(String user, String folderName, String title) {
        for (Folder folder:this.userList.get(user).getFolders()) {
            if(folder.getName().equals(folderName)){
                for (Notes notes:folder.getNotes()) {
                    if(notes.getTitle().equals(title))
                        return notes;
                }
            }
        }
        return null;
    }

    public Notes updateNotes(String user, String folderName, Notes updateNotes, Notes notes) {
        for (Folder folder:this.userList.get(user).getFolders()) {
            if(folder.getName().equals(folderName)){
                for (Notes note:folder.getNotes()) {
                    if(notes.getTitle().equals(note.getTitle())){
                        note.setTitle(updateNotes.getTitle());
                        note.setContent(updateNotes.getContent());
                        note.setModifiedDate(updateNotes.getModifiedDate());
                        folder.setModifiedDate(updateNotes.getModifiedDate());
                        return note;
                    }
                }
            }
        }
        return null;
    }

    public Notes deleteNotes(String user, String folderName, String notesTitle) {
        for (Folder folder:this.userList.get(user).getFolders()){
            if(folder.getName().equals(folderName)){
                for (Notes notes:folder.getNotes()) {
                    if(notes.getTitle().equals(notesTitle)){
                        folder.getNotes().remove(notes);
                        return notes;
                    }
                }
            }
        }
        return null;
    }
}
