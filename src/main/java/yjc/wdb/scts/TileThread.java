package yjc.wdb.scts;

import java.util.HashMap;
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

		runThread(81);
		runThread(84);
		runThread(87);
		runThread(90);
		runThread(93);
		runThread(96);
		runThread(99);
		runThread(101);
		runThread(104);
		runThread(107);
		runThread(110);
		runThread(113);
		runThread(116);
		runThread(119);
		runThread(121);
		runThread(124);
		runThread(127);
		runThread(130);
		runThread(133);
		runThread(136);
		runThread(139);
		runThread(142);
		runThread(145);
		runThread(148);
		runThread(151);
		runThread(154);
		runThread(157);
		runThread(160);
		runThread(163);
		runThread(166);
		runThread(169);
		runThread(172);
		runThread(175);
		runThread(178);
		runThread(181);
		runThread(184);
		runThread(187);
		runThread(190);
		runThread(193);
		runThread(196);
		runThread(201);	

	}
	
	public void runThread(int a){
		for(int i= 0; i < 3; i++){
			

			int tile_code  = (int) (Math.random() * 6) + 1;
			String user_id = "user" + (a + i);

			int cnt = (int) (Math.random() * 1000) + 1;

			while(t){
				try {
					
					Thread.sleep(500);
					time = (int) (Math.random() * 100) + 1;
					
					
					if(cnt % 2 == 0){
						
					
						
						if(tile_code == 1){
							
							time = (int) (Math.random() * 1);
							dao.insertVirtualCustomerCourse(user_id, time, tile_code);
							break;
						}else{
							dao.insertVirtualCustomerCourse(user_id, time, tile_code);
						}


						if(tile_code > 3){					
							tile_code += 1;

							if(tile_code >= 6){
								tile_code = 3;
							}
						}else if(tile_code > 0 && tile_code <= 3){
							tile_code -= 1;

							if(tile_code <= 0){
								tile_code = 1;
							}
						}

						
					}else{
				
						

						if(tile_code == 4){
							
							time = (int) (Math.random() * 1);
							
							dao.insertVirtualCustomerCourse(user_id, time, tile_code);
							break;
						}else{
							dao.insertVirtualCustomerCourse(user_id, time, tile_code);
						}

						if(tile_code <= 3){					
							tile_code += 1;

							if(tile_code == 4){
								tile_code = 6;
							}
						}else if(tile_code >= 4 && tile_code <= 6){
							tile_code -= 1;

							if(tile_code <= 3){
								tile_code = 4;
							}
						}

					}
					



				} catch (Exception e) {
				
					e.printStackTrace();
				}


			}

		}
	}

	
}
