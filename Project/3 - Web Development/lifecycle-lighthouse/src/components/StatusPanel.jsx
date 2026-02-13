export default function StatusPanel( {loading, error, beacon} ) {
    return (
        <div className="card mb-3">
            <div className="card-body">
                <h5 className="fw-bold">Status Panel</h5>

                {loading && <div className="text-primary">Loading data...</div>}

                {!loading && !error && (<div className="text-success">Data loaded successfully.</div>)}

                {error && <div className="text-danger">{error}</div>}

                <hr />

                <div>
                    Beacon Pulse:{" "}
                    <span className={beacon ? "text-success" : "text-danger"}>
                        {beacon ? "ON" : "OFF"}
                    </span>
                </div>
            </div>
        </div>
    );
}