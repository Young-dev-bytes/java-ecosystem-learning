const array1 = ["a", "b", "c"];
const array2 = ["c", "y", "e"];

function containsCommonItems(arr1, arr2) {
  if (!arr1 || !arr2) return;

  for (let i = 0; i < arr1.length; i++) {
    for (let j = 0; j < arr2.length; j++) {
      if (array1[i] === array2[j]) {
        return true;
      }
    }
  }

  return false;
}

// O(n^2)
console.log(containsCommonItems(array1, array2));

/* array = obj {
      a: true,
      b: true,
      c: true,
      x: true
   }

   array2[index] === obj.properties
*/
function containsCommonItems2(arr1, arr2) {
  // loop through first array and
  // create object where properties === items in the array

  if (!arr1 || !arr2) return;
  let map = {};
  for (let i = 0; i < arr1.length; i++) {
    if (!map[arr1[i]]) {
      map[arr1[i]] = true;
    }
  }

  // loop through second array and
  // check if item in second array exists on created object
  for (j = 0; j < arr2.length; j++) {
    if (map[arr2[j]]) return true;
  }
  return false;
}

// O(a) Space Complexity
// O(n + n) => (a + b) Time Complexity
console.log(containsCommonItems2(array1, array2));

function containsCommonItems3(arr1, arr2) {
  return arr1.some((item) => arr2.includes(item));
}

console.log(containsCommonItems3(array1, array2));
