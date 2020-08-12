package frolov.excelFromPro100.dataSource;

import java.io.*;
import java.util.ArrayList;

public class Reader {

    ArrayList<Material> materials = new ArrayList<>();

    public Reader (String fileName) throws Exception {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(fileName),
                        "windows-1251"
                )
        );

        String line = reader.readLine();

        while(line != null) {
            Material current = null;
            String[] split = line.split("\t");
            String name = split[7].trim();

            for(Material material : materials) {
                if(material.material.equals(name)) {
                    current = material;
                    break;
                }
            }

            if(current == null) {
                current = new Material(name);
                materials.add(current);
            }

            Line item = new Line(
                    split[0].trim(),
                    split[1].trim(),
                    split[2].trim(),
                    split[3].trim(),
                    split[4].trim(),
                    split[6].trim()
            );

            current.add(item);

            line = reader.readLine();
        }
    }

    public ArrayList<Material> getMaterials() {
        return materials;
    }

}
