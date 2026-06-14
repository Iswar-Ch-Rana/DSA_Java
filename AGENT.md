# DSA Java — Refactoring & Revision Guide

Two purposes:

1. **Agent instructions** — tell any AI agent how to refactor DSA files.
2. **Revision reference** — use this before interviews to recall patterns fast.

---

## How to Revise a File (5-min drill)

```
Step 1 (10s) → Read JavaDoc        → confirm WHAT + Time/Space
Step 2 (30s) → Read Mental Model   → reconstruct HOW in your head
Step 3 (60s) → Glance test cases   → recall edge cases
Step 4 (60s) → Read Key note       → recall the one tricky thing
Skip inline comments unless you blanked on something.
```

---

## Revision Cheat Sheet — Common Patterns

### Binary Tree Traversals

| Traversal | Collect When     | Direction           |
|-----------|------------------|---------------------|
| Preorder  | Enter node       | root → left → right |
| Inorder   | Between children | left → root → right |
| Postorder | Exit node        | left → right → root |

**Morris — the only difference:**

```
Preorder  → collect in Phase 1 (thread absent)
Inorder   → collect in Phase 2 (thread present)
```

**Morris two phases (when left exists):**

```
Phase 1: predecessor.right == null    → [collect if preorder], create thread, go LEFT
Phase 2: predecessor.right == current → [collect if inorder],  remove thread, go RIGHT
```

---

### BST Operations

| Operation | Mental Model                                                                        |
|-----------|-------------------------------------------------------------------------------------|
| Search    | null/match → return. val< → left. val> → right                                      |
| Insert    | Search for null slot → insert there                                                 |
| Delete    | no-left→right. no-right→left. both→attach right to bottomRight of left, return left |
| Floor     | node>key → ceil=node, go left. node<key → floor=node, go right                      |

---

### Tree BFS Patterns

| Problem        | Key Trick                                                  |
|----------------|------------------------------------------------------------|
| Level order    | snapshot `levelSize = queue.size()` at start of each level |
| Right view     | right-first DFS; first node per level = rightmost          |
| Left view      | BFS; peek first node per level = leftmost                  |
| Top view       | first-write per column (BFS + column map)                  |
| Bottom view    | overwrite per column — last write wins                     |
| Vertical order | BFS + TreeMap<column, List> — TreeMap auto-sorts columns   |
| Zigzag         | BFS + direction flag; flip after each level                |
| Width          | BFS + heap index, normalize; width = last - first + 1      |

---

### Tree Construction

| From                | Key Rule                                                 |
|---------------------|----------------------------------------------------------|
| Preorder + Inorder  | preorder[i++] = root. Split inorder at root → left/right |
| Postorder + Inorder | postorder[i--] = root. Build RIGHT first, then LEFT      |

**Only difference:**

```
Preorder  → index 0,   increment (++), LEFT  then RIGHT
Postorder → index end, decrement (--), RIGHT then LEFT
```

---

### Serialize / Deserialize

| Approach | Serialize                        | Deserialize                     |
|----------|----------------------------------|---------------------------------|
| BFS      | level-order, write "n" for nulls | each parent pops 2 tokens       |
| DFS      | preorder, write "n" for nulls    | consume tokens with int[] index |

**Gotcha:** BFS serialize → must use `LinkedList` not `ArrayDeque` — null children must be enqueued to keep tokens
aligned.

---

### Complexity Quick Reference

| Algorithm                    | Time       | Space          |
|------------------------------|------------|----------------|
| Morris Traversal             | O(N)       | O(1)           |
| Count Nodes (Complete BT)    | O((logN)²) | O(logN)        |
| BST Search / Insert / Delete | O(H) avg   | O(1) iterative |
| Burn Tree                    | O(N)       | O(N)           |
| Vertical Order               | O(N log N) | O(N)           |
| Merge Sort                   | O(N log N) | O(N)           |
| Rotate Array (optimal)       | O(N)       | O(1)           |

---

### Interview Traps — Always Remember

| Trap                                 | Fix                                                  |
|--------------------------------------|------------------------------------------------------|
| Static index not reset between calls | Use `int[] idx = {0}` wrapper                        |
| `ArrayDeque` rejects null            | Use `LinkedList` for BFS serialize                   |
| `Math.pow` floating-point            | Use `(int)((1L << h) - 1)`                           |
| `temp.reversed()` Java version       | Use manual backwards `for` loop                      |
| Skewed tree + recursion              | Prefer iterative for BST ops                         |
| Morris modifies tree temporarily     | Threads removed in Phase 2 — tree is restored        |
| Postorder → RIGHT before LEFT        | Index decrements from end                            |
| Delete BST (two children)            | Attach right subtree to bottom-right of left subtree |

