import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class Main {
    private static JButton lexicalButton;
    private static JTextField resultField; // For result (Accepted/Rejected)
    private static JTextArea lexemeOutputArea; // For lexeme output
    private static File uploadedFile;

    public static void main(String[] args) {
        // Create frame
        JFrame frame = new JFrame("Lexical Analyzer GUI");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create buttons
        JButton openButton = new JButton("Open File");
        lexicalButton = new JButton("Lexical Analysis");
        JButton syntaxButton = new JButton("Syntax Analysis");
        JButton semanticButton = new JButton("Semantic Analysis");
        JButton clearButton = new JButton("Clear");

        // Disable lexical button initially
        lexicalButton.setEnabled(false);
        lexicalButton.setForeground(Color.GRAY);

        // Create result box
        resultField = new JTextField();
        resultField.setEditable(false);
        resultField.setHorizontalAlignment(JTextField.CENTER);

        // Create lexeme output area
        lexemeOutputArea = new JTextArea(10, 40);
        lexemeOutputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(lexemeOutputArea);

        // Panel layout
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 5, 5));
        buttonPanel.add(openButton);
        buttonPanel.add(lexicalButton);
        buttonPanel.add(syntaxButton);
        buttonPanel.add(semanticButton);
        buttonPanel.add(clearButton);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(resultField, BorderLayout.NORTH);
        rightPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(buttonPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.CENTER);

        frame.add(mainPanel);

        // File chooser for opening files
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Text Files (*.txt)", "txt"));

        // Open File button action
        openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    uploadedFile = fileChooser.getSelectedFile();
                    if (uploadedFile.getName().endsWith(".txt")) {
                        lexicalButton.setEnabled(true);
                        lexicalButton.setForeground(Color.BLACK);
                    } else {
                        JOptionPane.showMessageDialog(frame,
                                "Error: Only .txt files are allowed!",
                                "Invalid File Type",
                                JOptionPane.ERROR_MESSAGE);
                        lexicalButton.setEnabled(false);
                        lexicalButton.setForeground(Color.GRAY);
                    }
                }
            }
        });

        // Lexical Analysis button action
        lexicalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (uploadedFile != null) {
                    try {
                        String lexemeOutput = LexicalAnalyzer.analyzeFile(uploadedFile);
                        resultField.setText("Lexical Phase: Accepted");
                        lexemeOutputArea.setText(lexemeOutput);
                    } catch (Exception ex) {
                        resultField.setText("Lexical Phase: Rejected");
                        lexemeOutputArea.setText("Error: " + ex.getMessage());
                    }
                }
            }
        });

        // Clear button action
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resultField.setText("");
                lexemeOutputArea.setText("");
                uploadedFile = null;
                lexicalButton.setEnabled(false);
                lexicalButton.setForeground(Color.GRAY);
            }
        });

        // Show frame
        frame.setVisible(true);
    }
}
