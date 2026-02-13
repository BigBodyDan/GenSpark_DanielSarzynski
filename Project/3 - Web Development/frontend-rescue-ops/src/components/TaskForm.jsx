import {useState} from "react";

export default function TaskForm( {onAddTask, existingTitles, maxTasks} ) {
    const [title, setTitle] = useState("");
    const [priority, setPriority] = useState("Low");
    const [error, setError] = useState("");

    const handleSubmit = (event) => {
        event.preventDefault();
        if (!title.trim()) {
            setError("Title cannot be empty");
            return;
        }
        if (existingTitles.includes(title.trim())) {
            setError("Task title already exists");
            return;
        }
        if (existingTitles.length >= maxTasks) {
            setError(`Max ${maxTasks} tasks allowed`);
            return;
        }
        onAddTask(title.trim(), priority);
        setTitle("");
        setPriority("Low");
        setError("");
    };

    return (
        <form onSubmit={handleSubmit} className="mb-3">
            <div className="mb-2">
                <input
                    type="text"
                    className="form-control"
                    placeholder="Task title"
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                />
            </div>
            <div className="mb-2">
                <select
                    className="form-select"
                    value={priority}
                    onChange={(e) => setPriority(e.target.value)}
                >
                    <option>Low</option>
                    <option>Med</option>
                    <option>High</option>
                </select>
            </div>

            {error && <div className="text-danger mb-2">{error}</div>}

            <button type="submit" className="btn btn-primary w-100">
                Add Task
            </button>
        </form>
    );
}