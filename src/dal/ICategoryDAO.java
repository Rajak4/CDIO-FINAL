package dal;

import java.util.List;

public interface ICategoryDAO {

    void createCategory(String name);

    String getCategory(int ID);

    List<String[]> getCategories();

}
