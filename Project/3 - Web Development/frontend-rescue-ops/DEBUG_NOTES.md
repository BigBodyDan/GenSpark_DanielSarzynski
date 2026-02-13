# DEBUG NOTES

This document shows bugs/issues, how they were reproduced, and how they were fixed.

---

## 1. State Mutation Bug
### Bug Description
The `moveTask` function directly modified the task object inside state.

### How It Was Reproduced
1. Added a task.
2. Moved it to another column.
3. UI did not update.
4. React DevTools showed state changed but component did not re-render properly.

### How It Was Fixed
Changed tasks array mapping to return a new object for updated task

## 2. Double Submit
### Bug Description
Form submission triggered twice when pressing Enter.

### How It Was Reproduced
1. Typed a task title 
2. Pressed Add Task
3. Two identical tasks were added.

### How It Was Fixed
Prevented default in `handleSubmit` in `TaskForm`

## 3. Empty Task Titles
### Bug Description
The form allowed users to create tasks with empty titles or only spaces.

### How It Was Reproduced
1. Open the app.
2. Clicked "Add Task" without typing anything.
3. A blank task appeared in the "To Do" column.

### How It Was Fixed
Added validation check using `title.trim()`

## 4. Missing Key Warning in Column
### Bug Description
React warning: `Each child in a list should have a unique key prop.`

### How It Was Reproduced
1. Added several tasks.
2. Opened browser console using DevTools.

### How It Was Fixed
Added `task.id` attribute to `TaskCard` component

## 5. Infinite Re-render
### Bug Description
The component entered an infinite render loop because a useEffect updated state while depending on that same state.

### How It Was Reproduced
1. Added a useEffect to count tasks.
2. Included tasks in the dependency array.
3. Updated derived state inside the effect.
4. App froze and console showed repeated renders.

### How It Was Fixed
Handled the count of tasks using `existingTitles.length` instead

## 6. Duplicate Task Titles
### Bug Description
Users could add multiple tasks with identical titles.

### How It Was Reproduced
1. Added a task.
2. Added another task with same name.

### How It Was Fixed
Added `existingTitles` attribute and checked if `title` exists before adding 

## 7. Move Button Allowed Same Column
### Bug Description
User could click "Move to To Do" while task was already in "To Do".

### How It Was Reproduced
1. Added a task. 
2. Clicked "Move to To Do".
3. Task remained.

### How It Was Fixed
Added `status` attribute to track where task is located and disabled appropriate buttons based on `status`