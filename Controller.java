/*
 * Course: CSC1120A - 121
 * Spring 2024
 * Lab 11 - Auto Complete
 * Name: Madison Betz
 * Created: 4/3/2024
 */
package betzm;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;


/**
 * displays the UI
 */
public class Controller extends Application {
    @FXML
    private TextField searchTextField;

    @FXML
    private ListView<String> listView;

    @FXML
    private TextField exactQueryUnsorted;

    @FXML
    private TextField allMatchesUnsorted;

    @FXML
    private TextField exactQuerySorted;

    @FXML
    private TextField allMatchesSorted;

    @FXML
    private TextField exactQueryBST;

    @FXML
    private TextField allMatchesBST;

    @FXML
    private TextField exactQueryTrie;

    @FXML
    private TextField allMatchesTrie;

    private UnorderedList unorderedList;
    private OrderedList orderedList;
    private BinarySearchTree binarySearchTree;
    private Trie trie;
    private String previous = "";
    private final List<String> prevResults = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().
                getResource("AutoComplete.fxml")));
        final int width = 308;
        final int height = 460;
        Scene scene = new Scene(root, width, height);
        primaryStage.setTitle("Auto Complete");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * initializes some of the javaFX variables
     */
    public void initialize() {
        exactQueryUnsorted.setDisable(true);
        allMatchesUnsorted.setDisable(true);
        allMatchesBST.setDisable(true);
        exactQueryBST.setDisable(true);
        exactQuerySorted.setDisable(true);
        allMatchesSorted.setDisable(true);
        exactQueryTrie.setDisable(true);
        allMatchesTrie.setDisable(true);

        searchTextField.setDisable(true);
        searchTextField.textProperty().addListener((observable, oldValue, newValue)
                -> displayResults(newValue));
    }

    @FXML
    private void chooseDictionary() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Dictionary");
        fileChooser.setInitialDirectory(new File("data"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            loadListView(selectedFile);
        }
    }

    private void loadListView(File file) {
        searchTextField.setDisable(false);
        try {
            List<String> words = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line);
            }

            reader.close();
            prevResults.clear();
            prevResults.addAll(words);
            listView.getItems().clear();
            listView.getItems().addAll(words);
            unorderedList = new UnorderedList(words);
            orderedList = new OrderedList(words);
            TreeSet<String> input = new TreeSet<>(words);
            binarySearchTree = new BinarySearchTree(input);
            trie = new Trie();

        } catch (IOException e) {
            showAlert("Error", "Failed to load word list", "Please check the selected file.");
        }
    }

    private void displayResults(String prefix) {
        if (!prefix.startsWith(previous)) {
            listView.getItems().clear();
            listView.getItems().addAll(prevResults);
        }

        List<String> filteredWords = new ArrayList<>();
        for (String word : listView.getItems()) {
            if (word.startsWith(prefix)) {
                filteredWords.add(word);
            }
        }

        listView.getItems().clear();
        listView.getItems().addAll(filteredWords);

        long startTime;
        long endTime;

        startTime = System.nanoTime();
        Object[] ulMatch = unorderedList.allMatches(prefix);
        endTime = System.nanoTime();
        displayResults(ulMatch, exactQueryUnsorted, allMatchesUnsorted,
                startTime, endTime, "UnorderedList");

        startTime = System.nanoTime();
        Object[] olMatch = orderedList.allMatches(prefix);
        endTime = System.nanoTime();
        displayResults(olMatch, exactQuerySorted, allMatchesSorted,
                startTime, endTime, "OrderedList");

        startTime = System.nanoTime();
        Object[] bstMatch = binarySearchTree.allMatches(prefix);
        endTime = System.nanoTime();
        displayResults(bstMatch, exactQueryBST, allMatchesBST,
                startTime, endTime, "BinarySearchTree");

        startTime = System.nanoTime();
        Object[] trieMatch = trie.allMatches(prefix);
        endTime = System.nanoTime();
        displayResults(trieMatch, exactQueryTrie, allMatchesTrie,
                startTime, endTime, "Trie");

        previous = prefix;
    }

    private void displayResults(Object[] matches, TextField exactTimeField,
                                TextField matchTimeField, long startTime, long endTime,
                                String type) {
        long exactTime = endTime - startTime;
        exactTimeField.setText(AutoCompleter.format(exactTime));
        startTime = System.nanoTime();
        boolean isExactMatch = false;

        for (Object match : matches) {
            String matchString = (String) match;
            switch (type) {
                case "UnorderedList" -> isExactMatch = unorderedList.exactMatch(matchString);
                case "OrderedList" -> isExactMatch = orderedList.exactMatch(matchString);
                case "BinarySearchTree" -> isExactMatch = binarySearchTree.exactMatch(matchString);
                case "Trie" -> isExactMatch = trie.exactMatch(matchString);
            }
        }


        endTime = System.nanoTime();
        long matchTime = endTime - startTime;
        matchTimeField.setText(AutoCompleter.format(matchTime));
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
