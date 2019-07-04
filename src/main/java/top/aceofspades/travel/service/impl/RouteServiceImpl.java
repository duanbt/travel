package top.aceofspades.travel.service.impl;

import top.aceofspades.travel.dao.RouteDao;
import top.aceofspades.travel.dao.RouteImgDao;
import top.aceofspades.travel.dao.SellerDao;
import top.aceofspades.travel.dao.impl.RouteDaoImpl;
import top.aceofspades.travel.dao.impl.RouteImgDaoImpl;
import top.aceofspades.travel.dao.impl.SellerDaoImpl;
import top.aceofspades.travel.domain.Page;
import top.aceofspades.travel.domain.Route;
import top.aceofspades.travel.domain.RouteImg;
import top.aceofspades.travel.domain.Seller;
import top.aceofspades.travel.service.RouteService;

import java.util.List;

/**
 * @author duanbt
 * @version 1.0
 **/
public class RouteServiceImpl implements RouteService {

    private RouteDao routeDao = new RouteDaoImpl();
    private RouteImgDao routeImgDao = new RouteImgDaoImpl();
    private SellerDao sellerDao = new SellerDaoImpl();

    @Override
    public Page<Route> pageQuery(Integer cid, int currentPage, int pageSize, String rname) {
        Page<Route> page = new Page<>();
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);

        int totalCount = routeDao.findTotalCount(cid, rname);
        page.setTotalCount(totalCount);

        int start = (currentPage - 1) * pageSize;
        List<Route> list = routeDao.findByPage(cid, start, pageSize, rname);
        page.setList(list);

        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1;
        page.setTotalPage(totalPage);

        return page;
    }

    @Override
    public Route findOne(Integer rid) {
        Route route = routeDao.findOne(rid);

        List<RouteImg> routeImgList =  routeImgDao.findByRid(route.getRid());
        route.setRouteImgList(routeImgList);

        Seller seller = sellerDao.findById(route.getSid());
        route.setSeller(seller);

        return route;
    }
}
