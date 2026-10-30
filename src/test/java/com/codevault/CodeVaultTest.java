package com.codevault;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for CodeVault class.
 */
public class CodeVaultTest {

    private CodeVault vault;

    @BeforeEach
    public void setUp() {
        vault = new CodeVault();
    }

    @Test
    public void testInitiallyEmpty() {
        assertTrue(vault.isEmpty());
        assertEquals(0, vault.getTotalSnippets());
        assertTrue(vault.getAllSnippets().isEmpty());
    }

    @Test
    public void testAddSnippet() {
        Snippet s1 = vault.addSnippet("Print statement", "Python", "print('Hello, World!')");
        assertEquals(1, s1.getId());
        assertEquals("Print statement", s1.getTitle());
        assertEquals("Python", s1.getLanguage());
        assertEquals("print('Hello, World!')", s1.getCode());

        Snippet s2 = vault.addSnippet("Main class", "Java", "public class Main {}");
        assertEquals(2, s2.getId());

        assertEquals(2, vault.getTotalSnippets());
        assertFalse(vault.isEmpty());
    }

    @Test
    public void testFindSnippetById() {
        vault.addSnippet("First", "Java", "int x = 1;");
        Snippet second = vault.addSnippet("Second", "Python", "x = 2");

        Snippet found = vault.findSnippetById(2);
        assertNotNull(found);
        assertEquals("Second", found.getTitle());

        Snippet notFound = vault.findSnippetById(99);
        assertNull(notFound);
    }

    @Test
    public void testDeleteSnippet() {
        vault.addSnippet("Snippet 1", "Java", "// code 1");
        vault.addSnippet("Snippet 2", "Python", "# code 2");

        boolean deleted = vault.deleteSnippet(1);
        assertTrue(deleted);
        assertEquals(1, vault.getTotalSnippets());
        assertNull(vault.findSnippetById(1));

        boolean deleteAgain = vault.deleteSnippet(1);
        assertFalse(deleteAgain);

        boolean deleteNonExistent = vault.deleteSnippet(999);
        assertFalse(deleteNonExistent);
    }

    @Test
    public void testSearchByTitle() {
        vault.addSnippet("Quick Sort Algorithm", "Java", "// quicksort");
        vault.addSnippet("Merge Sort Algorithm", "Python", "# mergesort");
        vault.addSnippet("Binary Search", "C++", "// binary search");

        ArrayList<Snippet> sortResults = vault.searchByTitle("sort");
        assertEquals(2, sortResults.size());

        ArrayList<Snippet> quickResults = vault.searchByTitle("QUICK");
        assertEquals(1, quickResults.size());
        assertEquals("Quick Sort Algorithm", quickResults.get(0).getTitle());

        ArrayList<Snippet> noMatch = vault.searchByTitle("Heap");
        assertTrue(noMatch.isEmpty());
    }

    @Test
    public void testSearchByLanguage() {
        vault.addSnippet("Snippet 1", "Java", "// java 1");
        vault.addSnippet("Snippet 2", "Python", "# python 1");
        vault.addSnippet("Snippet 3", "Java", "// java 2");

        ArrayList<Snippet> javaSnippets = vault.searchByLanguage("java");
        assertEquals(2, javaSnippets.size());

        ArrayList<Snippet> pySnippets = vault.searchByLanguage("PYTHON");
        assertEquals(1, pySnippets.size());

        ArrayList<Snippet> rustSnippets = vault.searchByLanguage("Rust");
        assertTrue(rustSnippets.isEmpty());
    }

    @Test
    public void testSearchGeneral() {
        vault.addSnippet("Hello Python", "Python", "print('hi')");
        vault.addSnippet("Hello Java", "Java", "System.out.println('hi');");
        vault.addSnippet("Rust Loop", "Rust", "loop {}");

        // Matches language or title
        ArrayList<Snippet> pyResults = vault.search("python");
        assertEquals(1, pyResults.size());

        ArrayList<Snippet> helloResults = vault.search("hello");
        assertEquals(2, helloResults.size());

        ArrayList<Snippet> emptyQuery = vault.search("");
        assertTrue(emptyQuery.isEmpty());
    }
}
