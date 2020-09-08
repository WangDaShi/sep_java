package com.example.talent.sep;

public class StreamAlerter {
    private RingBuffer ring;
    private Trie trie;

    public StreamAlerter(String[] strings) {
        this.trie = Trie.build(strings);
        int maxLen = 0;
        for(String s : strings){
            if(s.length() > maxLen){
                maxLen = s.length();
            }
        }
        this.ring = new RingBuffer(maxLen);
    }

    public boolean query(char ch) {
        ring.add(ch);
        return trie.match(ring);
    }
}
