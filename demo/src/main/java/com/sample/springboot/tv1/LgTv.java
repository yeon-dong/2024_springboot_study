package com.sample.springboot.tv1;

public class LgTv implements Tv {
	public LgTv() {
		System.out.println("====> LG TV");
	}

	
	@Override
	public void turnOn() {
		System.out.println("LG TV 전원 켜짐");
		
	}
	@Override
	public void turnOff() {
		System.out.println("LG TV 전원 꺼짐");		
	}
	@Override
	public void soundUp() {
		System.out.println("LG TV 소리 켜짐");		
	}
	@Override
	public void soundDown() {
		System.out.println("LG TV 소리 꺼짐");		
	}
}
