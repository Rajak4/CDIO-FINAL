package dto;

import java.io.*;
import java.net.URLDecoder;
import java.util.List;

public class CreateCSV {

    Item item = new Item();
    public String getPath() throws UnsupportedEncodingException {
        String path = this.getClass().getClassLoader().getResource("").getPath();
        String fullPath = URLDecoder.decode(path, "UTF-8");
        String pathArr[] = fullPath.split("/WEB-INF/classes/");
        System.out.println(pathArr[0]);
        fullPath = pathArr[0];
        return fullPath;
    }

    public void create(List<Item> array, String selected) {
        StringBuilder csv = new StringBuilder();

        System.out.println(selected);

        csv.append(selected.charAt(2) == '1' ? "Produktnavn," : "")
                .append(selected.charAt(3) == '1' ? "Pris i kr. (Excl. moms)," : "")
                .append(selected.charAt(4) == '1' ? "Antal," : "")
                .append(selected.charAt(0) == '1' ? "Kategori," : "")
                .append(selected.charAt(1) == '1' ? "Købers navn," : "")
                .append(selected.charAt(6) == '1' ? "Kommentar," : "")
                .append(selected.charAt(5) == '1' ? "Dato for køb," : "")
                .append("\n");

        for(Item item : array) {
            csv.append(selected.charAt(2) == '1' ? "\"" + item.getProductName() + "\"," : "")
                    .append(selected.charAt(3) == '1' ? item.getPrice() + "," : "")
                    .append(selected.charAt(4) == '1' ? item.getAmount() + "," : "")
                    .append(selected.charAt(0) == '1' ? item.getCategory() + "," : "")
                    .append(selected.charAt(1) == '1' ? item.getBuyersName() + "," : "")
                    .append(selected.charAt(6) == '1' ? "\"" + item.getComment() + "\"," : "")
                    .append(selected.charAt(5) == '1' ? item.getDateOfPurchase() : "")
                    .append("\n");
        }

        try {
            File file = new File(getPath() + "/Regnskab.csv");

            file.setWritable(true);
            FileWriter csvWriter = new FileWriter(file);

            csvWriter.append(csv);

            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
