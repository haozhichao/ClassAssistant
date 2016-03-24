package com.its.db.test;

import com.its.util.UuidUtils;
import org.junit.Test;

/**
 * Created by Administrator on 2016/3/22.
 */
public class ClassLoaderTest {
    @Test
    public void test(){
        ClassLoader classLoader  = Thread.currentThread().getContextClassLoader();
        System.out.println(classLoader+">>>>>>>>>>>>>>>>>>>>");
        System.out.println(classLoader.getParent()+">>>>>>>>>>>>>>>>>");
        System.out.println(classLoader.getParent().getParent()+">>>>>>>>>>>>>>>");

    }
    @Test
    public void test1(){
        System.out.println(UuidUtils.base58Uuid());
        System.out.println(UuidUtils.base64Uuid());

    }
}
