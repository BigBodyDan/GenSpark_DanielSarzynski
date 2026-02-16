# Callback Canyon
This Node.js script demonstrates the use of callbacks, higher-order functions, and step-by-step progress updates.


### Features Implemented:
- `traverseCanyon(actionCallback)` runs steps in sequence.
- Supports different callbacks:
  - Logger: logs each step.
  - Score Tracker: keeps a running score.
  - Danger Detector: warns of dangerous steps.
- Higher-order function `createStepLogger(prefix)` creates customized loggers.
- Step-by-step output shows progress and final result.
### How to Run:
1. Make sure Node.js is installed.
2. Open a terminal in the project folder.
3. Run: `callback-canyon.js`