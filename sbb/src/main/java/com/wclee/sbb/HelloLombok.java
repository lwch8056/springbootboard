package com.wclee.sbb;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
//import lombok.Setter;

@Getter
//@Setter
@RequiredArgsConstructor
public class HelloLombok {
	private final String hello; 
	private final int lombok; 
	
	public static void main(String[] args) {
//		HelloLombok helloLombok = new HelloLombok(); 
//		helloLombok.setHello("헬로");
//		helloLombok.setLombok(5);
		
		HelloLombok h = new HelloLombok("헬로", 555); 
		
		System.out.println(h.getHello() + "/" + h.getLombok());
	}
}
