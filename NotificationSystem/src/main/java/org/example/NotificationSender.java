package org.example;


import org.example.beans.Notification;

public interface NotificationSender {

    void send(Notification notification) throws Exception;
}
