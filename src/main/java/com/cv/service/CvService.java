package com.cv.service;

import com.cv.dto.CvDataDto;
import com.cv.entity.*;
import com.cv.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CvService {

    private final PersonalInfoRepository personalInfoRepository;
    private final CvStatRepository cvStatRepository;
    private final ExperienceRepository experienceRepository;
    private final SkillCategoryRepository skillCategoryRepository;
    private final SkillItemRepository skillItemRepository;
    private final SystemShowcaseRepository systemShowcaseRepository;
    private final EducationRepository educationRepository;

    public CvService(PersonalInfoRepository personalInfoRepository,
                     CvStatRepository cvStatRepository,
                     ExperienceRepository experienceRepository,
                     SkillCategoryRepository skillCategoryRepository,
                     SkillItemRepository skillItemRepository,
                     SystemShowcaseRepository systemShowcaseRepository,
                     EducationRepository educationRepository) {
        this.personalInfoRepository = personalInfoRepository;
        this.cvStatRepository = cvStatRepository;
        this.experienceRepository = experienceRepository;
        this.skillCategoryRepository = skillCategoryRepository;
        this.skillItemRepository = skillItemRepository;
        this.systemShowcaseRepository = systemShowcaseRepository;
        this.educationRepository = educationRepository;
    }

    @Transactional(readOnly = true)
    public CvDataDto getFullCvData() {
        PersonalInfo personal = personalInfoRepository.findAll().stream().findFirst().orElseGet(this::buildDefaultPersonalInfo);
        List<CvStat> stats = cvStatRepository.findAllByOrderBySortOrderAsc();
        List<Experience> experiences = experienceRepository.findAllByOrderBySortOrderAsc();
        List<SkillCategory> skillCategories = skillCategoryRepository.findAllByOrderBySortOrderAsc();
        List<SystemShowcase> showcases = systemShowcaseRepository.findAllByOrderBySortOrderAsc();
        List<Education> education = educationRepository.findAllByOrderBySortOrderAsc();

        return new CvDataDto(personal, stats, experiences, skillCategories, showcases, education);
    }

    // ── Personal Info ──────────────────────────────────────────────────────────

    @Transactional(readOnly = true)
    public PersonalInfo getPersonalInfo() {
        return personalInfoRepository.findAll().stream().findFirst().orElseGet(this::buildDefaultPersonalInfo);
    }

    public PersonalInfo updatePersonalInfo(PersonalInfo updated) {
        PersonalInfo existing = personalInfoRepository.findAll().stream().findFirst().orElseGet(PersonalInfo::new);
        existing.setName(updated.getName());
        existing.setTitle(updated.getTitle());
        existing.setSubtitle(updated.getSubtitle());
        existing.setExperienceYears(updated.getExperienceYears());
        existing.setEmail(updated.getEmail());
        existing.setPhone(updated.getPhone());
        existing.setLocation(updated.getLocation());
        existing.setLinkedin(updated.getLinkedin());
        existing.setLinkedinDisplay(updated.getLinkedinDisplay());
        existing.setGithub(updated.getGithub());
        existing.setAvatar(updated.getAvatar());
        existing.setBio(updated.getBio());
        existing.setAvailability(updated.getAvailability());
        return personalInfoRepository.save(existing);
    }

    public PersonalInfo buildDefaultPersonalInfo() {
        PersonalInfo p = new PersonalInfo();
        p.setName("Sultan Md Aslam");
        p.setTitle("Software Engineer II");
        p.setSubtitle("Backend & Distributed Systems Specialist");
        p.setExperienceYears("5+ Years");
        p.setEmail("smaslam199320@gmail.com");
        p.setPhone("+8801823140141");
        p.setLocation("154/8, Jheelkanoon residential Area, Hatirjheel, Dhaka");
        p.setLinkedin("https://linkedin.com/in/sultan-md-aslam-748835174");
        p.setLinkedinDisplay("linkedin.com/in/sultan-md-aslam-748835174");
        p.setAvatar("/profile.png");
        p.setBio("Senior Backend Engineer with 5+ years of experience designing and scaling distributed systems, real-time trip orchestration, event-driven architectures, and high-throughput microservices using Java, Spring Boot, Kafka, ScyllaDB, RabbitMQ, and Kubernetes.");
        p.setAvailability("Available for Senior / Lead Backend Engineering roles");
        return p;
    }

    // ── Stats ──────────────────────────────────────────────────────────────────

    @Transactional(readOnly = true)
    public List<CvStat> getAllStats() {
        return cvStatRepository.findAllByOrderBySortOrderAsc();
    }

    public CvStat saveStat(CvStat stat) {
        return cvStatRepository.save(stat);
    }

    public CvStat updateStat(Long id, CvStat stat) {
        CvStat existing = cvStatRepository.findById(id).orElseThrow(() -> new RuntimeException("Stat not found: " + id));
        existing.setLabel(stat.getLabel());
        existing.setValue(stat.getValue());
        existing.setDetail(stat.getDetail());
        existing.setSortOrder(stat.getSortOrder());
        return cvStatRepository.save(existing);
    }

    public void deleteStat(Long id) {
        cvStatRepository.deleteById(id);
    }

    // ── Experiences ────────────────────────────────────────────────────

    @Transactional(readOnly = true)
    public List<Experience> getAllExperiences() {
        return experienceRepository.findAllByOrderBySortOrderAsc();
    }

    public Experience saveExperience(Experience exp) {
        if (exp.getHighlights() != null) {
            for (ExperienceHighlight h : exp.getHighlights()) {
                h.setExperience(exp);
            }
        }
        return experienceRepository.save(exp);
    }

    public Experience updateExperience(Long id, Experience updated) {
        Experience existing = experienceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Experience not found: " + id));
        existing.setExpSlug(updated.getExpSlug());
        existing.setRole(updated.getRole());
        existing.setCompany(updated.getCompany());
        existing.setPeriod(updated.getPeriod());
        existing.setIsCurrent(updated.getIsCurrent());
        existing.setDepartment(updated.getDepartment());
        existing.setSummary(updated.getSummary());
        existing.setSortOrder(updated.getSortOrder());
        existing.setTechStack(updated.getTechStack());

        existing.getHighlights().clear();
        if (updated.getHighlights() != null) {
            for (ExperienceHighlight h : updated.getHighlights()) {
                existing.addHighlight(new ExperienceHighlight(h.getTitle(), h.getDesc(), h.getSortOrder()));
            }
        }

        return experienceRepository.save(existing);
    }

    public void deleteExperience(Long id) {
        experienceRepository.deleteById(id);
    }

    // ── Skills ─────────────────────────────────────────────────────────────────

    @Transactional(readOnly = true)
    public List<SkillCategory> getAllSkillCategories() {
        return skillCategoryRepository.findAllByOrderBySortOrderAsc();
    }

    public SkillCategory saveSkillCategory(SkillCategory category) {
        if (category.getSkills() != null) {
            for (SkillItem s : category.getSkills()) {
                s.setCategory(category);
            }
        }
        return skillCategoryRepository.save(category);
    }

    public SkillCategory updateSkillCategory(Long id, SkillCategory updated) {
        SkillCategory existing = skillCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SkillCategory not found: " + id));
        existing.setCategory(updated.getCategory());
        existing.setColor(updated.getColor());
        existing.setSortOrder(updated.getSortOrder());

        existing.getSkills().clear();
        if (updated.getSkills() != null) {
            for (SkillItem s : updated.getSkills()) {
                existing.addSkill(new SkillItem(s.getName(), s.getLevel(), s.getHighlight(), s.getSortOrder()));
            }
        }

        return skillCategoryRepository.save(existing);
    }

    public void deleteSkillCategory(Long id) {
        skillCategoryRepository.deleteById(id);
    }

    public void deleteSkillItem(Long id) {
        skillItemRepository.deleteById(id);
    }

    // ── System Showcases ───────────────────────────────────────────────────────

    @Transactional(readOnly = true)
    public List<SystemShowcase> getAllShowcases() {
        return systemShowcaseRepository.findAllByOrderBySortOrderAsc();
    }

    public SystemShowcase saveShowcase(SystemShowcase sc) {
        return systemShowcaseRepository.save(sc);
    }

    public SystemShowcase updateShowcase(Long id, SystemShowcase updated) {
        SystemShowcase existing = systemShowcaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Showcase not found: " + id));
        existing.setTitle(updated.getTitle());
        existing.setCompany(updated.getCompany());
        existing.setDescription(updated.getDescription());
        existing.setTags(updated.getTags());
        existing.setArchitecture(updated.getArchitecture());
        existing.setSortOrder(updated.getSortOrder());
        return systemShowcaseRepository.save(existing);
    }

    public void deleteShowcase(Long id) {
        systemShowcaseRepository.deleteById(id);
    }

    // ── Education ──────────────────────────────────────────────────────────────

    @Transactional(readOnly = true)
    public List<Education> getAllEducation() {
        return educationRepository.findAllByOrderBySortOrderAsc();
    }

    public Education saveEducation(Education edu) {
        return educationRepository.save(edu);
    }

    public Education updateEducation(Long id, Education updated) {
        Education existing = educationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Education not found: " + id));
        existing.setDegree(updated.getDegree());
        existing.setInstitution(updated.getInstitution());
        existing.setPeriod(updated.getPeriod());
        existing.setCgpa(updated.getCgpa());
        existing.setDescription(updated.getDescription());
        existing.setSortOrder(updated.getSortOrder());
        return educationRepository.save(existing);
    }

    public void deleteEducation(Long id) {
        educationRepository.deleteById(id);
    }

    // ── Data Seeder ────────────────────────────────────────────────────────────

    public void seedInitialData(boolean overwrite) {
        if (!overwrite && personalInfoRepository.count() > 0) {
            return; // Already populated
        }

        if (overwrite) {
            experienceRepository.deleteAll();
            skillCategoryRepository.deleteAll();
            systemShowcaseRepository.deleteAll();
            educationRepository.deleteAll();
            cvStatRepository.deleteAll();
            personalInfoRepository.deleteAll();
        }

        // 1. Personal Info
        personalInfoRepository.save(buildDefaultPersonalInfo());

        // 2. Stats
        cvStatRepository.save(new CvStat("Experience", "5+ Years", "High-scale engineering", 0));
        cvStatRepository.save(new CvStat("Companies", "4 Firms", "FinTech & Rideshare", 1));
        cvStatRepository.save(new CvStat("Real-Time Events", "Sub-Second", "WebSocket & Kafka", 2));
        cvStatRepository.save(new CvStat("Core Stack", "Java / Spring", "Distributed microservices", 3));

        // 3. Experiences
        Experience foodi = new Experience();
        foodi.setExpSlug("foodi");
        foodi.setRole("Software Engineer II");
        foodi.setCompany("Foodi");
        foodi.setPeriod("May 2025 – Present");
        foodi.setIsCurrent(true);
        foodi.setDepartment("Ridesharing Core Engine");
        foodi.setSummary("Architecting and scaling trip orchestration, real-time driver-passenger matching, event-driven dispatching, and high-frequency WebSocket communication.");
        foodi.setSortOrder(0);
        foodi.setTechStack(List.of("Java", "Spring Boot", "PostgreSQL", "ScyllaDB", "Kafka", "RabbitMQ", "WebSocket", "Gradle", "Docker", "Kubernetes (K8s)", "Redis"));
        foodi.addHighlight(new ExperienceHighlight("Trip Orchestration & Lifecycle Tracking", "Designed and built full ride lifecycle state machines (matching, driver assignment, pickup, route progress, stoppages, completion, and cash collection) with WebSocket-based sub-second updates.", 0));
        foodi.addHighlight(new ExperienceHighlight("Strategy Pattern & Event-Driven Trip Dispatch", "Implemented flexible trip request dispatching leveraging the Strategy Pattern and Event-Driven Architecture, enabling extensible dynamic dispatch rules and decoupled inter-service messaging.", 1));
        foodi.addHighlight(new ExperienceHighlight("Trip Search & Proximity Filtering", "Engineered high-performance trip search algorithms factoring geospatial coordinates, driver availability, and business filtering rules for sub-second driver matching.", 2));
        foodi.addHighlight(new ExperienceHighlight("Intelligent Driver Scoring & Ranking System", "Developed and tuned real-time driver ranking combining multidimensional factors: customer ratings, trip acceptance/completion rates, and proximity.", 3));
        foodi.addHighlight(new ExperienceHighlight("Advanced Route Updates & Scheduling", "Engineered cancellation handling workflows, dynamic ETA and route calculations, and integrated advance scheduled bookings for enhanced commuter flexibility.", 4));
        foodi.addHighlight(new ExperienceHighlight("WebSocket Gateway & Multi-Dispatch Bidding", "Integrated scalable WebSocket Gateway supporting concurrent multi-driver dispatch (bidding), live telemetry tracking, and seamless cross-platform sync.", 5));
        foodi.addHighlight(new ExperienceHighlight("RabbitMQ Relay & ScyllaDB/Redis Optimization", "Implemented database metadata tracking and configured RabbitMQ as an external relay broker for persistent, durable message delivery. Cut database latency through Redis caching and ScyllaDB/PostgreSQL query tuning.", 6));
        saveExperience(foodi);

        Experience adn = new Experience();
        adn.setExpSlug("adn-diginet");
        adn.setRole("Software Engineer");
        adn.setCompany("ADN Diginet Ltd");
        adn.setPeriod("Feb 2023 – Apr 2025");
        adn.setIsCurrent(false);
        adn.setDepartment("My Life (MetLife Insurance App)");
        adn.setSummary("Engineered mission-critical financial and insurance services, Kafka stream idempotency, complex data migrations, and load-tested REST APIs.");
        adn.setSortOrder(1);
        adn.setTechStack(List.of("Java", "Spring Boot", "PostgreSQL", "MSSQL", "Kafka", "Docker", "Kubernetes (K8s)", "Maven", "JavaScript", "K6", "Git"));
        adn.addHighlight(new ExperienceHighlight("Kafka Idempotency & Transaction Integrity", "Designed and enforced Kafka consumer idempotency across core transactional services, ensuring zero duplicate processing and guaranteed consistency across distributed financial workflows.", 0));
        adn.addHighlight(new ExperienceHighlight("Enterprise Data Migration", "Led end-to-end data migration pipelines, securely shifting millions of records from legacy databases to modernized schemas with zero downtime.", 1));
        adn.addHighlight(new ExperienceHighlight("System Architecture & High-Performance APIs", "Spearheaded architectural documentation, API design, and stress testing using K6, ensuring robust SLA compliance under heavy peak traffic.", 2));
        saveExperience(adn);

        Experience ctrends = new Experience();
        ctrends.setExpSlug("ctrends");
        ctrends.setRole("Software Engineer");
        ctrends.setCompany("Ctrends Software & Services Ltd");
        ctrends.setPeriod("Aug 2022 – Feb 2023");
        ctrends.setIsCurrent(false);
        ctrends.setDepartment("Enterprise Web Applications");
        ctrends.setSummary("Developed robust enterprise applications with Java Spring Boot, collaborating with cross-functional stakeholders on feature lifecycles.");
        ctrends.setSortOrder(2);
        ctrends.setTechStack(List.of("Java", "Spring Boot", "PostgreSQL", "Kafka", "Maven", "Docker", "Kubernetes", "K6", "Git"));
        ctrends.addHighlight(new ExperienceHighlight("Enterprise Application Engineering", "Designed, developed, and tested enterprise-grade microservices and web apps using Java Spring Boot and PostgreSQL.", 0));
        ctrends.addHighlight(new ExperienceHighlight("Cross-Functional Collaboration & QA", "Partnered closely with QA, product, and frontend engineers to deploy scalable modules, utilizing K6 for performance benchmarks.", 1));
        saveExperience(ctrends);

        Experience asian = new Experience();
        asian.setExpSlug("asian-tech");
        asian.setRole("Programmer");
        asian.setCompany("Asian Technology Limited");
        asian.setPeriod("Feb 2021 – Jul 2022");
        asian.setIsCurrent(false);
        asian.setDepartment("Software Engineering");
        asian.setSummary("Built scalable backend services, database schema designs, and messaging pipelines for client enterprise solutions.");
        asian.setSortOrder(3);
        asian.setTechStack(List.of("Java", "Spring Boot", "MSSQL", "Kafka", "Maven", "Git"));
        asian.addHighlight(new ExperienceHighlight("Spring Boot Microservices", "Built scalable backend modules and RESTful endpoints using Java and Spring Boot, persisting data across MSSQL instances.", 0));
        asian.addHighlight(new ExperienceHighlight("Kafka Event Pipelines", "Integrated asynchronous event streaming with Apache Kafka for background data sync and notification queues.", 1));
        saveExperience(asian);

        // 4. Skills
        SkillCategory backend = new SkillCategory("Backend & Core", "emerald", 0);
        backend.addSkill(new SkillItem("Java", 95, true, 0));
        backend.addSkill(new SkillItem("Spring Boot", 95, true, 1));
        backend.addSkill(new SkillItem("Microservices Architecture", 90, true, 2));
        backend.addSkill(new SkillItem("SOLID Principles", 95, false, 3));
        backend.addSkill(new SkillItem("Design Patterns (Strategy, Factory, etc.)", 92, true, 4));
        backend.addSkill(new SkillItem("RESTful API Design", 94, false, 5));
        backend.addSkill(new SkillItem("WebSocket & Realtime", 90, true, 6));
        saveSkillCategory(backend);

        SkillCategory messaging = new SkillCategory("Messaging & Streaming", "indigo", 1);
        messaging.addSkill(new SkillItem("Apache Kafka", 92, true, 0));
        messaging.addSkill(new SkillItem("Kafka Idempotency", 90, true, 1));
        messaging.addSkill(new SkillItem("RabbitMQ", 88, true, 2));
        messaging.addSkill(new SkillItem("Event-Driven Architecture", 92, true, 3));
        messaging.addSkill(new SkillItem("Message Durability & Relays", 86, false, 4));
        saveSkillCategory(messaging);

        SkillCategory db = new SkillCategory("Databases & In-Memory", "cyan", 2);
        db.addSkill(new SkillItem("PostgreSQL", 92, true, 0));
        db.addSkill(new SkillItem("ScyllaDB (NoSQL / Cassandra)", 85, true, 1));
        db.addSkill(new SkillItem("Redis Caching", 90, true, 2));
        db.addSkill(new SkillItem("MSSQL", 85, false, 3));
        db.addSkill(new SkillItem("MySQL", 84, false, 4));
        saveSkillCategory(db);

        SkillCategory devops = new SkillCategory("DevOps, Cloud & Testing", "amber", 3);
        devops.addSkill(new SkillItem("Docker", 90, true, 0));
        devops.addSkill(new SkillItem("Kubernetes (K8s)", 85, true, 1));
        devops.addSkill(new SkillItem("Gradle & Maven", 92, false, 2));
        devops.addSkill(new SkillItem("Git Version Control", 95, false, 3));
        devops.addSkill(new SkillItem("K6 Load Testing", 88, true, 4));
        devops.addSkill(new SkillItem("Linux Systems", 88, false, 5));
        saveSkillCategory(devops);

        // 5. System Showcases
        SystemShowcase sc1 = new SystemShowcase();
        sc1.setTitle("Rideshare Real-Time Trip Orchestration");
        sc1.setCompany("Foodi");
        sc1.setDescription("A high-concurrency trip dispatch engine managing the full driver-passenger lifecycle with multi-dispatch bidding, driver scoring, and live GPS tracking.");
        sc1.setSortOrder(0);
        sc1.setTags(List.of("WebSocket Gateway", "ScyllaDB", "Strategy Pattern", "RabbitMQ Relay"));
        sc1.setArchitecture(List.of(
                "Event-Driven Trip State Machine with WebSocket broadcast",
                "Geospatial driver ranking algorithm combining rating, acceptance & proximity",
                "RabbitMQ relay for durable background message persistence",
                "Sub-second Redis cache layer reducing primary database load"
        ));
        systemShowcaseRepository.save(sc1);

        SystemShowcase sc2 = new SystemShowcase();
        sc2.setTitle("Idempotent Event Stream for Core Insurance");
        sc2.setCompany("ADN Diginet / MetLife");
        sc2.setDescription("Distributed transaction guarantee ensuring zero duplicate transactions across multi-service policy management and claims lifecycles.");
        sc2.setSortOrder(1);
        sc2.setTags(List.of("Apache Kafka", "Spring Boot", "Transaction Outbox", "MSSQL/Postgres"));
        sc2.setArchitecture(List.of(
                "Deduplication barrier with unique transaction event keys",
                "Failover resilience & guaranteed at-least-once with idempotent consumer execution",
                "Zero-downtime legacy data migration with automated validation",
                "Performance validation with K6 peak load scripts"
        ));
        systemShowcaseRepository.save(sc2);

        // 6. Education
        educationRepository.save(new Education(
                "B.Sc. in Computer Science and Engineering",
                "Premier University Chittagong",
                "Graduated 2019",
                "3.02 / 4.00",
                "Focused on Algorithms, Data Structures, Distributed Computing, Database Systems, and Object-Oriented Software Engineering.",
                0
        ));
    }
}
