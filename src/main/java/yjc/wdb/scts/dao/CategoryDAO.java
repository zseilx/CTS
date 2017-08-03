package yjc.wdb.scts.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yjc.wdb.scts.bean.GoodsVO;

public interface CategoryDAO {
	public List<Map> selectDetail_categoryList(Map map) throws Exception;
	public List<Map> selectLarge_categoryList() throws Exception;
	public List<Map> selectCategoryLocation(Map map) throws Exception;
	
	/* µŒ∞≥ π≠æÓº≠ ∆Æ∑ª¿Ëº« √≥∏Æ«‘  service ø°º≠ insertDetail_category_locationø° ∆Æ∑£¿Ëº«¿∏∑Œ π≠¿Ω */
	public void deleteForRegister(Map map) throws Exception;
	public void deleteForRegister_position(Map map) throws Exception;
	public void insertDetail_category_location(Map map) throws Exception;
	
	public List<HashMap> loadDetailCategory(int drw_code) throws Exception;
	
	public List<GoodsVO> detailCategroyGoods(int detailctgry_code) throws Exception;
	public void deleteGoods_location(int drw_code, int tile_crdnt_x, int tile_crdnt_y) throws Exception;
	public void insertGoods_location(Map map) throws Exception;
	public List<GoodsVO> goods_locationList(int drw_code, int tile_crdnt_x, int tile_crdnt_y) throws Exception;
}
