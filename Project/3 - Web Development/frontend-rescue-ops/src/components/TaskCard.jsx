import {getPriorityClass} from "../utils/getPriorityStyle.js";

export default function TaskCard ( {task, onDelete, onMove} )  {
    const { id, title, priority, status } = task;

    return (
        <div className="card mb-2">
            <div className="card-body p-2">
                <h5 className="card-title">{title}</h5>
                <p>Priority: <span className={`badge ${getPriorityClass(priority)}`}>{priority}</span></p>
                <div className="d-flex justify-content-between">
                    <div>
                        {["To Do", "Doing", "Done"].map((col) =>
                            col !== status ? (
                                <button key={col} className="btn btn-sm btn-outline-primary me-1"
                                    onClick={() => onMove(id, col)}>
                                    Move to {col}
                                </button>
                            ) : null
                        )}
                    </div>

                    <button
                        className="btn btn-sm btn-danger"
                        onClick={() => {if (window.confirm("Delete this task?")) onDelete(id);}}>
                        Delete
                    </button>
                </div>
            </div>
        </div>
    );
}