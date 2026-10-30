package com.codevault;

import java.util.ArrayList;

/**
 * Manages the collection of code snippets in memory using an ArrayList.
 */
public class CodeVault {
    private ArrayList<Snippet> snippets;
    private int nextId;

    /**
     * Initializes an empty CodeVault snippet manager.
     */
    public CodeVault() {
        this.snippets = new ArrayList<>();
        this.nextId = 1;
    }

    /**
     * Adds a new snippet to the vault with an auto-generated ID.
     *
     * @param title    Title of the snippet
     * @param language Programming language
     * @param code     Source code content
     * @return The newly created Snippet
     */
    public Snippet addSnippet(String title, String language, String code) {
        Snippet snippet = new Snippet(nextId, title, language, code);
        snippets.add(snippet);
        nextId++;
        return snippet;
    }

    /**
     * Retrieves all saved snippets.
     *
     * @return An ArrayList containing all snippets
     */
    public ArrayList<Snippet> getAllSnippets() {
        return new ArrayList<>(snippets);
    }

    /**
     * Finds a snippet by its unique ID.
     *
     * @param id The ID to search for
     * @return The matching Snippet, or null if not found
     */
    public Snippet findSnippetById(int id) {
        for (Snippet snippet : snippets) {
            if (snippet.getId() == id) {
                return snippet;
            }
        }
        return null;
    }

    /**
     * Deletes a snippet by its unique ID.
     *
     * @param id The ID of the snippet to delete
     * @return true if snippet was found and removed, false otherwise
     */
    public boolean deleteSnippet(int id) {
        Snippet snippetToDelete = findSnippetById(id);
        if (snippetToDelete != null) {
            snippets.remove(snippetToDelete);
            return true;
        }
        return false;
    }

    /**
     * Searches snippets whose title contains the given query string (case-insensitive).
     *
     * @param title The title search term
     * @return An ArrayList of matching snippets
     */
    public ArrayList<Snippet> searchByTitle(String title) {
        ArrayList<Snippet> results = new ArrayList<>();
        if (title == null || title.trim().isEmpty()) {
            return results;
        }

        String searchLower = title.trim().toLowerCase();
        for (Snippet snippet : snippets) {
            if (snippet.getTitle().toLowerCase().contains(searchLower)) {
                results.add(snippet);
            }
        }
        return results;
    }

    /**
     * Searches snippets matching the given programming language (case-insensitive).
     *
     * @param language The programming language search term
     * @return An ArrayList of matching snippets
     */
    public ArrayList<Snippet> searchByLanguage(String language) {
        ArrayList<Snippet> results = new ArrayList<>();
        if (language == null || language.trim().isEmpty()) {
            return results;
        }

        String searchLower = language.trim().toLowerCase();
        for (Snippet snippet : snippets) {
            if (snippet.getLanguage().toLowerCase().contains(searchLower)) {
                results.add(snippet);
            }
        }
        return results;
    }

    /**
     * Searches snippets where either the title or the language matches the query.
     *
     * @param query The search query
     * @return An ArrayList of matching snippets
     */
    public ArrayList<Snippet> search(String query) {
        ArrayList<Snippet> results = new ArrayList<>();
        if (query == null || query.trim().isEmpty()) {
            return results;
        }

        String searchLower = query.trim().toLowerCase();
        for (Snippet snippet : snippets) {
            boolean matchesTitle = snippet.getTitle().toLowerCase().contains(searchLower);
            boolean matchesLang = snippet.getLanguage().toLowerCase().contains(searchLower);
            if (matchesTitle || matchesLang) {
                results.add(snippet);
            }
        }
        return results;
    }

    /**
     * Returns the total number of saved snippets.
     *
     * @return Total count of snippets
     */
    public int getTotalSnippets() {
        return snippets.size();
    }

    /**
     * Checks if the vault is empty.
     *
     * @return true if there are no snippets, false otherwise
     */
    public boolean isEmpty() {
        return snippets.isEmpty();
    }
}
