package controllers;

import javafx.fxml.FXML;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ResultController {

    @FXML
    public void initialize() throws IOException {
        FileReader reader = new FileReader("result.txt");
        Scanner scanner = new Scanner(reader);
        String result = scanner.nextLine();

        reader.close();
    }
}
