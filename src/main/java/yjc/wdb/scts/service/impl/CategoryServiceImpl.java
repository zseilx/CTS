package yjc.wdb.scts.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import yjc.wdb.scts.bean.GoodsVO;
import yjc.wdb.scts.dao.CategoryDAO;
import yjc.wdb.scts.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Inject
	private CategoryDAO dao;

	@Override
	public List<Map> selectDetail_categoryList(Map map) throws Exception {
		// TODO Auto-generated method stub
		return dao.selectDetail_categoryList(map);
	}

	@Override
	public List<Map> selectLarge_categoryList() throws Exception {
		// TODO Auto-generated method stub
		return dao.selectLarge_categoryList();
	}

	@Override
	public List<Map> selectCategoryLocation(Map map) throws Exception {
		// TODO Auto-generated method stub
		return dao.selectCategoryLocation(map);
	}

	@Override
	@Transactional
	public List<Map> insertDetail_category_location(Map map) throws Exception {
		// TODO Auto-generated method stub
		dao.deleteForRegister(map);
		
		if(map.get("tileList") != null) {
			dao.deleteForRegister_position(map);
			dao.insertDetail_category_location(map);
		}
		
		return dao.selectCategoryLocation(map);
		
		
	}

	@Override
	public List<HashMap> loadDetailCategory(int drw_code) throws Exception {
		// TODO Auto-generated method stub
		return dao.loadDetailCategory(drw_code);
	}

	@Override
	public List<GoodsVO> detailCategroyGoods(int detailctgry_code) throws Exception {
		
		return dao.detailCategroyGoods(detailctgry_code);
	}

	@Transactional
	@Override
	public List<GoodsVO> insertGoods_location(int drw_code, int tile_crdnt_x, int tile_crdnt_y, Map map) throws Exception {
		
		dao.deleteGoods_location(drw_code, tile_crdnt_x, tile_crdnt_y);
		
		if(map.get("goodsList") != null) {
				dao.insertGoods_location(map);
		}
		
		return dao.goods_locationList(drw_code, tile_crdnt_x, tile_crdnt_y);
	}

	@Override
	public List<GoodsVO> goods_locationList(int drw_code, int tile_crdnt_x, int tile_crdnt_y) throws Exception {
		
		return dao.goods_locationList(drw_code, tile_crdnt_x, tile_crdnt_y);
	}

}
