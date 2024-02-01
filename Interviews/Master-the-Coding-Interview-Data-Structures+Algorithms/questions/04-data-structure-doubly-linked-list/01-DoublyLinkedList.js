class DoublyLinkedList {
  constructor(value) {
    this.head = {
      value: value,
      prev: null,
      next: null,
    };
    this.tail = this.head;
    this.length = 1;
  }

  append(value) {
    const node = new Node(value);
    node.prev = this.tail;
    this.tail.next = node;
    this.tail = node;
    this.length++;
    return this;
  }

  prepend(value) {
    const node = new Node(value);
    node.next = this.head;
    this.head.prev = node;
    this.head = node;
    this.length++;
    return this.printList();
  }

  insert(index, value) {
    if (index >= this.length) {
      return this.append(value);
    }
    const node = new Node(value);
    const leader = this.traverseToIndex(index - 1);
    node.next = leader.next;
    node.prev = leader;
    leader.next.prev = node;
    leader.next = node;
    this.length++;
    return this.printList();
  }

  remove(index) {
    const leader = this.traverseToIndex(index - 1);
    const unwantedNode = leader.next;
    leader.next = unwantedNode.next;
    unwantedNode.next.prev = leader;
    this.length--;
    return this.printList();
  }

  traverseToIndex(index) {
    let count = 0;
    let currentNode = this.head;
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
    this.prev = null;
    this.next = null;
  }
}

let myLinkedList = new DoublyLinkedList(10);
myLinkedList.append(5);
myLinkedList.append(16);
myLinkedList.append(19);
myLinkedList.prepend(100);
console.log(myLinkedList);

myLinkedList.insert(3, 90);
console.log(myLinkedList);
myLinkedList.remove(3);
console.log(myLinkedList);
