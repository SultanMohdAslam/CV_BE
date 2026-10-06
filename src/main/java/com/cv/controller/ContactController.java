package com.cv.controller;

import com.cv.dto.ContactMessageRequest;
import com.cv.entity.ContactMessage;
import com.cv.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "*")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> submitMessage(@Valid @RequestBody ContactMessageRequest request) {
        ContactMessage saved = contactService.saveMessage(request);
        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Thank you! Your message has been sent successfully.",
                "id", saved.getId()
        ));
    }

    @GetMapping
    public ResponseEntity<List<ContactMessage>> getAllMessages() {
        return ResponseEntity.ok(contactService.getAllMessages());
    }

    @PatchMapping("/{id}/read")
    public ResponseEntity<Void> markAsRead(@PathVariable Long id) {
        contactService.markAsRead(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
        contactService.deleteMessage(id);
        return ResponseEntity.noContent().build();
    }
}
