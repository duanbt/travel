package top.aceofspades.travel.service;

import top.aceofspades.travel.domain.Page;
import top.aceofspades.travel.domain.Route;

/**
 * 旅游线路service
 *
 * @author duanbt
 * @version 1.0
 **/
public interface RouteService {

    /**
     * 分页查询指定分类的旅游线路
     *
     * @param cid         分类id
     * @param currentPage 页码
     * @param pageSize    每页条数
     * @param rname       路线名称
     * @return 分页数据
     */
    Page<Route> pageQuery(Integer cid, int currentPage, int pageSize, String rname);

    /**
     * 根据id查询旅游线路详情
     *
     * @param rid 主键
     * @return 路线详情
     */
    Route findOne(Integer rid);
}
