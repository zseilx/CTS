package yjc.wdb.scts.dao.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import yjc.wdb.scts.dao.CategoryDAO;

@Repository
public class CategoryDAOImpl implements CategoryDAO {
	
	private static final String NAMESPACE = "yjc.wdb.mapper.CategoryMapper";
	
	@Inject
	private SqlSession sql;

	@Override
	public List<Map> selectDetail_categoryList(Map map) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectList(NAMESPACE + ".selectDetail_categoryList", map);
	}

	@Override
	public List<Map> selectLarge_categoryList() throws Exception {
		// TODO Auto-generated method stub
		return sql.selectList(NAMESPACE + ".selectLarge_categoryList");
	}

}
