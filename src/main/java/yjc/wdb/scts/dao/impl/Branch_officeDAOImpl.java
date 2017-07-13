package yjc.wdb.scts.dao.impl;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import yjc.wdb.scts.dao.Branch_officeDAO;

@Repository
public class Branch_officeDAOImpl implements Branch_officeDAO {
	
	private static final String NAMESPACE = "yjc.wdb.mapper.Branch_officeMapper";
	
	@Inject
	private SqlSession sql;

	@Override
	public List<HashMap<String, String>> selectBranchNameList() throws Exception {
		// TODO Auto-generated method stub
		return sql.selectList(NAMESPACE + ".selectBranchNameList");
	}

}
