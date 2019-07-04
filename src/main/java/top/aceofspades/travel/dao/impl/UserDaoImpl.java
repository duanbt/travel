package top.aceofspades.travel.dao.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import top.aceofspades.travel.dao.UserDao;
import top.aceofspades.travel.domain.User;
import top.aceofspades.travel.util.H2Util;

/**
 * @author duanbt
 * @version 1.0
 **/
public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbc = new JdbcTemplate(H2Util.getDataSource());
    private NamedParameterJdbcTemplate namedJdbc = new NamedParameterJdbcTemplate(jdbc);

    @Override
    public void save(User user) {
        String sql = "INSERT INTO TAB_USER \n" +
                "    (USERNAME, PASSWORD, NAME, BIRTHDAY, SEX, TELEPHONE, EMAIL, STATUS, CODE)\n" +
                "    VALUES ( :username, :password, :name, :birthday, :sex, :telephone, :email, :status, :code )";
        namedJdbc.update(sql, new BeanPropertySqlParameterSource(user));
    }

    @Override
    public User findByUsername(String username) {
        String sql = "SELECT * FROM TAB_USER WHERE USERNAME = ?";
        try {
            return jdbc.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public User findByCode(String code) {
        String sql = "SELECT * FROM TAB_USER WHERE CODE = ?";
        try {
            return jdbc.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), code);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE TAB_USER SET \n" +
                "USERNAME=:username,PASSWORD=:password,NAME=:name,BIRTHDAY=:birthday,SEX=:sex,TELEPHONE=:telephone,\n" +
                "EMAIL=:email,STATUS=:status,CODE=:code\n" +
                "WHERE UID=:uid";
        namedJdbc.update(sql, new BeanPropertySqlParameterSource(user));
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        String sql = "SELECT * FROM TAB_USER WHERE USERNAME=? AND PASSWORD=?";
        try {
            return jdbc.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username, password);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
