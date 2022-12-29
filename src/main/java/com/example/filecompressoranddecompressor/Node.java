package com.example.filecompressoranddecompressor;

public class Node implements Comparable<Node>{
    char character;
    int frequency;
    Node left;
    Node right;

    Node(char character, int frequency){
        this.character = character;
        this.frequency = frequency;
    }

    @Override
    public int compareTo(Node that){
        if(this.frequency==that.frequency){
            return 1;
        }
        return this.frequency-that.frequency;
    }
}
