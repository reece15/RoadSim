package dao;


import java.util.List;

import dto.MapNode;

/**
 * @filename Data.java
 * @author Reece
 * @description  数据访问对象接口
 * @date 2015年10月15日下午11:07:04
 * @version 1.0
 */
public interface DataInterface {
	List<MapNode> loadMap();
}
