package com.example;

import java.util.Map;

public class App {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Использование: java -cp src com.example.App --cmd=<команда> [--text=\"текст\"] [--id=<id>]");
            System.out.println("Доступные команды: add, list, count");
            return;
        }
        
        // Парсинг аргументов
        Map<String, String> arguments = parseArguments(args);
        String command = arguments.get("cmd");
        
        NotesStore notesStore = new NotesStore();
        
        switch (command) {
            case "add":
                String text = arguments.get("text");
                if (text == null || text.trim().isEmpty()) {
                    System.out.println("Ошибка: не указан текст заметки (используйте --text=\"ваш текст\")");
                    return;
                }
                int newId = notesStore.addNote(text.trim());
                System.out.println("Добавлена заметка #" + newId);
                break;
                
            case "list":
                String[] notes = notesStore.getAllNotes();
                if (notes.length == 0) {
                    System.out.println("(empty)");
                } else {
                    for (String note : notes) {
                        System.out.println(note);
                    }
                }
                break;
                
            case "count":
                int count = notesStore.getNotesCount();
                System.out.println(count);
                break;
                
            default:
                System.out.println("Неизвестная команда: " + command);
                System.out.println("Доступные команды: add, list, count");
        }
    }
    
    private static Map<String, String> parseArguments(String[] args) {
        java.util.HashMap<String, String> map = new java.util.HashMap<>();
        for (String arg : args) {
            if (arg.startsWith("--")) {
                String[] parts = arg.substring(2).split("=", 2);
                if (parts.length == 2) {
                    String value = parts[1];
                    // Удаляем кавычки если есть
                    if (value.startsWith("\"") && value.endsWith("\"")) {
                        value = value.substring(1, value.length() - 1);
                    }
                    map.put(parts[0], value);
                } else {
                    map.put(parts[0], "");
                }
            }
        }
        return map;
    }
}