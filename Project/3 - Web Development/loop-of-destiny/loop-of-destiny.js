const readline = require("readline");
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

function ask(question) {
    return new Promise((resolve) => {
        rl.question(question, (answer) => {
            resolve(answer);
        });
    });
}

async function startGame() {

    const playerName = await ask("Enter your name, challenger: ");

    let turnsRemaining = 10;
    let energy = 10;
    let score = 0;
    let lives = 3;

    console.log(`\nWelcome, ${playerName}. The Loop of Destiny begins.\n`);

    let turn = 1;

    // Main Game Loop
    while (turnsRemaining > 0) {

        console.log("--------------------------------------------------");
        console.log(`Turn: ${turn}`);
        console.log(`Energy: ${energy} | Score: ${score} | Lives: ${lives} | Turns Left: ${turnsRemaining}`);
        console.log("Choose your action:");
        console.log("1. Attack");
        console.log("2. Rest");
        console.log("3. Focus");

        const choice = await ask("Enter 1, 2, or 3: ");

        // Turn-Based Decisions
        if (choice === "1") {
            console.log("You attack the dragon.");

            if (energy < 2) {
                console.log("You are too tired to attack.");
                continue;
            }

            energy -= 2;
            score += 3;

            if (Math.random() < 0.20) {
                lives--;
                console.log("The dragon strikes back. You lose 1 life.");
            }

        } else if (choice === "2") {
            console.log("You rest and recover energy.");
            energy += 3;

            if (energy > 10) {
                energy = 10;
            }

        } else if (choice === "3") {
            console.log("You focus your strength.");
            energy -= 1;
            score += 1;

        } else {
            console.log("Invalid choice. You lose your turn.");
        }

        // Win Condition
        if (score >= 20) {
            console.log(`\nVictory! ${playerName} has mastered the Loop of Destiny.`);
            break;
        }

        // Lose Conditions
        if (lives <= 0) {
            console.log(`\nDefeat. ${playerName} has fallen in the trial.`);
            break;
        }

        if (energy <= 0) {
            console.log("\nYou collapse from exhaustion. The trial ends.");
            break;
        }

        turnsRemaining--;
        turn++;
    }

    // Max Turns Reached
    if (turnsRemaining === 0 && score < 20 && lives > 0 && energy > 0) {
        console.log("\nThe trial fades away. You did not reach mastery in time.");
    }

    console.log("\nGame Over.");
    rl.close();
}

startGame();