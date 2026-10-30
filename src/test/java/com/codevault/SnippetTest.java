package com.codevault;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Snippet class.
 */
public class SnippetTest {

    @Test
    public void testSnippetCreationAndGetters() {
        Snippet snippet = new Snippet(1, "Hello World", "Java", "System.out.println(\"Hello\");");

        assertEquals(1, snippet.getId());
        assertEquals("Hello World", snippet.getTitle());
        assertEquals("Java", snippet.getLanguage());
        assertEquals("System.out.println(\"Hello\");", snippet.getCode());
    }

    @Test
    public void testSnippetSetters() {
        Snippet snippet = new Snippet(1, "Old Title", "Python", "print('old')");

        snippet.setId(10);
        snippet.setTitle("New Title");
        snippet.setLanguage("Java");
        snippet.setCode("System.out.println(\"new\");");

        assertEquals(10, snippet.getId());
        assertEquals("New Title", snippet.getTitle());
        assertEquals("Java", snippet.getLanguage());
        assertEquals("System.out.println(\"new\");", snippet.getCode());
    }

    @Test
    public void testToStringContainsEssentialFields() {
        Snippet snippet = new Snippet(5, "Binary Search", "C++", "int mid = (l + r) / 2;");
        String output = snippet.toString();

        assertTrue(output.contains("5"));
        assertTrue(output.contains("Binary Search"));
        assertTrue(output.contains("C++"));
        assertTrue(output.contains("int mid = (l + r) / 2;"));
    }
}
