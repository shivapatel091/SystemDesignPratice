package org.example.services.repository;

import org.example.beans.Notification;

import java.util.Optional;

public interface NotificationRepository {

   void save(Notification notification);
   Optional<Notification> findById(String id);
   Optional<Notification> findByRequestId(String requestId);
   void update(Notification notification);
}
