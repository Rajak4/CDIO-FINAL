package dal;

import java.util.List;

public interface IUserDAO {
    List<String[]> getUsers();

    void createUser(String name);
}
