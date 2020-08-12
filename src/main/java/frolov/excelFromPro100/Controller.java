package frolov.excelFromPro100;

import frolov.excelFromPro100.dataSource.Reader;
import frolov.excelFromPro100.dataSource.Builder;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
import java.util.HashMap;

public class Controller {
    private Reader reader;
    private Builder builder;
    private HashMap<String, String> map;
    private final String[] errors = new String[2];
    private String dataPath = "";
    private String templatePath = "";

    public String setDataPath(String path)
    {
        errors[0] = "";
        try {
            this.reader = new Reader(path);
            dataPath = path;
        } catch (Exception e) {
            errors[0] = e.getMessage();
        }
        return errors[0];
    }

    public String setTemplatePath(String path)
    {
        errors[1] = "";
        try {
            this.builder = new Builder(path);
            templatePath = path;
        } catch(Exception e) {
            errors[1] = e.getMessage();
        }
        return errors[1];
    }

    public String setMap(String path) {
        String err = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();
            HashMap<String, String> map = new HashMap<>();
            while (line != null) {
                String[] split = line.split(":");
                map.put(split[0].trim(), split[1].trim());
                line = reader.readLine();
            }
            this.map = map;
        } catch (Exception e) {
            err = e.getMessage();
        }
        return err;
    }

    public String exec(String directory) {

        String err = "";
        if(dataPath.equals("")) {
            err = "No data file chosen";
        } else if (templatePath.equals("")) {
            err = "No template file chosen";
        } else {
            try {
                HSSFWorkbook book = builder.save(reader.getMaterials(), map);
                String outPath = directory + "\\~temp";
                FileOutputStream stream = new FileOutputStream(outPath);
                book.write(stream);
                return outPath;
            } catch (IOException e) {
                err = e.getMessage();
            }
        }
        return err;
    }

}
