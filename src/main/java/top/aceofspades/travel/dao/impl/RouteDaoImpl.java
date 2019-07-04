package top.aceofspades.travel.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.util.StringUtils;
import top.aceofspades.travel.dao.RouteDao;
import top.aceofspades.travel.domain.Route;
import top.aceofspades.travel.util.H2Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author duanbt
 * @version 1.0
 **/
public class RouteDaoImpl implements RouteDao {

    private JdbcTemplate jdbc = new JdbcTemplate(H2Util.getDataSource());
    private NamedParameterJdbcTemplate namedJdbc = new NamedParameterJdbcTemplate(jdbc);

    @Override
    public int findTotalCount(Integer cid, String rname) {
        String sql = "SELECT COUNT(*) FROM TAB_ROUTE WHERE 1 = 1";

        StringBuilder sqlSb = new StringBuilder(sql);
        List<Object> params = new ArrayList<>();

        if (cid != null) {
            sqlSb.append(" AND CID = ?");
            params.add(cid);
        }

        if (!StringUtils.isEmpty(rname)) {
            sqlSb.append(" AND RNAME LIKE ?");
            params.add("%" + rname + "%");
        }

        sql = sqlSb.toString();
        return jdbc.queryForObject(sql, Integer.class, params.toArray());
    }

    @Override
    public List<Route> findByPage(Integer cid, int start, int pageSize, String rname) {
        String sql = "SELECT * FROM TAB_ROUTE WHERE 1 = 1";

        StringBuilder sqlSb = new StringBuilder(sql);
        Map<String, Object> paramMap = new HashMap<>();

        if (cid != null) {
            sqlSb.append(" AND CID = :cid");
            paramMap.put("cid", cid);
        }

        if (!StringUtils.isEmpty(rname)) {
            sqlSb.append(" AND RNAME LIKE :rname");
            paramMap.put("rname", "%" + rname + "%");
        }

        sqlSb.append(" LIMIT :pageSize OFFSET :start");
        paramMap.put("pageSize", pageSize);
        paramMap.put("start", start);

        sql = sqlSb.toString();
        return namedJdbc.query(sql, paramMap, new BeanPropertyRowMapper<>(Route.class));
    }

    @Override
    public Route findOne(Integer rid) {
        String sql = "SELECT * FROM TAB_ROUTE WHERE RID = ?";
        return jdbc.queryForObject(sql, new BeanPropertyRowMapper<>(Route.class), rid);
    }
}
