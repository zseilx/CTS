package yjc.wdb.scts.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import yjc.wdb.scts.bean.PageVO;
import yjc.wdb.scts.bean.StockVO;
import yjc.wdb.scts.dao.StockDAO;

@Repository
public class StockDAOImpl implements StockDAO {
	
	public static final String NAMESPACE = "yjc.wdb.mapper.StockMapper";
	@Inject
	private SqlSession sql;
	
	@Override
	public List<StockVO> selectStockList(PageVO cri) throws Exception {
		return sql.selectList(NAMESPACE+".selectStockList", cri);
	}

	@Override
	public List<HashMap> deleteStockList(String user_id, int goods_code) throws Exception {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		map.put("user_id", user_id);
		map.put("goods_code", goods_code);
		sql.delete(NAMESPACE+".deleteStock", map);
		return null;
	}

	@Override
	public List<StockVO> searchStockList(PageVO cri) throws Exception {
		return sql.selectList(NAMESPACE + ".searchStockList",cri);
	}

	@Override
	public int countSearch(PageVO cri) throws Exception {
		return sql.selectOne(NAMESPACE+".countSearch", cri);
	}

/*	@Override
	public List<HashMap> searchStockList(String goods_nm, int lclasctgry_code, int start_amount, int end_amount, int check) throws Exception {
		Map map = new HashMap();
		map.put("goods_nm", goods_nm);
		map.put("lclasctgry_code", lclasctgry_code);
		map.put("start_amount", start_amount);
		map.put("end_amount", end_amount);
		map.put("check", check);
		return sql.selectList(NAMESPACE+".searchStock", map);
	}*/

}
