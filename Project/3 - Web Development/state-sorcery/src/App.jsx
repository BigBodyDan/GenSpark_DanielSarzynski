import { useState } from "react";
import Counter from "./components/Counter.jsx";
import Toggle from "./components/Toggle.jsx";
import ListManager from "./components/ListManager.jsx";

function App() {
    const [lastAction, setLastAction] = useState("None");

    return (
        <div className="container-fluid py-5">
            <h1 className="text-center mb-4 fw-bold">State Sorcery</h1>

            <Counter setLastAction={setLastAction} />
            <Toggle setLastAction={setLastAction} />
            <ListManager setLastAction={setLastAction} />

            <div className="alert alert-info text-center mt-4">
                <strong>Last Action:</strong> {lastAction}
            </div>
        </div>
    );
}

export default App
