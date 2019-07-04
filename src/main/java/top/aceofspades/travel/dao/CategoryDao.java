package top.aceofspades.travel.dao;

import top.aceofspades.travel.domain.Category;

import java.util.List;

/**
 * @author duanbt
 * @version 1.0
 **/
public interface CategoryDao {
    List<Category> findAll();
}
