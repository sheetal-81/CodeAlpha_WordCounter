import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.filechooser.FileFilter;

public class WordCounterApp extends JFrame {
    JTextArea textArea;
    private JButton countButton;
    private JButton refreshButton;
    private JButton darkModeButton;
    private JButton lightModeButton;
    private JButton selectFileButton; // Added button for file selection
    private JLabel wordCountLabel;

    public WordCounterApp() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        
        textArea = new JTextArea();
        textArea.setPreferredSize(new Dimension(200, 750));
        textArea.setBackground(Color.white);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, gbc);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10)); // Changed to 5 rows

        countButton = new JButton("Count Words");
        countButton.setBounds(80, 200, 200, 50);
        countButton.setBackground(Color.BLACK);
        countButton.setForeground(Color.WHITE);
        countButton.setFont(new Font("Arial", Font.BOLD, 19));
        countButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textArea.getText();
                String[] words = text.split("\\s+");
                wordCountLabel.setText("Word Count: " + words.length);
            }
        });
        buttonPanel.add(countButton);

        refreshButton = new JButton("Refresh");
        refreshButton.setBounds(300, 300, 100, 150);
        refreshButton.setBackground(Color.RED);
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setFont(new Font("Arial", Font.BOLD, 19));
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                wordCountLabel.setText("Word Count: 0");
            }
        });
        buttonPanel.add(refreshButton);

        darkModeButton = new JButton("Dark Mode");
        darkModeButton.setFont(new Font("Arial", Font.BOLD, 18));
        darkModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleDarkMode(true);
            }
        });
        buttonPanel.add(darkModeButton);

        lightModeButton = new JButton("Light Mode");
        lightModeButton.setFont(new Font("Arial", Font.BOLD, 18));
        lightModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleDarkMode(false);
            }
        });
        buttonPanel.add(lightModeButton);

        selectFileButton = new JButton("Select File");
        selectFileButton.setFont(new Font("Arial", Font.BOLD, 18));
        selectFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileFilter() {
                    @Override
                    public boolean accept(File f) {
                        return f.isDirectory() || f.getName().toLowerCase().endsWith(".docx") || f.getName().toLowerCase().endsWith(".pdf");
                    }

                    @Override
                    public String getDescription() {
                        return "Documents (.docx, .pdf)";
                    }
                });
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    // Process the selected file (you can do something with it)
                }
            }
        });
        buttonPanel.add(selectFileButton);

        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.2;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(buttonPanel, gbc);

        wordCountLabel = new JLabel("Word Count: 0");
        wordCountLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Set font size to 16
        gbc = new GridBagConstraints();
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridy = 1; // Set grid row to 1 (below the previous component)
        add(wordCountLabel, gbc);


        setSize(900, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void toggleDarkMode(boolean darkMode) {
        try {
            if (darkMode) {
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            } else {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WordCounterApp();
            }
        });
    }
}
