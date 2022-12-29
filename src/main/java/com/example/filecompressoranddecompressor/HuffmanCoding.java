package com.example.filecompressoranddecompressor;

import java.util.HashMap;
import java.util.PriorityQueue;

public class HuffmanCoding {


    static int ALPHABET_SIZE = 256;
    public HuffmanEncodedResult compress(String text){
        int[] frequencyTable = buildfrequencyTable(text);
        Node root = buildHuffmanTree(frequencyTable);
        HashMap<Character, String> lookupTable = buildLookupTable(root);

        return new HuffmanEncodedResult(generateEncodedData(text, lookupTable), root);
    }
    static String generateEncodedData(String text, HashMap<Character, String> lookupTable){
        StringBuilder data = new StringBuilder();
        for(char c : text.toCharArray()){
            data.append(lookupTable.get(c));
        }

        return data.toString();
    }
    public String decompress(HuffmanEncodedResult result){

        String encodedData = result.getEncodedData();
        Node root = result.getRoot();
        StringBuilder buildData = new StringBuilder();

        int i=0;
        while(i<encodedData.length()){

            while(!(root.left==null && root.right==null)){
                char bit = encodedData.charAt(i);
                if(bit=='0'){
                    root = root.left;
                } else if (bit == '1') {
                    root = root.right;
                }
                i++;
            }
            buildData.append(root.character);
            root = result.getRoot();
        }

        return buildData.toString();
    }
    static int[] buildfrequencyTable(String text){
        int[] frequency = new int[ALPHABET_SIZE];
        for(char c : text.toCharArray()){
            frequency[c]++;
        }
        return frequency;
    }

    static Node buildHuffmanTree(int[] frequency){

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();

        for(int i=0 ; i<frequency.length ; i++) {
            if (frequency[i] > 0) {
                priorityQueue.add(new Node((char) (i), frequency[i]));
            }
        }

        while(priorityQueue.size()>1){
            Node leftNode = priorityQueue.remove();
            Node rightNode = priorityQueue.remove();

            Node parent = new Node('\0', leftNode.frequency+rightNode.frequency);
            parent.left = leftNode;
            parent.right = rightNode;

            priorityQueue.add(parent);
        }

        return priorityQueue.remove();
    }

    static HashMap<Character, String> buildLookupTable(Node root){
        HashMap<Character, String> lookupTable = new HashMap<>();
        buildLookupTableImpl(root, "", lookupTable);
        return lookupTable;
    }

    static void buildLookupTableImpl(Node root, String s, HashMap<Character, String> lookupTable){
        if(root == null){
            return;
        }
        if(root.left==null && root.right==null){
            lookupTable.put(root.character, s);
        }

        buildLookupTableImpl(root.left, s+"0", lookupTable);
        buildLookupTableImpl(root.right, s+"1", lookupTable);
    }
    public static void main(String[] args){

        String text = "hyderabad";
        HuffmanCoding encoder = new HuffmanCoding();
        HuffmanEncodedResult compressedData = encoder.compress(text);
        System.out.println(compressedData.encodedData);
        String decompressedData = encoder.decompress(compressedData);
        System.out.println(decompressedData);


    }
}
