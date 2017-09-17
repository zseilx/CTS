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
		// 가상 고객 시작 번호
		final int VIRTUAL = 80;
		// 쓰레드 돌리는 횟수 ( 쓰레드 1회전 시간 1초 )
		final int TOTALCNT = 999;
		// 한번에 고객 움직이는 최소 숫자
		final int MINMOVE = 10;
		// 한번에 고객 움직이는 최대 숫자
		final int MAXMOVE = 30;
		
		// 움직이는 고객 숫자
		int randomMove = 0 ;
		// 움직이는 방향
		/*	0 = 정지
		 *  1 = 왼쪽		2 = 오른쪽		3 = 위쪽		4 = 아래쪽
		 */
		int movePosition = 0;
		// 움직일 때 더해줄 값
		int plusValue = 0;
		
		// 고객 위치 정보
		short [] userPosition = new short [100];
		
		// DB에 넣을 때 사용할 MAP
		Map<String, Object> moveData = null;
		// 경로 정보 전체 저장할 리스트
		List<Map<String, String>> courseList = null;
		// 한 명의 경로 정보 저장할 맵
		Map<String, String> courseData = null;

		// 한 명의 경로 정보 저장할 맵
		moveData = new HashMap<String, Object>();
		// 경로 정보 전체 저장할 리스트
		courseList = new ArrayList<Map<String, String>>();
		
		// 고객 초기 위치 설정 
		for(int i=0; i<userPosition.length; i++) {
			// 유저 초기 위치 랜덤 생성
			userPosition[i] = (short) ((Math.random() * 10 % 6) + 1);

			// 한 명의 경로 정보 저장할 맵
			courseData = new HashMap<String, String>();
			
			// 아이디 및 경로 정보 저장
			courseData.put("user_id", "user" + (i + VIRTUAL));
			courseData.put("tile_code", userPosition[i] + "");
			
			// 경로 정보 리스트에 저장
			courseList.add(courseData);
			
			// 경로 리스트를 DB에 전달 하기 위해 다시 맵으로 포장
			moveData.put("courseList", courseList);
		}
		// DB에 가상 회원들의 경로 정보를 Insert
		try {
			dao.insertVirtualTotal(moveData);
			Thread.sleep(1000);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int loopCnt = 0;
		// 고객 실제 움직임
		while(loopCnt < TOTALCNT && t) {
			try {
				// 한번에 움직이는 고객 수 랜덤 생성
				randomMove = (int) ( Math.random() * (MAXMOVE-MINMOVE) ) + MINMOVE + 1;
				
				// 고객이 움직일 방향 랜덤 생성
				movePosition = (int) (Math.random() * 5);

				switch(movePosition) {
				// 스톱 아무 동작 안함
				case 0:	plusValue = 0;	break;
				// 왼쪽으로 이동
				case 1:	plusValue = -1;	break;
				// 오른쪽으로 이동
				case 2:	plusValue = 1;	break;
				// 위로 이동
				case 3:	plusValue = -3;	break;
				// 아래로 이동
				case 4:	plusValue = 3;	break;
				// 이상 동작, 아무 동작 안함
				default :	plusValue = 0;	break;
				}

				// 한 명의 경로 정보 저장할 맵
				moveData = new HashMap<String, Object>();
				// 경로 정보 전체 저장할 리스트
				courseList = new ArrayList<Map<String, String>>();
				
				for(int j=0; j<randomMove; j++) {
					// 움직이는 유저 번호
					// 랜덤 숫자만큼의 랜덤한 유저들을 움직이기 위해 유저 번호를 값을 저장
					int userNum = (int) (Math.random() * (100 / randomMove * j));
					
					// 가상 유저 위치 이동
					userPosition[userNum] += plusValue;
					
					if(userPosition[userNum] > 6) {
						userPosition[userNum] = (short) (userPosition[userNum] - 6);
					} else if(userPosition[userNum] < 1) {
						userPosition[userNum] = (short) (userPosition[userNum] + 6);
					}
					// 한 명의 경로 정보 저장할 맵
					courseData = new HashMap<String, String>();
					
					// 아이디 및 경로 정보 저장
					courseData.put("user_id", "user" + (userNum + VIRTUAL));
					courseData.put("tile_code", userPosition[userNum] + "");
					
					// 경로 정보 리스트에 저장
					courseList.add(courseData);
					
					// 경로 리스트를 DB에 전달 하기 위해 다시 맵으로 포장
					moveData.put("courseList", courseList);
				}
				try {
					// DB에 경로 이동하는 멤버들의 머문 시간을 Update
					dao.updateVirtualTotal(moveData);
				
					// DB에 경로 이동 한 멤버들의 경로 정보를 새로 Insert
					dao.insertVirtualTotal(moveData);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// 주기
				Thread.sleep(1000);
				loopCnt++;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} // while 반복 끝
		

		// 삭제 전 입력 데이터 저장
		// 한 명의 경로 정보 저장할 맵
		moveData = new HashMap<String, Object>();
		// 경로 정보 전체 저장할 리스트
		courseList = new ArrayList<Map<String, String>>();
		for(int i=0; i<userPosition.length; i++) {
			// 한 명의 경로 정보 저장할 맵
			courseData = new HashMap<String, String>();
			
			// 아이디 및 경로 정보 저장
			courseData.put("user_id", "user" + (i + VIRTUAL));
			
			// 경로 정보 리스트에 저장
			courseList.add(courseData);
			
			// 경로 리스트를 DB에 전달 하기 위해 다시 맵으로 포장
			moveData.put("courseList", courseList);
		}
		
		try {
			// 가상 고객 이동 데이터 전체 삭제
			dao.deleteVirtualTotal(moveData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}