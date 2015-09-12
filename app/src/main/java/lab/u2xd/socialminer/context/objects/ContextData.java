package lab.u2xd.socialminer.context.objects;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ysb on 2015-09-12.
 */
public class ContextData {

    private HashMap<String, String> listData;

    private ArrayList<ContextLinker> listLinkers;

    public ContextData(String[] Columns) {
        listData = new HashMap<>();
        listLinkers= new ArrayList<>();
    }

    public void setData(String Column, String value) {
        listData.put(Column, value);
    }

    public String[] getColumns() {
        return listData.keySet().toArray(new String[0]);
    }
}
