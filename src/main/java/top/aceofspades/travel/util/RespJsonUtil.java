package top.aceofspades.travel.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author duanbt
 * @version 1.0
 **/
public class RespJsonUtil {
    private RespJsonUtil(){
    }

    private static ObjectMapper mapper = new ObjectMapper();

    public static void writeJson(Object data, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String json = mapper.writeValueAsString(data);
        response.getWriter().write(json);
    }
}
