package com.datastruct;

class DataItem<K extends Comparable<K>, V> {
    private K dData;
    private V info;

    public DataItem(K dd, V info) {
        this.dData = dd;
        this.info = info;
    }

    public void setdData(K dData) { this.dData = dData; }
    public K getdData() { return dData; }
    
    public void setInfo(V info) { this.info = info; }
    public V getInfo() { return info; }

    public void displayItem() {
        System.out.print("/" + dData + ": (" + info + ") ");
    }
}

class nmNode<K extends Comparable<K>, V> {
    private static final int ORDER = 4; 
    private int numItems;
    private nmNode<K, V> parent;
    
    @SuppressWarnings("unchecked")
    private nmNode<K, V> childArray[] = new nmNode[ORDER];
    
    @SuppressWarnings("unchecked")
    private DataItem<K, V> itemArray[] = new DataItem[ORDER - 1];

    public void connectChild(int childNum, nmNode<K, V> child) {
        childArray[childNum] = child;
        if (child != null)
            child.parent = this;
    }

    public nmNode<K, V> disconnectChild(int childNum) {
        nmNode<K, V> tempnmNode = childArray[childNum];
        childArray[childNum] = null;
        return tempnmNode;
    }

    public nmNode<K, V> getChild(int childNum) { return childArray[childNum]; }
    public nmNode<K, V> getParent() { return parent; }
    public boolean isLeaf() { return (childArray[0] == null) ? true : false; }
    public int getNumItems() { return numItems; }
    public DataItem<K, V> getItem(int index) { return itemArray[index]; }
    public boolean isFull() { return (numItems == ORDER - 1) ? true : false; }

    public int findItem(K key) {
        for (int j = 0; j < ORDER - 1; j++) {
            if (itemArray[j] == null)
                break;
            else if (itemArray[j].getdData().compareTo(key) == 0)
                return j;
        }
        return -1;
    }

    public void deleteItem(K key) {
        for (int j = 0; j < numItems; j++) {
            if (itemArray[j] == null)
                break;
            else if (itemArray[j].getdData().compareTo(key) == 0) {
                for (int k = j; k < numItems; k++) {
                    itemArray[k] = itemArray[k + 1];
                }
                --numItems;
                break;
            }
        }
    }

    public int insertItem(DataItem<K, V> newItem) {
        numItems++;
        K newKey = newItem.getdData();
        for (int j = ORDER - 2; j >= 0; j--) {
            if (itemArray[j] == null)
                continue;
            else {
                K itsKey = itemArray[j].getdData();
                if (newKey.compareTo(itsKey) < 0)
                    itemArray[j + 1] = itemArray[j];
                else {
                    itemArray[j + 1] = newItem;
                    return j + 1;
                }
            }
        }
        itemArray[0] = newItem;
        return 0;
    }

    public DataItem<K, V> removeItem() {
        DataItem<K, V> temp = itemArray[numItems - 1];
        itemArray[numItems - 1] = null;
        numItems--;
        return temp;
    }

    public void displaynmNode() {
        for (int j = 0; j < numItems; j++)
            itemArray[j].displayItem();
        System.out.println("/");
    }
}

public class nmTree<K extends Comparable<K>, V> {
    private nmNode<K, V> root = new nmNode<K, V>();

    public int find(K key) {
        nmNode<K, V> curnmNode = root;
        int childNumber;
        while (true) {
            if ((childNumber = curnmNode.findItem(key)) != -1)
                return childNumber;
            else if (curnmNode.isLeaf())
                return -1;
            else
                curnmNode = getNextChild(curnmNode, key);
        }
    }

    public void insert(K key, V data) {
        nmNode<K, V> curnmNode = root;
        DataItem<K, V> tempItem = new DataItem<K, V>(key, data);

        while (true) {
            if (curnmNode.isLeaf())
                break;
            else
                curnmNode = getNextChild(curnmNode, key);
        }
        curnmNode.insertItem(tempItem);

        if (curnmNode.isFull()) {
            split(curnmNode); 
            curnmNode = curnmNode.getParent();
            while (curnmNode != null && curnmNode.isFull()) {
                split(curnmNode); 
                curnmNode = curnmNode.getParent();
            }
        }
    }

    public void split(nmNode<K, V> thisnmNode) {
        DataItem<K, V> itemB, itemC;
        nmNode<K, V> parent, child2, child3;
        int itemIndex;
        itemC = thisnmNode.removeItem();
        itemB = thisnmNode.removeItem();
        child2 = thisnmNode.disconnectChild(2);
        child3 = thisnmNode.disconnectChild(3);

        nmNode<K, V> newRight = new nmNode<K, V>();
        if (thisnmNode == root) {
            root = new nmNode<K, V>();
            parent = root;
            root.connectChild(0, thisnmNode);
        } else
            parent = thisnmNode.getParent();
            
        itemIndex = parent.insertItem(itemB);
        int n = parent.getNumItems();
        for (int j = n - 1; j > itemIndex; j--) {
            nmNode<K, V> temp = parent.disconnectChild(j);
            parent.connectChild(j + 1, temp);
        }
        parent.connectChild(itemIndex + 1, newRight);
        newRight.insertItem(itemC);
        newRight.connectChild(0, child2);
        newRight.connectChild(1, child3);
    }

    public nmNode<K, V> getNextChild(nmNode<K, V> thenmNode, K theValue) {
        int j;
        int numItems = thenmNode.getNumItems();
        for (j = 0; j < numItems; j++) {
            if (theValue.compareTo(thenmNode.getItem(j).getdData()) < 0)
                return thenmNode.getChild(j);
        }
        return thenmNode.getChild(j);
    }

    public void displayTree() {
        recDisplayTree(root, 0, 0);
    }

    private void recDisplayTree(nmNode<K, V> thisnmNode, int level, int childNumber) {
        System.out.print("level=" + level + " child=" + childNumber + " ");
        thisnmNode.displaynmNode();
        int numItems = thisnmNode.getNumItems();
        for (int j = 0; j < numItems + 1; j++) {
            nmNode<K, V> nextnmNode = thisnmNode.getChild(j);
            if (nextnmNode != null)
                recDisplayTree(nextnmNode, level + 1, j);
            else
                return;
        }
    }
}