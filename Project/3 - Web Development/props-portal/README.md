# Props Portal — Pass Data Like a Pro

### Overview
Build a React UI where data flows from parent to child components using props. 
You’ll create reusable components that render different content based on the props they receive, reinforcing React’s one-way data flow.

### Requirements (Must Have)
1. Create a parent component that stores an array of at least 6 objects (example: heroes, courses, products, quests).
   - Each object must have at least 4 fields (example: title, category, level, rating).
2. Create a reusable child component (example: Card) that accepts props and displays:
   - At minimum: 3 values from props (example: title, category, rating).
3. Render the list using map() in the parent component and pass props into the child component.
4. Add at least 2 props-based UI rules, for example:
   - If rating >= 4.5, show a “Top Pick” badge
   - If level === "Advanced", style it differently
5. Add a details view using props:
   - When a card is clicked, show a “Selected Item” panel that displays the selected item’s details