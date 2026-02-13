export default function DataDisplay( {data, filter} ) {
    const filteredData = filter === "All" ? data : data.filter((item) => item.priority === filter);

    return (
        <div className="card">
            <div className="card-body">
                <h5 className="fw-bold">Data Display</h5>

                {filteredData.length === 0 ? (
                    <div className="text-muted">No items to display.</div>
                ) : (
                    <div className="row">
                        {filteredData.map((item) => (<DataItem key={item.id} item={item} />))}
                    </div>
                )}
            </div>
        </div>

    );
}

function DataItem( { item } ) {
    return (
        <div className="col-md-4 mb-3">
            <div className="card h-100">
                <div className="card-body">
                    <h6>{item.title}</h6>
                    <span className={item.priority === "High" ? "badge bg-danger" : "badge bg-secondary"}>
                        {item.priority}
                    </span>
                </div>
            </div>
        </div>
    )
}

