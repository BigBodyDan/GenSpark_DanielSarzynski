export default function ControlsPanel( {onReload, filter, setFilter} ) {
    return (
        <div className="card mb-3">
            <div className="card-body">
                <h5 className="fw-bold">Controls Panel</h5>

                <div className="d-flex gap-3 align-items-center">
                    <button className="btn btn-primary" onClick={onReload}>
                        Reload
                    </button>

                    <select className="form-select w-auto" value={filter}
                        onChange={(e) => setFilter(e.target.value)}>
                        <option value="All">All</option>
                        <option value="High">High Priority</option>
                        <option value="Low">Low Priority</option>
                    </select>
                </div>
            </div>
        </div>
    );
}