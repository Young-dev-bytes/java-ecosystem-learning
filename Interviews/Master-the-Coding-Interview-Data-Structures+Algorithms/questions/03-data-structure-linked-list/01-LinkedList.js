class LinkedList {
  constructor(value) {
    this.head = {
      value: value,
      next: null,
    };

    this.tail = this.head;
    this.length = 1;
  }

  append(value) {
    // let node = {
    //   value: value,
    //   next: null,
    // };
    const node = new Node(value);

    this.tail.next = node;
    this.tail = node;
    this.length++;
    console.log(this);
    return this;
  }

  prepend(value) {
    // let node = {
    //   value: value,
    //   next: null,
    // };
    const node = new Node(value);
    node.next = this.head;
    this.head = node;
    this.length++;
    return this;
  }

  insert(index, value) {
    if (index >= this.length) {
      return this.append(value);
    }
    const node = new Node(value);
    const leader = this.traverseToIndex(index - 1);
    const holdPointer = leader.next;
    leader.next = node;
    node.next = holdPointer;
    this.length++;
    return this;
  }

  remove(index) {
    const leader = this.traverseToIndex(index - 1);

    const unwantedNode = leader.next;
    leader.next = unwantedNode.next;
    this.length--;
    return this.printList();
  }

  traverseToIndex(index) {
    let count = 0;
    let currentNode = this.head;
    // for (let i = 0; i < index; i++) {
    //   leader = leader.next;
    // }
    while (count !== index) {
      currentNode = currentNode.next;
      count++;
    }
    return currentNode;
  }

  printList() {
    const printArray = [];
    let currentNode = this.head;
    while (currentNode) {
      printArray.push(currentNode.value);
      currentNode = currentNode.next;
    }
    console.log(printArray);
  }
}

class Node {
  constructor(value) {
    this.value = value;
    this.next = null;
  }
}

let myLinkedList = new LinkedList(10);
myLinkedList.append(5);
myLinkedList.append(16);
myLinkedList.append(19);
myLinkedList.prepend(100);
console.log(myLinkedList);

myLinkedList.insert(3, 90);
console.log(myLinkedList);
myLinkedList.remove(3);
console.log(myLinkedList);
