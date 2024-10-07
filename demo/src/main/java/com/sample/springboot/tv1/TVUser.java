package com.sample.springboot.tv1;

public class TVUser {

	public static void main(String[] args) {
		BeanContainer container = new BeanContainer();
		Tv tv1 = (Tv) container.getBean(args[0]);
		
//		LgTv TV = new LgTv();
//		TV.turnOn();
//		TV.soundUp();
//		TV.soundDown();
//		TV.turnOff();
//		SamsungTv tv = new SamsungTv();
//		tv.turnOn();
//		tv.soundUp();
//		tv.soundDown();
//		tv.turnOff();
	}

}
