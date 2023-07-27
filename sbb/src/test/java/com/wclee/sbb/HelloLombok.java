package com.wclee.sbb;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
//@Setter
@Getter
public class HelloLombok {
	private final String hello; 
	private final int lombok; 
	
	public static void main(String[] args) {
//		HelloLombok hello = new HelloLombok(); 
//		hello.setHello("헬로");
//		hello.setLombok(5);
		HelloLombok hello = new HelloLombok("gpffh", 5);
		System.out.println(hello.getHello());
		System.out.println(hello.getLombok());
		
	}
}
