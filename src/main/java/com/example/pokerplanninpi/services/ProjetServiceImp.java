package com.example.pokerplanninpi.services;

import com.example.pokerplanninpi.entity.Notification;
import com.example.pokerplanninpi.entity.Projet;
import com.example.pokerplanninpi.repositories.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class ProjetServiceImp implements  ProjetService{


        @Autowired
        private SimpMessagingTemplate template;
        @Autowired
        ProjetRepository projetRepository;

        @Autowired
        NotificationServiceImp notificationService;


        @Override
        public List<Projet> getAllProjets() {
            return projetRepository.findAll();
        }

        @Override
        public Projet getProjetById(Integer id) {
            return projetRepository.findById(id).orElse(null);
        }

    @Override
    public Projet createProjet(Projet projet) {
        Projet savedProjet = projetRepository.save(projet);
        Notification notification = new Notification();
        String notificationContent = "A project named '" + savedProjet.getNom() + " is created successfully";
        notification.setContenu(notificationContent);
        notification.setDateEnvoi(new Date());
        notificationService.createNotification(notification);
        template.convertAndSend("/topic/notification", notificationContent);
        return savedProjet;
    }


    @Override
    public Projet updateProjet(Integer id, Projet projet) {
        Projet existingProjet = projetRepository.findById(id).orElse(null);
        if (existingProjet != null) {
            existingProjet.setNom(projet.getNom());
            existingProjet.setDescription(projet.getDescription());
            existingProjet.setDateDebut(projet.getDateDebut());
            existingProjet.setDateFin(projet.getDateFin());
            Projet updatedProjet = projetRepository.save(existingProjet);
            Notification notification = new Notification();
            String notificationContent = "A project named" + updatedProjet.getNom() + " is updated successfully";
            notification.setContenu(notificationContent);
            notification.setDateEnvoi(new Date());
            notificationService.createNotification(notification);
            template.convertAndSend("/topic/notification", notificationContent);
            return updatedProjet;
        }
        return null;
    }

    @Override
    public void deleteProjet(Integer id) {
        Projet existingProjet = projetRepository.findById(id).orElse(null);
        if (existingProjet != null) {
            Notification notification = new Notification();
            String notificationContent = "A project named '" + existingProjet.getNom() + " is deleted successfully";
            notification.setContenu(notificationContent);
            notification.setDateEnvoi(new Date());
            notificationService.createNotification(notification);
            projetRepository.deleteById(id);
            template.convertAndSend("/topic/notification", notificationContent);

        }
    }

}




