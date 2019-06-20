package dto;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CreateCSV {

    Item item = new Item();

    public void create(List<Item> array) {

        FileWriter csvWriter = null;
        File dir = new File(".");

        String path = System.getProperty("." + File.separator + "my.properties");

        try {
            csvWriter = new FileWriter(path);
            String k = ",";


            for(int i = 0; i < array.size(); i++){
                csvWriter.append(array.get(i).getBuyersName());
                csvWriter.append(k);
                csvWriter.append(array.get(i).getCategory());
                csvWriter.append(k);
                csvWriter.append(array.get(i).getComment());
                csvWriter.append(k);
                csvWriter.append(array.get(i).getDateOfPurchase());
                csvWriter.append(k);
                csvWriter.append(array.get(i).getProductName());
                csvWriter.append(k);

                int amount = array.get(i).getAmount();
                double price = array.get(i).getPrice();

                csvWriter.append(Integer.toString(amount));
                csvWriter.append(k);
                csvWriter.append(Double.toString(price));
                csvWriter.append("\n");
            }
            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
