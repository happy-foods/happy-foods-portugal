const fs = require('fs');

const text = fs.readFileSync('/menu_data.txt', 'utf8');

// Let's search for some meal array or list of meals.
// We can search for keywords.
console.log('--- Searching for menu items/meals ---');

// In React apps, meal data is often in an array like: [{id: "...", title: "..."}] or similar.
// Let's find any occurrences of "Acai" or "Avocado" or "sourdough" to see if there is a menu list.
const keywords = ['Acai', 'Avocado', 'Sourdough', 'salmon', 'chicken', 'salad', 'tofu', 'steak', 'yogurt'];
for (const kw of keywords) {
  const idx = text.indexOf(kw);
  if (idx !== -1) {
    console.log(`Found keyword "${kw}" at index ${idx}. Surrounding text:`);
    console.log(text.substring(Math.max(0, idx - 100), Math.min(text.length, idx + 200)));
    console.log('--------------------------------------------------');
  } else {
    console.log(`Keyword "${kw}" not found`);
  }
}

// Let's also look for plan details.
console.log('--- Searching for plan details (prices, subscription) ---');
const planKeywords = ['month', 'plan', 'price', 'delivery', 'Halal', 'Veg', 'Non-Veg'];
for (const kw of planKeywords) {
  const idx = text.indexOf(kw);
  if (idx !== -1) {
    console.log(`Found plan keyword "${kw}" at index ${idx}. Surrounding text:`);
    console.log(text.substring(Math.max(0, idx - 50), Math.min(text.length, idx + 150)));
    console.log('--------------------------------------------------');
  }
}
