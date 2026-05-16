Here's your **Sunday prep plan** — Managerial round + final revision before Monday.

---

## **Sunday Schedule (8-10 hours)**

---

### **Block 1: Managerial Round Prep (4-5 hours)**

This round tests **communication, ownership, teamwork, problem-solving mindset.** Not coding.

---

**Topic 1 — "Tell Me About Yourself" (30 min)**

Practice this exact script until it flows naturally (under 2 minutes):

```
"Hi, I'm Iswar Chandra Rana, a Software Engineer with about 
2 years of experience, currently working at Zeesta Software 
Services in Hyderabad.

I primarily work with Java 17, Spring Boot, and MySQL building 
production-grade backend systems. My main project is Arealytics, 
a real estate analytics platform where I migrated a legacy 
Node.js system to Spring Boot, improving performance by 40%.

One of my proudest achievements was optimizing database queries 
from 30 seconds down to under 5 seconds through indexing, 
denormalization, and stored procedure refactoring.

I've also worked with Docker, Flyway for database migrations, 
Keycloak for identity management, and recently built a custom 
MySQL MCP server for AI-assisted database exploration.

I'm excited about Blackhawk Network because of the payment 
and transaction scale you operate at — processing billions in 
gift card and prepaid transactions. That kind of high-reliability, 
high-volume backend engineering is exactly where I want to grow."
```

**Practice 5 times out loud.** Time yourself. Should feel natural, not rehearsed.

---

**Topic 2 — STAR Stories (90 min)**

Prepare **6 stories** using STAR format. Practice each one **under 3 minutes.**

**Story 1: Biggest Technical Achievement**
```
Situation: Arealytics had stored procedures taking 30 seconds, 
           users complaining, system nearly unusable

Task:      Diagnose root cause and fix without breaking 
           existing functionality

Action:    - Ran EXPLAIN ANALYZE, found full table scans
           - Added composite index on SaleGroup(IsActive, SaleID, GroupID)
           - Rewrote SP to eliminate double materialization
           - Used set-based operations instead of cursors
           - Ran ANALYZE TABLE to fix stale optimizer stats

Result:    Query time dropped from 30s to under 5s (83% reduction)
           Retrieval improved by 95%
           Zero downtime, no breaking changes
```

**Story 2: Legacy Migration / Ownership**
```
Situation: Arealytics ran on Node.js with heavy stored procedures,
           messy codebase, hard to maintain and scale

Task:      Migrate to Spring Boot while keeping system running

Action:    - Designed clean layered architecture 
           - Modular service design for separation of concerns
           - Migrated module by module, not big bang
           - Added proper error handling, pagination, OpenAPI docs
           - Dockerized with multi-stage builds

Result:    40% performance improvement
           Cleaner codebase, easier to onboard new developers
           Proper CI/CD with GitHub Actions
```

**Story 3: Innovation / Going Above and Beyond**
```
Situation: Developers spending hours manually exploring database
           schema and debugging slow queries

Task:      Find a way to speed up development workflow

Action:    - Built a custom MySQL MCP server using Node.js
           - Exposed 28 read-only tools for AI-assisted exploration
           - Schema browsing, query optimization suggestions
           - Safe read-only — no risk of data modification

Result:    Significantly faster debugging and schema exploration
           Team adopted it for daily development workflow
           Demonstrated initiative beyond assigned work
```

**Story 4: Handling a Production Crisis**
```
Situation: Production server crashed — Node.js OOM errors,
           PM2 process manager failing, users locked out

Task:      Get system back online immediately

Action:    - SSH'd into server, identified OOM as root cause
           - Killed runaway processes manually
           - Configured PM2 memory limits
           - Set heap size caps
           - Added swap space as safety net
           - Investigated root cause — unclosed DB connections

Result:    System restored within hours
           Preventive measures ensured it didn't happen again
           Documented the incident for team knowledge
```

