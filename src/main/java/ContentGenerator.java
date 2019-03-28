import java.util.Hashtable;
import java.util.Map;

public class ContentGenerator {// Отладочный класс для тестирования приложения

    public static Hashtable<String,String> createTable(){
        Hashtable<String,String> state = new Hashtable <String,String>();
        for(int i =0; i< 10; i++){
            state.put("https://" + i + "url.ururu","Some html code" + i);
            //state.put("n"+i,"Somehtmlcode");
        }
        return state;
    }

    public static  Hashtable<String,String> changeTable(Hashtable<String,String> state){

        state.put("https://OOPSurl.ururu", "Текст на русском");

        for(Map.Entry m:state.entrySet()) {
            if(m.getKey().equals("https://0url.ururu") || m.getKey().equals("5"))
                m.setValue("Измененный код");
        }

        state.remove("https://3url.ururu");

        state.put("https://OOPSur1l.ururu", "Созданный и тут же удаленный обьект");
        state.remove("https://OOPSur1l.ururu");

        return state;
    }
}
