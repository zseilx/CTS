package yjc.wdb.scts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import javax.inject.Inject;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import yjc.wdb.scts.dao.CourseDAO;
import yjc.wdb.scts.dao.impl.CourseDAOImpl;

@Service
public class TileThread{

	@Inject
	private CourseDAO dao;

	int tile_code = 0;
	String cours_pasng_time = null;

	int time = 0;
	
	int cnt = 0;

	
	boolean t = true;

	public boolean isT() {
		return t;
	}

	public void setT(boolean t) {
		this.t = t;
	}

	@Async
	public void myThread(){
		totalRunThread();
	}
	
	public void totalRunThread() {
		// ���� �� ���� ��ȣ
		final int VIRTUAL = 80;
		// ������ ������ Ƚ�� ( ������ 1ȸ�� �ð� 1�� )
		final int TOTALCNT = 999;
		// �ѹ��� �� �����̴� �ּ� ����
		final int MINMOVE = 10;
		// �ѹ��� �� �����̴� �ִ� ����
		final int MAXMOVE = 30;
		
		// �����̴� �� ����
		int randomMove = 0 ;
		// �����̴� ����
		/*	0 = ����
		 *  1 = ����		2 = ������		3 = ����		4 = �Ʒ���
		 */
		int movePosition = 0;
		// ������ �� ������ ��
		int plusValue = 0;
		
		// �� ��ġ ����
		short [] userPosition = new short [100];
		
		// DB�� ���� �� ����� MAP
		Map<String, Object> moveData = null;
		// ��� ���� ��ü ������ ����Ʈ
		List<Map<String, String>> courseList = null;
		// �� ���� ��� ���� ������ ��
		Map<String, String> courseData = null;

		// �� ���� ��� ���� ������ ��
		moveData = new HashMap<String, Object>();
		// ��� ���� ��ü ������ ����Ʈ
		courseList = new ArrayList<Map<String, String>>();
		
		// �� �ʱ� ��ġ ���� 
		for(int i=0; i<userPosition.length; i++) {
			// ���� �ʱ� ��ġ ���� ����
			userPosition[i] = (short) ((Math.random() * 10 % 6) + 1);

			// �� ���� ��� ���� ������ ��
			courseData = new HashMap<String, String>();
			
			// ���̵� �� ��� ���� ����
			courseData.put("user_id", "user" + (i + VIRTUAL));
			courseData.put("tile_code", userPosition[i] + "");
			
			// ��� ���� ����Ʈ�� ����
			courseList.add(courseData);
			
			// ��� ����Ʈ�� DB�� ���� �ϱ� ���� �ٽ� ������ ����
			moveData.put("courseList", courseList);
		}
		// DB�� ���� ȸ������ ��� ������ Insert
		try {
			dao.insertVirtualTotal(moveData);
			Thread.sleep(1000);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int loopCnt = 0;
		// �� ���� ������
		while(loopCnt < TOTALCNT && t) {
			try {
				// �ѹ��� �����̴� �� �� ���� ����
				randomMove = (int) ( Math.random() * (MAXMOVE-MINMOVE) ) + MINMOVE + 1;
				
				// ���� ������ ���� ���� ����
				movePosition = (int) (Math.random() * 5);

				switch(movePosition) {
				// ���� �ƹ� ���� ����
				case 0:	plusValue = 0;	break;
				// �������� �̵�
				case 1:	plusValue = -1;	break;
				// ���������� �̵�
				case 2:	plusValue = 1;	break;
				// ���� �̵�
				case 3:	plusValue = -3;	break;
				// �Ʒ��� �̵�
				case 4:	plusValue = 3;	break;
				// �̻� ����, �ƹ� ���� ����
				default :	plusValue = 0;	break;
				}

				// �� ���� ��� ���� ������ ��
				moveData = new HashMap<String, Object>();
				// ��� ���� ��ü ������ ����Ʈ
				courseList = new ArrayList<Map<String, String>>();
				
				for(int j=0; j<randomMove; j++) {
					// �����̴� ���� ��ȣ
					// ���� ���ڸ�ŭ�� ������ �������� �����̱� ���� ���� ��ȣ�� ���� ����
					int userNum = (int) (Math.random() * (100 / randomMove * j));
					
					// ���� ���� ��ġ �̵�
					userPosition[userNum] += plusValue;
					
					if(userPosition[userNum] > 6) {
						userPosition[userNum] = (short) (userPosition[userNum] - 6);
					} else if(userPosition[userNum] < 1) {
						userPosition[userNum] = (short) (userPosition[userNum] + 6);
					}
					// �� ���� ��� ���� ������ ��
					courseData = new HashMap<String, String>();
					
					// ���̵� �� ��� ���� ����
					courseData.put("user_id", "user" + (userNum + VIRTUAL));
					courseData.put("tile_code", userPosition[userNum] + "");
					
					// ��� ���� ����Ʈ�� ����
					courseList.add(courseData);
					
					// ��� ����Ʈ�� DB�� ���� �ϱ� ���� �ٽ� ������ ����
					moveData.put("courseList", courseList);
				}
				try {
					// DB�� ��� �̵��ϴ� ������� �ӹ� �ð��� Update
					dao.updateVirtualTotal(moveData);
				
					// DB�� ��� �̵� �� ������� ��� ������ ���� Insert
					dao.insertVirtualTotal(moveData);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// �ֱ�
				Thread.sleep(1000);
				loopCnt++;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} // while �ݺ� ��
		

		// ���� �� �Է� ������ ����
		// �� ���� ��� ���� ������ ��
		moveData = new HashMap<String, Object>();
		// ��� ���� ��ü ������ ����Ʈ
		courseList = new ArrayList<Map<String, String>>();
		for(int i=0; i<userPosition.length; i++) {
			// �� ���� ��� ���� ������ ��
			courseData = new HashMap<String, String>();
			
			// ���̵� �� ��� ���� ����
			courseData.put("user_id", "user" + (i + VIRTUAL));
			
			// ��� ���� ����Ʈ�� ����
			courseList.add(courseData);
			
			// ��� ����Ʈ�� DB�� ���� �ϱ� ���� �ٽ� ������ ����
			moveData.put("courseList", courseList);
		}
		
		try {
			// ���� �� �̵� ������ ��ü ����
			dao.deleteVirtualTotal(moveData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}