// simple
function logger(stepName, status) {
    console.log(`${stepName} is ${status}`);
}

// higher-order
function createStepLogger(prefix) {
    let count = 0;
    return function(stepName, status) {
        count++;
        logger(`${prefix} Step #${count}: ${stepName}`, status);
    };
}

function scoreTracker() {
    let score = 0;
    return function(stepName, status, points = 0) {
        score += points;
        console.log(`Step "${stepName}" completed. Current Score: ${score}`);
    };
}

function dangerDetector(stepName, status) {
    if (status === "dangerous") {
        console.warn(`Danger detected at step: ${stepName}!`);
    }
}

// Main
function traverseCanyon(actionCallback) {
    const steps = [
        { name: "Cross the Bridge", status: "safe", points: 10 },
        { name: "Hike the Rocky Path", status: "safe", points: 5 },
        { name: "Swing over the Chasm", status: "dangerous", points: 15 },
        { name: "Cross the River", status: "safe", points: 10 },
        { name: "Climb the Cliff", status: "dangerous", points: 20 },
        { name: "Rest", status: "safe", points: 0 },
        { name: 'Navigate Cave', status: 'caution', points: 12 },
        { name: "Walk Through Desert ", status: "safe", points: 13 },
        { name: 'Rappel Down', status: 'dangerous', points: 15 },
    ];

    steps.forEach(step => {
        actionCallback(step.name, step.status, step.points);
    });

    console.log("Canyon traversal complete!");
}


const stepLogger = createStepLogger("Adventure");
const tracker = scoreTracker();

console.log("=== Using logger ===");
traverseCanyon(stepLogger);

console.log("\n=== Using scoreTracker ===");
traverseCanyon(tracker);

console.log("\n=== Using dangerDetector ===");
traverseCanyon(dangerDetector);