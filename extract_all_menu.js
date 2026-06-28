const fs = require('fs');
const text = fs.readFileSync('/menu_data.txt', 'utf8');

// We see "Tuesday", "Wednesday", "Rajma", "Curry Pakora".
// Let's find the index of "Tuesday" or "Black Chana" and print 5000 characters around it.
const idx = text.indexOf('Black Chana');
if (idx !== -1) {
  console.log('Found "Black Chana" at', idx);
  console.log('Printing surrounding text (4000 chars):');
  console.log(text.substring(idx - 1000, idx + 3000));
} else {
  console.log('"Black Chana" not found');
}
