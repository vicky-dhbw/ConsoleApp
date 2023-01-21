package app;

public enum Configuration {
    INSTANCE;

    public final String fileSeparator=System.getProperty("file.separator");
    public final String userDirectory = System.getProperty("user.dir");
    public final String nameOfCSVLoaderJavaArchive = "csv_loader.jar";
    public final String fullPathToCSVLoaderJavaArchive=userDirectory+fileSeparator+"csv_loader"
            +fileSeparator+"jar"+fileSeparator+nameOfCSVLoaderJavaArchive;
    public final String nameOfXMLLoaderJavaArchive = "xml_loader.jar";
    public final String fullPathToXMLLoaderJavaArchive=userDirectory+fileSeparator+"xml_loader"
            +fileSeparator+"jar"+fileSeparator+nameOfXMLLoaderJavaArchive;

    public final String nameOfJSONLoaderJavaArchive = "json_loader_.jar";
    public final String fullPathToJSONLoaderJavaArchive=userDirectory+fileSeparator+"JSON_Load"
            +fileSeparator+"jar"+fileSeparator+nameOfJSONLoaderJavaArchive;

    public final String fullPathToMergeSortJavaArchive=userDirectory+fileSeparator+"merge_sort"
            +fileSeparator+"jar"+fileSeparator+"merge_sort.jar";
    public final String fullPathToQuickSortJavaArchive=userDirectory+fileSeparator+"quick_sort"
            +fileSeparator+"jar"+fileSeparator+"quick_sort.jar";
    public final String fullPathToLambdaSortJavaArchive=userDirectory+fileSeparator+"lambda_sort"
            +fileSeparator+"jar"+fileSeparator+"lambda_sort.jar";

    public final String fullPathToAesCryptographyJavaArchive=userDirectory+fileSeparator+"aes_cryptography"
            +fileSeparator+"jar"+fileSeparator+"aes_cryptography.jar";

    public final String pathToCSVFile=userDirectory+fileSeparator+"data"+fileSeparator+"gin.csv";
    public final String pathToXMLFile=userDirectory+fileSeparator+"data"+fileSeparator+"gin.xml";
    public final String pathToJSONFile=userDirectory+fileSeparator+"data"+fileSeparator+"gin.json";

    public final String pathToDataDir=userDirectory+fileSeparator+"data";



}
