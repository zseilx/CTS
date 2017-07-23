package yjc.wdb.scts.dao;

import java.util.List;
import java.util.Map;

public interface CategoryDAO {
	public List<Map> selectDetail_categoryList(Map map) throws Exception;
	public List<Map> selectLarge_categoryList() throws Exception;
}
