package memorymatchinggame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class MemoryMatchingGame {
    private static final int GRID_SIZE = 4;  
    private static final int NUM_CARDS = GRID_SIZE * GRID_SIZE;
    private final JFrame frame;
    private final JButton[] cards;
    private ImageIcon[] images;
    private final boolean[] matchedCards;
    private int flippedCardIndex = -1;
    private Timer flipBackTimer;
    private Timer gameTimer;
    private int timeLeft = 60;
    private final JLabel timerLabel;
    private int matchedPairs = 0;

    public MemoryMatchingGame() {
        frame = new JFrame("Memory Matching Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 1000);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Color.PINK);

        JPanel gamePanel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE, 10, 10));
        gamePanel.setBackground(Color.PINK);
        cards = new JButton[NUM_CARDS];
        images = new ImageIcon[NUM_CARDS];
        matchedCards = new boolean[NUM_CARDS];
        loadImages();

        for (int i = 0; i < NUM_CARDS; i++) {
            cards[i] = new JButton();
            cards[i].setFont(new Font("Arial", Font.BOLD, 14));
            cards[i].setText("~'?'~");
            cards[i].setPreferredSize(new Dimension(60, 60));
            int index = i;
            cards[i].addActionListener(e -> flipCard(index));
            gamePanel.add(cards[i]);
        }

        timerLabel = new JLabel("Time left: 60s", SwingConstants.CENTER);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 16));

        frame.add(timerLabel, BorderLayout.NORTH);
        frame.add(gamePanel, BorderLayout.CENTER);

        startGameTimer();
        frame.setVisible(true);
    }

    private void loadImages() {
        List<ImageIcon> imageList = new ArrayList<>();

        for (int i = 1; i <= 8; i++) {  
            String path = "/resources/images/silly" + i + ".png";
            URL imgURL = getClass().getResource(path);
            if (imgURL != null) {
                ImageIcon img = new ImageIcon(imgURL);
                imageList.add(img);
                imageList.add(img);  // Duplicate for pairing
            } else {
                System.out.println("Image not found: " + path);
            }
        }

        Collections.shuffle(imageList);
        images = imageList.toArray(new ImageIcon[0]);
    }

    private void flipCard(int index) {
        if (matchedCards[index] || (flippedCardIndex != -1 && flipBackTimer != null)) return;

        cards[index].setIcon(images[index]);
        cards[index].setText("");

        if (flippedCardIndex == -1) {
            flippedCardIndex = index;
        } else {
            if (images[flippedCardIndex].equals(images[index])) {
                matchedCards[flippedCardIndex] = true;
                matchedCards[index] = true;
                matchedPairs++;
                flippedCardIndex = -1;

                if (matchedPairs == (NUM_CARDS / 2)) {
                    JOptionPane.showMessageDialog(frame, "Congratulations! You've matched all pairs!", "You Win! <3", JOptionPane.INFORMATION_MESSAGE);
                    gameTimer.stop();
                }
            } else {
                int firstIndex = flippedCardIndex;
                flipBackTimer = new Timer(1000, (ActionEvent e) -> {
                    cards[firstIndex].setIcon(null);
                    cards[firstIndex].setText("~'?'~");
                    cards[index].setIcon(null);
                    cards[index].setText("~'?'~");
                    flippedCardIndex = -1;
                    flipBackTimer.stop();
                    flipBackTimer = null;
                });
                flipBackTimer.setRepeats(false);
                flipBackTimer.start();
            }
        }
    }

    private void startGameTimer() {
        gameTimer = new Timer(1000, (ActionEvent e) -> {
            timeLeft--;
            timerLabel.setText("Time left: " + timeLeft + "s");
            if (timeLeft <= 0) {
                gameTimer.stop();
                JOptionPane.showMessageDialog(frame, "Time's up! Game Over. <3", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            }
            else if(matchedPairs == (NUM_CARDS / 2)){
               gameTimer.stop();
            }
        });
        gameTimer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MemoryMatchingGame::new);
    }
}