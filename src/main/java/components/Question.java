package components;

import java.util.ArrayList;

public class Question {

    public ArrayList<Answer> answers;
    public String question;

    public Question(ArrayList<Answer> answers, String question) {
        this.answers = answers;
        this.question = question;
    }
}
