package com.example;

public class App {
    public static void main(String[] args) {
        if (args.length == 0) {
            printUsage();
            return;
        }
        
        String command = null;
        String text = null;
        String id = null;
        
        for (String arg : args) {
            if (arg.startsWith("--cmd=")) {
                command = arg.substring(6);
            } else if (arg.startsWith("--text=")) {
                text = arg.substring(7).replace("\"", "");
            } else if (arg.startsWith("--id=")) {
                id = arg.substring(5);
            }
        }
        
        try {
            switch (command) {
                case "add":
                    if (text == null || text.trim().isEmpty()) {
                        System.out.println("Ошибка: текст заметки не может быть пустым");
                        return;
                    }
                    int noteId = NotesStore.addNote(text);
                    System.out.println("Заметка добавлена с ID: " + noteId);
                    break;
                    
                case "list":
                    var notes = NotesStore.listNotes();
                    if (notes.isEmpty()) {
                        System.out.println("(empty)");
                    } else {
                        for (String[] note : notes) {
                            System.out.println(note[0] + ";" + note[1]);
                        }
                    }
                    break;
                    
                case "rm":
                    if (id == null) {
                        System.out.println("Ошибка: не указан ID для удаления");
                        return;
                    }
                    try {
                        int removeId = Integer.parseInt(id);
                        boolean removed = NotesStore.removeNote(removeId);
                        if (removed) {
                            System.out.println("Заметка #" + removeId + " удалена");
                        } else {
                            System.out.println("Not found #" + removeId);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка: ID должен быть числом");
                    }
                    break;
                    
                default:
                    System.out.println("Неизвестная команда: " + command);
                    printUsage();
                    break;
            }
        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
    
    private static void printUsage() {
        System.out.println("Использование:");
        System.out.println("  --cmd=add --text=\"Текст заметки\"");
        System.out.println("  --cmd=list");
        System.out.println("  --cmd=rm --id=<номер>");
    }
}
EOF# Проверим содержимое конфликтующего файла
cat src/com/example/App.java

# Создадим правильную версию
cat > src/com/example/App.java << 'EOF'
package com.example;

public class App {
    public static void main(String[] args) {
        if (args.length == 0) {
            printUsage();
            return;
        }
        
        String command = null;
        String text = null;
        String id = null;
        
        for (String arg : args) {
            if (arg.startsWith("--cmd=")) {
                command = arg.substring(6);
            } else if (arg.startsWith("--text=")) {
                text = arg.substring(7).replace("\"", "");
            } else if (arg.startsWith("--id=")) {
                id = arg.substring(5);
            }
        }
        
        try {
            switch (command) {
                case "add":
                    if (text == null || text.trim().isEmpty()) {
                        System.out.println("Ошибка: текст заметки не может быть пустым");
                        return;
                    }
                    int noteId = NotesStore.addNote(text);
                    System.out.println("Заметка добавлена с ID: " + noteId);
                    break;
                    
                case "list":
                    var notes = NotesStore.listNotes();
                    if (notes.isEmpty()) {
                        System.out.println("(empty)");
                    } else {
                        for (String[] note : notes) {
                            System.out.println(note[0] + ";" + note[1]);
                        }
                    }
                    break;
                    
                case "rm":
                    if (id == null) {
                        System.out.println("Ошибка: не указан ID для удаления");
                        return;
                    }
                    try {
                        int removeId = Integer.parseInt(id);
                        boolean removed = NotesStore.removeNote(removeId);
                        if (removed) {
                            System.out.println("Заметка #" + removeId + " удалена");
                        } else {
                            System.out.println("Not found #" + removeId);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка: ID должен быть числом");
                    }
                    break;
                    
                default:
                    System.out.println("Неизвестная команда: " + command);
                    printUsage();
                    break;
            }
        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
    
    private static void printUsage() {
        System.out.println("Использование:");
        System.out.println("  --cmd=add --text=\"Текст заметки\"");
        System.out.println("  --cmd=list");
        System.out.println("  --cmd=rm --id=<номер>");
    }
}
