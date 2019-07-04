package top.aceofspades.travel.domain;

import lombok.Data;

/**
 * 用户实体类
 *
 * @author duanbt
 * @version 1.0
 **/
@Data
public class User {
    private Integer uid;
    private String username;
    private String password;
    private String name;
    private String birthday;
    private String sex; // 男、女
    private String telephone;
    private String email;
    private String status;//Y表示激活， N表示未激活
    private String code;//激活码
}
