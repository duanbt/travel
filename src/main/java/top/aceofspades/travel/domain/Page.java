package top.aceofspades.travel.domain;

import lombok.Data;

import java.util.List;

/**
 * @author duanbt
 * @version 1.0
 **/
@Data
public class Page<T> {

    private int totalCount;

    private int totalPage;

    private int currentPage;

    private int pageSize;

    private List<T> list;
}
