import com.datastruct.*;

public class HuffmanCoding {
    public static void main(String[] args) {
        char[] charArray = { 'E', 'T', 'N', 'I', 'S'};
        int[] charfreq = { 29, 10, 9, 5, 4};

        //buat priority queue dengan heap min
        Heap<Integer, HuffmanNode> pq = new Heap<Integer, HuffmanNode>(charArray.length, true);
        //inputkan setiap huruf dan frekuensinya ke priority queue
        for(int i=0; i < charArray.length ; i++) {
            pq.add(charfreq[i], new HuffmanNode(charfreq[i], charArray[i], null, null));
        }
        //membuat heap minimum dari priority queue
        pq.buildHeap();

        HuffmanNode root = null;
        HuffmanNode x, y;
        int sum;
        while(pq.size() > 1) {
            sum = pq.getKey(pq.first());
            x = pq.getData(pq.first());
            pq.removeFirst();
            sum += pq.getKey(pq.first());
            y = pq.getData(pq.first());
            pq.removeFirst();
            root = new HuffmanNode(sum, '-', x, y);
            pq.insert(sum, root);
        }
        MyArrayList<String> huffmanCodes;
        huffmanCodes = root.getHuffmanCodes(root, charArray.length);
        System.out.println("---------------------");
        System.out.println(" Huruf | Huffman code ");
        System.out.println("---------------------");
        for(int i=0; i < huffmanCodes.size(); i++) {
            //split setiap string di ArrayList untuk mendapatkan huruf dan Huffman codenya
            String[] dataParts = huffmanCodes.get(i).trim().split("\\s+");
            //tampilkan huruf dan Huffman codenya
            System.out.println("   " + dataParts[0] + "   | " + dataParts[1]);
        }
        System.out.println("---------------------");
    }
}
