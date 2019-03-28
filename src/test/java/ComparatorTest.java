import java.util.Hashtable;
import java.util.Map;

import static org.junit.Assert.*;

public class ComparatorTest {

    @org.junit.Test
    public void compareDelete() {
        Hashtable<String,String> old_state = new Hashtable <String,String>(); //Хэш таблица состояний сайтов на вчера
        Hashtable<String,String> new_state = new Hashtable <String,String>(); //Хэш таблица состояний сайтов на сегодня

        old_state.put("TestURL1","TestCode1");
        old_state.put("TestURL2","TestCode2");
        old_state.put("TestURL3","TestCode3");

        new_state.putAll(old_state);
        new_state.remove("TestURL2");

        Comparator a = new Comparator();
        a.compare(old_state,new_state);

        boolean actDelState=a.isDeletedState();
        boolean exepDelState=true;

        assertEquals(actDelState,exepDelState);
    }

    @org.junit.Test
    public void compareCreate() {
        Hashtable<String,String> old_state = new Hashtable <String,String>(); //Хэш таблица состояний сайтов на вчера
        Hashtable<String,String> new_state = new Hashtable <String,String>(); //Хэш таблица состояний сайтов на сегодня

        old_state.put("TestURL1","TestCode1");
        old_state.put("TestURL2","TestCode2");
        old_state.put("TestURL3","TestCode3");

        new_state.putAll(old_state);
        new_state.put("TEST4","TestCode4");

        Comparator a = new Comparator();
        a.compare(old_state,new_state);

        boolean actState=a.isCreatedState();
        boolean exepState=true;

        assertEquals(actState,exepState);
    }
    @org.junit.Test
    public void compareChange() {
        Hashtable<String,String> old_state = new Hashtable <String,String>(); //Хэш таблица состояний сайтов на вчера
        Hashtable<String,String> new_state = new Hashtable <String,String>(); //Хэш таблица состояний сайтов на сегодня

        old_state.put("TestURL1","TestCode1");
        old_state.put("TestURL2","TestCode2");
        old_state.put("TestURL3","TestCode3");

        new_state.putAll(old_state);

        for(Map.Entry m:old_state.entrySet()){
            if(m.getKey()=="TestURL1")
                m.setValue("Changed!");
        }

        Comparator a = new Comparator();
        a.compare(old_state,new_state);

        boolean actState=a.isChangedState();
        boolean exepState=true;

        assertEquals(actState,exepState);
    }
}