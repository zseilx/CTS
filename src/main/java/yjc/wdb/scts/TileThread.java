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

	String result = "success";
	
	boolean t = true;

	public boolean isT() {
		return t;
	}

	public void setT(boolean t) {
		this.t = t;
	}

	@Async
	public void myThread(){


	
		for(int i= 0; i < 50; i++){
			time = (int) (Math.random() * 100) + 1;

			tile_code  = (int) (Math.random() * 6) + 1;
			String user_id = "user" + (81 + i + 50);

			
			while(t){
				System.out.println(t);
				try {
					Thread.sleep(1000);

					if(tile_code == 1){
						System.out.println("제발 ㅠㅠㅠ");
						time = 0;
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


					result = "success";

				} catch (Exception e) {
					result = "failed";
					e.printStackTrace();
				}


			}

		}


	}

	@Async
	public void myThread2(boolean t){


		for(int i= 0; i < 50; i++){
			time = (int) (Math.random() * 100) + 1;

			tile_code  = (int) (Math.random() * 6) + 1;
			String user_id = "user" +  (i + 81);

			while(t){
				try {
					Thread.sleep(1000);



					if(tile_code == 4){
						System.out.println("제발 ㅠㅠㅠ");
						time = 0;
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




				} catch (Exception e) {

					// TODO Auto-generated catch block
					e.printStackTrace();
				}


			}




		}
	}
}
