# Notes CLI - Консольное приложение для заметок

**Автор:** [ваш GitHub ник] (ссылка на ваш профиль GitHub)

## Описание
Простое консольное приложение для управления текстовыми заметками на Java. Заметки сохраняются в файл `data/notes.csv`.

## Требования
- Java 17 или выше
- Docker (для запуска в контейнере)

## Команды

### Локальный запуск

1. **Компиляция:**
```bash
javac src/com/example/*.java

# Проверить скомпилированные классы
ls -la src/com/example/*.class

# Проверить файл с заметками
ls -la data/notes.csv

# Посмотреть содержимое файла заметок
cat data/notes.csv

# Удалить скомпилированные классы
rm src/com/example/*.class

# Удалить все заметки
rm data/notes.csv

# Перекомпилировать и запустить заново
javac src/com/example/*.java
java -cp src com.example.App --cmd=add --text="Новая заметка после очистки"

# Проверка установки Java
java -version

#Компиляция Java классов
javac src/com/example/*.java

# Запуск приложения (базовый тест)
java -cp src com.example.App

#Добавление первой заметки
java -cp src com.example.App --cmd=add --text="Моя первая заметка"

#Просмотр всех заметок
java -cp src com.example.App --cmd=list

#Подсчет количества заметок
java -cp src com.example.App --cmd=count

# Запускаем контейнер
# В Windows CMD используем %cd%
docker run --rm -v %cd%/data:/app/data notes-cli --cmd=add --text="Заметка из Docker"

# Проверяем
docker run --rm -v %cd%/data:/app/data notes-cli --cmd=list