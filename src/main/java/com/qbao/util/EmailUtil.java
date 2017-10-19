package com.qbao.util;

import com.qbao.log.Constant;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author song.j
 * @create 2017-10-16 14:14:31
 **/
public class EmailUtil {


    private static EmailUtil emailUtil = null;

    private static JavaMailSenderImpl mailSender = null;

    public static EmailUtil getInstance() {
        if (emailUtil == null) {
            synchronized (EmailUtil.class) {
                JavaMailSenderImpl newMailSender = new JavaMailSenderImpl();
                newMailSender.setHost(Constant.emailSmtpHost);
                Properties properties = new Properties();
                properties.put("mail.smtp.auth", true);
                properties.put("mail.smtp.timeout", 25000);
                newMailSender.setJavaMailProperties(properties);

                newMailSender.setUsername(Constant.emailUser);
                newMailSender.setPassword(Constant.emailPass);
                emailUtil = new EmailUtil(newMailSender);
            }
        }

        return emailUtil;
    }


    private EmailUtil(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }


    public void sendEmail(String message) {
        send(message, Constant.sendTo.split(","), Constant.emailUser, Constant.subject);
    }


    public void send(String massage, String[] sendTo, String sendFrom, String subject) {
        MimeMessage mailMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true, "utf-8");
            messageHelper.setTo(sendTo);
            messageHelper.setFrom(sendFrom, "noreply");
            messageHelper.setSubject(subject);
            messageHelper.setText(massage, false);
            mailSender.send(mailMessage);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("send email error ");
        }
    }

}
