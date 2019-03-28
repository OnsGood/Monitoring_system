import org.junit.Test;

import java.util.Hashtable;

import static org.junit.Assert.*;

public class EmailSenderTest {

    @Test
    public void formMail() {
        Hashtable<String,String> old_state = ContentGenerator.createTable(); //Хэш таблица состояний сайтов на вчера
        Hashtable<String,String> new_state = new Hashtable <String,String>(); //Хэш таблица состояний сайтов на сегодня
        new_state.putAll(old_state);
        new_state = ContentGenerator.changeTable(new_state);

        Comparator a = new Comparator();
        a.compare(old_state,new_state);

        System.out.println();

        String asString = EmailSender.formMail("NAME",a);

        System.out.println(asString);

        String exString ="Здравствуйте, дорогая NAME!\n" +
                "За последние сутки во вверенных Вам сайтах произошли следующие изменения:\n" +
                "\n" +
                "1. Исчезли следующие страницы:\n" +
                "    https://3url.ururu\n" +
                "2. Появились следующие новые страницы:\n" +
                "    https://OOPSurl.ururu\n" +
                "3. Изменились следующие страницы:\n" +
                "    https://0url.ururu\n" +
                "\n" +
                "С уважением,\n" +
                "автоматизированная система\n" +
                "мониторинга.\n";

        assertEquals(asString,exString);
    }
}