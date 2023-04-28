package com.thrive.ui.test.categories;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.thrive.ui.core.BaseTestPage;

public class DeleteThisSynchTest extends BaseTestPage {
	
	static int sharedNum = 5;
	
	@Test
	public void thread1() {
		System.out.println("In Thread 1");
		int newNUm = newNum(5, 10);
		System.out.println("In Thread 1 :" + newNUm);
		sharedNum++;
		
	}
	
	@Test
	public void thread2() {
		System.out.println("In Thread 2");
		int newNUm = newNum(6, 11);
		System.out.println("In Thread 2 :" + newNUm);
		sharedNum++;
		
	}
	
	@Test
	public void thread3() {
		System.out.println("In Thread 3");
		int newNUm = newNum(6, 12);
		System.out.println("In Thread 3 :" + newNUm);
		sharedNum++;
		
	}
	
	@Test
	public void thread4() {
		System.out.println("In Thread 4");
		int newNUm = newNum(7, 13);
		System.out.println("In Thread 4 :" + newNUm);
		sharedNum++;
		
	}
	
	private synchronized int newNum(int num, int actual) {
		
		int newNum =  sharedNum + num;
		Assert.assertEquals(newNum, actual);
		return newNum;
		
	}

}
