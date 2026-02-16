# Promise Harbor
This Node.js script simulates ships arriving at a harbor using Promises.
### Features Implemented:
- `dockShip(shipName)` returns a Promise that resolves or rejects randomly.
- Chain of 3 `.then()` calls to transform data: logs arrival, adds cargo, updates status.
- `.catch()` handles errors (blank names, random failures).
- `.finally()` logs docking completion for each ship.
- Runs simulation for multiple ships and shows results clearly in the console.

### How to Run:
1. Make sure Node.js is installed.
2. Open a terminal in the project folder.
3. Run: `node promise-harbor.js`