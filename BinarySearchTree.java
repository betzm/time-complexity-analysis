/*
 * Course: CSC1120A - 121
 * Spring 2024
 * Lab 11 - Auto Complete
 * Name: Madison Betz
 * Created: 4/5/2024
 */
package betzm;

import java.util.TreeSet;

/**
 * implements the AutoCompleter interface using a binary search tree
 */
public class BinarySearchTree implements AutoCompleter {
    private final TreeSet<String> items;

    /**
     * initializes binary search tree with tree set
     * @param items in set passed into binary search tree
     * @throws NullPointerException if no list is provided
     */
    public BinarySearchTree(TreeSet<String> items) {
        this.items = items;
        if (items == null) {
            throw new NullPointerException("No list");
        }
        TreeSet<String> copy = new TreeSet<>(items);
        items.clear();
        items.addAll(copy);
    }

    @Override
    public boolean add(String word) {
        if (word == null || word.isEmpty()) {
            throw new IllegalArgumentException("Invalid word");
        }
        return items.add(word);
    }

    @Override
    public boolean exactMatch(String target) {
        return target != null && items.contains(target);
    }

    @Override
    public int size() {
        return items.size();
    }

    @Override
    public String getBackingClass() {
        return TreeSet.class.getName();
    }

    @Override
    public String[] allMatches(String prefix) {
        if (prefix == null) {
            return new String[0];
        }
        return items.subSet(prefix, prefix + Character.MAX_VALUE).toArray(new String[0]);
    }
}
