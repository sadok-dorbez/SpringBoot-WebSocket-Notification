package com.example.pokerplanninpi.repositories;

import com.example.pokerplanninpi.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,Integer> {
}
