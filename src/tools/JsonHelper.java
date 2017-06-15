package tools;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.ItemDAO;

import java.util.ArrayList;

/**
 * Created by Shinelon on 2017/6/15.
 */
public class JsonHelper {
    public static String getItemJson(ItemDAO item) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(item);
    }

    public static String getItemsJson(ArrayList<ItemDAO> items) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(items);
    }
}
