# Dragon Cave Logic Challenge
This Node.js script is a text-based adventure where you explore a cave, make choices, collect items, and try to escape.
You must manage health, gold, and limited turns before the dragon wakes.
Your stats and inventory evolve based on your decisions, leading to different endings.
There are 7 major decision points. 

### Endings:
- **Win**: Escape with 100+ gold and survive.
- **Survival**: Escape alive with less than 100 gold.
- **Lose**: Health reaches 0 or dragon wakes.

### Bonus Features:
- Random events using `Math.random()`
- Inventory system
- Turn-based timer mechanic
- Replay loop

### How to Run:
1. Make sure Node.js is installed.
2. Open a terminal in the project folder.
3. Run: `node dragon-cave.js`