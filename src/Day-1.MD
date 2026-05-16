Here's your **Friday prep plan** — topic by topic, hour by hour.

---

## **Friday Schedule (10-12 hours total)**

Assuming you start morning and take breaks between topics.

---

### **Block 1: Java Core (4-5 hours)**

**Topic 1 — OOP & SOLID (45 min)**
- 4 pillars — Abstraction, Encapsulation, Inheritance, Polymorphism
- Compile-time vs Runtime polymorphism
- Abstract class vs Interface — when to use which
- SOLID principles — prepare one real example for each from Arealytics
- Composition vs Inheritance
- Marker interfaces — Serializable, Cloneable

**Topic 2 — Collections Internals (60 min)**
- How HashMap works internally — hashing, buckets, treeification at 8 nodes
- HashMap vs HashTable vs ConcurrentHashMap
- How ConcurrentHashMap achieves thread safety (segment locking → node locking in Java 8+)
- ArrayList vs LinkedList — when to use which
- HashSet internal implementation (backed by HashMap)
- TreeMap vs LinkedHashMap
- fail-fast vs fail-safe iterators
- Comparable vs Comparator

**Topic 3 — Multithreading & Concurrency (60 min)**
- Thread vs Runnable vs Callable
- synchronized keyword — method level vs block level
- volatile keyword — visibility guarantee
- wait/notify vs sleep
- ExecutorService — FixedThreadPool, CachedThreadPool
- CompletableFuture — thenApply, thenCompose, thenCombine
- Deadlock — what it is, how to prevent
- ThreadLocal — use case in Spring (RequestContextHolder)
- Atomic classes — AtomicInteger, AtomicReference

**Topic 4 — Java 8-17 Features (45 min)**
- Stream API — filter, map, flatMap, reduce, collect
- Optional — why, how, common mistakes
- Functional interfaces — Predicate, Function, Consumer, Supplier
- Method references — Class::method
- Lambda expressions — how they relate to functional interfaces
- Java 14: Records — use as DTOs
- Java 15: Text blocks
- Java 16: Pattern matching for instanceof
- Java 17: Sealed classes

**Topic 5 — Exception Handling & Memory (30 min)**
- Checked vs Unchecked exceptions
- Custom exceptions — when and how
- try-with-resources — AutoCloseable
- Exception hierarchy — Error vs Exception
- Stack vs Heap memory
- Garbage Collection — basics, GC roots, types (G1, ZGC)
- Memory leaks in Java — common causes

**Topic 6 — String & Immutability (20 min)**
- Why String is immutable
- String pool
- String vs StringBuilder vs StringBuffer
- == vs .equals()
- hashCode and equals contract

---

### **Block 2: Spring Boot Deep Dive (4-5 hours)**

**Topic 7 — Spring Core & IoC (45 min)**
- What is IoC and DI — explain simply
- ApplicationContext vs BeanFactory
- Bean lifecycle — instantiation → populate properties → BeanNameAware → BeanFactoryAware → PreInitialization (BeanPostProcessor) → @PostConstruct → InitializingBean → custom init → ready → @PreDestroy → DisposableBean → destroy
- Bean scopes — singleton, prototype, request, session
- @Component vs @Service vs @Repository vs @Controller
- @Autowired — constructor vs field vs setter injection
- Why constructor injection is preferred
- @Qualifier, @Primary
- Profiles — @Profile, application-dev.yml

**Topic 8 — Spring Boot Internals (45 min)**
- Auto-configuration — how it works, @EnableAutoConfiguration
- @SpringBootApplication — what it combines
- application.yml vs application.properties
- Actuator — health, metrics, info endpoints
- Starters — what they are, how they simplify dependencies
- Embedded server — Tomcat, how it starts
- @ConfigurationProperties — type-safe config
- @ConditionalOnProperty, @ConditionalOnClass

