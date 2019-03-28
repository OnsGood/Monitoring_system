import java.util.Hashtable;
import java.util.Map;

public class Comparator {// сравнивает таблицы, и хранит данные до следующего сравнения

    private StringBuffer createdURL;
    private StringBuffer deletedURL;
    private StringBuffer changedURL;
    private boolean createdState;
    private boolean deletedState;
    private boolean changedState;

    public void compare(Hashtable<String,String> old_state, Hashtable<String,String> new_state){

        createdURL = new StringBuffer();
        deletedURL = new StringBuffer();
        changedURL = new StringBuffer();
        createdState = false;
        deletedState = false;
        changedState = false;

        for(Map.Entry m:old_state.entrySet()){

            if(new_state.containsKey(m.getKey())){
                String val = new_state.get(m.getKey());
                if(m.getValue().equals(val) == false){
                    changedState = true;
                    changedURL.append("    " + m.getKey() + "\n");
                }
            }else{
                deletedState = true;
                deletedURL.append("    " + m.getKey() + "\n");
            }
        }
        for(Map.Entry m:new_state.entrySet()){
            if(!old_state.containsKey(m.getKey())){
                createdState=true;
                createdURL.append("    " + m.getKey() + "\n");
            }
        }
    }

    public boolean isCreatedState() {
        return createdState;
    }

    public boolean isDeletedState() {
        return deletedState;
    }

    public boolean isChangedState() {
        return changedState;
    }

    public String getCreatedURL() {
        return createdURL.toString();
    }

    public String getDeletedURL() {
        return deletedURL.toString();
    }

    public String getChangedURL() {
        return changedURL.toString();
    }

    Comparator(){}
}
