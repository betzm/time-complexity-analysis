/*
 * Course: CSC1120A - 121
 * Spring 2024
 * Lab 8 - JUnit Testing
 * Name: Madison Betz
 * Created: 3/5/2024
 */
package betzm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests methods in UnorderedList
 */
class AutoCompleterTest {
    private UnorderedList unorderedList;
    private OrderedList orderedList;
    private BinarySearchTree binarySearchTree;
    private Trie trie;
    private HashTable hashTable;

    @BeforeEach
    void setUp() {
        unorderedList = new UnorderedList(new ArrayList<>());
        orderedList = new OrderedList(new ArrayList<>());
        binarySearchTree = new BinarySearchTree(new TreeSet<>());
        trie = new Trie();
        hashTable = new HashTable();
    }

    @Test
    void testGetBackingClass() {
        //unorderedList and orderedList tests
        List<String> wordsArrayList = new ArrayList<>(Arrays.asList("mango", "banana", "kiwi"));
        unorderedList = new UnorderedList(wordsArrayList);
        assertEquals("java.util.ArrayList", unorderedList.getBackingClass());
        orderedList = new OrderedList(wordsArrayList);
        assertEquals("java.util.ArrayList", orderedList.getBackingClass());
        List<String> wordsLinkedList = new LinkedList<>(Arrays.asList("mango", "banana", "kiwi"));
        unorderedList = new UnorderedList(wordsLinkedList);
        assertEquals("java.util.LinkedList", unorderedList.getBackingClass());
        orderedList = new OrderedList(wordsLinkedList);
        assertEquals("java.util.ArrayList", orderedList.getBackingClass());

        //binarySearchTree test
        assertEquals("java.util.TreeSet", binarySearchTree.getBackingClass());

        //trie test
        assertEquals("betzm.ListMap", trie.getBackingClass());

        //hashTable test
        assertEquals("java.util.HashSet", hashTable.getBackingClass());
    }

    @Test
    void testAdd() {
        //unorderedList tests
        assertTrue(unorderedList.add("blueberry"));
        assertTrue(unorderedList.add("mango"));
        assertTrue(unorderedList.add("peach"));
        assertFalse(unorderedList.add("mango"));
        assertThrows(IllegalArgumentException.class, () -> unorderedList.add(null));
        assertThrows(IllegalArgumentException.class, () -> unorderedList.add(""));

        //orderedList tests
        assertTrue(orderedList.add("blueberry"));
        assertTrue(orderedList.add("mango"));
        assertTrue(orderedList.add("peach"));
        assertFalse(orderedList.add("mango"));
        assertThrows(IllegalArgumentException.class, () -> orderedList.add(null));
        assertThrows(IllegalArgumentException.class, () -> orderedList.add(""));

        //binarySearchTree tests
        assertTrue(binarySearchTree.add("blueberry"));
        assertTrue(binarySearchTree.add("mango"));
        assertTrue(binarySearchTree.add("peach"));
        assertFalse(binarySearchTree.add("mango"));
        assertThrows(IllegalArgumentException.class, () -> binarySearchTree.add(null));
        assertThrows(IllegalArgumentException.class, () -> binarySearchTree.add(""));

        //trie tests
        assertTrue(trie.add("blueberry"));
        assertTrue(trie.add("mango"));
        assertTrue(trie.add("peach"));
        assertFalse(trie.add("mango"));
        assertThrows(IllegalArgumentException.class, () -> trie.add(null));
        assertThrows(IllegalArgumentException.class, () -> trie.add(""));

        //hashTable tests
        assertTrue(hashTable.add("blueberry"));
        assertTrue(hashTable.add("mango"));
        assertTrue(hashTable.add("peach"));
        assertFalse(hashTable.add("mango"));
        assertThrows(IllegalArgumentException.class, () -> hashTable.add(null));
        assertThrows(IllegalArgumentException.class, () -> hashTable.add(""));
    }

