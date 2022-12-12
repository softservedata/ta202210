package com.softserve.edu.testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class FirstTest {
    
    @BeforeSuite
    public void beforeSuite() {
        System.out.println("@BeforeSuite FirstTest");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("@AfterSuite FirstTest");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("\t@BeforeTest FirstTest");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("\t@AfterTest FirstTest");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("\t\t@BeforeClass FirstTest");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        System.out.println("\t\t@AfterClass FirstTest");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("\t\t\t@BeforeMethod FirstTest, ThreadId = " + Thread.currentThread().getId());
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("\t\t\t@AfterMethod FirstTest");
        //throw new RuntimeException("Hi");
    }

    @Test
    public void f1() {
        System.out.println("\t\t\t\t@Test f1() FirstTest, ThreadId = " + Thread.currentThread().getId());
    }

    @Test
    public void f2() {
        System.out.println("\t\t\t\t@Test f2() FirstTest, ThreadId = " + Thread.currentThread().getId());
    }

    @Test
    public void f3() {
        System.out.println("\t\t\t\t@Test f3() FirstTest, ThreadId = " + Thread.currentThread().getId());
    }
}
