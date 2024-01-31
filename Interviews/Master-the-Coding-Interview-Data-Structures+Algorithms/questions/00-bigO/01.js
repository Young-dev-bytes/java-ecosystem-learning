// [1, 2, 3, 9]; Sum = 8;
// [1,2,4,4]; Sum = 8
// [6, 4, 3, 2, 1, 7]  9
// Naive
function hasPairWithSum(arr, sum) {
  var len = arr.length;
  for (var i = 0; i < len - 1; i++) {
    for (var j = i + 1; j < len; j++) {
      if (arr[i] + arr[j] === sum) {
        return true;
      }
    }
  }
  return false;
}

// Better
function hasPairWithSum2(arr, sum) {
  const mySet = new Set();
  const len = arr.length;
  for (let i = 0; i < len; i++) {
    if (mySet.has(arr[i])) {
      return true;
    }
    mySet.add(sum - arr[i]);
    console.log(mySet);
  }
  return false;
}

const res = hasPairWithSum([6, 4, 3, 2, 1, 7], 19);
const res2 = hasPairWithSum2([6, 4, 3, 2, 1, 7], 19);
console.log(res);
console.log(res2);
