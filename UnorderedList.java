/*
 * Course: CSC1120A - 121
 * Spring 2024
 * Lab 8 - JUnit Testing
 * Name: Madison Betz
 * Created: 3/7/2024
 */
package betzm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Implements the AutoCompleter interface using an unordered list
 */
public class UnorderedList implements AutoCompleter {
    private final List<String> list;

    /**
     * Constructor that checks for duplicates
     * @param list passed into class
     * @throws NullPointerException if list is empty
     */
    public UnorderedList(List<String> list) {
        this.list = list;
        if (list == null) {
            throw new NullPointerException("No list provided.");
        }
        Set<String> unique = new HashSet<>(list);
        list.clear();
        list.addAll(unique);
    }
    @Override
    public boolean add(String word) {
        boolean add;
        if (word == null || word.isEmpty()) {
            throw new IllegalArgumentException("Invalid word to add.");
        } else if (list.contains(word)) {
            add = false;
        } else {
            list.add(word);
            add = true;
        }
        return add;
    }

    @Override
    public boolean exactMatch(String target) {
        return target != null && !target.isEmpty() && list.contains(target);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public String getBackingClass() {
        String listType;
        if (list != null) {
            listType = list.getClass().getName();
        } else {
            throw new NullPointerException("No list provided.");
        }
        return listType;
    }

    @Override
    public String[] allMatches(String prefix) {
        if (prefix == null) {
            return new String[0];
        }
        List<String> matches = new ArrayList<>();
        for (String word : list) {
            if (word.startsWith(prefix)) {
                matches.add(word);
            }
        }
        return matches.toArray(new String[0]);
    }
}
