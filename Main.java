
import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main {
    private static String lexicalResult = "";
    private static boolean lexicalResultAccepted = false;

    public static void main(String[] args) {
        // frame
        JFrame frame = new JFrame("Mini Compiler_ALBAN_BANGAHON");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 500);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Color.BLACK);

        // input box sa taas
        JTextArea intputBox = new JTextArea();
        intputBox.setEditable(false);
        intputBox.setFont(new Font("Consolas", Font.PLAIN, 16));
        intputBox.setForeground(Color.WHITE);
        intputBox.setBackground(Color.BLACK);
        intputBox.setBorder(new LineBorder(Color.darkGray, 1));
        JScrollPane inputScrollPane = new JScrollPane(intputBox);
        inputScrollPane.setPreferredSize(new Dimension(600, 50));
        intputBox.setText("\n   Start by Opening File");

        // Result box
        JTextArea resultBox = new JTextArea();
        resultBox.setEditable(false);
        resultBox.setFont(new Font("Consolas", Font.PLAIN, 16));
        resultBox.setForeground(Color.GREEN);
        resultBox.setBackground(Color.BLACK);
        resultBox.setBorder(new LineBorder(Color.darkGray, 0));
        resultBox.setBorder(new EmptyBorder(20, 20, 0, 5));
        JScrollPane resultScrollPane = new JScrollPane(resultBox);
        resultScrollPane.setPreferredSize(new Dimension(600, 50));

        // panel for input and result
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.add(inputScrollPane);
        textPanel.add(resultScrollPane);
        textPanel.setBorder(new LineBorder(Color.darkGray, 0));
        frame.add(textPanel, BorderLayout.NORTH);

        // Big box area
        JTextArea bigTextArea = new JTextArea();
        bigTextArea.setEditable(false);
        bigTextArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        bigTextArea.setForeground(Color.WHITE);
        bigTextArea.setBackground(Color.BLACK);
        bigTextArea.setBorder(new LineBorder(Color.darkGray, 1));
        bigTextArea.setBorder(new EmptyBorder(30, 30, 0, 5));
        JScrollPane bigTextScrollPane = new JScrollPane(bigTextArea);
        frame.add(bigTextScrollPane, BorderLayout.CENTER);

        // buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1));
        buttonPanel.setBackground(Color.DARK_GRAY);
        buttonPanel.setBorder(new LineBorder(Color.darkGray, 0));

        ImageIcon openFileIcon = new ImageIcon("./public/upload-file.png");
        ImageIcon lexicalIcon = new ImageIcon("./public/chart.png");
        ImageIcon syntaxIcon = new ImageIcon("./public/gold.png");
        ImageIcon semanticIcon = new ImageIcon("./public/tick.png");
        ImageIcon clearIcon = new ImageIcon("./public/delete.png");

        Image openFileIconSize = openFileIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        Image lexicalIconSize = lexicalIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        Image syntaxIconSize = syntaxIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        Image semanticIconSize = semanticIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        Image clearIconSize = clearIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);

        openFileIcon = new ImageIcon(openFileIconSize);
        lexicalIcon = new ImageIcon(lexicalIconSize);
        syntaxIcon = new ImageIcon(syntaxIconSize);
        semanticIcon = new ImageIcon(semanticIconSize);
        clearIcon = new ImageIcon(clearIconSize);

        JButton openFileButton = new JButton("Open File", openFileIcon);
        JButton lexicalButton = new JButton("Lexical Analysis", lexicalIcon);
        JButton syntaxButton = new JButton("Syntax Analysis", syntaxIcon);
        JButton semanticButton = new JButton("Semantic Analysis", semanticIcon);
        JButton clearButton = new JButton("Clear", clearIcon);

        openFileButton.setBackground(Color.BLACK);
        openFileButton.setForeground(Color.GREEN);
        openFileButton.setFont(new Font("Consolas", Font.PLAIN, 15));

        lexicalButton.setBackground(Color.BLACK);
        lexicalButton.setForeground(Color.GREEN);
        lexicalButton.setPreferredSize(new Dimension(250, 30));
        lexicalButton.setFont(new Font("Consolas", Font.PLAIN, 15));

        syntaxButton.setBackground(Color.BLACK);
        syntaxButton.setForeground(Color.GREEN);
        syntaxButton.setPreferredSize(new Dimension(250, 30));
        syntaxButton.setFont(new Font("Consolas", Font.PLAIN, 15));

        semanticButton.setBackground(Color.BLACK);
        semanticButton.setForeground(Color.GREEN);
        semanticButton.setPreferredSize(new Dimension(250, 30));
        semanticButton.setFont(new Font("Consolas", Font.PLAIN, 15));

        clearButton.setBackground(Color.BLACK);
        clearButton.setForeground(Color.RED);
        clearButton.setPreferredSize(new Dimension(250, 30));
        clearButton.setFont(new Font("Consolas", Font.PLAIN, 15));

        // Disable muna
        lexicalButton.setEnabled(false);
        syntaxButton.setEnabled(false);
        semanticButton.setEnabled(false);

        // Add buttons
        buttonPanel.add(openFileButton);
        buttonPanel.add(lexicalButton);
        buttonPanel.add(syntaxButton);
        buttonPanel.add(semanticButton);
        buttonPanel.add(clearButton);

        frame.add(buttonPanel, BorderLayout.WEST);

        // Open File
        openFileButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();

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
                    lexicalResult = reader.readLine();
                    intputBox.setText("\n  " + lexicalResult);
                    resultBox.setText("File loaded. Ready for Lexical Analysis.");
                    lexicalButton.setEnabled(true);
                } catch (IOException ex) {
                    resultBox.setText("Error: Unable to read the file.");
                }
            }
        });

        // Lexical Analysis
        lexicalButton.addActionListener(e -> {
            try {
                LexicalAnalyzer analyzer = new LexicalAnalyzer();
                String lexicalOutput = analyzer.analyze(lexicalResult);
                resultBox.setText("Lexical analysis phase success. Proceed to Syntax Analysis.");
                bigTextArea.setText(lexicalOutput);
                lexicalResultAccepted = true;
                syntaxButton.setEnabled(true);
            } catch (Exception ex) {
                resultBox.setForeground(Color.RED);
                resultBox.setText("Lexical analysis failed: " + ex.getMessage());
                bigTextArea.setText("");
                lexicalResultAccepted = false;
            }
        });

        // Syntax Analysis
        syntaxButton.addActionListener(e -> {
            if (lexicalResultAccepted) {
                try {
                    String parseTree = SyntaxAnalyzer.analyze(lexicalResult);
                    resultBox.setText("Syntax analysis phase success. Proceed to Semantic Analysis");
                    bigTextArea.setText(parseTree);
                    semanticButton.setEnabled(true);
                } catch (Exception ex) {
                    resultBox.setForeground(Color.RED);
                    resultBox.setText("Syntax analysis failed: " + ex.getMessage());
                    bigTextArea.setText("");
                }
            } else {
                resultBox.setText("Perform lexical analysis first and ensure it's accepted.");
            }
        });

        // Semantic Analysis
        semanticButton.addActionListener(e -> {
            if (lexicalResultAccepted) {
                try {
                    String semanticResult = SemanticAnalyzer.analyze(lexicalResult);
                    resultBox.setText("Semantic analysis phase success.");
                    bigTextArea.setText(semanticResult);
                } catch (Exception ex) {
                    resultBox.setForeground(Color.RED);
                    resultBox.setText("Semantic analysis failed: " + ex.getMessage());
                    bigTextArea.setText("");
                }
            } else {
                resultBox.setText("Perform lexical and syntax analysis first.");
            }
        });

        // eraser to
        clearButton.addActionListener(e -> {
            lexicalResult = "";
            lexicalResultAccepted = false;
            intputBox.setText("\n   Start by Opening File");
            resultBox.setText("");
            bigTextArea.setText("");
            lexicalButton.setEnabled(false);
            syntaxButton.setEnabled(false);
            semanticButton.setEnabled(false);
        });

        frame.setVisible(true);
    }
}
