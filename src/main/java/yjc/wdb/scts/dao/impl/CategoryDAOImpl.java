package yjc.wdb.scts.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import yjc.wdb.scts.bean.GoodsVO;
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

	@Override
	public List<Map> selectCategoryLocation(Map map) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectList(NAMESPACE + ".selectCategoryLocation", map);
	}

	@Override
	public void deleteForRegister(Map map) throws Exception {
		// TODO Auto-generated method stub
		sql.delete(NAMESPACE + ".deleteForRegister", map);
	}

	@Override
	public void insertDetail_category_location(Map map) throws Exception {
		// TODO Auto-generated method stub
		sql.insert(NAMESPACE + ".insertDetail_category_location", map);
	}

	@Override
	public void deleteForRegister_position(Map map) throws Exception {
		// TODO Auto-generated method stub
		sql.delete(NAMESPACE + ".deleteForRegister_position", map);
	}

	@Override
	public List<HashMap> loadDetailCategory(int drw_code) throws Exception {
		
		return sql.selectList(NAMESPACE+".loadDetailCategory", drw_code);
	}

	@Override
	public List<GoodsVO> detailCategroyGoods(int detailctgry_code) throws Exception {
		
		return sql.selectList(NAMESPACE+".detailCategoryGoods", detailctgry_code);
	}

	@Override
	public void deleteGoods_location(int drw_code, int tile_crdnt_x, int tile_crdnt_y) throws Exception {
	
		Map map = new HashMap();
		map.put("drw_code", drw_code);
		map.put("tile_crdnt_x", tile_crdnt_x);
		map.put("tile_crdnt_y", tile_crdnt_y);
		
		sql.delete(NAMESPACE+".deleteGoods_location", map);
		
	}

	@Override
	public void insertGoods_location(Map map) throws Exception {
		
		sql.insert(NAMESPACE+".insertGoods_location", map);
		
	}

	@Override
	public List<GoodsVO> goods_locationList(int drw_code, int tile_crdnt_x, int tile_crdnt_y) throws Exception {
		
		Map map = new HashMap();
		map.put("drw_code", drw_code);
		map.put("tile_crdnt_x", tile_crdnt_x);
		map.put("tile_crdnt_y", tile_crdnt_y);
		
		return sql.selectList(NAMESPACE+".goods_locationList", map);
	}

}
