package app;

import common.org.Gin;
import java.util.List;

public interface IFileLoader {

    List<Gin> loadFile(String filepath);
}
