package com.example.pokerplanninpi.services;

import com.example.pokerplanninpi.entity.Notification;

import java.util.List;

public interface NotificationService {



    List<Notification> getAllNotifications();

    Notification getNotificationById(Integer id);
    Notification createNotification(Notification notification);
    Notification updateNotification(Integer id, Notification notification);
        void deleteNotification(Integer id);


}
