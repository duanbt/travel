package top.aceofspades.travel.service.impl;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.apachecommons.CommonsLog;
import top.aceofspades.travel.dao.CategoryDao;
import top.aceofspades.travel.dao.impl.CategoryDaoImpl;
import top.aceofspades.travel.domain.Category;
import top.aceofspades.travel.service.CategoryService;

import java.time.Duration;
import java.util.List;

/**
 * @author duanbt
 * @version 1.0
 **/
@CommonsLog
public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao = new CategoryDaoImpl();

    private Cache<String, List<Category>> categoryCache;

    private static final String CATEGORY_CACHE_KEY = "category";

    public CategoryServiceImpl() {
        categoryCache = Caffeine.newBuilder()
                .expireAfterAccess(Duration.ofMinutes(1))
                .expireAfterWrite(Duration.ofMinutes(2))
                .refreshAfterWrite(Duration.ofSeconds(10))
                .maximumSize(100)
                .build(key -> {
                    log.info("刷新缓存category");
                    return categoryDao.findAll();
                });
    }

    @Override
    public List<Category> findAll() {
        return categoryCache.get(CATEGORY_CACHE_KEY, key -> {
            log.info("数据库查询category");
            return categoryDao.findAll();
        });
    }
}
