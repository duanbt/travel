package top.aceofspades.travel.dao;

import top.aceofspades.travel.domain.RouteImg;

import java.util.List;

/**
 * @author duanbt
 * @version 1.0
 **/
public interface RouteImgDao {
    List<RouteImg> findByRid(int rid);
}
