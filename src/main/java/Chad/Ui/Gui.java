package Chad.Ui;

import Chad.Chad;
import Chad.TaskList.Task;
import Chad.TaskList.TaskList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Gui extends Application implements Ui {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image chadImage = new Image(this.getClass().getResourceAsStream("/images/DaChad.png"));
    
    private Chad chad; // Instance of the Chad class

    @Override
    public void init() {
        chad = new Chad("./data/chad.txt"); // Initialize your Chad instance here
    }

    @Override
    public void showWelcome() {
        // Display the welcome message in the dialog container
        dialogContainer.getChildren().add(DialogBox.getChadDialog("Hello from Chad\nWhat can I do for you?", chadImage));
    }

    @Override
    public void start(Stage stage) {
        // Setting up required components
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        // Event handlers for sending user input
        sendButton.setOnMouseClicked((event) -> handleUserInput());
        userInput.setOnAction((event) -> handleUserInput()); // Handle Enter key as well

        // Layout setup
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();

        // Additional window properties
        stage.setTitle("Chad");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
        mainLayout.setPrefSize(400.0, 600.0);

        // ScrollPane settings
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(625.0);
        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);

        // AnchorPane constraints
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // Listener for scrolling
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    // Read command from user input
    @Override
    public String readCommand() {
        return userInput.getText(); // Get text from the user input field
    }

    // Show error message to the user
    @Override
    public void showError(String errorMessage) {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog("Error: " + errorMessage, dukeImage));
    }

    // Show goodbye message to the user
    @Override
public void showBye() {
    dialogContainer.getChildren().add(DialogBox.getChadDialog("Bye. Hope to see you again soon!", chadImage)); // Display goodbye message
    // Optionally implement logic to close the application or the window
    System.exit(0); // This will terminate the application
}

// Show the list of tasks in the UI
@Override
public void showTaskList(TaskList tasks) {
    if (tasks.getNoOfTask() == 0) {
        dialogContainer.getChildren().add(DialogBox.getChadDialog("The list is empty.", chadImage));
    } else {
        dialogContainer.getChildren().add(DialogBox.getChadDialog("Here are the tasks in your list:", chadImage));
        for (int i = 0; i < tasks.getNoOfTask(); i++) {
            dialogContainer.getChildren().add(DialogBox.getChadDialog((i + 1) + ". " + tasks.getTaskById(i).toString(), chadImage));
        }
    }
}

// Show a help message to the user
@Override
public void showHelp(String helpContentString) {
    dialogContainer.getChildren().add(DialogBox.getChadDialog(helpContentString, chadImage));
}

// Show a message when a task is deleted
@Override
public void showDeleteTask(Task task, int noOfTask) {
    String message = "Noted. I've removed this task: " + task.toString() + "\nNow you have " + noOfTask + " tasks in the list.";
    dialogContainer.getChildren().add(DialogBox.getChadDialog(message, chadImage));
}

// Show a message when a task is added
@Override
public void showAddTask(Task task, int noOfTask) {
    String message = "Got it. I've added this task: " + task.toString() + "\nNow you have " + noOfTask + " tasks in the list.";
    dialogContainer.getChildren().add(DialogBox.getChadDialog(message, chadImage));
}

// Show a message when a task is marked as done
@Override
public void showMarkTask(Task task) {
    dialogContainer.getChildren().add(DialogBox.getChadDialog("Nice! I've marked this task as done:\n" + task.toString(), chadImage));
}

// Show a message when a task is unmarked
@Override
public void showUnMarkTask(Task task) {
    dialogContainer.getChildren().add(DialogBox.getChadDialog("OK, I've marked this task as not done yet:\n" + task.toString(), chadImage));
}
}