**Topic 9 — Spring Data JPA & Hibernate (60 min)**
- JPA vs Hibernate vs Spring Data JPA — relationship
- Entity lifecycle — Transient → Managed → Detached → Removed
- Lazy vs Eager loading — default for @OneToMany, @ManyToOne
- N+1 problem — what it is, how to fix (JOIN FETCH, @EntityGraph, @BatchSize)
- @Query — JPQL vs native queries
- Pagination — Pageable, Page, Slice
- Auditing — @CreatedDate, @LastModifiedDate, @CreatedBy
- Projections — interface-based, class-based
- Cascade types — PERSIST, MERGE, REMOVE, ALL
- orphanRemoval vs CascadeType.REMOVE
- First level cache (session) vs Second level cache (Ehcache/Redis)
- Dirty checking — how Hibernate detects changes
- **Prepare:** How you used JPA in Arealytics, any optimization you did

**Topic 10 — Spring Security (60 min)**
- Security filter chain — how a request flows through filters
- Authentication vs Authorization
- JWT flow — generate token → validate → extract claims
- How you implemented JWT in your project
- @PreAuthorize, @Secured, @RolesAllowed
- CORS configuration
- CSRF — what it is, when to disable (stateless APIs)
- BCryptPasswordEncoder
- SecurityContextHolder — how Spring stores authenticated user
- OAuth2 basics — Authorization Code flow, Client Credentials
- **Prepare:** How Keycloak integrates with Spring Security in your Arealytics project

**Topic 11 — REST API Best Practices (30 min)**
- HTTP methods — GET, POST, PUT, PATCH, DELETE semantics
- Status codes — 200, 201, 204, 400, 401, 403, 404, 409, 500
- API versioning — URI vs header vs param
- Pagination — offset vs cursor
- Error response structure — consistent format
- HATEOAS — what it is (don't need deep knowledge)
- Idempotency — which methods are idempotent
- Rate limiting basics
- OpenAPI 3.0 / Swagger — how you used it in AreaDocs

**Topic 12 — Transaction Management (30 min)**
- @Transactional — how it works (proxy-based AOP)
- Propagation types — REQUIRED, REQUIRES_NEW, NESTED, SUPPORTS
- Isolation levels — READ_UNCOMMITTED, READ_COMMITTED, REPEATABLE_READ, SERIALIZABLE
- Dirty read, Non-repeatable read, Phantom read
- Rollback — rollbackFor, noRollbackFor
- Why @Transactional doesn't work on private methods or same-class calls
- **Prepare:** Payment transaction example — *"if Blackhawk processes a gift card purchase, what isolation level would you use and why?"*

**Topic 13 — Spring Boot Extras (30 min)**
- @ControllerAdvice + @ExceptionHandler — global error handling
- Interceptors vs Filters — difference, use cases
- @Async — how it works, thread pool config
- Scheduling — @Scheduled, cron expressions
- Caching — @Cacheable, @CacheEvict with Redis
- Flyway — how you use it in Arealytics for SP deployment

---

### **Block 3: Quick Revision Before Sleep (30 min)**

```
✅ Read through all notes once
✅ For each topic, say the key points out loud
✅ Make sure you can connect every topic to your project
✅ Sleep well — retention needs rest
```

---

## **How to Study Each Topic**

```
For every topic:
1. Read / revise concept (10 min)
2. Ask yourself "how did I use this in Arealytics?" (5 min)
3. Explain it out loud as if interviewer asked (5 min)
4. Move on — don't get stuck on one topic
```

---

## **Priority if You Run Out of Time**

```
MUST DO (non-negotiable):
  ✅ Collections internals (HashMap)
  ✅ Spring Bean lifecycle
  ✅ JPA — N+1, lazy/eager, pagination
  ✅ Spring Security — JWT flow, filter chain
  ✅ @Transactional — propagation, isolation
  ✅ SOLID with project examples

SHOULD DO:
  ✅ Multithreading basics
  ✅ Java 8-17 features
  ✅ REST best practices
  ✅ Spring Boot internals

NICE TO HAVE:
  ✅ GC details
  ✅ Caching
  ✅ HATEOAS
```

---

Want me to create a **similar detailed plan for Saturday (System Design + DB + DSA)**?