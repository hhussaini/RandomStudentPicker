package randomstudentpicker;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
//import javafx.scene.layout.Background;
//import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * This is a simple little program useful for randomly picking
 * students registered in our class to be called on to
 * answer a question during lecture.
 * 
 * @author Richard McKenna
 */
public class RandomStudentPicker extends Application {

    // HERE ARE THE STUDENTS WE'LL PICK FROM
    String[] students = {"Amir Ahmed", "Timothy	Barnard", "Neal	Beeken", "Paul Chan", "Ji En Chen", "Henry Chin", "Jeffrey Ching", "Patrick Chou", "Brandon Cuadrado", "Vlad Deshkovich", "Shawn Felix", "Alejandro Flores", "Joseph Giardina", "Paul Grindle", "John Guastavino", "Bryan Hauser", "John Hurtle", "Hamza Hussaini", "Albert Ibragimov", "Christopher Jativa", "Kyle Johnson", "Konrad Juszkiewicz", "Ethan Kelly", "Jun Young Kim", "Junyoung Kim", "Chuntak Kong", "Chaeyoung Lee", "Young-Sup Lee", "John Legutko", "David Levin", "Kevin Li", "Susan Lin", "Zhengyang Liu", "Shi Lin Lu", "Matthew Luce", "Paul Mannarino", "Eric McHugh", "Sean Milligan", "Wesley Nam", "Ryan Naughton", "Aaron Pajares", "Danbee Park", "William Perez", "David Pirraglia", "Kamin Punjarojanakul", "Eduardo Quispe", "Aseem Rai", "Godson Raju", "Roger Rosenquist", "Kevin Setayesh", "Sheryar Shah", "Sanjay Shivraj", "Sharanjit Singh", "Anthony Soricelliv", "Nicolas Sosa", "Zhongli Su", "Jeffrey Sun", "Yu Sun", "Nelson Tsui", "David Tvedt", "Nicholas Vea", "Bo Wang", "Matthew Wong", "Raymond Xue", "Edwin Yan", "Marvin Yan", "Weixing Yang", "Jing Yu", "Numaer Zaker"};
    String student = "";
    // THESE ARE OUR ONLY CONTROLS, A BUTTON AND A DISPLAY LABEL
    Button pickButton;
    final Label studentNameLabel = new Label();
    
    @Override
    public void start(Stage primaryStage) {
        // MAKE OUR TWO CONTROLS
        pickButton = new Button("Pick Random Student");
        pickButton.setFont(new Font("Cursive", 36));
        studentNameLabel.setFont(new Font("Serif", 48));

        // PUT THEM IN A CONTAINER
        BorderPane root = new BorderPane();
        FlowPane topPane = new FlowPane();
        topPane.getChildren().add(pickButton);
        topPane.setAlignment(Pos.CENTER);
        root.setTop(topPane);
        root.setCenter(studentNameLabel);

        // AND PUT THE CONTAINER IN THE WINDOW (i.e. the "stage")
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        
        // PROVIDE A RESPONSE TO BUTTON CLICKS
        pickButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Task<Void> task = new Task<Void>() {
                    @Override protected Void call() throws Exception {
                        for (int i = 0; i < 20; i++) {
                            // RANDOMLY SELECT A STUDENT
                            int randomIndex = (int) (Math.random() * students.length);
                            student = students[randomIndex];
                            
                            // THIS WILL BE DONE ASYNCHRONOUSLY VIA MULTITHREADING
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    studentNameLabel.setText(student);
                                }
                            });
                            
                            // SLEEP EACH FRAME
                            try { Thread.sleep(400); }
                            catch(InterruptedException ie) {
                                ie.printStackTrace();
                            }
                        }
                        return null;
                    }
                };
                // THIS GETS THE THREAD ROLLING
                Thread thread = new Thread(task);
                thread.start();
            }
        });
        // OPEN THE WINDOW
        primaryStage.show();
    }

    /**
     * This starts our JavaFX application rolling.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
