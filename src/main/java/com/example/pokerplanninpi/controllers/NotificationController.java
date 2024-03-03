package com.example.pokerplanninpi.controllers;

import com.example.pokerplanninpi.entity.Notification;
import com.example.pokerplanninpi.services.NotificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value="/api/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationServices;
    @GetMapping
    public List<Notification> getAllNotifications() {
        return notificationServices.getAllNotifications();
    }

    @GetMapping("/{id}")
    public Notification getNotificationById(@PathVariable Integer id) {
        return notificationServices.getNotificationById(id);
    }

    @PostMapping
    public Notification createNotification(@RequestBody Notification notification) {
        return notificationServices.createNotification(notification);
    }

    @PutMapping("/{id}")
    public Notification updateNotification(@PathVariable Integer id, @RequestBody Notification notification) {
        return notificationServices.updateNotification(id, notification);
    }

    @DeleteMapping("/{id}")
    public void deleteNotification(@PathVariable Integer id) {
        notificationServices.deleteNotification(id);
    }

}