    @Test
    void testSize() {
        //unorderedList tests
        assertEquals(0, unorderedList.size());
        unorderedList.add("blueberry");
        unorderedList.add("mango");
        unorderedList.add("peach");
        assertEquals(3, unorderedList.size());
        unorderedList.add("mango");
        assertEquals(3, unorderedList.size());
        unorderedList.add("banana");
        assertEquals(4, unorderedList.size());

        //orderedList tests
        assertEquals(0, orderedList.size());
        orderedList.add("blueberry");
        orderedList.add("mango");
        orderedList.add("peach");
        assertEquals(3, orderedList.size());
        orderedList.add("mango");
        assertEquals(3, orderedList.size());
        orderedList.add("banana");
        assertEquals(4, orderedList.size());

        //binarySearchTree tests
        assertEquals(0, binarySearchTree.size());
        binarySearchTree.add("blueberry");
        binarySearchTree.add("mango");
        binarySearchTree.add("peach");
        assertEquals(3, binarySearchTree.size());
        binarySearchTree.add("mango");
        assertEquals(3, binarySearchTree.size());
        binarySearchTree.add("banana");
        assertEquals(4, binarySearchTree.size());

        //trie tests
        assertEquals(0, trie.size());
        trie.add("blueberry");
        trie.add("mango");
        trie.add("peach");
        assertEquals(3, trie.size());
        trie.add("mango");
        assertEquals(3, trie.size());
        trie.add("banana");
        assertEquals(4, trie.size());

        //hashTable tests
        assertEquals(0, hashTable.size());
        hashTable.add("blueberry");
        hashTable.add("mango");
        hashTable.add("peach");
        assertEquals(3, hashTable.size());
        hashTable.add("mango");
        assertEquals(3, hashTable.size());
        hashTable.add("banana");
        assertEquals(4, hashTable.size());
    }

    @Test
    void testExactMatch() {
        //unorderedList tests
        unorderedList.add("blueberry");
        unorderedList.add("mango");
        unorderedList.add("peach");
        assertTrue(unorderedList.exactMatch("mango"));
        assertFalse(unorderedList.exactMatch("grape"));
        assertFalse(unorderedList.exactMatch(null));
        assertFalse(unorderedList.exactMatch("Mango"));
        assertFalse(unorderedList.exactMatch(""));

        //orderedList tests
        orderedList.add("blueberry");
        orderedList.add("mango");
        orderedList.add("peach");
        assertTrue(orderedList.exactMatch("mango"));
        assertFalse(orderedList.exactMatch("grape"));
        assertFalse(orderedList.exactMatch(null));
        assertFalse(orderedList.exactMatch("Mango"));
        assertFalse(orderedList.exactMatch(""));

        //binarySearchTree tests
        binarySearchTree.add("blueberry");
        binarySearchTree.add("mango");
        binarySearchTree.add("peach");
        assertTrue(binarySearchTree.exactMatch("mango"));
        assertFalse(binarySearchTree.exactMatch("grape"));
        assertFalse(binarySearchTree.exactMatch(null));
        assertFalse(binarySearchTree.exactMatch("Mango"));
        assertFalse(binarySearchTree.exactMatch(""));

        //trie tests
        trie.add("blueberry");
        trie.add("mango");
        trie.add("peach");
        assertTrue(trie.exactMatch("mango"));
        assertFalse(trie.exactMatch("grape"));
        assertFalse(trie.exactMatch(null));
        assertFalse(trie.exactMatch("Mango"));
        assertFalse(trie.exactMatch(""));

        //hashTable tests
        hashTable.add("blueberry");
        hashTable.add("mango");
        hashTable.add("peach");
        assertTrue(hashTable.exactMatch("mango"));
        assertFalse(hashTable.exactMatch("grape"));
        assertFalse(hashTable.exactMatch(null));
        assertFalse(hashTable.exactMatch("Mango"));
        assertFalse(hashTable.exactMatch(""));
    }

