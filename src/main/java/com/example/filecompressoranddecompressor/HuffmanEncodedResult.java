package com.example.filecompressoranddecompressor;

public class HuffmanEncodedResult {
    Node root;
    String encodedData;

    HuffmanEncodedResult(String encodedData, Node root){
        this.encodedData=encodedData;
        this.root=root;
    }

    public String getEncodedData(){
        return this.encodedData;
    }
    public Node getRoot(){
        return this.root;
    }
}
