package com.example.pokerplanninpi.services;

import com.example.pokerplanninpi.entity.Projet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ScheduledTasks {

    @Autowired
    private ProjetServiceImp projetService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Scheduled(cron = "0 */1 * * * ?") // Runs every  1 minutes
    public void checkProjectDeadlines() {
        Date currentDate = new Date();
        List<Projet> projects = projetService.getAllProjets();
        for (Projet projet : projects) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentDate);
            calendar.set(Calendar.HOUR_OF_DAY,  0);
            calendar.set(Calendar.MINUTE,  0);
            calendar.set(Calendar.SECOND,  0);
            calendar.set(Calendar.MILLISECOND,  0);
            Date startOfDay = calendar.getTime();

            calendar.setTime(projet.getDateFin());
            calendar.set(Calendar.HOUR_OF_DAY,  0);
            calendar.set(Calendar.MINUTE,  0);
            calendar.set(Calendar.SECOND,  0);
            calendar.set(Calendar.MILLISECOND,  0);
            Date projectDeadlineStartOfDay = calendar.getTime();

            if (projectDeadlineStartOfDay.equals(startOfDay)) {
                String message = "The deadline for project '" + projet.getNom() + "' has been achieved.";
                messagingTemplate.convertAndSend("/topic/notification", message);
            }
        }
    }
}
