package service;

import dto.SimDto;

/**
 * @filename LightThreadService.java
 * @author Reece
 * @description  
 * @date 2015年10月16日上午1:28:02
 * @version 
 */
public class LightThreadService extends SimService implements Runnable{

	public LightThreadService(SimDto dto) {
		this.dto = dto;
	}

	@Override
	public void run() {
		while(!super.getDto().isFlagOver()){
			lightRun();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
