package top.aceofspades.travel.util;

import java.util.UUID;

/**
 * @author duanbt
 * @version 1.0
 **/
public class UuidUtil {
    private UuidUtil(){
    }

    public static String uuid(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
