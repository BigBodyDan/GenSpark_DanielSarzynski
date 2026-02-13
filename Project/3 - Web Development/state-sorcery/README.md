# State Sorcery — Interactive UI with Events

### Overview

Build an interactive React app where the UI changes based on state and user events. 
You’ll practice useState, event handling, and updating the UI predictably based on user actions.

### Requirements (Must Have)

1. Create a small app called “State Sorcery” that includes:
   - A Counter section
   - A Toggle section
   - A List Manager section
2. Counter (State + Events)
   - Display a number starting at 0
   - Buttons:
     - +1 (increment)
     - -1 (decrement)
     - Reset (back to 0)
   - Add a rule: counter cannot go below 0 (show a message if user tries)
3. Toggle (Boolean State)
   - A button that toggles between “Spell Shield: ON” and “Spell Shield: OFF”
   - The UI must visibly change (example: different text + className style change)
4. List Manager (Array State)
   - An input + button to add items to a list (example: “spells”, “tasks”, “ingredients”)
   - Must support:
     - Add item
     - Remove item (remove button per row)
   - Block blank submissions (no empty strings)
5. Derived UI
   - Display: Total Items: X
   - Display: Last Action: … (updates after each event: add/remove/toggle/reset/etc.)