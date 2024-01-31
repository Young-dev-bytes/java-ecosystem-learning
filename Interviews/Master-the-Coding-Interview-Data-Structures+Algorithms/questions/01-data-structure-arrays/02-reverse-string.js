function reverse(str) {
  if (!str || str.length < 2 || typeof str != "string") return "error";

  const backwords = [];
  const totalItems = str.length;
  for (let i = str.length - 1; i >= 0; i--) {
    backwords.push(str[i]);
  }

  console.log(backwords);
  return backwords.join("");
}

console.log(reverse("Young"));

console.log("Young".split("").reverse().join(""));

function reverse2(str) {
  return str.split("").reverse().join("");
}

const reverse3 = (str) => str.split("").reverse().join("");

const reverse4 = (str) => [...str].reverse().join("");

var str1 = "Qiu";
const [...str] = "Uooo";
console.log(str);
console.log(reverse4(str1));
console.log(str1);
