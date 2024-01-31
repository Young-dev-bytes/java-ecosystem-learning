class Plyer {
  constructor(name, type) {
    console.log("Player", this);
    this.name = name;
    this.type = type;
  }

  introduce() {
    console.log(`hi, I'm ${this.name}, I'm a ${this.type}`);
  }
}

class Wizard extends Plyer {
  constructor(name, type) {
    super(name, type);
    console.log("Wizard", this);
  }

  play() {
    console.log(`WEEEEE, I'm a ${this.type}`);
  }
}

const wizard1 = new Wizard("Shelly", "Healer");
const wizard2 = new Wizard("Young", "Darker");

wizard1.introduce();
wizard1.play();
