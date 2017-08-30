package yjc.wdb.scts.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import yjc.wdb.scts.bean.GoodsVO;
import yjc.wdb.scts.bean.TileVO;

public interface TileDAO {
	public List<HashMap<String, String>> selectTileList() throws Exception;	// 이전 버전
	public List<HashMap<String, String>> selectTileListUp(Map map) throws Exception; // 업글 버전
	public void insertTile(TileVO vo) throws Exception;
	public HashMap<String, String> selectTile_LocationOne(TileVO vo) throws Exception;
	public void insertDrawingTile(Map<String, Object> map) throws Exception;	// 아직 구현 안됨
	public void updateTileBeaconSet(HashMap<String, String> vo) throws Exception;
	
	public List<HashMap> tile_goods(int drw_code, int tile_crdnt_x, int tile_crdnt_y) throws Exception;	
	public List<HashMap> avgStayTime(int drw_code, int tile_crdnt_x, int tile_crdnt_y) throws Exception;
	public List<GoodsVO> goods_locationList(int tile_code) throws Exception;
	
	public void deleteLo(int drw_code) throws Exception;
	public void deleteForRegister_position(Map map) throws Exception;
	public void insertDetail_category_location(Map map) throws Exception;
	
	public void deleteGoodsLo(int drw_code) throws Exception;
	public void insertGoods_location(Map map) throws Exception;
	
	public void deleteCourse(Map map) throws Exception;
	
	public List<HashMap> goods_graph(JSONObject json) throws Exception;

}
