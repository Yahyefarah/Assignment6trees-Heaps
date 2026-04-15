import java.util.*;


//this is unfinished clases  for the queastion three
//the w3schools links was helpful but i still couldnt finished it
class Huffman {

    // Node for tree
    class Node {
        char ch;
        int freq;
        Node left, right;

        Node(char ch, int freq) {
            this.ch = ch;
            this.freq = freq;
        }
    }

    HashMap<Character, Integer> freqMap = new HashMap<>();
    HashMap<Character, String> codeMap = new HashMap<>();
    Node root;

    // Step 1: count frequency
    public void frequencyCount(String text) {
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
    }

    // Step 2: build Huffman tree using min heap
    public void buildHuffman() {
        PriorityQueue<Node> heap =
                new PriorityQueue<>((a, b) -> a.freq - b.freq);

        // add all characters
        for (char c : freqMap.keySet()) {
            heap.add(new Node(c, freqMap.get(c)));
        }

        // combine smallest nodes
        while (heap.size() > 1) {
            Node left = heap.poll();
            Node right = heap.poll();

            Node parent = new Node('\0', left.freq + right.freq);
            parent.left = left;   // left = 0
            parent.right = right; // right = 1

            heap.add(parent);
        }

        root = heap.poll();
    }

    // Step 3: generate codes
    public void genCode() {
        generate(root, "");
    }

    private void generate(Node node, String code) {
        if (node == null) return;

        // leaf node → store code
        if (node.left == null && node.right == null) {
            codeMap.put(node.ch, code);
            return;
        }

        generate(node.left, code + "0");
        generate(node.right, code + "1");
    }

    // Step 4: encode text
    public String encode(String text) {
        String result = "";

        for (int i = 0; i < text.length(); i++) {
            result += codeMap.get(text.charAt(i));
        }

        return result;
    }
}
