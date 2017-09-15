package yjc.wdb.scts.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import yjc.wdb.scts.bean.TileVO;

public interface CourseDAO {
	public void insertCourse(HashMap<String, String> vo) throws Exception;
	public int selectTodayVisitCnt(int bhf_code) throws Exception;
	public HashMap<String, String> tileProbability(TileVO vo) throws Exception;
	public List<HashMap<String, String>> tileUserinfo(TileVO vo) throws Exception;
	public void updateStayTime(HashMap<String, String> vo) throws Exception;
	public int monthAvgVisitor(int bhf_code) throws Exception;
	public List<HashMap> tileGender(int day, int bhf_code) throws Exception;
	public List<HashMap> tileAge(int day, int bhf_code) throws Exception;
	public List<HashMap> oneTileAvgTime(int day, int drw_code, int tile_crdnt_x, int tile_crdnt_y) throws Exception;
	public List<HashMap> oneTileGender(int day, int drw_code, int tile_crdnt_x, int tile_crdnt_y) throws Exception;
	public List<HashMap> oneTileAge(int day, int drw_code, int tile_crdnt_x, int tile_crdnt_y) throws Exception;
	public List<HashMap> tileTotal(int day, int drw_code, int tile_crdnt_x, int tile_crdnt_y)  throws Exception;
	public int tileTodayVisitCnt(int drw_code, int tile_crdnt_x, int tile_crdnt_y) throws Exception;
	
	public List<HashMap> categoryTypeMap(int bhf_code) throws Exception;
	public List<HashMap> zoneTypeMap(int bhf_code) throws Exception;

	public List<HashMap<String, String>> testTileColor(int bhf_code) throws Exception;
	public int realTimeVisitor(int bhf_code) throws Exception;
	public int realTimeVisitor2(int bhf_code) throws Exception;
	
	public void insertVirtualCustomerCourse(String user_id, int cours_stay_time, int tile_code) throws Exception;
	
	public void updateCustomCourse() throws Exception;

	public void insertVirtualTotal(Map map) throws Exception;
	public void updateVirtualTotal(Map map) throws Exception;
	public void deleteVirtualTotal(Map map) throws Exception;
}
