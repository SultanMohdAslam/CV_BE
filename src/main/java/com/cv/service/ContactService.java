package com.cv.service;

import com.cv.dto.ContactMessageRequest;
import com.cv.entity.ContactMessage;
import com.cv.repository.ContactMessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ContactService {

    private final ContactMessageRepository contactMessageRepository;

    public ContactService(ContactMessageRepository contactMessageRepository) {
        this.contactMessageRepository = contactMessageRepository;
    }

    public ContactMessage saveMessage(ContactMessageRequest req) {
        ContactMessage msg = new ContactMessage(req.getName(), req.getEmail(), req.getSubject(), req.getMessage());
        return contactMessageRepository.save(msg);
    }

    @Transactional(readOnly = true)
    public List<ContactMessage> getAllMessages() {
        return contactMessageRepository.findAllByOrderByCreatedAtDesc();
    }

    public void markAsRead(Long id) {
        contactMessageRepository.findById(id).ifPresent(msg -> {
            msg.setIsRead(true);
            contactMessageRepository.save(msg);
        });
    }

    public void deleteMessage(Long id) {
        contactMessageRepository.deleteById(id);
    }
}
