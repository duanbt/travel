package top.aceofspades.travel.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import top.aceofspades.travel.dao.RouteImgDao;
import top.aceofspades.travel.domain.RouteImg;
import top.aceofspades.travel.util.H2Util;

import java.util.List;

/**
 * @author duanbt
 * @version 1.0
 **/
public class RouteImgDaoImpl implements RouteImgDao {
    private JdbcTemplate jdbc = new JdbcTemplate(H2Util.getDataSource());

    @Override
    public List<RouteImg> findByRid(int rid) {
        String sql = "SELECT * FROM TAB_ROUTE_IMG WHERE RID = ?";
        return jdbc.query(sql, new BeanPropertyRowMapper<>(RouteImg.class), rid);
    }
}
