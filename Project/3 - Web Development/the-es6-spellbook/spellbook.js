const spells = [
    { name: "Fireball", element: "Fire", power: 80, manaCost: 30 },
    { name: "Frost Lance", element: "Ice", power: 65, manaCost: 20 },
    { name: "Thunder Bolt", element: "Lightning", power: 90, manaCost: 40 },
    { name: "Earthquake", element: "Earth", power: 85, manaCost: 35 },
    { name: "Whirlwind", element: "Air", power: 60, manaCost: 15 },
    { name: "Water Cannon", element: "Water", power: 70, manaCost: 25 },
    { name: "Taunt", element: "Dark", power: 75, manaCost: 28 },
    { name: "Flash", element: "Light", power: 88, manaCost: 38 },
    { name: "Blaze Kick", element: "Fire", power: 85, manaCost: 42 },
    { name: "Leaf Storm", element: "Earth", power: 60, manaCost: 12 }
];

// Format spell for display
const formatSpell = ({ name, element, power, manaCost }) =>
    `${getElementIcon(element)} ${name} — Power: ${power}, Mana: ${manaCost}`;

// Get icon based on element
const getElementIcon = (element) => {
    const icons = {
        Fire: "🔥",
        Water: "💧",
        Earth: "🌍",
        Air: "💨",
        Ice: "❄️",
        Lightning: "⚡️",
        Dark: "🌑",
        Light: "✨ "
    };

    return icons[element] || "🔮";
};

// Calculate average power
const calculateAveragePower = (spellArray) => {
    const totalPower = spellArray.reduce((sum, { power }) => sum + power, 0);
    return totalPower / spellArray.length;
};

// Highest power spell
const getHighestPowerSpell = (spellArray) =>
    spellArray.reduce((highest, current) =>
        current.power > highest.power ? current : highest
    );

const getSpellSummary = (spellArray) => {
    const totalSpells = spellArray.length;
    const averagePower = calculateAveragePower(spellArray);
    const { name: strongestSpellName } = getHighestPowerSpell(spellArray);

    return `
=== Spell Summary ===
Total Spells: ${totalSpells}
Average Power: ${averagePower.toFixed(2)}
Strongest Spell: ${strongestSpellName}
`;
};

//Output
console.log("=== Spellbook ===\n");

// Destructuring array
const [firstSpell, secondSpell] = spells;
console.log("First Two Spells Preview:");
console.log(formatSpell(firstSpell));
console.log(formatSpell(secondSpell));
console.log("\nAll Spells:");

spells.forEach(spell => console.log(formatSpell(spell)));

console.log(getSpellSummary(spells));