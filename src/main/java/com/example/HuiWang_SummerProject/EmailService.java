package com.example.HuiWang_SummerProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

@Service
public class EmailService {

    @Autowired
    public JavaMailSender emailSender;
    private SimpleMailMessage simpleMailMessage;

    public void sendSimpleMessage(
            String to, String subject, String text, String walletPath) throws MessagingException {

        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);

        mimeMessageHelper.setTo(to);

        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(text, true);
        FileSystemResource file = new FileSystemResource(walletPath);
        mimeMessageHelper.addAttachment(file.getFilename(), file);
        emailSender.send(mimeMessage);

    }


}






