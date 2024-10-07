package com.sample.springboot.tv1;

public class BeanContainer {
	public Object getBean(String id) {
		if(id.equals("lg")) {
			return new LgTv();
		} else if(id.equals("samsung")) {
			return new SamsungTv();
		}
		return null;
	}
}
