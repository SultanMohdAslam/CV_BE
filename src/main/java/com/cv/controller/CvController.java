package com.cv.controller;

import com.cv.dto.CvDataDto;
import com.cv.entity.*;
import com.cv.service.CvService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cv")
@CrossOrigin(origins = "*")
public class CvController {

    private final CvService cvService;

    public CvController(CvService cvService) {
        this.cvService = cvService;
    }

    // ── Full Composite CV ──────────────────────────────────────────────────────

    @GetMapping
    public ResponseEntity<CvDataDto> getFullCv() {
        return ResponseEntity.ok(cvService.getFullCvData());
    }

    @PostMapping("/seed")
    public ResponseEntity<Map<String, String>> seedData(@RequestParam(defaultValue = "false") boolean overwrite) {
        cvService.seedInitialData(overwrite);
        return ResponseEntity.ok(Map.of("message", "Database successfully seeded"));
    }

    // ── Personal Info ──────────────────────────────────────────────────────────

    @GetMapping("/personal")
    public ResponseEntity<PersonalInfo> getPersonalInfo() {
        return ResponseEntity.ok(cvService.getPersonalInfo());
    }

    @PutMapping("/personal")
    public ResponseEntity<PersonalInfo> updatePersonalInfo(@RequestBody PersonalInfo personalInfo) {
        return ResponseEntity.ok(cvService.updatePersonalInfo(personalInfo));
    }

    // ── Stats ──────────────────────────────────────────────────────────────────

    @GetMapping("/stats")
    public ResponseEntity<List<CvStat>> getStats() {
        return ResponseEntity.ok(cvService.getAllStats());
    }

    @PostMapping("/stats")
    public ResponseEntity<CvStat> createStat(@RequestBody CvStat stat) {
        return ResponseEntity.ok(cvService.saveStat(stat));
    }

    @PutMapping("/stats/{id}")
    public ResponseEntity<CvStat> updateStat(@PathVariable Long id, @RequestBody CvStat stat) {
        return ResponseEntity.ok(cvService.updateStat(id, stat));
    }

    @DeleteMapping("/stats/{id}")
    public ResponseEntity<Void> deleteStat(@PathVariable Long id) {
        cvService.deleteStat(id);
        return ResponseEntity.noContent().build();
    }

    // ── Experiences ────────────────────────────────────────────────────────────

    @GetMapping("/experiences")
    public ResponseEntity<List<Experience>> getExperiences() {
        return ResponseEntity.ok(cvService.getAllExperiences());
    }

    @PostMapping("/experiences")
    public ResponseEntity<Experience> createExperience(@RequestBody Experience exp) {
        return ResponseEntity.ok(cvService.saveExperience(exp));
    }

    @PutMapping("/experiences/{id}")
    public ResponseEntity<Experience> updateExperience(@PathVariable Long id, @RequestBody Experience exp) {
        return ResponseEntity.ok(cvService.updateExperience(id, exp));
    }

    @DeleteMapping("/experiences/{id}")
    public ResponseEntity<Void> deleteExperience(@PathVariable Long id) {
        cvService.deleteExperience(id);
        return ResponseEntity.noContent().build();
    }

    // ── Skills ─────────────────────────────────────────────────────────────────

    @GetMapping("/skills")
    public ResponseEntity<List<SkillCategory>> getSkillCategories() {
        return ResponseEntity.ok(cvService.getAllSkillCategories());
    }

    @PostMapping("/skills/categories")
    public ResponseEntity<SkillCategory> createSkillCategory(@RequestBody SkillCategory category) {
        return ResponseEntity.ok(cvService.saveSkillCategory(category));
    }

    @PutMapping("/skills/categories/{id}")
    public ResponseEntity<SkillCategory> updateSkillCategory(@PathVariable Long id, @RequestBody SkillCategory category) {
        return ResponseEntity.ok(cvService.updateSkillCategory(id, category));
    }

    @DeleteMapping("/skills/categories/{id}")
    public ResponseEntity<Void> deleteSkillCategory(@PathVariable Long id) {
        cvService.deleteSkillCategory(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/skills/items/{id}")
    public ResponseEntity<Void> deleteSkillItem(@PathVariable Long id) {
        cvService.deleteSkillItem(id);
        return ResponseEntity.noContent().build();
    }

    // ── System Showcases ───────────────────────────────────────────────────────

    @GetMapping("/showcases")
    public ResponseEntity<List<SystemShowcase>> getShowcases() {
        return ResponseEntity.ok(cvService.getAllShowcases());
    }

    @PostMapping("/showcases")
    public ResponseEntity<SystemShowcase> createShowcase(@RequestBody SystemShowcase showcase) {
        return ResponseEntity.ok(cvService.saveShowcase(showcase));
    }

    @PutMapping("/showcases/{id}")
    public ResponseEntity<SystemShowcase> updateShowcase(@PathVariable Long id, @RequestBody SystemShowcase showcase) {
        return ResponseEntity.ok(cvService.updateShowcase(id, showcase));
    }

    @DeleteMapping("/showcases/{id}")
    public ResponseEntity<Void> deleteShowcase(@PathVariable Long id) {
        cvService.deleteShowcase(id);
        return ResponseEntity.noContent().build();
    }

    // ── Education ──────────────────────────────────────────────────────────────

    @GetMapping("/education")
    public ResponseEntity<List<Education>> getEducation() {
        return ResponseEntity.ok(cvService.getAllEducation());
    }

    @PostMapping("/education")
    public ResponseEntity<Education> createEducation(@RequestBody Education edu) {
        return ResponseEntity.ok(cvService.saveEducation(edu));
    }

    @PutMapping("/education/{id}")
    public ResponseEntity<Education> updateEducation(@PathVariable Long id, @RequestBody Education edu) {
        return ResponseEntity.ok(cvService.updateEducation(id, edu));
    }

    @DeleteMapping("/education/{id}")
    public ResponseEntity<Void> deleteEducation(@PathVariable Long id) {
        cvService.deleteEducation(id);
        return ResponseEntity.noContent().build();
    }
}
