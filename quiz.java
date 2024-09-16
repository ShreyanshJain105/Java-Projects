import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Quiz {
    private List<Question> questions;
    private int score;
    private int currentQuestionIndex;
    private Scanner scanner;
    private Timer timer;

    public Quiz(List<Question> questions) {
        this.questions = questions;
        this.score = 0;
        this.currentQuestionIndex = 0;
        this.scanner = new Scanner(System.in);
        this.timer = new Timer();
    }

    public void start() {
        while (currentQuestionIndex < questions.size()) {
            displayQuestion();
            int userAnswer = getUserAnswer();
            checkAnswer(userAnswer);
            currentQuestionIndex++;
        }
        displayResult();
    }

    private void displayQuestion() {
        Question question = questions.get(currentQuestionIndex);
        System.out.println(question.getQuestion());
        for (int i = 0; i < question.getOptions().size(); i++) {
            System.out.println((i + 1) + ". " + question.getOptions().get(i));
        }
        System.out.print("Enter your answer (1-" + question.getOptions().size() + "): ");
    }

    private int getUserAnswer() {
        int userAnswer = scanner.nextInt();
        startTimer(10); // 10 seconds to answer
        return userAnswer;
    }

    private void checkAnswer(int userAnswer) {
        Question question = questions.get(currentQuestionIndex);
        if (userAnswer == question.getCorrectAnswer()) {
            score++;
            System.out.println("Correct!");
        } else {
            System.out.println("Incorrect. The correct answer is " + question.getCorrectAnswer());
        }
    }

    private void startTimer(int seconds) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Time's up!");
                scanner.next(); // clear input buffer
            }
        }, seconds * 1000);
    }

    private void displayResult() {
        System.out.println("Quiz complete! Your score is " + score + " out of " + questions.size());
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            System.out.println((i + 1) + ". " + question.getQuestion() + " - " + (question.getCorrectAnswer() == getUserAnswer() ? "Correct" : "Incorrect"));
        }
    }
}