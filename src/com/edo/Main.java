package com.edo;

import com.edo.datamodel.TodoData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main-window.fxml"));
        primaryStage.setTitle("Todo List");
        primaryStage.setScene(new Scene(root, 900, 500));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws Exception {
        try {
            TodoData.getInstance().saveTodoData();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void init() throws Exception {
        try {
            TodoData.getInstance().loadTodoData();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

// C:\Users\DELL>f:
// F:\>cd F:\MY-WORKING-SPACE\07-JULY\programming\todo-list-app\out\artifacts\todo_list_app_jar
// F:\MY-WORKING-SPACE\07-JULY\programming\todo-list-app\out\artifacts\todo_list_app_jar>java --module-path F:\MY-WORKING-SPACE\07-JULY\programming\lib\javafx-sdk-17.0.2\lib --add-modules javafx.controls,javafx.fxml -jar todo-list-app.jar