    @Test
    void testAllMatches() {
        //unorderedList tests
        unorderedList.add("blueberry");
        unorderedList.add("mango");
        unorderedList.add("peach");
        assertArrayEquals(new String[] {"mango"}, unorderedList.allMatches("m"));
        assertArrayEquals(new String[0], unorderedList.allMatches("cherry"));
        unorderedList.add("melon");
        unorderedList.add("mandarin");
        assertArrayEquals(new String[] {"mango", "melon", "mandarin"},
                unorderedList.allMatches("m"));
        assertArrayEquals(new String[0], unorderedList.allMatches(null));

        //orderedList tests
        orderedList.add("blueberry");
        orderedList.add("mango");
        orderedList.add("peach");
        assertArrayEquals(new String[] {"mango"}, orderedList.allMatches("m"));
        assertArrayEquals(new String[0], orderedList.allMatches("cherry"));
        orderedList.add("melon");
        orderedList.add("mandarin");
        assertArrayEquals(new String[] {"mandarin", "mango", "melon"},
                orderedList.allMatches("m"));
        assertArrayEquals(new String[0], orderedList.allMatches(null));

        //binarySearchTree tests
        binarySearchTree.add("blueberry");
        binarySearchTree.add("mango");
        binarySearchTree.add("peach");
        assertArrayEquals(new String[] {"mango"}, binarySearchTree.allMatches("m"));
        assertArrayEquals(new String[0], binarySearchTree.allMatches("cherry"));
        binarySearchTree.add("melon");
        binarySearchTree.add("mandarin");
        assertArrayEquals(new String[] {"mandarin", "mango", "melon"},
                binarySearchTree.allMatches("m"));
        assertArrayEquals(new String[0], binarySearchTree.allMatches(null));

        //trie tests
        trie.add("blueberry");
        trie.add("mango");
        trie.add("peach");
        assertArrayEquals(new String[] {"mango"}, trie.allMatches("m"));
        assertArrayEquals(new String[0], trie.allMatches("cherry"));
        trie.add("melon");
        trie.add("mandarin");
        assertArrayEquals(new String[0], trie.allMatches(null));

        //hashTable tests
        hashTable.add("blueberry");
        hashTable.add("mango");
        hashTable.add("peach");
        assertArrayEquals(new String[] {"mango"}, hashTable.allMatches("m"));
        assertArrayEquals(new String[0], hashTable.allMatches("cherry"));
        hashTable.add("melon");
        hashTable.add("mandarin");
        assertArrayEquals(new String[] {"mandarin", "melon", "mango"},
                hashTable.allMatches("m"));
        assertArrayEquals(new String[0], hashTable.allMatches(null));
    }

    @Test
    void testFormat() {
        final long TEST_DAYS = 192_720_000_000_000L;
        final long TEST_HOURS = 51_728_000_000_000L;
        final long TEST_MINS = 2_575_300_000_000L;
        final long TEST_SECS = 18_800_000_000L;
        final long TEST_MILLI = 998_800_000L;
        final long TEST_MICRO = 318_800L;
        final long TEST_NANO = 7L;
        assertEquals("2 day(s) 5 hour(s) 32 minute(s) ", AutoCompleter.format(TEST_DAYS));
        assertEquals("14 hour(s) 22 minute(s) 8 second(s)", AutoCompleter.format(TEST_HOURS));
        assertEquals("42 minute(s) 55.3 second(s)", AutoCompleter.format(TEST_MINS));
        assertEquals("18.8 second(s)", AutoCompleter.format(TEST_SECS));
        assertEquals("998.8 millisecond(s)", AutoCompleter.format(TEST_MILLI));
        assertEquals("318.8 microsecond(s)", AutoCompleter.format(TEST_MICRO));
        assertEquals("7 nanosecond(s)", AutoCompleter.format(TEST_NANO));
    }
}

/*
  Discussion: What method did you find most difficult to test? Why?
  I found it most difficult to test the size method because it had taken me a
  while to realize that the reason my size test was returning false was because
  I had not first checked to see that my add method was working. Otherwise, I
  was initially confused on how to test the getBackingClass method because I did
  not fully understand how the method worked.

  Also, I am curious as to why the methods test in the order that they do. I
  noticed that they do not go in order of how they are listed but still go in the
  same order everytime.

*/
