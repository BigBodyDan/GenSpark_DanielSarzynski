function simulateRunner( runner ) {
    return new Promise((resolve, reject) => {
        // +- 20% time
        const variation = 0.8 + Math.random() * 0.4;
        const timeMs = Math.floor(runner.duration * variation);

        console.log(`${runner.name} starting...`);
        setTimeout(() => {
            if (runner.fail && Math.random() < 0.25 ) { //25% to fail
                const error = new Error(`${runner.name} failed`);
                error.timeMs = runner.duration;
                error.status = 'Failed';
                error.runnerName = runner.name;
                reject(error);
            } else {
                console.log(`${runner.name} finished!`);
                resolve({ timeMs: timeMs, status: 'Success' });
            }
        }, timeMs);
    });
}

// main
async function asyncRelay() {
    const runners = [
        { name: 'Runner 1', duration: 1000, fail: false },
        { name: 'Runner 2', duration: 1500, fail: true }, // This runner can fail
        { name: 'Runner 3', duration: 1200, fail: false },
        { name: 'Runner 4', duration: 2000, fail: true }, // This runner can fail
        { name: 'Runner 5', duration: 800, fail: false },
        { name: 'Runner 6', duration: 1000, fail: false },
    ];

    const results = [];
    let totalTime = 0;
    let completedRunners = 0;

    try {
        for (const runner of runners) {
            const result = await simulateRunner(runner);
            results.push(result);
            totalTime += result.timeMs;
            completedRunners++;
        }
        console.log('\nRelay finished successfully!');
    } catch (error) {
        results.push(error);
        totalTime += error.timeMs;
        console.log(`\nRelay failed at ${error.runnerName}!`);
    }

    console.log('\n--- Final Summary ---');
    console.log(`Total time: ${totalTime} ms`);
    console.log(`Completed runners: ${completedRunners}`);
    console.log(`Final status: ${results.some(r => r.status === 'Failed') ? 'Failed' : 'Success'}`);
}

// execute
asyncRelay();
