package top.aceofspades.travel.util;

import lombok.extern.apachecommons.CommonsLog;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author duanbt
 * @version 1.0
 **/
@CommonsLog
public class MailUtil {

    private MailUtil() {
    }

    private static final Properties props;

    static {
        InputStream in = MailUtil.class.getClassLoader().getResourceAsStream("mail.properties");
        props = new Properties();
        try {
            props.load(in);
        } catch (IOException e) {
            log.error("加载mail.properties文件失败", e);
            throw new RuntimeException("加载mail.properties文件失败", e);
        }
    }


    public static void sendMail(String to, String text, String title) {
        Session session;
        if ("true".equals(props.getProperty("mail.smtp.auth"))) {
            Authenticator authenticator = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    String username = props.getProperty("mail.user");
                    String password = props.getProperty("mail.password");
                    return new PasswordAuthentication(username, password);
                }
            };
            session = Session.getDefaultInstance(props, authenticator);
        } else {
            session = Session.getDefaultInstance(props);
        }

        Message message = new MimeMessage(session);
        String username = props.getProperty("mail.user");
        try {
            InternetAddress from = new InternetAddress(username);
            message.setFrom(from);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(title);
            message.setContent(text, "text/html;charset=UTF-8");
            Transport.send(message);
        } catch (MessagingException e) {
            log.error("邮件发送失败", e);
            throw new RuntimeException("邮件发送失败", e);
        }
    }
}
