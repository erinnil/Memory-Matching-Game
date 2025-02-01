# Memory-Matching-Game

The Memory Matching Game is a simple Java-based game where players flip cards to find matching pairs. The game consists of a 4x4 grid (16 cards), where 8 pairs of matching cards exist. The goal is to match all the pairs before time runs out.
How to Play:
Click on a card to reveal its hidden image.
Click on a second card:
If the two cards match, they stay flipped.
If they don't match, they flip back after 1 second.
The game ends when all 8 pairs are matched.
If time runs out before all pairs are matched, you lose.
Features:
4x4 Grid: 16 cards with randomized images.
Pairs Matching: 8 pairs
Timer: 60-second countdown to complete the game.
Automatic Flip Back: Unmatched cards flip back after 1 second.
Win Condition: The game ends when all pairs are matched.
Lose Condition: If the timer reaches 0, you lose.
Installation & Setup
Ensure you have Java (JDK 8 or later) installed.
Clone or download this repository.
Place your images inside the resources/images/ directory:
Example: resources/images/image1.png, image2.png, etc.
Compile and run the game using an IDE like NetBeans, Eclipse, or IntelliJ.
Running the Game
Using Command Line:
javac MemoryMatchingGame.java
java MemoryMatchingGame
Using an IDE:
Open the project in NetBeans, Eclipse, or IntelliJ.
Run MemoryMatchingGame.java.
Customization
Modify loadImages() to change the image set.
Adjust the timer length in startGameTimer().
Modify flipCard() for different game mechanics.
License
This project is open-source and can be modified or distributed freely.
