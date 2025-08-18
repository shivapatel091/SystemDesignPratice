package org.example.services.repository;

import org.example.beans.Notification;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryNotificationRepository implements NotificationRepository {

    private final ConcurrentHashMap<String, Notification> store = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, String> requestIdToIdMap = new ConcurrentHashMap<>();

    @Override
    public void save(Notification notification) {
        store.put(notification.getId(), notification);
        requestIdToIdMap.put(notification.getRequestId(),notification.getId());
    }

    @Override
    public Optional<Notification> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Notification> findByRequestId(String requestId) {
        String key = requestIdToIdMap.get(requestId);
         return Optional.ofNullable(store.get(key));
    }

    @Override
    public void update(Notification notification) {
        store.put(notification.getId(), notification);
    }
}
