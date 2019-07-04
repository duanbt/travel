package top.aceofspades.travel.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import top.aceofspades.travel.dao.CategoryDao;
import top.aceofspades.travel.domain.Category;
import top.aceofspades.travel.util.H2Util;

import java.util.List;

/**
 * @author duanbt
 * @version 1.0
 **/
public class CategoryDaoImpl implements CategoryDao {

    private JdbcTemplate jdbc = new JdbcTemplate(H2Util.getDataSource());

    @Override
    public List<Category> findAll() {
        String sql = "SELECT * FROM TAB_CATEGORY";
        return jdbc.query(sql, new BeanPropertyRowMapper<>(Category.class));
    }
}
