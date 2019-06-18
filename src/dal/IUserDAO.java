package dal;

import java.util.List;

public interface IUserDAO {
    String getUser(int ID);

    List<String[]> getUsers();

    void createUser(String name);
}
