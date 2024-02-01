let obj1 = { a: true };
let obj2 = obj1;
obj1.a = "Young";

delete obj1;

console.log(obj1);
console.log(obj2);
