package yjc.wdb.scts.dao;

import java.util.HashMap;
import java.util.List;

public interface Branch_officeDAO {
	public List<HashMap<String, String>> selectBranchNameList() throws Exception;
}
