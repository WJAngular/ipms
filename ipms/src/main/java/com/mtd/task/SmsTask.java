package com.mtd.task;


import org.springframework.stereotype.Component;

/**
 * 基于注解的定时器
 * @author hj
 */
@Component
public class SmsTask {

	public void show(){
		System.out.println("Annotation：is show run");
	}
}