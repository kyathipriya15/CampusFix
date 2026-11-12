# Interview preparation - CampusFix and HeartGuard-AI

## CampusFix - 90 second explanation
"CampusFix is an educational Java full-stack campus maintenance portal. Users enter a complaint, location and category. Spring MVC receives the request, a service applies business rules to classify priority and SLA time, Spring Data JPA stores it, and Thymeleaf renders the ticket dashboard. An operator advances tickets through states. The key logic is a duplicate guard, deadline calculation and validated state transitions. The demo uses H2; there is also an optional MySQL configuration. I am working on improving tests and adding authentication before public deployment." **Practice and adapt this to the student's actual contributions; never imply it is completed unless verified.**

## Java interview questions
1. **What is a full-stack Java app?** Frontend UI + Java backend + database, connected through HTTP and data-access components.
2. **MVC vs REST?** MVC returns rendered pages; REST exposes data/resources as JSON and HTTP status codes. This demo supports both.
3. **What is Spring Boot?** Opinionated framework for bootstrapping Spring apps, auto-configuration and embedded server.
4. **What does `@Service` do?** Registers business-logic component; transactions can be applied around service methods.
5. **What is JPA?** Java Persistence API abstraction for mapping Java objects to relational tables; Hibernate is a common implementation.
6. **Why separate Controller, Service and Repository?** To isolate request handling, business rules and persistence; easier testing.
7. **How is priority calculated?** Critical for safety keywords; otherwise based on category; SLA uses a 4/24/48/72-hour rule.
8. **How are duplicates handled?** Same active category/building/room in past 48h is rejected. Caveat: simultaneous requests need database-level safety.
9. **What is a DB transaction?** A unit of work committed or rolled back together; `@Transactional` is used for writes.
10. **What is an edge case?** Empty descriptions, resolved tickets, repeated reports, dates, and concurrent tickets.
11. **What are the limits?** No auth/RBAC, SLA emails or robust concurrency yet. It is a learning prototype, not production-ready.
12. **How would you improve it?** Add Spring Security, role restrictions, optimistic locking, pagination, integration tests and deployment automation.

## HeartGuard-AI - first verify actual completed work
The college brief names fairness-aware transformer **FairHeart-Former**, uncertainty-aware explainer **TrustHeart-XAI**, multimodal fusion network **MedFusion-HeartNet**, ensemble prediction, SHAP explainability, and Flask/MySQL interface. It describes aims and architecture; **it does not supply reproducible training logs, dataset details, performance metrics or an implementation repository**. Prepare to say which components the student personally implemented, which were team contributions and which are only proposed.

1. Why predict **risk** instead of diagnosing heart disease? A risk model supports screening and triage; it does not replace clinical evaluation.
2. What is **multimodal** data? Combining modalities like tabular clinical values, imaging or signals; list only sources truly used.
3. Why **SHAP**? To estimate feature contributions for a prediction; it does not prove causation.
4. What does **fairness** mean? Compare error metrics across defined subgroups, report disparate performance and mitigation tradeoffs.
5. What is **uncertainty**? Confidence/calibration, estimated through an explicit method; don't claim if not measured.
6. How would you **evaluate**? Hold-out testing; metrics including recall, specificity, precision, F1, ROC-AUC; calibration and subgroup comparisons.
7. Why avoid **data leakage**? It inflates reported performance when train/test share patients or correlated records.
8. Why **Flask and MySQL**? Flask serves web requests; MySQL stores user/account or non-sensitive application records; clarify exact role.
9. How are **low/moderate/high** defined? Must specify labels/thresholds and clinical grounding; do not invent them.
10. What's unverified in the brief? Dataset, ethics/consent, training results, code, calibration, demographics, clinical validation and security review.

## Proof checklist before final resume
- [ ] She personally runs and explains CampusFix, fixes Maven/build errors, captures real screenshots.
- [ ] Adds own code and tests; pushes a public repository under her own GitHub profile.
- [ ] She can diagram UI → Controller → Service → Repository → DB.
- [ ] She identifies dataset, input modalities, actual implemented models, training method, evaluation metrics and GitHub artifacts for HeartGuard-AI.
- [ ] All certifications and CGPA are verified.
- [ ] Resume project verbs upgraded from "designing / developing" to "built / implemented" **only for completed, evidenced work**.
