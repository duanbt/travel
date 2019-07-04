package top.aceofspades.travel.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import top.aceofspades.travel.dao.SellerDao;
import top.aceofspades.travel.domain.Seller;
import top.aceofspades.travel.util.H2Util;

/**
 * @author duanbt
 * @version 1.0
 **/
public class SellerDaoImpl implements SellerDao {
    private JdbcTemplate jdbc = new JdbcTemplate(H2Util.getDataSource());

    @Override
    public Seller findById(int sid) {
        String sql = "SELECT * FROM TAB_SELLER WHERE SID = ?";
        return jdbc.queryForObject(sql, new BeanPropertyRowMapper<>(Seller.class), sid);
    }
}
