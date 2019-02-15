package com.bms.api.constants;

/**
 * A collection of API URLs and parameters
 *
 * @since v0.1
 * @author PAR
 */
public interface UrlConstants {
	
    public static final String PARAM_SORT_COLUMN = "sortColumn";
    public static final String PARAM_SORT_ORDER = "sortOrder";
    public static final String PARAM_PAGE_NUMBER = "pageNo";
    public static final String PARAM_PAGE_SIZE = "pageSize";

    // Default Parameters
    public static final String DEFAULT_SORT_ORDER = "desc";
    public static final int DEFAULT_LIMIT = 10;
    public static final int DEFAULT_PAGE_NUM = 0;
}
