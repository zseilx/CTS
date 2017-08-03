package yjc.wdb.scts.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yjc.wdb.scts.bean.GoodsVO;

public interface CategoryService {

	public List<Map> selectDetail_categoryList(Map map) throws Exception;
	public List<Map> selectLarge_categoryList() throws Exception;
	public List<Map> selectCategoryLocation(Map map) throws Exception;
	
	public void insertDetail_category_location(Map map) throws Exception;
	
	public List<HashMap> loadDetailCategory(int drw_code) throws Exception;
	
	public List<GoodsVO> detailCategroyGoods(int detailctgry_code) throws Exception;
	public List<GoodsVO> goods_locationList(int drw_code, int tile_crdnt_x, int tile_crdnt_y, Map map) throws Exception;
}
