package top.aceofspades.travel.web.servlet;

import top.aceofspades.travel.constant.SessionConstant;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * 验证码
 *
 * @author duanbt
 * @version 1.0
 **/
@WebServlet("/check-code")
public class CheckCodeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //通知浏览器不要缓存
        resp.setHeader("pragma", "no-cache");
        resp.setHeader("cache-control", "no-cache");
        resp.setHeader("expires", "0");

        int width = 80;
        int height = 25;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        graphics.setColor(Color.GRAY);
        graphics.fillRect(0 ,0, width, height);

        String checkCode = randomCheckCode();
        req.getSession().setAttribute(SessionConstant.CHECK_CODE, checkCode);

        graphics.setColor(Color.YELLOW);
        graphics.setFont(new Font("黑体", Font.BOLD, 24));
        graphics.drawString(checkCode, 20, 20);
        ImageIO.write(image, "PNG", resp.getOutputStream());
    }

    private String randomCheckCode() {
        String base = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(4);
        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(base.length());
            sb.append(base.charAt(index));
        }
        return sb.toString();
    }
}
