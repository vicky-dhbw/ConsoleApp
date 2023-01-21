import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.org.Gin;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JSONLoader_ {
    private static final JSONLoader_ jsonLoaderInstance=new JSONLoader_();
    public Port port;
    private List<Gin> gins=new ArrayList<>();
    public JSONLoader_(){
        port=new Port();
    }

    public static JSONLoader_ getInstance(){
        return jsonLoaderInstance;
    }

    public List<Gin> innerLoad(String filePath) {
        ObjectMapper objectMapper=new ObjectMapper();
        File file=new File(filePath);
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

