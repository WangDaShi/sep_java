package com.example.talent.sep;

import java.util.HashMap;
import java.util.Map;

public class Trie {

    private Map<Character,Node> nodes = new HashMap<>();

    private Trie(){ }

    public static Trie build(String[] words){
        Trie trie = new Trie();
        for(String word : words){
            trie.add(word);
        }
        return trie;
    }

    /**
     * 查找环形表中的单词在字典树中是否存在
     * @param ring
     * @return
     */
    public boolean match(RingBuffer ring){
        Map<Character,Node> currNodes = nodes;
        int i = 0;
        char c;
        while(i < ring.length()){
            c = ring.get(i);
            if (currNodes.containsKey(c)){
                currNodes = currNodes.get(c).nodes;
            }else if(currNodes.isEmpty()){
                return true;// 已经遍历到分支的结尾了，则认为匹配成功
            }else{
                return false;
            }
            i++;
        }
        return true;
    }

    private void add(String word){
        char[] chars = word.toCharArray();
        int i = chars.length -1;
        Map<Character,Node> currNodes = nodes;
        while (i >= 0){
            char c = chars[i];
            if(currNodes.containsKey(c)){
                currNodes = currNodes.get(c).nodes;
            }else{
                Node node = new Node(chars[i]);
                currNodes.put(c,node);
                currNodes = node.nodes;
            }
            i--;
        }
    }

    /**
     * 内部类,维持一个字典树
     */
    private class Node{
        Node(char c){
            this.c = c;
        }
        private char c;
        Map<Character,Node> nodes = new HashMap<>();
    }
}

