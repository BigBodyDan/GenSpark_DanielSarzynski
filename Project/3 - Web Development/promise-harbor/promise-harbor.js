function dockShip(shipName) {
    return new Promise((resolve, reject) => {
        // Random delay between 500ms - 2000ms
        const delay = Math.floor(Math.random() * 1500) + 500;

        setTimeout(() => {
            if (!shipName) {
                reject("Error: Ship name cannot be blank.");
            } else if (Math.random() < 0.3) { // Random failure
                reject(`Error: ${shipName} failed to dock due to stormy seas.`);
            } else {
                resolve({ name: shipName, status: "Arrived" });
            }
        }, delay);
    });
}

function processShip(ship) {
    dockShip(ship)
        .then(data => {
            console.log(`${data.name} has docked successfully.`);
            return { ...data, cargo: Math.floor(Math.random() * 100) + " tons" };
        })
        .then(data => {
            console.log(`Cargo info for ${data.name}: ${data.cargo}`);
            return { ...data, status: "Unloading" };
        })
        .then(data => {
            console.log(`${data.name} status updated to: ${data.status}`);
            return data;
        })
        .catch(err => {
            console.error(err);
        })
        .finally(() => {
            console.log(`Docking attempt for ${ship} complete.\n`);
        });
}

const ships = ["Bolko", "Semko", "Przemko", "Gniewko", "Mieszko", "Leszko"];

ships.forEach(ship => processShip(ship));

processShip(""); // blank name to trigger error