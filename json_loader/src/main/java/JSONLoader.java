import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONLoader {
    private static final JSONLoader jsonLoaderInstance=new JSONLoader();
    public Port port;
    public JSONLoader(){
        port=new Port();
    }


    public static JSONLoader getInstance(){
        return jsonLoaderInstance;
    }
    public List<Gin> innerLoad(String filePath) {
        ObjectMapper objectMapper=new ObjectMapper();
        File file=new File(filePath);
        List<Gin> gins=new ArrayList<>();
        try {
            gins= objectMapper.readValue(file, new TypeReference<>() {
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        return gins;
    }

    public class Port implements IJSONLoader{
        @Override
        public List<Gin> load(String filePath) {
            return innerLoad(filePath);
        }
    }
}
