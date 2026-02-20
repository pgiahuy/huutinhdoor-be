package com.pgh.huutinhdoor.service;

public interface EmailService {
    void sendEmail(String to, String... params);
}
