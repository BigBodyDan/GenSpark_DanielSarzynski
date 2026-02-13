export default function CourseCard( {title, category, level, rating, onSelect} ) {
    const isTopPick = rating >= 4.5;
    const isAdvanced = level === "Advanced";

    return (
        <div className={`card h-100 shadow-sm ${isAdvanced ? "border-danger" : ""}`}
             onClick={onSelect} style={{ cursor: "pointer" }}>
            <div className="card-body">
                <h5>{title}
                    {isTopPick && (<span className="badge bg-success ms-2">Top Pick</span>)}
                </h5>
                <p className="card-text"><strong>Category:</strong> {category}</p>
                <p className="card-text"><strong>Level: </strong>
                    <span className={isAdvanced ? "text-danger fw-bold" : "text-success"}>
                        {level}
                    </span>
                </p>
                <p className="card-text"><strong>Rating:</strong> {rating}</p>
            </div>
        </div>
    );
}