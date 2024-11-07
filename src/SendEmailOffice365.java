import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmailOffice365 {

    private static final Logger LOGGER = Logger.getLogger(SendEmailOffice365.class.getName());

    private static final String SMTPServer = "smtp.office365.com";
    private static final int SMTPServerPort = 587;
    private static final String SMTPAccount = "firstShare@M365x43594918.onmicrosoft.com";
    private static final String SMTPPW = "xptmxm123!@#";

    private final String from = "firstShare@cake.run.place";
    private final String to = "leeyosebi@gmail.com";

    private final String subject = "Teste3";
    private final String messageContent = "Teste de Mensagem";

    public void sendEmail() {
        // Email Sesseion
        final Session session = Session.getInstance(this.getEmailProperties(), new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SMTPAccount, SMTPPW);
            }
        });

        try {
            // Email Content
            final Message message = new MimeMessage(session);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setFrom(new InternetAddress(from));
            message.setSubject(subject);
            message.setText(messageContent);
            message.setSentDate(new Date());

            // Send
            Transport.send(message);
            LOGGER.info("Email sent successfully to " + to);
        } catch (final MessagingException ex) {
            LOGGER.log(Level.WARNING, "Error sending email: " + ex.getMessage(), ex);
        }
    }

    // Email properties method
    public Properties getEmailProperties() {
        final Properties config = new Properties();
        config.put("mail.smtp.auth", "true");
        config.put("mail.smtp.starttls.enable", "true");
        config.put("mail.smtp.host", SMTPServer);
        config.put("mail.smtp.port", String.valueOf(SMTPServerPort));
        return config;
    }

    public static void main(final String[] args) {
        new SendEmailOffice365().sendEmail();
    }
}