**Story 5: Teamwork / Disagreement**
```
Situation: Team wanted to keep business rules hardcoded in 
           application code for simplicity

Task:      I believed we needed a centralized rules engine
           for maintainability

Action:    - Didn't push my opinion aggressively
           - Created a small proof-of-concept with Drools
           - Showed how 30+ rules could be managed without 
             code redeployment
           - Demonstrated time saved for future rule changes
           - Let the results speak for themselves

Result:    Team agreed to adopt Drools
           30+ configurable rules running without redeployment
           Business team can request rule changes faster
```

**Story 6: Failure / Learning**
```
Situation: During early days at Zeesta, I deployed a database
           migration without fully testing in staging

Task:      Migration caused unexpected column conflicts in production

Action:    - Took ownership immediately, didn't blame anyone
           - Rolled back the migration manually
           - Fixed the issue and re-tested thoroughly
           - After that, set up Flyway with proper versioned 
             and repeatable migrations
           - Automated SP deployments via GitHub Actions

Result:    Never had a migration issue again
           Team adopted Flyway as standard practice
           Learned: never skip staging, automate everything
```

---

**Topic 3 — Common Managerial Questions (60 min)**

Practice answering each one **out loud:**

**About You:**
- Why do you want to leave your current company?
  → *"I've grown a lot at Zeesta, but I want to work at a larger scale. Blackhawk processes billions in transactions — that's the kind of engineering challenge I'm ready for."*

- Why Blackhawk Network?
  → *"Three reasons: scale of transactions, payment domain expertise, and the engineering challenges of high-reliability systems. Gift card and prepaid payment processing requires the kind of backend reliability I want to build my career around."*

- Where do you see yourself in 3 years?
  → *"I want to grow into a senior engineer who can own and design systems end to end — from database schema to API design to deployment strategy. I also want to mentor junior engineers the way my seniors helped me."*

- What's your biggest strength?
  → *"I take ownership. When queries were taking 30 seconds, nobody assigned me to fix it — I dug into the execution plans myself and reduced it to under 5 seconds. I don't wait to be told what to do."*

- What's your biggest weakness?
  → *"Sometimes I go too deep into optimization before shipping. I've learned to balance — ship a working solution first, then optimize. My SP optimization taught me that iterative improvement works better than perfection upfront."*

**About Work Style:**
- How do you handle tight deadlines?
  → *"I break the task into must-have vs nice-to-have. During the Node.js to Spring Boot migration, we had a tight timeline. I prioritized core API functionality first, deferred nice-to-haves like advanced caching, and delivered on time."*

- How do you learn new technologies?
  → *"I learn by building. For example, to learn Drools, I didn't just read docs — I built a POC with real business rules from our project. I also use AI tools like Claude and Copilot to accelerate learning, but I make sure I understand every line."*

- How do you handle code reviews / feedback?
  → *"I welcome it. Early in my career I took feedback personally, but I've learned that code review is about making the product better, not judging the person. I now actively ask for reviews before merging."*

- Describe your ideal work environment
  → *"A team that values ownership, clean code, and continuous learning. I thrive when I can take a problem end-to-end — from understanding the requirement to deploying the solution."*

**Tricky Questions:**
- What if you and your manager disagree on a technical approach?
  → *"I'd present my reasoning with data or a POC. Like with Drools — I didn't argue, I built a proof. If after that my manager still disagrees and has valid reasons, I'd commit to their approach. The team moving forward matters more than being right."*

- Tell me about a time you helped a teammate
  → *"When a junior developer was struggling with Docker, I paired with them for an afternoon. I walked through our Dockerfile, explained multi-stage builds, and helped them Dockerize their service. They were able to do it independently after that."*

- What would you do in your first 30 days at Blackhawk?
  → *"First two weeks: understand the codebase, domain, and team processes. Read documentation, set up local environment, review recent PRs. Week 3-4: pick up small tasks or bug fixes to start contributing. Ask a lot of questions — there's no faster way to learn."*

---

**Topic 4 — Questions YOU Should Ask Them (15 min)**

Always have 3-4 questions ready. Shows genuine interest:

