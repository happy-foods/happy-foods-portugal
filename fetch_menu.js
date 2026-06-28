const fs = require('fs');

async function main() {
  const url = 'https://happy-foods-plan--ibtisamzim.replit.app/assets/index-B1bK4iTC.js';
  try {
    const res = await fetch(url);
    const text = await res.text();
    fs.writeFileSync('/menu_data.txt', text, 'utf8');
    console.log('Successfully saved JS content to /menu_data.txt, size:', text.length);
  } catch (err) {
    console.error('Error fetching:', err);
  }
}

main();
