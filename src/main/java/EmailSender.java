import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

public class EmailSender {//Формирует и отправляет email
    public static void sendMail(String your_Mail, String rec_mail, String subject, String text, String pass) throws IOException, MessagingException {
        final Properties properties = new Properties();
        properties.load(EmailSender.class.getClassLoader().getResourceAsStream("mail.properties"));

        Session mailSession = Session.getDefaultInstance(properties);
        MimeMessage message = new MimeMessage(mailSession);

        message.setFrom(new InternetAddress(your_Mail));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(rec_mail));
        message.setSubject(subject);
        message.setText(text);

        Transport tr = mailSession.getTransport();
        tr.connect(null,pass);
        tr.sendMessage(message,message.getAllRecipients());
        tr.close();
    }

    public static String formMail(String name, Comparator comparator){
        StringBuffer buffer = new StringBuffer();
        int list=1;
        String a1 = "Здравствуйте, дорогая " + name + "!\n" +
                    "За последние сутки во вверенных Вам сайтах произошли следующие изменения:\n\n";
        String a2 = "\nС уважением,\n" +
                "автоматизированная система\n" +
                "мониторинга.\n";
        String del ="Исчезли следующие страницы:\n";
        String add ="Появились следующие новые страницы:\n";
        String chan="Изменились следующие страницы:\n";

        buffer.append(a1);
        if(comparator.isDeletedState()){
            buffer.append(list + ". " + del);
            buffer.append(comparator.getDeletedURL());
            list++;
        }
        if(comparator.isCreatedState()){
            buffer.append(list + ". " + add);
            buffer.append(comparator.getCreatedURL());
            list++;
        }
        if(comparator.isChangedState()){
            buffer.append(list + ". " + chan);
            buffer.append(comparator.getChangedURL());
            list++;
        }
        buffer.append(a2);
        return  buffer.toString();
    }
}
