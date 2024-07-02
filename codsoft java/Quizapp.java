import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Quizapp extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JLabel questionLabel;
    private JRadioButton[] options;
    private JButton nextButton;
    private ButtonGroup optionsGroup;
    private JLabel timerLabel;
    private Timer timer;
    private int timeLeft = 30; // Time for each question in seconds
    private int currentQuestionIndex = 0;
    private int score = 0;

    // Sample questions and answers
    private String[][] questions = {
        {"How many days are there in a week?", "7", "6", "5", "9"},
        {"How many hours in a day?", "29", "23", "24", "26"},
        {"How many letter are there in English?", "26", "25", "31", "28"}
    };

    private String[] correctAnswers = {"7", "24", "26"};

    public Quizapp() {
        setTitle("Quiz Application");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        questionLabel = new JLabel("Question");
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(4, 1));

        options = new JRadioButton[4];
        optionsGroup = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            optionsGroup.add(options[i]);
            optionsPanel.add(options[i]);
        }
        add(optionsPanel, BorderLayout.CENTER);

        nextButton = new JButton("Next");
        nextButton.addActionListener(this);
        add(nextButton, BorderLayout.SOUTH);

        timerLabel = new JLabel("Time left: " + timeLeft);
        timerLabel.setHorizontalAlignment(JLabel.CENTER);
        add(timerLabel, BorderLayout.EAST);

        loadQuestion(currentQuestionIndex);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeLeft--;
                timerLabel.setText("Time left: " + timeLeft);
                if (timeLeft <= 0) {
                    checkAnswer();
                    loadNextQuestion();
                }
            }
        });
        timer.start();
    }

    private void loadQuestion(int questionIndex) {
        if (questionIndex >= questions.length) {
            endQuiz();
            return;
        }
        questionLabel.setText(questions[questionIndex][0]);
        for (int i = 0; i < 4; i++) {
            options[i].setText(questions[questionIndex][i + 1]);
        }
        optionsGroup.clearSelection();
        timeLeft = 30; // reset time for the new question
    }

    private void checkAnswer() {
        String selectedAnswer = null;
        for (JRadioButton option : options) {
            if (option.isSelected()) {
                selectedAnswer = option.getText();
                break;
            }
        }
        if (selectedAnswer != null && selectedAnswer.equals(correctAnswers[currentQuestionIndex])) {
            score++;
        }
    }

    private void loadNextQuestion() {
        currentQuestionIndex++;
        loadQuestion(currentQuestionIndex);
    }

    private void endQuiz() {
        timer.stop();
        JOptionPane.showMessageDialog(this, "Quiz Over! Your score: " + score);
        System.exit(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        checkAnswer();
        loadNextQuestion();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Quizapp().setVisible(true);
            }
        });
    }
}
