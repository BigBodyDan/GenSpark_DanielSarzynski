# Loop of Destiny
This Node.js script is a turn-based challenge that relies on loops and conditional logic. The game uses a while loop for turn-based gameplay.
Each loop iteration represents one turn.
The loop runs until the player wins, loses, or runs out of turns.

### The Game Ends When
- Score reaches 20 (win)
- Lives reach 0
- Energy reaches 0
- Turns reach 0

The break statement exits the loop on win or loss.
The continue statement skips a turn if energy is too low to attack.

### How to Run:
1. Make sure Node.js is installed.
2. Open a terminal in the project folder.
3. Run: `node loop-of-destiny.js`