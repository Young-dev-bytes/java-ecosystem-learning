const strings = ["a", "b", "c", "d"];

// push O(1)
strings.push("e"); // O(1)
console.log(strings);

// pop  O(1)
strings.pop();
strings.pop();
console.log(strings);

// unshift O(n)  Inserts new elements at the start of an array, and returns the new length of the array.
strings.unshift("c");
console.log(strings);

// splice O(n)
strings.splice(2, 0, "alien");
console.log(strings);
