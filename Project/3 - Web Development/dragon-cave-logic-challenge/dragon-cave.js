const readline = require("readline");

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

function ask(question) {
    return new Promise((resolve) => {
        rl.question(question, (answer) => {
            resolve(answer.trim().toLowerCase());
        });
    });
}

async function startGame() {
    console.log("------- Dragon Cave -------\n");

    const nameInput = await ask("What is your name, traveler? ");
    const playerName = nameInput || "Hero";

    console.log(`\nWelcome, ${playerName}. Your goal is to escape the cave with treasure.`);

    // player stats
    let health = 100;
    let gold = 0;
    let hasTorch = false;
    let inventory = [];
    let turns = 0;
    const maxTurns = 7;

    let gameOver = false;

    while (!gameOver) {
        turns++;

        if (turns > maxTurns) {
            console.log("\nThe dragon wakes due to too much noise...");
            console.log("The dragon burns you alive.");
            health = 0;
            gameOver = true;
            break;
        }

        console.log(`\nHealth: ${health} | Gold: ${gold} | Turns Left: ${maxTurns - turns + 1}`);
        console.log("Inventory:", inventory.length ? inventory.join(", ") : "Empty");

        // decision 1
        if (turns === 1) {
            const choice = await ask("\nYou see two paths: (1)Left or (2)Right? ");
            if (choice === "1") {
                console.log("You find a torch on the wall.");
                hasTorch = true;
                inventory.push("Torch");
            } else if (choice === "2") {
                console.log("You trip in the dark and hurt yourself.");
                health -= 20;
            }
        }

        // decision 2
        else if (turns === 2) {
            const choice = await ask("\nYou hear a noise. (1)Investigate or (2)Hide? ");
            if (choice === "1") {
                console.log("You find 20 gold.");
                gold += 20;
            } else if (choice === "2") {
                console.log("You stay safe but gain nothing.");
            }
        }

        // decision 3
        else if (turns === 3) {
            const choice = await ask("\nA sleeping dragon is ahead. (1)Sneak or (2)Attack? ");
            const dragonWake = Math.random() < 0.5;

            if (choice === "1") {
                if (dragonWake) {
                    console.log("The dragon wakes while you sneak!");
                    health -= 40;
                } else {
                    console.log("You sneak past safely and find 50 gold.");
                    gold += 50;
                }
            } else if (choice === "2") {
                console.log("You attack the dragon!");
                console.log("You gained 80 gold");
                health -= 50;
                gold += 80;
            }
        }

        // decision 4
        else if (turns === 4) {
            const choice = await ask("\nYou find a chest. (1)Open or (2)Ignore? ");
            if (choice === "1") {
                console.log("The chest contains a potion.");
                inventory.push("Potion");
            } else if (choice === "2"){
                console.log("You move on.");
            }
        }

        // decision 5
        else if (turns === 5) {
            const choice = await ask("\nYou face a cave troll. (1)Fight or (2)Run? ");
            if (choice === "1") {
                health -= 30;
                gold += 30;
                console.log("You defeat the troll but lose health.");
                console.log("You gained 30 gold");
            } else if (choice === "2") {
                console.log("You escape safely.");
            }
        }

        // decision 6
        else if (turns === 6) {
            const choice = await ask("You see a mysterious mushroom: (1)Eat it or (2)Ignore?");
            const poisonous  = Math.random() < 0.5;
            if (choice === "1") {
                if (poisonous) {
                    console.log("The mushroom was poisonous.");
                    console.log("You lose 25 health.");
                    health -= 25;
                } else {
                    console.log("The mushroom healed you.");
                    console.log("You gain 25 health.");
                    health += 25;
                }
            } else if (choice === "2") {
                console.log("You move on.");
            }
        }

        // decision 7
        else if (turns === 7) {
            const choice = await ask("\nYou see the cave exit. (1)Leave or (2)Search last room? ");
            if (choice === "2") {
                console.log("You search for treasure...");
                const trap = Math.random() < 0.5;
                if (trap) {
                    console.log("It was a trap! You lose health");
                    health -= 50;
                } else {
                    console.log("You find 50 gold!");
                    gold += 50;
                }
            }
            console.log("You head toward the exit...");
            gameOver = true;
        }

        if (health <= 0) {
            console.log("\nYou have died in the Dragon Cave. You Lose!");
            gameOver = true;
        }
    }

    // endings
    if (health > 0 && gold >= 100) {
        console.log(`\n${playerName} escaped with ${gold} gold! You Win!`);
    } else if (health > 0) {
        console.log(`\nYou escaped alive with ${gold} gold. You need 100 gold to win.`);
    }

    const replay = await ask("\nPlay again? (y/n) ");
    if (replay === "y") {
        console.log("\nRestarting game...\n");
        return startGame();
    } else {
        console.log("\nThanks for playing.");
        rl.close();
    }
}

startGame();
