package top.aceofspades.travel.dao;

import top.aceofspades.travel.domain.Route;

import java.util.List;

/**
 * @author duanbt
 * @version 1.0
 **/
public interface RouteDao {
    int findTotalCount(Integer cid, String rname);

    List<Route> findByPage(Integer cid, int start, int pageSize, String rname);

    Route findOne(Integer rid);
}
