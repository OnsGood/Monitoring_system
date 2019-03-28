import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Hashtable;

public class Application {
    public static final String RECIPIENT_NAME = "И.О. секретаря";
    public static final String RECIPIENT_EMAIL = "Почта получателя";
    public static final String SENDER_EMAIL = "Почта отправителя";
    public static final String SENDER_PASSWORD = "Пароль отправителя";
    public static final String TITLE = "Заголовок письма";

    public static void main(String[] args) throws IOException, MessagingException {
        Hashtable<String,String> old_state = new Hashtable <String,String>(); //Хэш таблица состояний сайтов на вчера
        Hashtable<String,String> new_state = new Hashtable <String,String>(); //Хэш таблица состояний сайтов на сегодня

        Comparator comparator = new Comparator();
        comparator.compare(old_state, new_state);

        String mail_text = EmailSender.formMail(RECIPIENT_NAME,comparator);

        if(comparator.isDeletedState() || comparator.isChangedState() || comparator.isCreatedState()) {
            EmailSender.sendMail(SENDER_EMAIL, RECIPIENT_EMAIL,TITLE,mail_text,SENDER_PASSWORD);
        }else{
            System.out.println("Изменений не происходило!");
        }
    }

}
