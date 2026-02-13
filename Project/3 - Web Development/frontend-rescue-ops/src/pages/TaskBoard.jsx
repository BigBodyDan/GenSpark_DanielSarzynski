import Column from "../components/Column.jsx";
import TaskForm from "../components/TaskForm.jsx";
import useTasks from "../hooks/useTasks.jsx";

const MAX_TASKS = 10;

export default function TaskBoard() {
    const { tasks, addTask, deleteTask, moveTask } = useTasks();
    const columns = ["To Do", "Doing", "Done"];

    return (
        <div className="container-fluid py-4">
            <h1 className="mb-4 text-center fw-bold">Frontend Rescue Ops</h1>
            <TaskForm
                onAddTask={addTask}
                existingTitles={tasks.map((t) => t.title)}
                maxTasks={MAX_TASKS}
            />

            <div className="row">
                {columns.map((col) => (
                    <Column
                        key={col}
                        title={col}
                        tasks={tasks.filter((t) => t.status === col)}
                        onDelete={deleteTask}
                        onMove={moveTask}
                    />
                ))}
            </div>
        </div>
    );
}