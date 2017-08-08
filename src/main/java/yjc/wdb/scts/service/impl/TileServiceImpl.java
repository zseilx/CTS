package yjc.wdb.scts.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import yjc.wdb.scts.bean.GoodsVO;
import yjc.wdb.scts.bean.TileVO;
import yjc.wdb.scts.dao.BeaconDAO;
import yjc.wdb.scts.dao.TileDAO;
import yjc.wdb.scts.service.TileService;

@Service
public class TileServiceImpl implements TileService {

	@Inject
	private TileDAO tiledao;
	
	@Inject
	private BeaconDAO beacondao;
	
	@Override
	public List<HashMap<String, String>> selectTileList() throws Exception {
		// TODO Auto-generated method stub
		return tiledao.selectTileList();
	}

	@Override
	public void insertTile(TileVO vo) throws Exception {
		// TODO Auto-generated method stub
		tiledao.insertTile(vo);
	}

	@Override
	public HashMap<String, String> selectTile_LocationOne(TileVO vo) throws Exception {
		// TODO Auto-generated method stub
		return tiledao.selectTile_LocationOne(vo);
	}

	@Override
	@Transactional
	public void updateTileBeaconSet(HashMap<String, String> vo) throws Exception {
		// TODO Auto-generated method stub
		tiledao.updateTileBeaconSet(vo);
		
		vo.remove("tile_code");
		vo.put("beacon_sttus", "USE");
		
		beacondao.updateBeaconSttus(vo);
	}

	@Override
	public List<HashMap<String, String>> selectTileListUp(Map map) throws Exception {
		// TODO Auto-generated method stub
		return tiledao.selectTileListUp(map);
	}

	@Override
	@Transactional
	public JSONObject tile_goods(int drw_code, int tile_crdnt_x, int tile_crdnt_y) throws Exception {
		
		JSONObject json = new JSONObject();
	
		String[] user_group = {"10/m/yes", "10/m/no", "10/w/no", "10/w/yes", "20/m/yes", "20/m/no", "20/w/no", "20/w/yes", "30/m/yes", "30/m/no", "30/w/no", "30/w/yes", "40/m/yes", "40/m/no", "40/w/no", "40/w/yes", "50/m/yes", "50/m/no", "50/w/no", "50/w/yes"};
		
		
		
		List<HashMap> avgList = tiledao.avgStayTime(drw_code, tile_crdnt_x, tile_crdnt_y);
		List<HashMap<String, String>> avg = new ArrayList<HashMap<String, String>>();
		
		int count = 0;

		for(int i = 0; i < user_group.length; i++){
			count = 0;

			for(int j = 0; j < avgList.size(); j++){
				
				if(user_group[i].equals(avgList.get(j).get("user_group").toString())){
	
					HashMap map = new HashMap();
					map.put("user_group", avgList.get(j).get("user_group"));
					map.put("avgStayTime", avgList.get(j).get("avgStayTime"));
					count++;
					avg.add(map);
				}
				
			}
			
			if(count == 0){
				HashMap map = new HashMap();
				map.put("user_group", user_group[i]);
				map.put("avgStayTime", "0");
				
				avg.add(map);
		
			}
		}
		

		List<HashMap> goodsList = tiledao.tile_goods(drw_code, tile_crdnt_x, tile_crdnt_y);
		List<HashMap<String, String>> goods = new ArrayList<HashMap<String, String>>();
		
		for(int i = 0; i < user_group.length; i++){
			count = 0;

			for(int j = 0; j < goodsList.size(); j++){
				
				if(user_group[i].equals(goodsList.get(j).get("user_group").toString())){
	
					HashMap map = new HashMap();
					map.put("user_group", goodsList.get(j).get("user_group"));
					map.put("totalPrice", goodsList.get(j).get("totalPrice"));
					count++;
					goods.add(map);
				}
				
			}
			
			if(count == 0){
				HashMap map = new HashMap();
				map.put("user_group", user_group[i]);
				map.put("totalPrice", "0");
				
				goods.add(map);
		
			}
		}

		json.put("tile_goods", goods);
		json.put("avgStay", avg);

		return json;
	}

	@Override
	public List<GoodsVO> goods_locationList(int tile_code) throws Exception {
		
		return tiledao.goods_locationList(tile_code);
	}
	
	@Override
	@Transactional
	public void insertDetail_category_location(Map map) throws Exception {

		if(map.get("tileList") != null) {
			tiledao.deleteLo(Integer.parseInt(map.get("drw_code").toString()));
			//tiledao.deleteForRegister_position(map);
			tiledao.insertDetail_category_location(map);
	
		}
		
	}

	@Override
	@Transactional
	public void insertGoods_location(Map map) throws Exception {
		
		tiledao.deleteGoodsLo(Integer.parseInt(map.get("drw_code").toString()));
		if(map.get("goodsList") != null) {
			tiledao.insertGoods_location(map);
		}
		
	}

}
