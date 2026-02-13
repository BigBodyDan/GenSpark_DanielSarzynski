import {useState} from "react";
import {generateId} from "../utils/generateId.js";

export default function useTasks( initialTasks = [] ) {
    const [tasks, setTasks] = useState(initialTasks);

    const addTask = (title, priority) => {
        setTasks((prev) => [...prev, { id: generateId(), title, priority, status: "To Do" },]);
    };

    const deleteTask = (id) => {
        setTasks((prev) => prev.filter((task) => task.id !== id));
    };

    const moveTask = (id, newStatus) => {
        setTasks((prev) => prev.map((task) => task.id === id ? { ...task, status: newStatus } : task));
    };

    return { tasks, addTask, deleteTask, moveTask };
};