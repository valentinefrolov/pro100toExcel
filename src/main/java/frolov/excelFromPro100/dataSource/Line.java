package frolov.excelFromPro100.dataSource;


public class Line {

    String name;
    int width;
    int widthEdge;
    int height;
    int heightEdge;
    int count;
    int index;

    Line (
            String name,
            String width,
            String widthEdge,
            String height,
            String heightEdge,
            String count

    ) throws NumberFormatException {

        this.name = name;
        this.width = Integer.parseInt(width);

        if(widthEdge.equals("=")) {
            this.widthEdge = 2;
        } else if(widthEdge.equals("—")) {
            this.widthEdge = 1;
        } else {
            this.widthEdge = 0;
        }

        this.height = Integer.parseInt(height);

        if(heightEdge.equals("=")) {
            this.heightEdge = 2;
        } else if(heightEdge.equals("—")) {
            this.heightEdge = 1;
        } else {
            this.heightEdge = 0;
        }
        this.count = Integer.parseInt(count);
    }

    void setIndex(int index) {
        this.index = index;
    }

    public String toString() {
        return "index: " + index +
                "; name: " + name +
                "; width: " + width +
                "; height: " + height +
                "; wEdge: " + widthEdge +
                "; hEdge: " + heightEdge +
                "; count: " + count
                ;
    }

    public String parse(String cellValue) {
        switch (cellValue.trim()) {
            case "{row}":
                return String.valueOf(index);
            case "{name}":
                return name;
            case "{width}":
                return String.valueOf(width);
            case "{height}":
                return String.valueOf(height);
            case "{count}":
                return String.valueOf(count);
            case "{w1}":
            case "{w2}":
                return this.widthEdge-- > 0 ? "+" : "";
            case "{h1}":
            case "{h2}":
                return this.heightEdge-- > 0 ? "+" : "";
        }
        return cellValue;
    }
}