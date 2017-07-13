package yjc.wdb.scts.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import yjc.wdb.scts.bean.PageVO;
import yjc.wdb.scts.bean.StockVO;
import yjc.wdb.scts.dao.StockDAO;
import yjc.wdb.scts.service.StockService;

@Service
public class StockServiceImpl implements StockService {
	
	@Inject
	private StockDAO dao;
	
	@Override
	public List<StockVO> selectStockList(PageVO cri) throws Exception {
		return dao.selectStockList(cri);
	}

	@Override
	public List<HashMap> deleteStockList(String user_id, int goods_code) throws Exception {
		return dao.deleteStockList(user_id, goods_code);
	}

	@Override
	public List<StockVO> searchStockList(PageVO cri) throws Exception {
		return dao.searchStockList(cri);
	}

	@Override
	public int countSearch(PageVO cri) throws Exception {
		return dao.countSearch(cri);
	}

}
