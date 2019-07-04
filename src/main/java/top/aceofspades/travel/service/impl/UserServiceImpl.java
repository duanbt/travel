package top.aceofspades.travel.service.impl;

import top.aceofspades.travel.dao.UserDao;
import top.aceofspades.travel.dao.impl.UserDaoImpl;
import top.aceofspades.travel.domain.User;
import top.aceofspades.travel.service.UserService;
import top.aceofspades.travel.util.MailUtil;
import top.aceofspades.travel.util.UuidUtil;

/**
 * @author duanbt
 * @version 1.0
 **/
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public void register(User user) {
        User userPO = userDao.findByUsername(user.getUsername());
        if (userPO != null) {
            throw new RuntimeException("用户名已存在");
        }

        //激活码
        user.setCode(UuidUtil.uuid());
        user.setStatus("N");
        userDao.save(user);

        //发送激活邮件
        //language=HTML
        String content = "<a href='http://localhost/user/active?code=" + user.getCode() + "'>点击激活【旅游网】</a>";
        MailUtil.sendMail(user.getEmail(), content, "激活邮件");
    }

    public void active(String code) {
        User user = userDao.findByCode(code);
        if (user == null) {
            throw new RuntimeException("激活用户异常，激活码不存在");
        }
        user.setStatus("Y");
        userDao.update(user);
    }

    @Override
    public User login(User user) {
        User userPO = userDao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if(userPO == null){
            throw new RuntimeException("用户名或密码不正确");
        }
        if(!"Y".equals(userPO.getStatus())){
            throw new RuntimeException("账号未激活");
        }
        return userPO;
    }
}
