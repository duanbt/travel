package top.aceofspades.travel.web.servlet;

import lombok.extern.apachecommons.CommonsLog;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.StringUtils;
import top.aceofspades.travel.constant.SessionConstant;
import top.aceofspades.travel.domain.ApiResponse;
import top.aceofspades.travel.domain.User;
import top.aceofspades.travel.service.UserService;
import top.aceofspades.travel.service.impl.UserServiceImpl;
import top.aceofspades.travel.util.RespJsonUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author duanbt
 * @version 1.0
 **/
@CommonsLog
@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    public void register(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //验证码
        String check = req.getParameter("check");
        HttpSession session = req.getSession();
        String checkCodeServer = (String) session.getAttribute(SessionConstant.CHECK_CODE);
        session.removeAttribute(SessionConstant.CHECK_CODE);
        if (checkCodeServer == null || !checkCodeServer.equalsIgnoreCase(check)) {
            ApiResponse apiResponse = ApiResponse.fail("验证码错误");
            writeValue(apiResponse, resp);
            return;
        }

        Map<String, String[]> params = req.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, params);
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.warn("赋值失败", e);
            writeValue(ApiResponse.fail(e.getMessage()), resp);
            return;
        }

        try {
            userService.register(user);
            writeValue(ApiResponse.success(), resp);
        } catch (Exception e) {
            log.info("注册失败", e);
            writeValue(ApiResponse.fail(e.getMessage()), resp);
            return;
        }
    }

    public void active(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String code = req.getParameter("code");
        if (StringUtils.isEmpty(code)) {
            writeValue(ApiResponse.fail("激活码不能为空"), resp);
            return;
        }
        try {
            userService.active(code);
            resp.sendRedirect("/active_ok.html");
        } catch (Exception e) {
            log.error("激活失败", e);
            resp.sendRedirect("/error/500.html");
        }
    }

    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //验证码
        String check = req.getParameter("check");
        HttpSession session = req.getSession();
        String checkCodeServer = (String) session.getAttribute(SessionConstant.CHECK_CODE);
        session.removeAttribute(SessionConstant.CHECK_CODE);
        if (checkCodeServer == null || !checkCodeServer.equalsIgnoreCase(check)) {
            ApiResponse apiResponse = ApiResponse.fail("验证码错误");
            RespJsonUtil.writeJson(apiResponse, resp);
            return;
        }

        Map<String, String[]> params = req.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, params);
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.warn("赋值失败", e);
            RespJsonUtil.writeJson(ApiResponse.fail(e.getMessage()), resp);
            return;
        }
        try {
            User u = userService.login(user);
            req.getSession().setAttribute(SessionConstant.USER, u);
            RespJsonUtil.writeJson(ApiResponse.success(u), resp);
        } catch (Exception e) {
            RespJsonUtil.writeJson(ApiResponse.fail(e.getMessage()), resp);
        }
    }


    public void findOne(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute(SessionConstant.USER);
        writeValue(ApiResponse.success(user), resp);
    }

    public void exit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().invalidate();
        resp.sendRedirect("/login.html");
    }
}
