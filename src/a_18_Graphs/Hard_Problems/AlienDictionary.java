package a_18_Graphs.Hard_Problems;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

/**
 * Alien Dictionary — deduce the character ordering of an alien alphabet from a sorted word list.
 * <p>
 * Idea:
 * Step 1: Compare each adjacent word pair — the first mismatched character gives a directed
 *         ordering constraint: w1[j] → w2[j] (w1[j] comes before w2[j] in the alien alphabet).
 * Step 2: Build a directed graph on the K alien characters using those constraints.
 * Step 3: Topological sort of the graph = the alien alphabet order.
 *         If a cycle exists (contradictory constraints) → return "".
 *         If a word is a prefix of the previous word (e.g. "abc" before "ab") → return "".
 * <p>
 * Time  : O(N × L + K) — N words of max length L to extract edges, K characters for topo sort.
 * Space : O(K)         — adjacency list on K ≤ 26 characters.
 */
public class AlienDictionary {

    /*
     * Mental Model:
     *
     *   Graph nodes = unique characters (mapped to 0–25 via c - 'a')
     *   Graph edges = ordering constraints extracted from adjacent word pairs
     *
     *   Compare words[i] and words[i+1] character by character:
     *       first mismatch at position j  →  add edge words[i][j] → words[i+1][j], stop
     *       no mismatch but words[i] longer than words[i+1]  →  invalid ("abc" before "ab")
     *
     *   Topological sort (DFS or BFS/Kahn's) on the character graph:
     *       no cycle  →  valid alien order (characters in topo order)
     *       cycle     →  contradictory constraints → ""
     *
     *   Only include characters that actually appear in the word list (present[]).
     */

    // ─────────────────────────────────────────────────────────────
    // Method 1: DFS (post-order stack + inPath cycle guard)
    // ─────────────────────────────────────────────────────────────

    public String findOrderDFS(String[] words, int N, int K) {
        List<Integer>[] adj = buildAdj(words);
        if (adj == null) return ""; // prefix violation detected

        boolean[] present = presentLetters(words);
        boolean[] visited = new boolean[26];
        boolean[] inPath  = new boolean[26];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < 26; i++) {
            if (present[i] && !visited[i]) {
                if (hasCycle(i, adj, visited, inPath, stack))
                    return ""; // cycle → invalid
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            int c = stack.pop();
            if (present[c])
                sb.append((char) ('a' + c));
        }
        return sb.toString();
    }

    private boolean hasCycle(int node, List<Integer>[] adj,
                             boolean[] visited, boolean[] inPath, Deque<Integer> stack) {
        visited[node] = true;
        inPath[node]  = true;

        for (int neighbour : adj[node]) {
            if (inPath[neighbour]) return true;
            if (!visited[neighbour] && hasCycle(neighbour, adj, visited, inPath, stack)) return true;
        }

        inPath[node] = false;
        stack.push(node); // post-order
        return false;
    }

    // ─────────────────────────────────────────────────────────────
    // Method 2: BFS — Kahn's Algorithm
    // ─────────────────────────────────────────────────────────────

    public String findOrderBFS(String[] words, int N, int K) {
        List<Integer>[] adj = buildAdj(words);
        if (adj == null) return ""; // prefix violation detected

        boolean[] present = presentLetters(words);

        int[] indegree = new int[26];
        for (int u = 0; u < 26; u++) {
            for (int v : adj[u]) indegree[v]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        int distinctCount = 0;
        for (int i = 0; i < 26; i++) {
            if (present[i]) {
                distinctCount++;
                if (indegree[i] == 0)
                    queue.offer(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            int node = queue.poll();
            sb.append((char) ('a' + node));
            for (int neighbour : adj[node]) {
                if (--indegree[neighbour] == 0)
                    queue.offer(neighbour);
            }
        }

        return sb.length() == distinctCount ? sb.toString() : ""; // cycle → not all chars processed
    }

    // ─────────────────────────────────────────────────────────────
    // Shared helpers
    // ─────────────────────────────────────────────────────────────

    @SuppressWarnings("unchecked")
    private static List<Integer>[] buildAdj(String[] words) {
        List<Integer>[] adj = new ArrayList[26];
        for (int i = 0; i < 26; i++) adj[i] = new ArrayList<>();

        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i], w2 = words[i + 1];
            int len = Math.min(w1.length(), w2.length());
            boolean foundMismatch = false;

            for (int j = 0; j < len; j++) {
                if (w1.charAt(j) != w2.charAt(j)) {
                    adj[w1.charAt(j) - 'a'].add(w2.charAt(j) - 'a');
                    foundMismatch = true;
                    break;
                }
            }
            if (!foundMismatch && w1.length() > w2.length()) return null; // "abc" before "ab" → invalid
        }
        return adj;
    }

    private static boolean[] presentLetters(String[] words) {
        boolean[] present = new boolean[26];
        for (String word : words) {
            for (char c : word.toCharArray())
                present[c - 'a'] = true;
        }
        return present;
    }

    // ─────────────────────────────────────────────────────────────
    // Main
    // ─────────────────────────────────────────────────────────────

    public static void main(String[] args) {
        AlienDictionary obj = new AlienDictionary();

        // TC1: classic example — order: b < a < c < d
        //   "baa","abcd","abca","cab","cad"
        //   Constraints: b<a (baa vs abcd), a<c (abcd vs abca? no — abca < cab via a<c), c<d (cab vs cad)
        String[] w1 = {"baa", "abcd", "abca", "cab", "cad"};
        System.out.println("TC1 DFS: " + obj.findOrderDFS(w1, 5, 4)); // contains b,a,c,d in order
        System.out.println("TC1 BFS: " + obj.findOrderBFS(w1, 5, 4));

        // TC2: simple two-word comparison — x < y
        String[] w2 = {"x", "y"};
        System.out.println("TC2 DFS: " + obj.findOrderDFS(w2, 2, 2)); // "xy"
        System.out.println("TC2 BFS: " + obj.findOrderBFS(w2, 2, 2)); // "xy"

        // TC3: contradictory order (cycle) — a < b and b < a
        //   "ab","ba","ab"  →  a<b (ab vs ba), b<a (ba vs ab) → cycle → ""
        String[] w3 = {"ab", "ba", "ab"};
        System.out.println("TC3 DFS: " + obj.findOrderDFS(w3, 3, 2)); // ""
        System.out.println("TC3 BFS: " + obj.findOrderBFS(w3, 3, 2)); // ""

        // TC4: prefix violation — "abc" appears before "ab" → invalid
        String[] w4 = {"abc", "ab"};
        System.out.println("TC4 DFS: " + obj.findOrderDFS(w4, 2, 3)); // ""
        System.out.println("TC4 BFS: " + obj.findOrderBFS(w4, 2, 3)); // ""

        // TC5: single word — all characters present but no ordering constraints
        String[] w5 = {"abc"};
        System.out.println("TC5 DFS: " + obj.findOrderDFS(w5, 1, 3)); // "abc" or any permutation of a,b,c
        System.out.println("TC5 BFS: " + obj.findOrderBFS(w5, 1, 3)); // "abc" or any permutation of a,b,c
    }
}
