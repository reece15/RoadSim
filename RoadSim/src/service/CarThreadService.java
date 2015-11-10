package service;

import dto.SimDto;

/**
 * @filename CarThreadService.java
 * @author Reece
 * @description  
 * @date 2015年10月16日上午1:29:02
 * @version 
 */
public class CarThreadService extends SimService implements Runnable{

	public CarThreadService(SimDto dto) {
		this.dto = dto;
	}

	@Override
	public void run() {
		while(!this.getDto().isFlagOver()){
			carRun();
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
