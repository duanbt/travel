package top.aceofspades.travel.service;

import top.aceofspades.travel.domain.User;

/**
 * @author duanbt
 * @version 1.0
 **/
public interface UserService {

    void register(User user);

    void active(String code);

    User login(User user);
}
