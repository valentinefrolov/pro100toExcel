package frolov.excelFromPro100.dataSource;


import java.util.ArrayList;

public class Material {
    String material;
    ArrayList<Line> items = new ArrayList<>();

    public Material(String name) {
        setName(name);
    }

    public void add(Line line) {
        line.setIndex(items.size() + 1);
        items.add(line);
    }

    public String getName()
    {
        return material;
    }

    public void setName(String material)
    {
        this.material = material;
    }

    public ArrayList<Line> getItems() {
        return items;
    }

    public String toString() {
        return "material: " + material + ", count:  " + items.size();
    }

}
