# The Async Relay
This Node.js script simulates a 6-stage relay race. Each runner starts after the previous one finishes. The script shows loading indicators, handles errors, and outputs a final summary.

### Features Implemented:
- 6 async stages with variable durations
- Console-based "loading" messages when a runner starts and finishes
- Error handling: one runner may fail, stopping the relay
- Final summary includes total time, completed runners, and final status

### How to Run:
1. Make sure Node.js is installed.
2. Open a terminal in the project folder.
3. Run: `node async-relay.js`