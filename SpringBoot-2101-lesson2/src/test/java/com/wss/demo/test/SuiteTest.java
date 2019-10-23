package com.wss.demo.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

// 设置测试运行器，并且指定多个测试类
@RunWith(Suite.class)
@SuiteClasses({FirstTest.class, SecondTest.class, ThirdTest.class})
public class SuiteTest {

}