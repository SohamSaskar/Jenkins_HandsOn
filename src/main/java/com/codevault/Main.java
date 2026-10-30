package com.codevault;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main entry point for the CodeVault application.
 * Provides a menu-driven console interface for managing code snippets.
 */
public class Main {

    public static void main(String[] args) {
        CodeVault vault = new CodeVault();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("========================================");
        System.out.println("  CodeVault: Developer Snippet Manager  ");
        System.out.println("========================================");

        while (running) {
            printMainMenu();
            int choice = readIntegerInput(scanner, "Enter your choice (1-6): ");

            System.out.println();
            switch (choice) {
                case 1:
                    addSnippet(scanner, vault);
                    break;
                case 2:
                    displayAllSnippets(vault);
                    break;
                case 3:
                    searchSnippets(scanner, vault);
                    break;
                case 4:
                    deleteSnippet(scanner, vault);
                    break;
                case 5:
                    displayTotalCount(vault);
                    break;
                case 6:
                    running = false;
                    System.out.println("Exiting CodeVault. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please enter a number between 1 and 6.");
            }
            System.out.println();
        }

        scanner.close();
    }

    /**
     * Prints the main menu options to the console.
     */
    private static void printMainMenu() {
        System.out.println("----------------------------------------");
        System.out.println("MAIN MENU");
        System.out.println("----------------------------------------");
        System.out.println("1. Add a new snippet");
        System.out.println("2. Display all snippets");
        System.out.println("3. Search snippets");
        System.out.println("4. Delete a snippet by ID");
        System.out.println("5. Display total number of snippets");
        System.out.println("6. Exit");
        System.out.println("----------------------------------------");
    }

    /**
     * Handles adding a new snippet from user input.
     */
    private static void addSnippet(Scanner scanner, CodeVault vault) {
        System.out.println("--- Add Code Snippet ---");

        String title = "";
        while (title.isEmpty()) {
            System.out.print("Enter snippet title: ");
            title = scanner.nextLine().trim();
            if (title.isEmpty()) {
                System.out.println("Title cannot be empty. Please enter a title.");
            }
        }

        String language = "";
        while (language.isEmpty()) {
            System.out.print("Enter programming language: ");
            language = scanner.nextLine().trim();
            if (language.isEmpty()) {
                System.out.println("Language cannot be empty. Please enter a language.");
            }
        }

        System.out.println("Enter code (type 'END' on a new line to finish):");
        StringBuilder codeBuilder = new StringBuilder();
        while (true) {
            String line = scanner.nextLine();
            if (line.equalsIgnoreCase("END")) {
                break;
            }
            codeBuilder.append(line).append(System.lineSeparator());
        }

        String code = codeBuilder.toString().stripTrailing();
        if (code.isEmpty()) {
            System.out.println("Warning: Code content is empty. Snippet not added.");
            return;
        }

        Snippet created = vault.addSnippet(title, language, code);
        System.out.println("Success: Snippet added with ID: " + created.getId());
    }

    /**
     * Displays all snippets currently saved in the vault.
     */
    private static void displayAllSnippets(CodeVault vault) {
        System.out.println("--- All Saved Snippets ---");
        ArrayList<Snippet> list = vault.getAllSnippets();

        if (list.isEmpty()) {
            System.out.println("No snippets found. The vault is currently empty.");
            return;
        }

        for (Snippet snippet : list) {
            printSnippet(snippet);
        }
    }

    /**
     * Handles searching snippets by title or programming language.
     */
    private static void searchSnippets(Scanner scanner, CodeVault vault) {
        if (vault.isEmpty()) {
            System.out.println("The vault is currently empty. No snippets to search.");
            return;
        }

        System.out.println("--- Search Snippets ---");
        System.out.println("1. Search by title");
        System.out.println("2. Search by programming language");
        System.out.println("3. Search across both (title or language)");
        System.out.println("4. Cancel");

        int option = readIntegerInput(scanner, "Choose search option (1-4): ");
        System.out.println();

        ArrayList<Snippet> results = new ArrayList<>();
        switch (option) {
            case 1:
                System.out.print("Enter title keyword to search: ");
                String titleQuery = scanner.nextLine().trim();
                results = vault.searchByTitle(titleQuery);
                break;
            case 2:
                System.out.print("Enter programming language to search: ");
                String langQuery = scanner.nextLine().trim();
                results = vault.searchByLanguage(langQuery);
                break;
            case 3:
                System.out.print("Enter search keyword: ");
                String generalQuery = scanner.nextLine().trim();
                results = vault.search(generalQuery);
                break;
            case 4:
                System.out.println("Search cancelled.");
                return;
            default:
                System.out.println("Invalid search option.");
                return;
        }

        if (results.isEmpty()) {
            System.out.println("No matching snippets found.");
        } else {
            System.out.println("Found " + results.size() + " matching snippet(s):");
            for (Snippet snippet : results) {
                printSnippet(snippet);
            }
        }
    }

    /**
     * Handles deleting a snippet by its unique ID.
     */
    private static void deleteSnippet(Scanner scanner, CodeVault vault) {
        if (vault.isEmpty()) {
            System.out.println("The vault is currently empty. No snippets to delete.");
            return;
        }

        System.out.println("--- Delete Snippet ---");
        int id = readIntegerInput(scanner, "Enter the ID of the snippet to delete: ");

        boolean removed = vault.deleteSnippet(id);
        if (removed) {
            System.out.println("Success: Snippet with ID " + id + " has been deleted.");
        } else {
            System.out.println("Error: No snippet found with ID " + id + ".");
        }
    }

    /**
     * Displays the total count of snippets.
     */
    private static void displayTotalCount(CodeVault vault) {
        System.out.println("--- Total Snippets ---");
        System.out.println("Total snippets saved: " + vault.getTotalSnippets());
    }

    /**
     * Prints a single snippet in a clean, readable format.
     */
    private static void printSnippet(Snippet snippet) {
        System.out.println("----------------------------------------");
        System.out.println("ID:       " + snippet.getId());
        System.out.println("Title:    " + snippet.getTitle());
        System.out.println("Language: " + snippet.getLanguage());
        System.out.println("Code:");
        System.out.println(snippet.getCode());
        System.out.println("----------------------------------------");
    }

    /**
     * Safely reads an integer from console, reprompting if invalid.
     */
    private static int readIntegerInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }
}
