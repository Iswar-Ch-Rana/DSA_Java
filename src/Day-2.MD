Here's your **Saturday prep plan** — System Design, Database, and DSA.

---

## **Saturday Schedule (10-12 hours total)**

---

### **Block 1: Database Deep Dive (3-4 hours)**

This is **critical** for Blackhawk — they deal with payment transactions at scale.

**Topic 1 — SQL Fundamentals (45 min)**
- JOINs — INNER, LEFT, RIGHT, FULL, CROSS, SELF join
- Subqueries vs JOINs — when to use which
- GROUP BY, HAVING, ORDER BY execution order
- Window functions — ROW_NUMBER, RANK, DENSE_RANK, LAG, LEAD
- UNION vs UNION ALL
- EXISTS vs IN — performance difference
- COALESCE, CASE WHEN
- **Practice:** Write 5-6 queries from memory — employee-department type problems
- **Prepare:** Your stored procedure optimization story with exact SQL examples

**Topic 2 — Indexing Deep Dive (45 min)**
- What is an index — B-tree structure, how it speeds up reads
- Clustered vs Non-clustered index
- Composite index — column order matters (leftmost prefix rule)
- Covering index — what it is, when to use
- When NOT to index — low cardinality, frequent writes, small tables
- Index scan vs Index seek vs Full table scan
- EXPLAIN / EXPLAIN ANALYZE — how to read execution plans
- **Prepare:** *"I added a composite index on SaleGroup(IsActive, SaleID, GroupID) which eliminated a full table scan"* — be ready to explain WHY that column order

**Topic 3 — Transactions & ACID (60 min)**
- ACID properties — explain each with a **gift card purchase example:**
    - Atomicity: debit buyer + credit gift card = one unit
    - Consistency: balance can never go negative
    - Isolation: two people buying last gift card simultaneously
    - Durability: once confirmed, survives server crash
- Isolation levels with examples:
    - READ_UNCOMMITTED → dirty read (see uncommitted data)
    - READ_COMMITTED → no dirty read, but non-repeatable read possible
    - REPEATABLE_READ → no dirty/non-repeatable read, phantom read possible
    - SERIALIZABLE → safest, slowest
- Deadlock — what causes it, how to detect, how to prevent
- Optimistic locking vs Pessimistic locking
    - Optimistic → version column, @Version in JPA
    - Pessimistic → SELECT FOR UPDATE
- **Prepare:** *"For a gift card balance deduction, which locking strategy would you use?"* → Pessimistic for critical financial ops, Optimistic for read-heavy

**Topic 4 — Database Design (45 min)**
- Normalization — 1NF, 2NF, 3NF, BCNF with examples
- Denormalization — why, when, tradeoffs
- **Prepare:** Your denormalization story from Arealytics — 95% retrieval improvement
- One-to-One, One-to-Many, Many-to-Many — how to model in JPA and raw SQL
- Soft delete vs Hard delete — IsActive flag pattern
- Audit columns — CreatedBy, CreatedDate, ModifiedBy, ModifiedDate
- UUID vs Auto-increment for primary keys — tradeoffs
- Database partitioning — horizontal (sharding) vs vertical

**Topic 5 — Performance & Advanced (30 min)**
- Query optimization techniques — avoid SELECT *, use LIMIT, proper indexing
- Connection pooling — HikariCP, why it matters
- Read replicas — when to use
- Database migration — Flyway (you know this well)
- Stored procedures — pros/cons, when to use vs application-level logic
- **Prepare:** *"Why did Arealytics use stored procedures? Would you recommend that approach?"* — be honest about tradeoffs

---

### **Block 2: System Design (3-4 hours)**

For 1-2 years experience, they won't ask full-scale design. Expect **API design + basic architecture.**

**Topic 6 — System Design Fundamentals (60 min)**
- Client → Load Balancer → API Gateway → Services → Database
- Monolith vs Microservices — tradeoffs, when to choose which
- Horizontal vs Vertical scaling
- Load balancing — round robin, least connections
- Caching — when to cache, cache invalidation strategies (TTL, write-through, write-behind)
- Redis — use cases (session, cache, rate limiting, pub/sub)
- CDN — what it does, when to use
- Message Queue — sync vs async communication, when to use Kafka vs RabbitMQ
- CAP theorem — Consistency, Availability, Partition tolerance (pick 2)
- Database replication — master-slave, read replicas
- API Gateway — rate limiting, auth, routing

**Topic 7 — Design a Gift Card System (60 min)**

This is **most likely** question given Blackhawk's domain. Practice this end-to-end:

```
Requirements:
  - User buys a gift card (physical or digital)
  - Gift card has a balance
  - User can check balance
  - User can redeem at merchant
  - User can transfer balance
  - Gift cards can expire
  - Handle millions of transactions daily

Your answer structure:
  1. Clarify requirements (2 min)
  2. API design (5 min)
  3. Database schema (5 min)
  4. High-level architecture (5 min)
  5. Deep dive on one component (5 min)
  6. Handle edge cases (5 min)

API Design:
  POST   /api/gift-cards              → purchase a gift card
  GET    /api/gift-cards/{id}/balance  → check balance
  POST   /api/gift-cards/{id}/redeem  → redeem at merchant
  POST   /api/gift-cards/{id}/transfer → transfer to another card
  GET    /api/gift-cards/{id}/transactions → transaction history

Database Tables:
  gift_cards (id, code, balance, status, expiry_date, buyer_id, created_at)
  transactions (id, gift_card_id, type, amount, merchant_id, idempotency_key, created_at)
  merchants (id, name, api_key)

Key Design Decisions:
  - Idempotency key on transactions → prevent double charge
  - Pessimistic lock on balance update → prevent race condition
  - Transaction log → immutable audit trail
  - Redis cache for balance checks → reduce DB load
  - Kafka for async notifications → email/SMS after purchase
```

