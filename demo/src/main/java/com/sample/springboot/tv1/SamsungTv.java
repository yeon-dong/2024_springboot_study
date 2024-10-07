package com.sample.springboot.tv1;

public class SamsungTv implements Tv {
	public SamsungTv() {
		System.out.println("====> Samsung TV");
	}
	@Override
	public void turnOn() {
		System.out.println("Samsung TV 전원 켜짐");
	}
	@Override
	public void turnOff() {
		System.out.println("Samsung TV 전원 꺼짐");
	}
	@Override
	public void soundUp() {
		System.out.println("Samsung TV 소리 켜짐");
	
	}
	@Override
	public void soundDown() {
		System.out.println("Samsung TV 소리 꺼짐");
		
	}
}
