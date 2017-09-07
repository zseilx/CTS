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


	
		for(int i= 0; i < 100; i++){
			

			tile_code  = (int) (Math.random() * 6) + 1;
			String user_id = "user" + (81 + i);

			cnt = (int) (Math.random() * 1000) + 1;
			
		
			while(t){
				try {
					
					Thread.sleep(1000);
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
