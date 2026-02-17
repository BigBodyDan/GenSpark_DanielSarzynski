const readline = require("readline");

// validation
function isValidNumber(value) {
    return typeof value === "number" && !isNaN(value);
}


// 1. Calculate total with tax
function calculateTotalWithTax(amount, taxRate) {
    if (!isValidNumber(amount) || !isValidNumber(taxRate)) {
        return "Invalid input.";
    }
    return amount + (amount * taxRate);
}

// 2. Check if number is even
function isEven(number) {
    if (!isValidNumber(number)) {
        return "Invalid input.";
    }
    return number % 2 === 0;
}

// 3. Convert text to Title Case
function toTitleCase(text) {
    if (typeof text !== "string") {
        return "Invalid input.";
    }
    return text
        .toLowerCase()
        .split(" ")
        .map(word => word.charAt(0).toUpperCase() + word.slice(1))
        .join(" ");
}

// 4. Find max of three numbers
function findMax(a, b, c) {
    if (!isValidNumber(a) || !isValidNumber(b) || !isValidNumber(c)) {
        return "Invalid input.";
    }
    return Math.max(a, b, c);
}

// 5. Format username
function formatUsername(firstName, lastName) {
    if (typeof firstName !== "string" || typeof lastName !== "string") {
        return "Invalid input.";
    }
    return (firstName.trim() + "." + lastName.trim()).toLowerCase();
}

// 6. Sum helper used by calculateAverage
function sumArray(numbers) {
    return numbers.reduce((sum, num) => sum + num, 0);
}

// 7. Calculate average
function calculateAverage(numbersArray) {
    if (!Array.isArray(numbersArray) || numbersArray.length === 0) {
        return "Invalid input.";
    }

    for (let num of numbersArray) {
        if (!isValidNumber(num)) {
            return "Invalid input.";
        }
    }

    const total = sumArray(numbersArray);
    return total / numbersArray.length;
}

// dispatcher
function runTool(choice, rl) {
    switch (choice) {
        case "1":
            rl.question("Enter amount: ", amount => {
                rl.question("Enter tax rate (e.g., 0.06): ", tax => {
                    const result = calculateTotalWithTax(Number(amount), Number(tax));
                    console.log("Result:", result);
                    showMenu(rl);
                });
            });
            break;

        case "2":
            rl.question("Enter number: ", num => {
                const result = isEven(Number(num));
                console.log("Result:", result);
                showMenu(rl);
            });
            break;

        case "3":
            rl.question("Enter text: ", text => {
                const result = toTitleCase(text);
                console.log("Result:", result);
                showMenu(rl);
            });
            break;

        case "4":
            rl.question("Enter three numbers separated by commas: ", input => {
                const parts = input.split(",").map(Number);
                const result = findMax(parts[0], parts[1], parts[2]);
                console.log("Result:", result);
                showMenu(rl);
            });
            break;

        case "5":
            rl.question("Enter first name: ", first => {
                rl.question("Enter last name: ", last => {
                    const result = formatUsername(first, last);
                    console.log("Result:", result);
                    showMenu(rl);
                });
            });
            break;

        case "6":
            rl.question("Enter numbers separated by commas: ", input => {
                const numbers = input.split(",").map(Number);
                const result = calculateAverage(numbers);
                console.log("Result:", result);
                showMenu(rl);
            });
            break;

        case "0":
            console.log("Exiting Function Forge.");
            rl.close();
            break;

        default:
            console.log("Invalid selection.");
            showMenu(rl);
    }
}

// Menu
function showMenu(rl) {
    console.log("\n--- Function Forge ---");
    console.log("1. Calculate Total With Tax");
    console.log("2. Check If Number Is Even");
    console.log("3. Convert To Title Case");
    console.log("4. Find Max Of Three Numbers");
    console.log("5. Format Username");
    console.log("6. Calculate Average");
    console.log("0. Exit");

    rl.question("Choose a tool: ", choice => {
        runTool(choice, rl);
    });
}

// Main
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

showMenu(rl);
