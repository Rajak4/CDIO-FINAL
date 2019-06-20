package dto;

import java.io.*;
import java.util.List;

public class CreateCSV {

    Item item = new Item();

    public void create(List<Item> array) {
        String k = ",";
        String CVS = "";
        try {


            for(int i = 0; i < array.size(); i++){
                CVS += array.get(i).getBuyersName();
                CVS += k;
                CVS += array.get(i).getCategory();
                CVS += k;
                CVS += array.get(i).getComment();
                CVS += k;
                CVS += array.get(i).getDateOfPurchase();
                CVS += k;
                CVS += array.get(i).getProductName();
                CVS += k;

                int amount = array.get(i).getAmount();
                double price = array.get(i).getPrice();

                CVS += Integer.toString(amount);
                CVS +=k;
                CVS +=Double.toString(price);
                CVS +="\n";
            }

            File file = new File("Regnskab.csv");

            file.setWritable(true);
            FileWriter csvWriter = new FileWriter(file);

            csvWriter.append(CVS);

            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
