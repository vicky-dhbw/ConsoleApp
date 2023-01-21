import common.org.Gin;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSVLoader {

    private static final CSVLoader csvLoaderInstance=new CSVLoader();
    public Port port;
    private final List<Gin> gins=new ArrayList<>();

    public CSVLoader(){
        port=new Port();
    }

    public static CSVLoader getInstance(){
        return csvLoaderInstance;
    }

    public List<Gin>  innerLoad(String filePath) {

        try {
            BufferedReader bufferedReader=new BufferedReader(new FileReader(filePath));
            String line;

            bufferedReader.readLine();
            while((line=bufferedReader.readLine())!=null){
                String[] entries = line.split(";");
                Gin gin=new Gin();
                String manufacturer=entries[0];
                String bottle=entries[1];
                String size=entries[2];
                String price=entries[3];
                gin.setManufacturer(manufacturer);
                gin.setBottle(bottle);
                gin.setPrice(price);
                gin.setSize(size);
                gins.add(gin);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return gins;
    }

    public class Port implements ICSVLoader{
        @Override
        public List<Gin>  load(String filePath) {
            return innerLoad(filePath);
        }
    }
}
