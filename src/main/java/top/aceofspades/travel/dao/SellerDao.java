package top.aceofspades.travel.dao;

import top.aceofspades.travel.domain.Seller;

/**
 * @author duanbt
 * @version 1.0
 **/
public interface SellerDao {
    Seller findById(int sid);
}
