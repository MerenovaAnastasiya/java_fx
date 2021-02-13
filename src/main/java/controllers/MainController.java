package controllers;

import components.Answer;
import components.Question;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainController {

    ArrayList<Question> questions = new ArrayList<>();
    int currentQuestion = 0;
    int correctAnswersCount;


    @FXML
    Button quitButton;

    @FXML
    Button answerButton;

    @FXML
    Pane answersPane;

    @FXML
    ToggleGroup answers;

    @FXML
    Text result;

    @FXML
    public void quit() {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void answerTheQuestion() throws IOException {
        RadioButton selectedButton = (RadioButton) answers.getSelectedToggle();
        ObservableList<Toggle> buttons = answers.getToggles();
        if (selectedButton == null) {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            URL xmlUrl = getClass().getResource("/window.fxml");
            loader.setLocation(xmlUrl);
            Parent root = loader.load();
            Scene modalScene = new Scene(root);
            stage.setWidth(200);
            stage.setHeight(200);
            stage.setScene(modalScene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(quitButton.getScene().getWindow());
            stage.show();
        } else {
            if (currentQuestion == questions.size() - 1) {
                FileWriter fileWriter = new FileWriter("result.txt");
                fileWriter.write(String.valueOf(correctAnswersCount));
                fileWriter.flush();
                FXMLLoader loader = new FXMLLoader();
                URL url = getClass().getResource("/result.fxml");
                loader.setLocation(url);
                Parent root = loader.load();
                Scene scene = quitButton.getScene();
                scene.setRoot(root);
                File file = new File("result.txt");
                file.delete();
                fileWriter.close();
            } else {
                for (int i = 0; i < buttons.size(); i++) {
                    if (buttons.get(i).equals(selectedButton)) {
                        int answerNumber = i;
                        Question question = questions.get(currentQuestion);
                        ArrayList<Answer> answers = question.answers;
                        Answer userAnswer = answers.get(answerNumber);
                        if (userAnswer.isCorrect) {
                            correctAnswersCount++;
                        }
                    }
                }
                currentQuestion += 1;
                for (int i = 0; i < buttons.size(); i++) {
                    ((RadioButton) buttons.get(i)).setText(questions.get(currentQuestion).answers.get(i).answer);
                    buttons.get(i).setSelected(false);
                }
            }
        }
    }

    @FXML
    public void initialize() {
        ObservableList<Toggle> buttons = answers.getToggles();

        //добавляем вопросы и ответы
        ArrayList<Answer> answers1 = new ArrayList<>();
        answers1.add(new Answer("правильный овтет", true));
        answers1.add(new Answer("Ответ2", false));
        answers1.add(new Answer("Ответ3", false));
        questions.add(new Question(answers1, "Вопрос 1"));

        ArrayList<Answer> answers2 = new ArrayList<>();
        answers2.add(new Answer("1", true));
        answers2.add(new Answer("2", false));
        answers2.add(new Answer("3", false));
        questions.add(new Question(answers2, "Вопрсо 2"));

//        ObservableList<Node> children = answersGroup.getChildren();
//        ArrayList<RadioButton> answerButtons = new ArrayList<>();
//        for (int i = 0; i < children.size(); i++) {
//            if(children.get(i) instanceof RadioButton) {
//                RadioButton button = (RadioButton) children.get(i);
//                answerButtons.add(button);
//            }
//        }
        for (int i = 0; i < buttons.size(); i++) {
            ((RadioButton) buttons.get(i)).setText(answers1.get(i).answer);
        }
    }


}
