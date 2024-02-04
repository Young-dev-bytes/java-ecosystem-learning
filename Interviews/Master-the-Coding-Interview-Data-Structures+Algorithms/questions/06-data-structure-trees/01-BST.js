class Node {
  constructor(value) {
    this.left = null;
    this.right = null;
    this.value = value;
  }
}

class BinarySearchTree {
  constructor() {
    this.root = null;
  }
  insert(value) {
    const newNode = new Node(value);

    if (!this.root) {
      this.root = newNode;
      return this;
    }

    let currentNode = this.root;

    while (true) {
      if (value < currentNode.value) {
        if (!currentNode.left) {
          currentNode.left = newNode;
          return this;
        }
        currentNode = currentNode.left;
      } else {
        if (!currentNode.right) {
          currentNode.right = newNode;
          return this;
        }
        currentNode = currentNode.right;
      }
    }
  }
  lookup(value) {
    if (!this.root) return false;
    let currentNode = this.root;
    while (currentNode) {
      if (value < currentNode.value) {
        if (currentNode.left) {
          currentNode = currentNode.left;
        }
      } else if (value > currentNode.value) {
        if (currentNode.right) {
          currentNode = currentNode.right;
        }
      } else if (value === currentNode.value) {
        return currentNode;
      }
    }
    return false;
  }

  remove(value) {
    if (!this.root) {
      return false;
    }
    let currentNode = this.root;
    let parentNode = null;

    while (currentNode) {
      if (value < currentNode.value) {
        if (currentNode.left) {
          parentNode = currentNode;
          currentNode = currentNode.left;
        }
      } else if (value > currentNode.value) {
        if (currentNode.right) {
          parentNode = currentNode;
          currentNode = currentNode.right;
        }
      } else if (value === currentNode.value) {
        //we have a match, get to work

        // option1: no right child
        if (currentNode.right === null) {
          if (parentNode === null) {
            this.root = currentNode.left;
          } else {
            //if parent > current value, make current left child a child of parent
            if (currentNode.value < parentNode.value) {
              parentNode.left = currentNode.left;
            }

            //if parent < current value, make left child a right child of parent
            else if (currentNode.value > parentNode) {
              parentNode.right = currentNode.left;
            }
          }
        }

        //Option 2: Right child which doesnt have a left child
        else if (currentNode.right.left === null) {
          currentNode.right.left = currentNode.left;
          if (parentNode === null) {
            this.root = currentNode.right;
          } else {
            //if parent > current, make right child of the left the parent
            if (currentNode.value < parentNode.value) {
              parentNode.left = currentNode.right;
            }
            //if parent < current, make right child a right child of the parent
            else if (currentNode.value > parentNode.value) {
              parentNode.right = currentNode.right;
            }
          }
        }

        //Option 3: Right child that has a left child
        else {
          let leftmost = currentNode.right.left;
          let leftmostParent = currentNode.right;

          while (leftmost.left !== null) {
            leftmostParent = leftmost;
            leftmost = leftmost.left;
          }
          // todo://
        }
      }
    }
  }
}

const tree = new BinarySearchTree();
tree.insert(9);
tree.insert(4);
tree.insert(6);

tree.insert(20);
tree.insert(170);
tree.insert(15);
tree.insert(1);
// tree.insert(170);
console.log(traverse(tree.root));
// console.log(tree.lookup(20));
console.log(traverse(tree.lookup(171)));
//     9
//  4     20
//1  6  15  170

function traverse(node) {
  const tree = { value: node.value };
  tree.left = node.left === null ? null : traverse(node.left);
  tree.right = node.right === null ? null : traverse(node.right);
  return tree;
}
