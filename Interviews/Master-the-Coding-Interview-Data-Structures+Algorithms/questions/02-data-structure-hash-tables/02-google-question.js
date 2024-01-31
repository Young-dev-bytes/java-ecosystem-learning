//Google Question
//Given an array = [2,5,1,2,3,5,1,2,4]:
//It should return 2

//Given an array = [2,1,1,2,3,5,1,2,4]:
//It should return 1

//Given an array = [2,3,4,5]:
//It should return undefined

function firstRecurringCharacter(input) {
  for (let i = 0; i < input.length; i++) {
    for (let j = i + 1; j < input.length; j++) {
      if (input[i] === input[j]) {
        return input[i];
      }
    }
  }

  // return undefined;
}

function firstRecurringCharacter2(input) {
  let map = {};
  for (let i = 0; i < input.length; i++) {
    if (map[input[i]] !== undefined) {
      return input[i];
    } else {
      map[input[i]] = i;
    }
  }
  // return undefined;
}

function firstRecurringCharacter3(input) {
  const seen = new Set();

  for (let i = 0; i < input.length; i++) {
    if (seen.has(input[i])) {
      return input[i];
    }
    seen.add(input[i]);
  }

  // 如果没有重复字符，返回undefined
  return undefined;
}

//Bonus... What if we had this:
// [2,5,5,2,3,5,1,2,4]
// return 5 because the pairs are before 2,2

console.log(firstRecurringCharacter([2, 1, 1, 2, 3, 5, 1, 2, 4])); //<!----> wrong
console.log(firstRecurringCharacter2([2, 1, 1, 2, 3, 5, 1, 2, 4]));
console.log(firstRecurringCharacter2([2, 3, 4, 5]));
console.log(firstRecurringCharacter2([2, 5, 5, 2, 3, 5, 1, 2, 4]));

// 示例使用：
console.log(firstRecurringCharacter3([2, 1, 1, 2, 3, 5, 1, 2, 4])); // 输出: 1
console.log(firstRecurringCharacter3([2, 5, 1, 2, 3, 5, 1, 2, 4])); // 输出: 2
