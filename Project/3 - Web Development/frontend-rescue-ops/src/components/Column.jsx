import TaskCard from "./TaskCard";
import {getHeaderStyle} from "../utils/getHeaderStyle.js";

export default function Column( {title, tasks, onDelete, onMove} ) {
    return (
        <div className="col-md-4 mb-4">
            <div className="card shadow-sm h-100">
                <div className={`card-header text-center fw-bold ${getHeaderStyle(title)}`}>
                    {title}
                </div>

                <div className="card-body bg-light">
                    {tasks.length === 0 && (<p className="text-muted text-center small">No tasks</p>)}

                    {tasks.map((task) => (
                        <TaskCard
                            key={task.id}
                            task={task}
                            onDelete={onDelete}
                            onMove={onMove}
                        />
                    ))}
                </div>
            </div>
        </div>
    );
}