package mail;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import java.util.Properties;

public class SendMail {

    public static void main(String[] args) {

        final String username = "arun810balaji1@gmail.com";
        final String password = "Adgjmptw1!";

        Properties prop = new Properties();
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

        	MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("arun810balaji1@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("arun810balaji1@gmail.com")
            );
            message.setSubject("Hypermarket Invoice");
            
            BodyPart messageBodyPart1 = new MimeBodyPart();
            
            messageBodyPart1.setText("Dear Customer,"
                    + "\n\n Thanks for Shopping with us!"
                    + "\n\n Have a Nice day");
            
            MimeBodyPart messageBodyPart2 = new MimeBodyPart();
            String filename = "D:\\temp\\FirstPdf.pdf";
            DataSource source = new FileDataSource(filename);  
            messageBodyPart2.setDataHandler(new DataHandler(source));  
            messageBodyPart2.setFileName(filename);  
             
             
            //5) create Multipart object and add MimeBodyPart objects to this object      
            Multipart multipart = new MimeMultipart();  
            multipart.addBodyPart(messageBodyPart1);  
            multipart.addBodyPart(messageBodyPart2);  
          
            //6) set the multiplart object to the message object  
            message.setContent(multipart );
            
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}