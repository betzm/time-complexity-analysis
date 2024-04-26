/*
 * Course: CSC1120A - 121
 * Spring 2024
 * Lab 14 - Even More Auto Complete
 * Name: Madison Betz
 * Created: 4/24/2024
 */
package betzm;

import java.util.HashSet;

/**
 * implementation of a Hash Table using Hash Set with AutoCompleter
 */
public class HashTable implements AutoCompleter {
    private final HashSet<String> items;

    /**
     * constructor for the hash table implementation
     */
    public HashTable() {
        items = new HashSet<>();
    }

    @Override
    public boolean add(String word) {
        if (word == null || word.isEmpty()) {
            throw new IllegalArgumentException("words can't be null or empty");
        }
        return items.add(word);
    }

    @Override
    public boolean exactMatch(String target) {
        return target != null && !target.isEmpty() && items.contains(target);
    }

    @Override
    public int size() {
        return items.size();
    }

    @Override
    public String getBackingClass() {
        return HashSet.class.getName();
    }

    @Override
    public String[] allMatches(String prefix) {
        if (prefix == null) {
            return new String[0];
        }
        return items.stream()
                .filter(word -> word.startsWith(prefix))
                .toArray(String[]::new);
    }
}
