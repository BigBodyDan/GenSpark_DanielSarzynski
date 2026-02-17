function randomPrice(){ // random between 1 - 100
    return Math.floor(Math.random() * 100) + 1
}

let roster = [];
while (roster.length < 10) {
    roster.push(randomPrice());
}

console.log("Initial Roster:", roster);

for (let round = 1; round <= 5; round++) {
    console.log("\n--- Round " + round + " ---");

    if (round === 1) {
        let end = randomPrice();
        let start = randomPrice();
        roster.push(end); // add to end
        roster.unshift(start); // add to start
        console.log(`Added ${start} and ${end}`);
    }

    else if (round === 2) {
        let addition = randomPrice() % 10;
        roster[2] = roster[2] + addition;
        console.log(`Updated index 2 by adding ${addition}`);
    }

    else if (round === 3) {
        // Sort roster descending
        roster.sort(function (a, b) {return b - a;});
        console.log("Sorted roster in descending order");
    }

    else if (round === 4) {
        let end = roster.pop(); // remove from end
        let start = roster.shift(); // remove from start

        console.log(`Removed last entry: ${end}`);
        console.log(`Removed first entry: ${start}`);
    }

    else if (round === 5) {
        // Search for a value
        let valueToFind = randomPrice();
        console.log(`Looking for value ${valueToFind}`);
        if (roster.includes(valueToFind)) {
            console.log(`Value ${valueToFind} exists in roster`);
        } else {
            console.log(`Value ${valueToFind} not found`);
        }
    }

    console.log("Roster:", roster);
}

console.log("\nFinal Roster:", roster);

// Stats Board
let totalEntries = roster.length;
let sum = 0;
let highest = Math.max(...roster);
let lowest = Math.min(...roster);
let countAbove50 = 0;

for (let price of roster) {
    sum += price;

    if (price > 50) {
        countAbove50++;
    }
}

let average = sum / totalEntries;

console.log("\n----- STATS BOARD -----");
console.log("Total Entries:", totalEntries);
console.log("Sum of Values:", sum);
console.log("Average Value:", average.toFixed(2));
console.log("Highest Value:", highest);
console.log("Lowest Value:", lowest);
console.log("Count > 50:", countAbove50);
