import com.datastruct.*;

public class Main {
    public static void main(String[] args) {
        char[] charArray = { 'A', 'I', 'M', 'O', 'T', 'F', 'K', 'N', 'R' };
        int[] charfreq = { 45, 35, 29, 19, 4, 8, 15, 10, 5 };

        Heap<Integer, HuffmanNode> pq = new Heap<Integer, HuffmanNode>(charArray.length, true);
        
        for(int i=0; i < charArray.length ; i++) {
           pq.add(charfreq[i], new HuffmanNode(charfreq[i], charArray[i], null, null));
        }
        
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
        
        System.out.println("5.b. Tabel Huffman Code");
        System.out.println("---------------------");
        System.out.println(" Huruf | Huffman code ");
        System.out.println("---------------------");
        for(int i=0; i < huffmanCodes.size(); i++) {
            String[] dataParts = huffmanCodes.get(i).trim().split("\\s+");
            
            System.out.println("   " + dataParts[0] + "   | " + dataParts[1]);
        }
        System.out.println("---------------------\n");

        
        String wordToEncode = "INFORMATIKA";
        System.out.print("5.c. Huffman Code untuk " + wordToEncode + ": ");
        for (int i = 0; i < wordToEncode.length(); i++) {
            char c = wordToEncode.charAt(i);
            for (int j = 0; j < huffmanCodes.size(); j++) {
                String[] dataParts = huffmanCodes.get(j).trim().split("\\s+");
                if (dataParts[0].charAt(0) == c) {
                    System.out.print(dataParts[1] + " ");
                    break;
                }
            }
        }
        System.out.println("\n");

        
        String bitStream = "10111001010000001";
        System.out.print("5.d. Kata dari Huffman Code " + bitStream + ": ");
        
        HuffmanNode curr = root;
        for (int i = 0; i < bitStream.length(); i++) {
            if (bitStream.charAt(i) == '1') {
                curr = (HuffmanNode) curr.getLlink();
            } else {
                curr = (HuffmanNode) curr.getRlink();
            }
            
            if (curr.getLlink() == null && curr.getRlink() == null) {
                System.out.print(curr.getData());
                curr = root;
            }
        }
        System.out.println();
    }
}