package com.wss.demo.test;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class BasicTest {
    // 第0个测试方法
    @Test
    public void test0() throws Exception {
        System.out.println("testing0...");
    }

    // 第1个测试方法
    @Test
    public void test1() {
        System.out.println("testing1...");
    }

    // 超时测试，如果超过2000毫秒没有执行完，认为执行失败
    @Test(timeout=2000)
    public void testTimeout() throws InterruptedException {
        // 休眠1秒，测试结果为成功，如果将其修改为3秒，则测试结果为失败
        TimeUnit.SECONDS.sleep(1);
        System.out.println("testTimeout...");
    }

    // 异常测试，指定期望出现的异常
    @Test(expected=NullPointerException.class)
    public void testException() {
        System.out.println("testException...");
        throw new NullPointerException();
    }

    // 忽略被注解的测试方法
    @Ignore
    public void ignore() {
        System.out.println("ignore...");
    }

    // 在执行每一个测试方法之前都要执行该方法
    @Before
    public void setUp() {
        System.out.println("before...");
    }

    // 在执行每一个测试方法之后都要执行该方法
    @After
    public void tearDown() {
        System.out.println("after...");
    }

    // 在执行所有测试方法之前执行该方法
    @BeforeClass
    public static void beforeClass() {
        System.out.println("@BeforeClass...");
    }

    // 在执行所有测试方法之后执行该方法
    @AfterClass
    public static void afterClass() {
        System.out.println("@AfterClass...");
    }
}