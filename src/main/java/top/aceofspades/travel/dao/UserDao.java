package top.aceofspades.travel.dao;

import top.aceofspades.travel.domain.User;

/**
 * @author duanbt
 * @version 1.0
 **/
public interface UserDao {

    void save(User user);

    User findByUsername(String username);

    User findByCode(String code);

    void update(User user);

    User findByUsernameAndPassword(String username, String password);
}
