import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main {
    private static String lexicalResult = "";
    private static boolean lexicalResultAccepted = false;

    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Mini Compiler_ALBAN_BANGAHON");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLayout(new BorderLayout());

        // Result box (top area for showing analysis status)
        JTextArea resultBox = new JTextArea();
        resultBox.setEditable(false);
        resultBox.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane resultScrollPane = new JScrollPane(resultBox);
        resultScrollPane.setPreferredSize(new Dimension(600, 60));
        frame.add(resultScrollPane, BorderLayout.NORTH);

        // Big box area (for lexemes, parse tree, etc.)
        JTextArea bigTextArea = new JTextArea();
        bigTextArea.setEditable(false);
        bigTextArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane bigTextScrollPane = new JScrollPane(bigTextArea);
        frame.add(bigTextScrollPane, BorderLayout.CENTER);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1));

        JButton openFileButton = new JButton("Open File");
        JButton lexicalButton = new JButton("Lexical Analysis");
        JButton syntaxButton = new JButton("Syntax Analysis");
        JButton semanticButton = new JButton("Semantic Analysis");
        JButton clearButton = new JButton("Clear");

        // Disable buttons until the proper steps are followed
        lexicalButton.setEnabled(false);
        syntaxButton.setEnabled(false);
        semanticButton.setEnabled(false);

        buttonPanel.add(openFileButton);
        buttonPanel.add(lexicalButton);
        buttonPanel.add(syntaxButton);
        buttonPanel.add(semanticButton);
        buttonPanel.add(clearButton);

        frame.add(buttonPanel, BorderLayout.WEST);

        // Open File Button Action
        openFileButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();

            // Add a filter to only show .txt files
            FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("Text Files (*.txt)", "txt");
            fileChooser.setFileFilter(txtFilter);

            int result = fileChooser.showOpenDialog(frame);

            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                if (!file.getName().endsWith(".txt")) {
                    resultBox.setText("Error: Only .txt files are allowed.");
                    lexicalButton.setEnabled(false);
                    return;
                }

                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    lexicalResult = reader.readLine(); // Read the first line of the file
                    resultBox.setText("File loaded. Ready for Lexical Analysis.");
                    lexicalButton.setEnabled(true); // Enable lexical analysis button
                } catch (IOException ex) {
                    resultBox.setText("Error: Unable to read the file.");
                }
            }
        });

        // Lexical Analysis Button Action
        lexicalButton.addActionListener(e -> {
            try {
                LexicalAnalyzer analyzer = new LexicalAnalyzer();
                String lexicalOutput = analyzer.analyze(lexicalResult); // Process the line into tokens
                resultBox.setText("Lexical analysis phase success.");
                bigTextArea.setText(lexicalOutput);
                lexicalResultAccepted = true;
                syntaxButton.setEnabled(true); // Enable syntax analysis button
            } catch (Exception ex) {
                resultBox.setText("Lexical analysis failed: " + ex.getMessage());
                bigTextArea.setText("");
                lexicalResultAccepted = false;
            }
        });

        // Syntax Analysis Button Action
        syntaxButton.addActionListener(e -> {
            if (lexicalResultAccepted) {
                try {
                    String parseTree = SyntaxAnalyzer.analyze(lexicalResult);
                    resultBox.setText("Syntax analysis phase success.");
                    bigTextArea.setText(parseTree);
                    semanticButton.setEnabled(true); // Enable semantic analysis button
                } catch (Exception ex) {
                    resultBox.setText("Syntax analysis failed: " + ex.getMessage());
                    bigTextArea.setText("");
                }
            } else {
                resultBox.setText("Perform lexical analysis first and ensure it's accepted.");
            }
        });

        // Semantic Analysis Button Action
        semanticButton.addActionListener(e -> {
            if (lexicalResultAccepted) {
                try {
                    // After lexical analysis, semantic analysis should receive tokenized data
                    String semanticResult = SemanticAnalyzer.analyze(lexicalResult);
                    resultBox.setText("Semantic analysis phase success.");
                    bigTextArea.setText(semanticResult); // Display semantic analysis result
                } catch (Exception ex) {
                    resultBox.setText("Semantic analysis failed: " + ex.getMessage());
                    bigTextArea.setText("");
                }
            } else {
                resultBox.setText("Perform lexical and syntax analysis first.");
            }
        });

        // Clear Button Action
        clearButton.addActionListener(e -> {
            lexicalResult = "";
            lexicalResultAccepted = false;
            resultBox.setText("");
            bigTextArea.setText("");
            lexicalButton.setEnabled(false);
            syntaxButton.setEnabled(false);
            semanticButton.setEnabled(false);
        });

        // Display the frame
        frame.setVisible(true);
    }
}
