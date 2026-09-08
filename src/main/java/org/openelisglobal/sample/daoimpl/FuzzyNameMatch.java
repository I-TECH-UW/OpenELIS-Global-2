package org.openelisglobal.sample.daoimpl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * Tolerances for the misspelled-name half of the patient search.
 *
 * <p>
 * PostgreSQL's {@code levenshtein()} is plain Levenshtein, so swapping two
 * neighbouring letters - by far the most common typing slip - costs two edits,
 * the same as two unrelated mistakes. Raising the edit budget to two for every
 * short term would match almost anything, so the swapped spellings are instead
 * matched literally and the edit budget stays tight.
 */
final class FuzzyNameMatch {

    /**
     * Below three characters a term already matches a large share of the table
     * through the substring predicate, and fuzziness would add only noise.
     */
    private static final int MIN_FUZZY_LENGTH = 3;

    private static final int TWO_EDIT_LENGTH = 6;

    /**
     * PostgreSQL's levenshtein() raises an error above 255 characters, so a longer
     * term is matched by the substring predicate alone rather than failing the
     * whole search.
     */
    static final int MAX_FUZZY_LENGTH = 255;

    private FuzzyNameMatch() {
    }

    static String normalize(String term) {
        return term.trim().toLowerCase(Locale.ROOT);
    }

    static boolean isFuzzyMatchable(String term) {
        if (term == null) {
            return false;
        }
        int length = normalize(term).length();
        return length >= MIN_FUZZY_LENGTH && length <= MAX_FUZZY_LENGTH;
    }

    static int maxEdits(String term) {
        return normalize(term).length() >= TWO_EDIT_LENGTH ? 2 : 1;
    }

    /** Every spelling of the term with one pair of neighbouring letters swapped. */
    static List<String> adjacentSwaps(String term) {
        String normalized = normalize(term);
        Set<String> swaps = new LinkedHashSet<>();
        for (int i = 0; i + 1 < normalized.length(); i++) {
            if (normalized.charAt(i) == normalized.charAt(i + 1)) {
                continue;
            }
            swaps.add(normalized.substring(0, i) + normalized.charAt(i + 1) + normalized.charAt(i)
                    + normalized.substring(i + 2));
        }
        return new ArrayList<>(swaps);
    }
}