```
1. "What does the tech stack look like for the team 
    I'd be joining?"

2. "What's the biggest engineering challenge the team 
    is currently facing?"

3. "How does the team handle deployments — CI/CD, 
    release cadence?"

4. "What does growth and learning look like for 
    engineers here? Are there opportunities to work 
    on different services?"

5. "How does Blackhawk handle transaction reliability 
    at scale — any specific patterns or tools I'd 
    get to work with?"
```

---

### **Block 2: Full Technical Revision (3-4 hours)**

Go through **Friday + Saturday topics** in rapid-fire mode. Don't re-study — just verify you remember.

**Java Rapid Fire (45 min)**
```
✅ HashMap internals — say it out loud in 2 minutes
✅ ConcurrentHashMap — how is it different?
✅ synchronized vs volatile — one sentence each
✅ CompletableFuture — when would you use it?
✅ Stream API — write a groupBy example mentally
✅ SOLID — one project example per principle
✅ String immutability — why?
```

**Spring Boot Rapid Fire (45 min)**
```
✅ Bean lifecycle — say the full flow
✅ @Transactional — propagation REQUIRED vs REQUIRES_NEW
✅ Isolation levels — name all 4, one problem each solves
✅ N+1 problem — what is it, how to fix
✅ Spring Security filter chain — how JWT request flows
✅ Constructor injection — why preferred
✅ @ControllerAdvice — how you use it
```

**Database Rapid Fire (45 min)**
```
✅ ACID — gift card purchase example
✅ Composite index — column order matters, why?
✅ Optimistic vs Pessimistic locking — when each?
✅ Your SP optimization — tell the full story in 3 minutes
✅ Denormalization — your story, tradeoffs
✅ Isolation levels — which for payment transactions?
✅ Deadlock — what causes it?
```

**System Design Rapid Fire (30 min)**
```
✅ Gift Card System — walk through in 10 minutes
  - APIs, schema, idempotency, caching, async notifications
✅ Payment API — state machine, retry, webhook
✅ Double-Entry Ledger — explain your AreaDocs design
✅ Blue-Green Deployment — explain your Keycloak approach
```

**DSA Rapid Fire (30 min)**
```
✅ Two Sum — write solution mentally
✅ Reverse LinkedList — iterative approach
✅ Valid Parentheses — stack approach
✅ Binary Search — template
✅ Max Depth of Binary Tree — recursive
✅ Sliding Window — template pattern
```

---

### **Block 3: Night Before Checklist (30 min)**

```
Logistics:
  ✅ Know the office address, parking, timing
  ✅ Reach 15 minutes early
  ✅ Carry printed resume (2-3 copies)
  ✅ Carry a notebook and pen
  ✅ Dress smart casual or formal
  ✅ Charge your phone

Mental prep:
  ✅ It's okay to say "I don't know, but here's how I'd approach it"
  ✅ It's okay to ask clarifying questions
  ✅ Take 5 seconds to think before answering
  ✅ If stuck on DSA, talk through your thought process
  ✅ Be honest — interviewers respect honesty over bluffing
  ✅ Smile, make eye contact, be confident

Morning routine:
  ✅ Light breakfast
  ✅ Review your STAR stories one last time
  ✅ Review "Tell me about yourself" one last time
  ✅ Deep breath — you've prepared well
```

---

## **Monday Interview Flow**

```
Round 1 — Technical (45-60 min):
  Likely: Java + Spring Boot + SQL
  Your weapon: Project examples + deep knowledge

Round 2 — Technical (45-60 min):
  Likely: System Design + DSA + Database
  Your weapon: Gift card design + clean problem solving

Round 3 — Managerial (30-45 min):
  Likely: STAR stories + cultural fit + why Blackhawk
  Your weapon: 6 prepared stories + genuine enthusiasm
```

---

## **One Final Advice**

> **Connect every answer to your real experience.**

Don't just say *"HashMap uses buckets and treeification."*

Say *"HashMap uses buckets and treeification — in my project I used ConcurrentHashMap for a shared cache because we had multiple threads accessing sale data simultaneously."*

**Real experience > textbook answers.** That's your biggest advantage.

---

You've got this Iswar! Want me to do a **mock interview** for any of the three rounds tonight or tomorrow?