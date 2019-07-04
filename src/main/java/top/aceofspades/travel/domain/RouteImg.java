package top.aceofspades.travel.domain;

import lombok.Data;

/**
 * 旅游线路图片实体类
 *
 * @author duanbt
 * @version 1.0
 **/
@Data
public class RouteImg {
    private int rgid;
    private int rid;
    private String bigPic;
    private String smallPic;
}
