package yjc.wdb.scts.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import yjc.wdb.scts.dao.Branch_officeDAO;
import yjc.wdb.scts.dao.CouponDAO;
import yjc.wdb.scts.service.Branch_officeService;

@Service
public class Branch_officeServiceImpl implements Branch_officeService {
	
	@Inject
	private Branch_officeDAO dao;

	@Override
	public List<HashMap<String, String>> selectBranchNameList() throws Exception {
		// TODO Auto-generated method stub
		return dao.selectBranchNameList();
	}

}
