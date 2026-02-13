import { useState } from "react";

export default function Toggle( {setLastAction } ) {
    const [shieldOn, setShieldOn] = useState(false);

    const toggleShield = () => {
        const newState = !shieldOn;
        setShieldOn(newState);
        setLastAction(`Spell Shield turned ${newState ? "ON" : "OFF"}`);
    };

    return (
        <div className="card mb-4">
            <div className="card-body text-center">
                <h3 className="fw-bold">Spell Shield</h3>

                <button className={`btn ${shieldOn ? "btn-primary" : "btn-outline-primary"}`}
                        onClick={toggleShield}>
                        Spell Shield: {shieldOn ? "ON" : "OFF"}
                </button>
            </div>
        </div>
    );
}