# The Wizard’s Variable Vault
This Node.js script simulates a wizard managing a magical vault using variables, operators, and conditional logic.

### Inputs Requested
- Wizard name 
- Wizard rank 
- Number of spells to craft

### Features Implemented:
- Wizard profile setup using user input 
- Rank selection using numeric choice (1–3)
- Randomized inventory values:
  - Gold coins 
  - Mana crystals 
  - Vault key (true/false)
  - Vault security level 
- Spell cost calculator (3 mana + 10 gold per spell)
- Resource affordability check 
- Inventory deduction when crafting succeeds 
- Message showing missing resources if crafting fails 
- Vault access rules using logical operators

### How to Run:
1. Make sure Node.js is installed.
2. Open a terminal in the project folder.
3. Run: `node wizards-vault.js`