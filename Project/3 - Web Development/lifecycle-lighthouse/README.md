# Lifecycle Lighthouse — Loading + Effects Dashboard

### Overview

Build a small “dashboard” that simulates real-world React behavior: loading data, updating the UI when data arrives, and reacting to state changes using useEffect.
This assignment teaches lifecycle thinking (mount/update/unmount) through effects, dependencies, and cleanup.

### Requirements (Must Have)

1. Base UI Layout
   - Create a page titled “Lifecycle Lighthouse” with:
   - A Status Panel (Loading / Success / Error)
   - A Controls Panel
   - A Data Display area (cards or list)
2. Simulated Data Fetch (useEffect on Mount)
   - On initial render, automatically start a “fetch” using setTimeout (simulating an API call). 
   - Must use useEffect(() => { ... }, []). 
   - Use these state variables (or equivalent):
      - loading (boolean)
      - error (string or null)
      - data (array)
- Behavior:
  - When fetching starts: loading = true, error = null 
  - After 1–2 seconds:
  - Either success: set data to an array of at least 6 items 
  - Or failure: set error to a message and keep data empty 
  - Display the correct UI state:
    - Loading message/spinner text 
    - Error message 
    - Data list/cards
3. Reload Button (Re-run Fetch)
   - Add a Reload button that re-runs the fetch simulation.
   - Reload must reset state correctly:
     - loading true
     - error cleared
     - data cleared (or replaced after fetch completes)
4. Filter Control (Effect Dependencies)
   - Add a simple filter control (dropdown or buttons), e.g.:
     - “All”, “High Priority”, “Low Priority”
   - Each data item must include a field that supports filtering (example: priority: "High" | "Low").
   - The UI must display only the filtered items.
- Implementation rule:
     - Use derived rendering (recommended) or a second useEffect that reacts to filter changes—either is acceptable, but you must clearly manage dependencies if using an effect. 
5. Cleanup (Unmount Awareness)
- Implement one cleanup example:

  - Start a timer (or interval) that updates a “Beacon” status (example: “Beacon Pulse: ON/OFF”) every second.
  - Use useEffect cleanup to stop it:
    - return () => clearInterval(id)
  - You must show that the cleanup exists in code.