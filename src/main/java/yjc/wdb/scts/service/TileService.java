package yjc.wdb.scts.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;


import yjc.wdb.scts.bean.GoodsVO;

import yjc.wdb.scts.bean.TileVO;

public interface TileService {
	public List<HashMap<String, String>> selectTileList() throws Exception; // 이전에 쓰던거
	public List<HashMap<String, String>> selectTileListUp(Map map) throws Exception; // 업글 버전
	public void insertTile(TileVO vo) throws Exception;
	public HashMap<String, String> selectTile_LocationOne(TileVO vo) throws Exception;
	public void updateTileBeaconSet(HashMap<String, String> vo) throws Exception;
	

	public void insertDetail_category_location(Map map) throws Exception;
	
	public JSONObject tile_goods(int drw_code, int tile_crdnt_x, int tile_crdnt_y) throws Exception;
	public List<GoodsVO> goods_locationList(int tile_code) throws Exception;

}
