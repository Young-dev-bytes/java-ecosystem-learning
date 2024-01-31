let user = {
  age: 54,
  name: "Young",
  magic: true,
  scream: function () {
    console.log("ahhhhhh");
  },
};

console.log(user.age); // O(1)
user.spell = "Ariaaaaa"; // O(1)
user.scream(); // O(1)
user;
