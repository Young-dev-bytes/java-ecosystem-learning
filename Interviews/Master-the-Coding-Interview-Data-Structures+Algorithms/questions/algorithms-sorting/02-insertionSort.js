const numbers = [99, 44, 6, 2, 1, 5, 63, 87, 283, 4, 0];

// numbers.splice(1, 1);
// const newArr = numbers.splice(1, 0, 8);
// console.log(newArr);
// console.log(numbers);

function insertionSort(array) {
  const length = array.length;

  for (let i = 0; i < length; i++) {
    if (array[i] < array[0]) {
      // Move number to the first position
      array.unshift(array.splice(i, 1)[0]);
    } else {
      // Find where number should go
      for (let j = 1; j < i; j++) {
        if (array[i] > array[j - 1] && array[i] < array[j]) {
          // move the number to the right root
          array.splice(j, 0, array.splice(i, 1)[0]);
        }
      }
    }
  }
}

insertionSort(numbers);
console.log(numbers);
