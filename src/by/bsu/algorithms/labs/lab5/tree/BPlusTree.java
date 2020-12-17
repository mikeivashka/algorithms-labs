package by.bsu.algorithms.labs.lab5.tree;

import lombok.Getter;

import java.util.*;

public class BPlusTree {
    int m;
    @Getter
    private InternalNode root;
    @Getter
    private LeafNode firstLeaf;

    public BPlusTree(int m) {
        this.m = m;
        this.root = null;
    }

    // Binary search program
    private int binarySearch(Integer[] dps, int numPairs, int t) {
        return Arrays.binarySearch(dps, 0, numPairs, t);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BPlusTree{");
        sb.append("m=").append(m);
        sb.append(", root=").append(root);
        sb.append(", firstLeaf=").append(firstLeaf);
        sb.append('}');
        return sb.toString();
    }

    // Find the leaf node
    private LeafNode findLeafNode(int key) {

        Integer[] keys = this.root.values;
        int i;

        for (i = 0; i < this.root.degree - 1; i++) {
            if (key < keys[i]) {
                break;
            }
        }

        Node child = this.root.childPointers[i];
        if (child instanceof LeafNode) {
            return (LeafNode) child;
        } else {
            return findLeafNode((InternalNode) child, key);
        }
    }

    private LeafNode findLeafNode(InternalNode node, int key) {

        Integer[] keys = node.values;
        int i;

        for (i = 0; i < node.degree - 1; i++) {
            if (key < keys[i]) {
                break;
            }
        }
        Node childNode = node.childPointers[i];
        if (childNode instanceof LeafNode) {
            return (LeafNode) childNode;
        } else {
            return findLeafNode((InternalNode) node.childPointers[i], key);
        }
    }

    private int findIndexOfPointer(Node[] pointers, LeafNode node) {
        int i;
        for (i = 0; i < pointers.length; i++) {
            if (pointers[i] == node) {
                break;
            }
        }
        return i;
    }

    // Get the mid point
    private int getMidpoint() {
        return (int) Math.ceil((this.m + 1) / 2.0) - 1;
    }

    // Balance the tree
    private void handleDeficiency(InternalNode in) {

        InternalNode sibling;
        InternalNode parent = in.parent;

        if (this.root == in) {
            for (int i = 0; i < in.childPointers.length; i++) {
                if (in.childPointers[i] != null) {
                    if (in.childPointers[i] instanceof InternalNode) {
                        this.root = (InternalNode) in.childPointers[i];
                        this.root.parent = null;
                    } else if (in.childPointers[i] instanceof LeafNode) {
                        this.root = null;
                    }
                }
            }
        } else if (in.leftSibling != null && in.leftSibling.isLendable()) {
            sibling = in.leftSibling;
        } else if (in.rightSibling != null && in.rightSibling.isLendable()) {
            sibling = in.rightSibling;
            int borrowedKey = sibling.values[0];
            Node pointer = sibling.childPointers[0];
            in.values[in.degree - 1] = parent.values[0];
            in.childPointers[in.degree] = pointer;
            parent.values[0] = borrowedKey;
            sibling.removePointer(0);
            Arrays.sort(sibling.values);
            sibling.removePointer(0);
            shiftDown(in.childPointers, 1);
        } else if (in.leftSibling != null && in.leftSibling.isMergeable()) {

        } else if (in.rightSibling != null && in.rightSibling.isMergeable()) {
            sibling = in.rightSibling;
            sibling.values[sibling.degree - 1] = parent.values[parent.degree - 2];
            Arrays.sort(sibling.values, 0, sibling.degree);
            parent.values[parent.degree - 2] = null;
            for (int i = 0; i < in.childPointers.length; i++) {
                if (in.childPointers[i] != null) {
                    sibling.prependChildPointer(in.childPointers[i]);
                    in.childPointers[i].parent = sibling;
                    in.removePointer(i);
                }
            }
            parent.removePointer(in);
            sibling.leftSibling = in.leftSibling;
        }
        if (parent != null && parent.isDeficient()) {
            handleDeficiency(parent);
        }
    }

    private boolean isEmpty() {
        return firstLeaf == null;
    }

    private int linearNullSearch(Integer[] dps) {
        for (int i = 0; i < dps.length; i++) {
            if (dps[i] == null) {
                return i;
            }
        }
        return -1;
    }

    private int linearNullSearch(Node[] pointers) {
        for (int i = 0; i < pointers.length; i++) {
            if (pointers[i] == null) {
                return i;
            }
        }
        return -1;
    }

    private void shiftDown(Node[] pointers, int amount) {
        Node[] newPointers = new Node[this.m + 1];
        for (int i = amount; i < pointers.length; i++) {
            newPointers[i - amount] = pointers[i];
        }
        pointers = newPointers;
    }

    private Node[] splitChildPointers(InternalNode in, int split) {

        Node[] pointers = in.childPointers;
        Node[] halfPointers = new Node[this.m + 1];

        for (int i = split + 1; i < pointers.length; i++) {
            halfPointers[i - split - 1] = pointers[i];
            in.removePointer(i);
        }

        return halfPointers;
    }

    private Integer[] splitDictionary(LeafNode ln, int split) {

        Integer[] values = ln.values;

        Integer[] halfDict = new Integer[this.m];

        for (int i = split; i < values.length; i++) {
            halfDict[i - split] = values[i];
            ln.delete(i);
        }

        return halfDict;
    }

    private void splitInternalNode(InternalNode in) {
        InternalNode parent = in.parent;
        int midpoint = getMidpoint();
        int newParentKey = in.values[midpoint];
        Integer[] halfKeys = splitKeys(in.values, midpoint);
        Node[] halfPointers = splitChildPointers(in, midpoint);

        in.degree = linearNullSearch(in.childPointers);

        InternalNode sibling = new InternalNode(this.m, halfKeys, halfPointers);
        for (Node pointer : halfPointers) {
            if (pointer != null) {
                pointer.parent = sibling;
            }
        }
        sibling.rightSibling = in.rightSibling;
        if (sibling.rightSibling != null) {
            sibling.rightSibling.leftSibling = sibling;
        }
        in.rightSibling = sibling;
        sibling.leftSibling = in;
        if (parent == null) {
            Integer[] keys = new Integer[this.m];
            keys[0] = newParentKey;
            InternalNode newRoot = new InternalNode(this.m, keys);
            newRoot.appendChildPointer(in);
            newRoot.appendChildPointer(sibling);
            this.root = newRoot;
            in.parent = newRoot;
            sibling.parent = newRoot;
        } else {
            parent.values[parent.degree - 1] = newParentKey;
            Arrays.sort(parent.values, 0, parent.degree);
            int pointerIndex = parent.findIndexOfPointer(in) + 1;
            parent.insertChildPointer(sibling, pointerIndex);
            sibling.parent = parent;
        }
    }

    private Integer[] splitKeys(Integer[] keys, int split) {
        Integer[] halfKeys = new Integer[this.m];
        keys[split] = null;
        for (int i = split + 1; i < keys.length; i++) {
            halfKeys[i - split - 1] = keys[i];
            keys[i] = null;
        }
        return halfKeys;
    }

    public void insert(int val) {
        if (isEmpty()) {
            LeafNode ln = new LeafNode(this.m, val);
            this.firstLeaf = ln;
        } else {
            LeafNode ln = (this.root == null) ? this.firstLeaf : findLeafNode(val);
            if (!ln.insert(val)) {
                ln.values[ln.numPairs] = val;
                ln.numPairs++;
                Arrays.sort(ln.values);
                int midpoint = getMidpoint();
                Integer[] halfDict = splitDictionary(ln, midpoint);
                if (ln.parent == null) {
                    Integer[] parentKeys = new Integer[this.m];
                    parentKeys[0] = halfDict[0];
                    InternalNode parent = new InternalNode(this.m, parentKeys);
                    ln.parent = parent;
                    parent.appendChildPointer(ln);
                } else {
                    int newParentKey = halfDict[0];
                    ln.parent.values[ln.parent.degree - 1] = newParentKey;
                    Arrays.sort(ln.parent.values, 0, ln.parent.degree);
                }

                LeafNode newLeafNode = new LeafNode(this.m, halfDict, ln.parent);

                int pointerIndex = ln.parent.findIndexOfPointer(ln) + 1;
                ln.parent.insertChildPointer(newLeafNode, pointerIndex);

                newLeafNode.rightSibling = ln.rightSibling;
                if (newLeafNode.rightSibling != null) {
                    newLeafNode.rightSibling.leftSibling = newLeafNode;
                }
                ln.rightSibling = newLeafNode;
                newLeafNode.leftSibling = ln;

                if (this.root == null) {

                    this.root = ln.parent;

                } else {
                    InternalNode in = ln.parent;
                    while (in != null) {
                        if (in.isOverfull()) {
                            splitInternalNode(in);
                        } else {
                            break;
                        }
                        in = in.parent;
                    }
                }
            }
        }
    }

    public Integer lookUpFromTop(int key) {
        if (isEmpty()) {
            return null;
        }
        LeafNode ln = (this.root == null) ? this.firstLeaf : findLeafNode(key);
        Integer[] dps = ln.values;
        int index = binarySearch(dps, ln.numPairs, key);
        if (index < 0) {
            return dps[-index - 1] != null ? dps[-index - 1] : ln.rightSibling.values[0];
        } else {
            return dps[index];
        }
    }

    public void DFSearch() {
        if (this.root != null) traverseInOrder(this.root, new HashSet<>());
    }

    private void traverseInOrder(Node internalNode, Set<Node> visited) {
        if (internalNode == null) return;
        visited.add(internalNode);
        System.out.println(Arrays.toString(internalNode.getValues()));
        if (internalNode instanceof InternalNode) {
            Node[] childNodes = ((InternalNode) internalNode).childPointers;
            for (int i = 0; i < m; i++) {
                if (!visited.contains(childNodes[i])) {
                    traverseInOrder(((InternalNode) internalNode).childPointers[i], visited);
                }
            }
        }


    }

    public Integer search(int key) {
        if (isEmpty()) {
            return null;
        }
        LeafNode ln = (this.root == null) ? this.firstLeaf : findLeafNode(key);
        Integer[] dps = ln.values;
        int index = binarySearch(dps, ln.numPairs, key);

        if (index < 0) {
            return null;
        } else {
            return dps[index];
        }
    }

    public List<Integer> search(int lowerBound, int upperBound) {
        ArrayList<Integer> values = new ArrayList<>();
        LeafNode currNode = this.firstLeaf;
        while (currNode != null) {
            Integer[] dps = currNode.values;
            for (Integer dp : dps) {
                if (dp == null) {
                    break;
                }
                if (lowerBound <= dp && dp <= upperBound) {
                    values.add(dp);
                }
            }
            currNode = currNode.rightSibling;
        }
        return values;
    }

    public abstract class Node {
        InternalNode parent;

        public abstract Integer[] getValues();
    }

    public class InternalNode extends Node {
        int maxDegree;
        int minDegree;
        int degree;
        InternalNode leftSibling;
        InternalNode rightSibling;
        Integer[] values;
        @Getter
        Node[] childPointers;

        private InternalNode(int m, Integer[] values) {
            this.maxDegree = m;
            this.minDegree = (int) Math.ceil(m / 2.0);
            this.degree = 0;
            this.values = values;
            this.childPointers = new Node[this.maxDegree + 1];
        }

        private InternalNode(int m, Integer[] values, Node[] pointers) {
            this.maxDegree = m;
            this.minDegree = (int) Math.ceil(m / 2.0);
            this.degree = linearNullSearch(pointers);
            this.values = values;
            this.childPointers = pointers;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("InternalNode{");
            sb.append("maxDegree=").append(maxDegree);
            sb.append(", minDegree=").append(minDegree);
            sb.append(", degree=").append(degree);
            sb.append(", leftSibling=").append(leftSibling);
            sb.append(", rightSibling=").append(rightSibling);
            sb.append(", values=").append(Arrays.toString(values));
            sb.append(", childPointers=").append(Arrays.toString(childPointers));
            sb.append('}');
            return sb.toString();
        }

        @Override
        public Integer[] getValues() {
            return values;
        }

        private void appendChildPointer(Node pointer) {
            this.childPointers[degree] = pointer;
            this.degree++;
        }

        private int findIndexOfPointer(Node pointer) {
            for (int i = 0; i < childPointers.length; i++) {
                if (childPointers[i] == pointer) {
                    return i;
                }
            }
            return -1;
        }

        private void insertChildPointer(Node pointer, int index) {
            if (degree - index >= 0) System.arraycopy(childPointers, index, childPointers, index + 1, degree - index);
            this.childPointers[index] = pointer;
            this.degree++;
        }

        private boolean isDeficient() {
            return this.degree < this.minDegree;
        }

        private boolean isLendable() {
            return this.degree > this.minDegree;
        }

        private boolean isMergeable() {
            return this.degree == this.minDegree;
        }

        private boolean isOverfull() {
            return this.degree == maxDegree + 1;
        }

        private void prependChildPointer(Node pointer) {
            if (degree - 1 + 1 >= 0) System.arraycopy(childPointers, 0, childPointers, 1, degree - 1 + 1);
            this.childPointers[0] = pointer;
            this.degree++;
        }

        private void removeKey(int index) {
            this.values[index] = null;
        }

        private void removePointer(int index) {
            this.childPointers[index] = null;
            this.degree--;
        }

        private void removePointer(Node pointer) {
            for (int i = 0; i < childPointers.length; i++) {
                if (childPointers[i] == pointer) {
                    this.childPointers[i] = null;
                }
            }
            this.degree--;
        }
    }

    public class LeafNode extends Node {
        int maxNumPairs;
        int minNumPairs;
        int numPairs;
        LeafNode leftSibling;
        LeafNode rightSibling;
        private Integer[] values;

        public LeafNode(int m, Integer dp) {
            this.maxNumPairs = m - 1;
            this.minNumPairs = m / 2 - 1;
            this.values = new Integer[m];
            this.numPairs = 0;
            this.insert(dp);
        }

        public LeafNode(int m, Integer[] dps, InternalNode parent) {
            this.maxNumPairs = m - 1;
            this.minNumPairs = m / 2 - 1;
            this.values = dps;
            this.numPairs = linearNullSearch(dps);
            this.parent = parent;
        }

        @Override
        public Integer[] getValues() {
            return values;
        }

        public void delete(int index) {
            this.values[index] = null;
            numPairs--;
        }

        public boolean insert(Integer dp) {
            if (this.isFull()) {
                return false;
            } else {
                this.values[numPairs] = dp;
                numPairs++;
                Arrays.sort(this.values, 0, numPairs);
                return true;
            }
        }

        public boolean isDeficient() {
            return numPairs < minNumPairs;
        }

        public boolean isFull() {
            return numPairs == maxNumPairs;
        }

        public boolean isLendable() {
            return numPairs > minNumPairs;
        }

        public boolean isMergeable() {
            return numPairs == minNumPairs;
        }
    }
}