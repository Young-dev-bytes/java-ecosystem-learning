const numbers = [99, 44, 6, 2, 1, 5, 63, 87, 283, 4, 0];

function bubbleSortAsc(array) {
  //Code here
  const length = array.length;

  for (let i = 0; i < length; i++) {
    for (let j = 0; j < length - i; j++) {
      if (array[j] > array[j + 1]) {
        const temp = array[j];
        array[j] = array[j + 1];
        array[j + 1] = temp;
      }
    }
  }
}

function bubbleSortDesc(array) {
  //Code here
  const length = array.length;

  for (let i = 0; i < length; i++) {
    for (let j = 0; j < length - i; j++) {
      if (array[j] < array[j + 1]) {
        const temp = array[j];
        array[j] = array[j + 1];
        array[j + 1] = temp;
      }
    }
  }
}

bubbleSortAsc(numbers);
bubbleSortDesc(numbers);
console.log(numbers);
