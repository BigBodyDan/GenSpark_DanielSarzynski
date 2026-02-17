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

async function runVault() {

    const wizardName = await ask("Enter your wizard name: ");

    console.log("\nChoose your rank:");
    console.log("1 - Apprentice");
    console.log("2 - Adept");
    console.log("3 - Master");

    const rankChoice = await ask("");

    let wizardRank;

    switch (rankChoice) {
        case "1": wizardRank = "Apprentice"; break;
        case "2": wizardRank = "Adept"; break;
        case "3": wizardRank = "Master"; break;
        default: wizardRank = "Apprentice"; break;
    }

    console.log(`\nWelcome ${wizardRank} ${wizardName} to the Wizard's Vault.\n`);

    // Random Vault Inventory
    let goldCoins = Math.floor(Math.random() * 201) + 50;       // 50–250
    let manaCrystals = Math.floor(Math.random() * 51) + 20;    // 20–70
    let hasKey = Math.random() < 0.6;                           // true or false
    let potionName = "Healing Potion";
    let potionCount = Math.floor(Math.random() * 10) + 1;        // 1–10
    let vaultSecurityLevel = Math.floor(Math.random() * 5) + 1; // 1–5

    console.log("--- Current Vault Status ---");
    console.log(`Gold Coins: ${goldCoins}`);
    console.log(`Mana Crystals: ${manaCrystals}`);
    console.log(`Has Key: ${hasKey}`);
    console.log(`Potion: ${potionName}`);
    console.log(`Potion Count: ${potionCount}`);
    console.log(`Vault Security Level: ${vaultSecurityLevel}`);
    console.log("-----------------------------\n");

    const spellInput = await ask("How many spells would you like to craft? ");
    const spellCount = Number(spellInput);

    const manaCostPerSpell = 3;
    const goldCostPerSpell = 10;

    const totalManaCost = spellCount * manaCostPerSpell;
    const totalGoldCost = spellCount * goldCostPerSpell;

    console.log(`\nTo craft ${spellCount} spell(s):`);
    console.log(`Gold needed: ${totalGoldCost}`);
    console.log(`Mana needed: ${totalManaCost}\n`);

    const enoughMana = manaCrystals >= totalManaCost;
    const enoughGold = goldCoins >= totalGoldCost;

    if (enoughMana && enoughGold) {
        console.log("You have enough resources. Crafting spells...\n");

        manaCrystals -= totalManaCost;
        goldCoins -= totalGoldCost;

        console.log("--- Updated Inventory ---");
        console.log(`Gold Coins: ${goldCoins}`);
        console.log(`Mana Crystals: ${manaCrystals}`);
    } else {
        console.log("You do not have enough resources.");

        if (!enoughMana && !enoughGold) {
            console.log("You are missing both mana crystals and gold coins.");
        } else if (!enoughMana) {
            console.log("You are missing mana crystals.");
        } else if (!enoughGold) {
            console.log("You are missing gold coins.");
        }
    }

    const vaultOpens = (hasKey === true && vaultSecurityLevel <= 3) || wizardRank === "Master";

    console.log("\n--- Vault Access Check ---");

    if (vaultOpens) {
        console.log("Vault Opened");
    } else {
        console.log("Access Denied");
    }

    rl.close();
}

runVault();