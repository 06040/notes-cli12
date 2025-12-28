package com.example;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class NotesStore {
    private static final String NOTES_FILE = "data/notes.csv";
    private static final String DATA_DIR = "data";
    
    static {
        try {
            Files.createDirectories(Paths.get(DATA_DIR));
            if (!Files.exists(Paths.get(NOTES_FILE))) {
                Files.createFile(Paths.get(NOTES_FILE));
            }
        } catch (IOException e) {
            System.err.println("Ошибка при инициализации хранилища: " + e.getMessage());
        }
    }
    
    public static int addNote(String text) throws IOException {
        List<String[]> notes = readAllNotes();
        int newId = notes.isEmpty() ? 1 : Integer.parseInt(notes.get(notes.size() - 1)[0]) + 1;
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOTES_FILE, true))) {
            writer.write(newId + ";" + text);
            writer.newLine();
        }
        return newId;
    }
    
    public static List<String[]> listNotes() throws IOException {
        return readAllNotes();
    }
    
    private static List<String[]> readAllNotes() throws IOException {
        List<String[]> notes = new ArrayList<>();
        Path filePath = Paths.get(NOTES_FILE);
        
        if (Files.exists(filePath) && Files.size(filePath) > 0) {
            try (BufferedReader reader = new BufferedReader(new FileReader(NOTES_FILE))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (!line.trim().isEmpty()) {
                        String[] parts = line.split(";", 2);
                        if (parts.length == 2) {
                            notes.add(parts);
                        }
                    }
                }
            }
        }
        return notes;
    }
}
