package data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class dataReader {


    public List<HashMap<String, String>> getJsonIntoHashMap() throws IOException {

        // read json to string
        String jsonString = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//src//main//java//data//data.json"), StandardCharsets.UTF_8);

        //string to maps using Jackson databind

        ObjectMapper obj= new ObjectMapper();
        List<HashMap<String,String>> map=obj.readValue(jsonString, new TypeReference<List<HashMap<String,String>>>(){});

        return map;
    }


}
