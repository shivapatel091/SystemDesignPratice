package org.example;

import org.example.beans.Notification;

public class EmailSender implements NotificationSender {

    public void send(Notification notification) throws Exception {
        System.out.println("Sending email notification...");
    }
}
