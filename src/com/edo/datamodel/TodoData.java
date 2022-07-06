package com.edo.datamodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TodoData {
    private static TodoData instance = null;
    private final String filename = "TodoData.txt";
    private ObservableList<TodoItem> todoItems;
    private final DateTimeFormatter formatter;

    public static TodoData getInstance() {
        if (instance == null) {
            instance = new TodoData();
        }
        return instance;
    }

    private TodoData() {
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    public ObservableList<TodoItem> getTodoItems() {
        return todoItems;
    }

    public void addTodoItem(TodoItem item) {
        todoItems.add(item);
    }

    public void deleteTodoItem(TodoItem item) {
        todoItems.remove(item);
    }

    public void loadTodoData() throws IOException {
        todoItems = FXCollections.observableArrayList();

        try (Scanner scanner = new Scanner(new FileReader("TodoData.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] pieces = line.split("\t");

                String shortDescription = pieces[0];
                String details = pieces[1];
                LocalDate deadline = LocalDate.parse(pieces[2], formatter);
                TodoItem item = new TodoItem(shortDescription, details, deadline);
                todoItems.add(item);
            }
        }

    }

    public void saveTodoData() throws IOException {
        try (FileWriter fw = new FileWriter("TodoData.txt")) {
            for (TodoItem item : todoItems) {
                fw.write(String.format("%s\t%s\t%s\n", item.getShortDescription(), item.getDetails(), item.getDeadline().format(formatter)));
            }
        }
    }

}
