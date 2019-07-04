package top.aceofspades.travel.domain;

import lombok.Data;

/**
 * 收藏实体类
 *
 * @author duanbt
 * @version 1.0
 **/
@Data
public class Favorite {
    private Route route;
    private String date;
    private User user;
}
