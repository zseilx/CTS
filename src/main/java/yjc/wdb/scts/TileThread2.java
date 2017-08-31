package yjc.wdb.scts;

import java.util.HashMap;

import javax.inject.Inject;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import yjc.wdb.scts.dao.CourseDAO;


public class TileThread2 extends Thread{
	
	@Inject
	private CourseDAO dao;
	int tile_code = 0;
	String cours_pasng_time = null;
	boolean right = false;
	int time = 0;
	
	@Override
	public void run() {
		for(int i= 0; i < 50; i++){
			time = (int) (Math.random() * 100) + 1;
			cours_pasng_time = time + "";
			tile_code  = (int) (Math.random() * 6) + 1;
			String user_id = "user" + (81 + i + 50);
			
			while(true){
				try {
					Thread.sleep(1000);
					
					JSONObject json = new JSONObject();
					json.put("user_id", user_id);
					json.put("tile_code", tile_code+"");
					json.put("cours_pasng_time", cours_pasng_time);
					System.out.println(json);
					//dao.insertVirtualCustomerCourse(json);
					
					if(tile_code > 3){					
						tile_code += 1;
						
						if(tile_code == 6){
							tile_code = 3;
						}
					}else if(tile_code <= 3){
						tile_code -= 1;
					}
					

		
					if(tile_code == 1){
						System.out.println("Á¦¹ß ¤Ð¤Ð¤Ð"+json.toString());
						break;
					}
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			if(i == 100){
				right = true;
			}
			
		}
			
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

}