---

### Rotate Array — Reverse Trick

```
k %= n
reverse [0,   k-1]
reverse [k,   n-1]
reverse [0,   n-1]
```

---

### Boundary Traversal Order

```
1. Root              (if not leaf)
2. Left boundary     top-down,  exclude leaves
3. All leaves        left → right
4. Right boundary    collect top-down, add in reverse (bottom-up)
```

---

## Agent Instructions — How to Refactor a File

### Skill 1 — Class-Level JavaDoc

```java
/**
 * <Problem name> — <one-line summary>.
 * <p>
 * Idea:
 * - <step 1>
 * - <step 2>
 * <p>
 * Time  : O(?)
 * Space : O(?)
 * <p>
 * Key   : <the one thing to remember — interview anchor>
 */
```

---

### Skill 2 — Mental Model Block

Place directly above each main method. Pseudocode only (4–10 lines).

```java
/*
 * Mental Model:
 *
 *   if <condition>:
 *       <action>
 *
 *   else:
 *       <sub-condition>:
 *           <action>
 */
```

---

### Skill 3 — Naming Conventions

| Bad         | Good                                        |
|-------------|---------------------------------------------|
| `q`         | `queue`                                     |
| `ans`       | `result`                                    |
| `ele`       | `rootVal` / `current`                       |
| `len`       | `levelSize`                                 |
| `pos`       | `inorderIdx`                                |
| `map`       | `columnMap` / `parentMap` / `levelMap`      |
| `curr`      | `current`                                   |
| `leftChild` | `predecessor` (Morris)                      |
| `dummy`     | `originalRoot`                              |
| `healper`   | `handleDeletion`                            |
| `solve`     | descriptive e.g. `dfsRight`, `buildSubTree` |

---

### Skill 4 — Inline Comments

Only add where *why* is non-obvious:

```java
// Phase 1: no thread → COLLECT, create thread, go left.
// Phase 2: thread exists → remove thread, go right.
// Equal depths → perfect subtree → use 2^h - 1 formula.
// RIGHT before LEFT — postorder index decrements from end.
// -1 because last BFS level increments time but burns nothing new.
// LinkedList (not ArrayDeque) — allows null children in BFS queue.
```

---

### Skill 5 — Section Dividers (multi-approach files)

```java
// ─────────────────────────────────────────────────────────────
// Method 1: Recursive — O(H) stack
// ─────────────────────────────────────────────────────────────

// ─────────────────────────────────────────────────────────────
// Shared Helper (used by both methods)
// ─────────────────────────────────────────────────────────────
```

---

### Skill 6 — Alternate Approaches

Structure: swap only Step 1, share Step 2.

```
primary()   → Step1a() + sharedHelper()
alternate() → Step1b() + sharedHelper()
```

Add when:

- Recursive → add Iterative (O(1) space, preferred for BST)
- DFS → add BFS
- Brute O(N²) → add Optimal

---

### Skill 7 — Test Cases (4–6 per file)

| # | Case                                     |
|---|------------------------------------------|
| 1 | `null` root                              |
| 2 | Single node                              |
| 3 | Left-skewed                              |
| 4 | Right-skewed                             |
| 5 | Standard mixed tree                      |
| 6 | Interview trap (non-obvious depth/shape) |

---

### Skill 8 — Common Gotchas to Always Comment

| Trap           | Comment to add                                          |
|----------------|---------------------------------------------------------|
| `static` index | `// int[] used as mutable counter — resets per call`    |
| `LinkedList`   | `// LinkedList (not ArrayDeque) — allows null children` |
| `1L << h`      | `// Avoids floating-point error from Math.pow`          |
| Backwards loop | `// Java 8+ safe reverse`                               |

---

### Skill 9 — Explicit Imports

Replace `import java.util.*` with individual imports.

---

## Quick Checklist (per file)

- [ ] Class JavaDoc — Idea + TC/SC + Key note
- [ ] Mental model block above method(s)
- [ ] Self-documenting variable names
- [ ] Explicit imports (no wildcard)
- [ ] Inline comments on non-obvious lines only
- [ ] Section dividers for multiple approaches
- [ ] Alternate approach with shared helper
- [ ] 4–6 test cases (null, single, skewed, tricky)
- [ ] ASCII tree diagrams for non-trivial test trees
- [ ] One-line interview anchor at end of JavaDoc
