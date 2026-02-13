import { useState } from "react";

export default function ListManager( {setLastAction } ) {
    const [input, setInput] = useState("");
    const [items, setItems] = useState([]);

    const addItem = () => {
        const trimmed = input.trim();

        if (!trimmed) {
            setLastAction("Blocked empty submission");
            return;
        }

        setItems([...items, trimmed]);
        setInput("");
        setLastAction(`Added item: ${trimmed}`);
    };

    const removeItem = (index) => {
        const removed = items[index];
        const updated = items.filter((_, i) => i !== index);

        setItems(updated);
        setLastAction(`Removed item: ${removed}`);
    };

    return (
        <div className="card mb-4">
            <div className="card-body">
                <h3 className="text-center mb-3 fw-bold">List Manager</h3>

                <div className="input-group mb-3">
                    <input
                        type="text"
                        className="form-control"
                        placeholder="Enter item"
                        value={input}
                        onChange={(e) => setInput(e.target.value)}
                    />
                    <button className="btn btn-dark" onClick={addItem}>
                        Add
                    </button>
                </div>

                <ul className="list-group">
                    {items.map((item, index) => (
                        <li key={index} className="list-group-item d-flex justify-content-between align-items-center">
                            {item}
                            <button className="btn btn-sm btn-outline-danger"
                                onClick={() => removeItem(index)}>
                                Remove
                            </button>
                        </li>
                    ))}
                </ul>

                <div className="mt-3">
                    <strong>Total Items:</strong> {items.length}
                </div>
            </div>
        </div>
    );
}