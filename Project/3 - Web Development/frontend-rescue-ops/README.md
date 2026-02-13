# Frontend Rescue Ops

Frontend Rescue Ops is a clean, maintainable React task board application.
It demonstrates proper state management, reusable components, debugging discipline, and common front-end guardrails.

---

## Tech Stack

- React
- Bootstrap 5

---

## Application Features
#### Task Board (3 Columns)
- To Do 
- Doing 
- Done

#### Core Functionality
- Add a task
- Move a task between columns
- Delete a task

---

### Best Practices Implemented
- Controlled form inputs with input validation (no empty titles)
- Immutable state updates 
- Proper key usage in lists 
- Single source of truth for tasks
- Clear component separation

### Guardrails Include
- Confirm before deleting a task 
- Prevent duplicate task titles 
- Disable move button if task is already in that column 
- Prevent adding more than 10 tasks

---

## How to Run

1. Install dependencies
```bash
npm install
```
2. Start development server
```bash
npm run dev
```
3. Open in browser
```bash
http://localhost:5173
```

--- 

### Project Structure
```
src/
  components/
  hooks/
  pages/
  utils/
```


