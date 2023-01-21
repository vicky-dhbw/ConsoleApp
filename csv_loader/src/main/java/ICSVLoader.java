import common.org.Gin;
import java.util.List;

public interface ICSVLoader {
    List<Gin> load(String filePath);
}
