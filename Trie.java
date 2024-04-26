/*
 * Course: CSC1120A - 121
 * Spring 2024
 * Lab 13 - Auto Complete Continued
 * Name: Madison Betz
 * Created: 4/17/2024
 */
package betzm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Data structure that stores each string as a chain of characters
 */
public class Trie implements AutoCompleter {
    private boolean endsWord;
    private Map<Character, Trie> edges;

    /**
     * constructor for trie class
     */
    public Trie() {
        edges = new ListMap<>();
        endsWord = false;
    }

    @Override
    public boolean add(String word) {
        if (word == null || word.isEmpty()) {
            throw new IllegalArgumentException("Word cannot be null or empty");
        }
        Trie current = this;
        for (char c : word.toCharArray()) {
            if (!current.edges.containsKey(c)) {
                current.edges.put(c, new Trie());
            }
            current = current.edges.get(c);
        }
        boolean wordExists = current.endsWord;
        current.endsWord = true;
        return !wordExists;
    }

    @Override
    public boolean exactMatch(String target) {
        if (target == null || target.isEmpty()) {
            return false;
        }
        Trie node = search(target);
        return node != null && node.endsWord;
    }

    @Override
    public int size() {
        return countWords(this);
    }

    @Override
    public String getBackingClass() {
        return ListMap.class.getName();
    }

    @Override
    public String[] allMatches(String prefix) {
        if (prefix == null) {
            return new String[0];
        }
        Trie node = search(prefix);
        if (node == null) {
            return new String[0];
        }
        return collectWords(prefix, node).toArray(new String[0]);
    }

    private Trie search(String prefix) {
        Trie current = this;
        for (char c : prefix.toCharArray()) {
            if (!current.edges.containsKey(c)) {
                return null;
            }
            current = current.edges.get(c);
        }
        return current;
    }

    private int countWords(Trie node) {
        int count = node.endsWord ? 1 : 0;
        for (Trie child : node.edges.values()) {
            count += countWords(child);
        }
        return count;
    }

    private List<String> collectWords(String prefix, Trie node) {
        List<String> matches = new ArrayList<>();
        if (node.endsWord) {
            matches.add(prefix);
        }
        for (Map.Entry<Character, Trie> entry : node.edges.entrySet()) {
            matches.addAll(collectWords(prefix + entry.getKey(), entry.getValue()));
        }
        return matches;
    }
}
