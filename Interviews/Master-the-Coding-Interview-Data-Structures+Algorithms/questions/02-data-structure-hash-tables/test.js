//Google Question
//Given an array = [2,5,1,2,3,5,1,2,4]:
//It should return 2

//Given an array = [2,1,1,2,3,5,1,2,4]:
//It should return 1

//Given an array = [2,3,4,5]:
//It should return undefined

function firstRecurringCharacter1(input) {
  const seen = new Set();
  for (let i = 0; i < input.length; i++) {
    if (seen.has(input[i])) {
      return input[i];
    }
    seen.add(input[i]);
  }
}

function firstRecurringCharacter(input) {
  const seen = new Set();
  for (let i = 0; i < input.length; i++) {
    if (seen.has(input[i])) {
      return input[i];
    }
    seen.add(input[i]);
  }
}

console.log(firstRecurringCharacter([2, 1, 3, 2, 3, 5, 1, 2, 4])); //<!----> wrong
// console.log(firstRecurringCharacter2([2, 1, 1, 2, 3, 5, 1, 2, 4]));
// console.log(firstRecurringCharacter2([2, 3, 4, 5]));
// console.log(firstRecurringCharacter2([2, 5, 5, 2, 3, 5, 1, 2, 4]));
