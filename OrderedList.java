/*
 * Course: CSC1120A - 121
 * Spring 2024
 * Lab 11 - Auto Complete
 * Name: Madison Betz
 * Created: 3/28/2024
 */
package betzm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Implements the AutoCompleter interface using an ordered list
 */
public class OrderedList implements AutoCompleter {
    private final List<String> items;

    /**
     * ensures that the list does not contain duplicates and that
     * all the items are in order
     * @param items in list
     */
    public OrderedList(List<String> items) {
        Set<String> set = new TreeSet<>(items);
        this.items = new ArrayList<>(set);
    }

    @Override
    public boolean add(String word) {
        if (word == null || word.isEmpty()) {
            throw new IllegalArgumentException("Invalid word");
        }
        ListIterator<String> iterator = items.listIterator();
        while (iterator.hasNext()) {
            int compare = iterator.next().compareTo(word);
            if (compare == 0) {
                return false;
            } else if (compare > 0) {
                iterator.previous();
                iterator.add(word);
                return true;
            }
        }
        iterator.add(word);
        return true;
    }

    @Override
    public boolean exactMatch(String target) {
        if (target == null || target.isEmpty()) {
            return false;
        }
        return Collections.binarySearch(items, target) >= 0;
    }

    @Override
    public int size() {
        return items.size();
    }

    @Override
    public String getBackingClass() {
        return ArrayList.class.getName();
    }

    @Override
    public String[] allMatches(String prefix) {
        if (prefix == null) {
            return new String[0];
        }
        int firstIndex = Collections.binarySearch(items, prefix);
        if (firstIndex < 0) {
            firstIndex = -(firstIndex + 1);
        }
        int lastIndex = firstIndex;
        while (lastIndex < items.size() && items.get(lastIndex).startsWith(prefix)) {
            lastIndex++;
        }
        List<String> matches = items.subList(firstIndex, lastIndex);
        return matches.toArray(new String[0]);
    }
}
