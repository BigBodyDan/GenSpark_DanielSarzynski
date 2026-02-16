const items = [
    { name: "Health Potion", category: "Potion", price: 25, power: 50 },
    { name: "Invisibility Cloak", category: "Clothing", price: 120, power: 80 },
    { name: "Fire Wand", category: "Weapon", price: 75, power: 90 },
    { name: "Teleport Scroll", category: "Scroll", price: 60, power: 70 },
    { name: "Magic Ring", category: "Accessory", price: 200, power: 95 },
    { name: "Speed Elixir", category: "Potion", price: 30, power: 40 },
    { name: "Iron Dagger", category: "Weapon", price: 100, power: 90 },
    { name: "Elven Bow", category: "Weapon", price: 150, power: 85 },
    { name: "Steel Helmet", category: "Clothing", price: 45, power: 20 },
    { name: "Book of Spells", category: "Scroll", price: 80, power: 75 },
    { name: "Ruby Amulet", category: "Accessory", price: 90, power: 60 },
    { name: "Phoenix Feather", category: "Ingredient", price: 180, power: 87 },
    { name: "Thunder Axe", category: "Weapon", price: 130, power: 88 },
    { name: "Defense Elixir", category: "Potion", price: 40, power: 55 },
    { name: "Fairy Dust", category: "Ingredient", price: 20, power: 30 },
    { name: "Wolf Talisman", category: "Accessory", price: 100, power: 75 },
    { name: "Quarter Staff", category: "Weapon", price: 200, power: 150 },
    { name: "Leather Tunic", category: "Clothing", price: 50, power: 40 },
    { name: "Mandrake Root", category: "Ingredient", price: 35, power: 20 },
    { name: "Iron Chestplate", category: "Clothing", price: 250, power: 100 }
];

// forEach
console.log("=== Magic Store Items ===");
items.forEach(item => {
    console.log(`${item.name} [${item.category}] - ${item.price}gp, Power: ${item.power}`);
});

// Filter arrays
const expensiveItems = items.filter(item => item.price > 100);
const potions = items.filter(item => item.category === "Potion");

console.log("\n=== Expensive Items (> 100gp) ===");
expensiveItems.forEach(item => console.log(item.name));

console.log("\n=== Potions ===");
potions.forEach(item => console.log(item.name));

// Map arrays
const itemNames = items.map(item => item.name);
const formattedDescriptions = items.map(item => `${item.name} costs ${item.price}gp and has ${item.power} power`);

console.log("\n=== Item Names ===");
console.log(itemNames);

console.log("\n=== Formatted Descriptions ===");
console.log(formattedDescriptions);

// Report object
const totalCount = items.length;
const filteredCount = expensiveItems.length;
const avgPrice = items.reduce((sum, item) => sum + item.price, 0) / totalCount;
const highestPowerItem = items.reduce((max, item) => item.power > max.power ? item : max, items[0]);

const report = {
    totalItems: totalCount,
    expensiveItemsCount: filteredCount,
    averagePrice: avgPrice.toFixed(2),
    highestPowerItem: highestPowerItem
};

console.log("\n=== Report ===");
console.log(report);