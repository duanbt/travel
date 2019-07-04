package top.aceofspades.travel.web.servlet;

import org.springframework.util.StringUtils;
import top.aceofspades.travel.domain.ApiResponse;
import top.aceofspades.travel.domain.Page;
import top.aceofspades.travel.domain.Route;
import top.aceofspades.travel.service.RouteService;
import top.aceofspades.travel.service.impl.RouteServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * @author duanbt
 * @version 1.0
 **/
@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {

    private RouteService routeService = new RouteServiceImpl();

    public void pageQuery(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        String currentPageStr = req.getParameter("currentPage");
        String pageSizeStr = req.getParameter("pageSize");
        String cidStr = req.getParameter("cid");
        Integer cid = null;
        if (!StringUtils.isEmpty(cidStr)) {
            cid = Integer.parseInt(cidStr);
        }

        String rname = req.getParameter("rname");
        rname = new String(rname.getBytes("iso-8859-1"), "utf-8");

        int currentPage = 1;
        if (!StringUtils.isEmpty(currentPageStr)) {
            currentPage = Integer.parseInt(currentPageStr);
        }

        int pageSize = 5;
        if (!StringUtils.isEmpty(pageSizeStr)) {
            pageSize = Integer.parseInt(pageSizeStr);
        }

        try {
            Page<Route> page = routeService.pageQuery(cid, currentPage, pageSize, rname);
            writeValue(ApiResponse.success(page), resp);
        } catch (Exception e) {
            writeValue(ApiResponse.fail(e.getMessage()), resp);
        }
    }

    public void findOne(HttpServletRequest req, HttpServletResponse resp){
        String rid = req.getParameter("rid");
        Route route = routeService.findOne(Integer.parseInt(rid));
        writeValue(ApiResponse.success(route), resp);
    }
}
