# Step-by-Step Guide

1. Prerequisites:  
   - Java Development Kit (JDK 8 or higher).

2. File Structure:  
   - Place the MemoryGame.java file in a folder structure like:  
     com/example/memorygame/MemoryGame.java  

3. Compile the Code:  
   ```bash
   javac com/example/memorygame/MemoryGame.java
   ```

4. Run the Game:  
   ```bash
   java com.example.memorygame.MemoryGame
   ```

5. How to Play:  
   - A 4×4 grid appears with tiles labeled "?" by default.  
   - Click on any tile to reveal its hidden number.  
   - Click on a second tile to try to find a pair.  
   - If both tiles match, they stay revealed. Otherwise, they'll flip back after a second.  
   - Continue until all 16 tiles are revealed.

6. Customization:  
   - Change the grid layout or tile count by modifying numPairs in the MemoryGame constructor.  
   - Adjust the difficulty (tile flipping speed) via the `Timer` delay.

7. Troubleshooting:  
   - If the GUI does not display, ensure your Java version supports Swing.  
   - If the tiles do not match correctly, check that the tile values are properly shuffled and assigned.

Enjoy playing the Memory Game!
