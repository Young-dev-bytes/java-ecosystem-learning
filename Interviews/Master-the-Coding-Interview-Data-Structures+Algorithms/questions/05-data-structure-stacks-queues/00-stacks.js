class Node {
  constructor(value) {
    this.value = value;
    this.next = null;
  }
}

class Stack {
  constructor() {
    this.top = null;
    this.bottom = null;
    this.length = 0;
  }

  // find first element
  peek() {
    return this.top;
  }

  // add one element in the end
  push(value) {
    const newNode = new Node(value);
    if (this.length === 0) {
      this.top = newNode;
      this.bottom = newNode;
    } else {
      const holdingPoint = this.top;
      this.top = newNode;
      this.top.next = holdingPoint;
    }
    this.length++;
    return this;
  }

  // remove last value
  pop() {
    if (!this.top) return null;
    if (this.top === this.bottom) {
      this.bottom = null;
    }

    const holdingPoint = this.top;
    this.top = holdingPoint.next;
    this.length--;
    return this;
  }
}

const myStack = new Stack();
console.log(myStack.push("google"));
console.log(myStack.push("google2"));
console.log(myStack.peek());
console.log(myStack.pop());
console.log(myStack.pop());
