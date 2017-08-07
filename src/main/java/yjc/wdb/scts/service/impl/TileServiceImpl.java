package yjc.wdb.scts.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		
		System.out.println("여기옴 11111");
		
		String[] user_group = {"10/m/yes", "10/m/no", "10/w/no", "10/w/yes", "20/m/yes", "20/m/no", "20/w/no", "20/w/yes", "30/m/yes", "30/m/no", "30/w/no", "30/w/yes", "40/m/yes", "40/m/no", "40/w/no", "40/w/yes", "50/m/yes", "50/m/no", "50/w/no", "50/w/yes"};
		
		System.out.println(user_group.length);
		
		/*List<HashMap> avgList = tiledao.avgStayTime(drw_code, tile_crdnt_x, tile_crdnt_y);
		List<HashMap<String, String>> avg = new ArrayList<HashMap<String, String>>();
		
		int count = 0;
		for(int i = 0; i < user_group.length; i++){
			System.out.println("여기옴 33333");
			for(int j = 0; j < avgList.size(); j++){
				
				if(user_group[i].equals(avgList.get(i).get("user_group").toString())){
					HashMap map = new HashMap();
					map.put("user_group", avgList.get(i).get("user_group"));
					map.put("avgStayTime", avgList.get(i).get("avgStayTime"));

					avg.add(map);
				}else{
					HashMap map = new HashMap();
					map.put("user_group", user_group[i]);
					map.put("avgStayTime", "0");
					avgList.add(map);
					count++;
				}
				
				if(count == 1){
					break;
				}
			}
		}
		
		System.out.println("와아아아아  "+avg.toString());
		
		List<HashMap> goodsList = tiledao.tile_goods(drw_code, tile_crdnt_x, tile_crdnt_y);
		*/
		return json;
	}

}