**Topic 8 — Design a Payment API (45 min)**

```
Key concepts to cover:
  - Idempotency → unique request_id per transaction
  - State machine → INITIATED → PROCESSING → SUCCESS/FAILED
  - Retry with exponential backoff
  - Webhook callbacks → notify merchant of result
  - Timeout handling → what if payment gateway doesn't respond?
  - Reconciliation → daily batch to match records
  - PCI compliance basics → never store raw card numbers
```

**Topic 9 — Common Design Patterns in Your Resume (30 min)**
- Double-Entry Ledger — you built this in AreaDocs, explain clearly
    - Every transaction has debit + credit entries
    - Sum of all entries = 0 always
    - Why it ensures data integrity
- Blue-Green Deployment — how you did it with Keycloak
- Event-driven architecture — when Kafka makes sense
- Circuit Breaker pattern — Resilience4j, what happens when a service is down
- API Gateway pattern — routing, auth, rate limiting

---

### **Block 3: DSA (3-4 hours)**

For 1-2 years SDE, expect **Easy to Medium** LeetCode level. Focus on patterns, not grinding.

**Topic 10 — Arrays & Strings (45 min)**

Practice these patterns:
- Two Sum → HashMap approach O(n)
- Two Pointer → sorted array, container with most water
- Sliding Window → longest substring without repeating chars, max sum subarray
- Prefix Sum → subarray sum equals K
- Kadane's Algorithm → maximum subarray sum

**Problems to solve:**
```
1. Two Sum (Easy)
2. Best Time to Buy and Sell Stock (Easy)
3. Maximum Subarray (Medium)
4. Longest Substring Without Repeating Characters (Medium)
5. Group Anagrams (Medium)
```

**Topic 11 — HashMap & HashSet (30 min)**

- Frequency count pattern
- Finding duplicates
- Two Sum using HashMap
- Valid Anagram
- First non-repeating character

**Problems:**
```
1. Valid Anagram (Easy)
2. First Unique Character in String (Easy)
3. Top K Frequent Elements (Medium)
```

**Topic 12 — LinkedList (30 min)**

- Reverse a linked list (iterative + recursive)
- Detect cycle — Floyd's algorithm (slow/fast pointer)
- Merge two sorted lists
- Find middle node

**Problems:**
```
1. Reverse Linked List (Easy)
2. Linked List Cycle (Easy)
3. Merge Two Sorted Lists (Easy)
4. Remove Nth Node From End (Medium)
```

**Topic 13 — Stack & Queue (30 min)**

- Valid Parentheses
- Min Stack
- Next Greater Element
- Implement Queue using Stacks

**Problems:**
```
1. Valid Parentheses (Easy)
2. Min Stack (Medium)
3. Implement Queue using Stacks (Easy)
```

**Topic 14 — Binary Search (30 min)**

- Classic binary search
- Search in rotated sorted array
- Find first/last position
- Square root using binary search

**Problems:**
```
1. Binary Search (Easy)
2. Search in Rotated Sorted Array (Medium)
3. Find First and Last Position (Medium)
```

**Topic 15 — Trees (30 min)**

- BFS vs DFS traversal
- Inorder, Preorder, Postorder
- Max depth of binary tree
- Level order traversal
- Validate BST

**Problems:**
```
1. Maximum Depth of Binary Tree (Easy)
2. Invert Binary Tree (Easy)
3. Binary Tree Level Order Traversal (Medium)
4. Validate BST (Medium)
```

---

### **Block 4: Evening Revision (30 min)**

```
✅ Can you explain ACID with gift card example? Say it out loud
✅ Can you design a gift card system in 20 minutes? Draw on paper
✅ Can you solve Two Sum, Reverse LinkedList, Valid Parentheses without looking?
✅ Can you explain your Double-Entry Ledger design from AreaDocs?
✅ Sleep well — Sunday is managerial prep + full revision
```

---

## **Priority if You Run Out of Time**

```
MUST DO (non-negotiable):
  ✅ ACID with payment examples
  ✅ Indexing + your optimization story
  ✅ Isolation levels + locking
  ✅ Gift Card System Design
  ✅ DSA: Two Sum, Reverse LinkedList, Valid Parentheses, Binary Search

SHOULD DO:
  ✅ Payment API design
  ✅ Double-Entry Ledger explanation
  ✅ Sliding Window, HashMap problems
  ✅ Trees basics

NICE TO HAVE:
  ✅ CAP theorem
  ✅ Sharding
  ✅ Circuit Breaker pattern
```

---

Want me to create the **Sunday (Day 3) plan for Managerial round + full revision**?