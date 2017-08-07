package yjc.wdb.scts.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yjc.wdb.scts.bean.TileVO;

public interface TileDAO {
	public List<HashMap<String, String>> selectTileList() throws Exception;	// ���� ����
	public List<HashMap<String, String>> selectTileListUp(Map map) throws Exception; // ���� ����
	public void insertTile(TileVO vo) throws Exception;
	public HashMap<String, String> selectTile_LocationOne(TileVO vo) throws Exception;
	public void insertDrawingTile(Map<String, Object> map) throws Exception;	// ���� ���� �ȵ�
	public void updateTileBeaconSet(HashMap<String, String> vo) throws Exception;
	
	public List<HashMap> tile_goods(int drw_code, int tile_crdnt_x, int tile_crdnt_y) throws Exception;	
	public List<HashMap> avgStayTime(int drw_code, int tile_crdnt_x, int tile_crdnt_y) throws Exception;	
}
