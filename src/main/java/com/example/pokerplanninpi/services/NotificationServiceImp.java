package com.example.pokerplanninpi.services;
import java.util.List;

import com.example.pokerplanninpi.entity.Notification;
import com.example.pokerplanninpi.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImp implements NotificationService{


        @Autowired
        NotificationRepository notificationRepository;

        @Override
        public List<Notification> getAllNotifications() {
            return notificationRepository.findAll();
        }

        @Override
        public Notification getNotificationById(Integer id) {
            return notificationRepository.findById(id).orElse(null);
        }

        @Override
        public Notification createNotification(Notification notification) {
            return notificationRepository.save(notification);
        }

        @Override
        public Notification updateNotification(Integer id, Notification notification) {
            Notification existingNotification = notificationRepository.findById(id).orElse(null);
            if (existingNotification != null) {
                existingNotification.setContenu(notification.getContenu());
                existingNotification.setDateEnvoi(notification.getDateEnvoi());

                return notificationRepository.save(existingNotification);
            }
            return null;
        }

        @Override
        public void deleteNotification(Integer id) {
            notificationRepository.deleteById(id);
        }


}
