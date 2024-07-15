package org.petspa.petcaresystem.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfig {

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("petspa392@gmail.com");
        mailSender.setPassword("xobc lofw asdm ghok");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    @Bean
    public SimpleMailMessage sendCofirmCode() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText(
                "This is your the verification code:\n%s\n");
        return message;
    }

//    @Bean
//    public SimpleMailMessage sendAppointmentInformationForGuess() {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setText(
//                "Appointment ID:\n%s\n"
//                + "Guess email:\n%s\n"
//                + "Guess phone's number:\n%s\n");
//        return message;
//    }



}
