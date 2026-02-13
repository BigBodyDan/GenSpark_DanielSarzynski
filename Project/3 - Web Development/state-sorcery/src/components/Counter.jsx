import { useState } from "react";

export default function Counter( {setLastAction } ) {
    const [count, setCount] = useState(0);
    const [message, setMessage] = useState("");

    const increment = () => {
        setCount(count + 1);
        setMessage("");
        setLastAction("Incremented counter");
    };

    const decrement = () => {
        if (count === 0) {
            setMessage("Counter cannot go below 0");
            setLastAction("Attempted to decrement below 0");
            return;
        }

        setCount(count - 1);
        setMessage("");
        setLastAction("Decremented counter");
    };

    const reset = () => {
        setCount(0);
        setMessage("");
        setLastAction("Reset counter");
    };

    return (
        <div className="card mb-4">
            <div className="card-body text-center">
                <h3 className="fw-bold">Counter</h3>
                <h2 className="my-3">{count}</h2>

                {message && (<div className="alert alert-warning py-2">{message}</div>)}

                <div className="d-flex justify-content-center gap-2">
                    <button className="btn btn-success" onClick={increment}>
                        +1
                    </button>
                    <button className="btn btn-danger" onClick={decrement}>
                        -1
                    </button>
                    <button className="btn btn-secondary" onClick={reset}>
                        Reset
                    </button>
                </div>
            </div>
        </div>
    );
}