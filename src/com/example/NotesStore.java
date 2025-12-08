package com.example;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class NotesStore {
    private static final String DATA_FILE = "data/notes.csv";
    
    public NotesStore() {
        try {
            Files.createDirectories(Paths.get("data"));
        } catch (IOException e) {
            System.err.println("Ошибка при создании директории data: " + e.getMessage());
        }
    }
    
    public int addNote(String text) {
        List<String> notes = readAllNotes();
        int newId = notes.size() + 1;
        
        try (FileWriter fw = new FileWriter(DATA_FILE, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(newId + ";" + text);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Ошибка при записи заметки: " + e.getMessage());
        }
        
        return newId;
    }
    
    public String[] getAllNotes() {
        List<String> notes = readAllNotes();
        return notes.toArray(new String[0]);
    }
    
    public int getNotesCount() {
        return readAllNotes().size();
    }
    
    private List<String> readAllNotes() {
        List<String> notes = new ArrayList<>();
        File file = new File(DATA_FILE);
        
        if (!file.exists()) {
            return notes;
        }
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                notes.add(line);
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении заметок: " + e.getMessage());
        }
        
        return notes;
    }
}