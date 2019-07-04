package top.aceofspades.travel.web.servlet;

import top.aceofspades.travel.domain.ApiResponse;
import top.aceofspades.travel.domain.Category;
import top.aceofspades.travel.service.CategoryService;
import top.aceofspades.travel.service.impl.CategoryServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 分类查询
 *
 * @author duanbt
 * @version 1.0
 **/
@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet{

    private CategoryService service = new CategoryServiceImpl();

    public void findAll(HttpServletRequest req, HttpServletResponse resp){
        List<Category> categories = service.findAll();
        writeValue(ApiResponse.success(categories), resp);
    }
}
