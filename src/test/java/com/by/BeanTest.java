package com.by;

import com.sun.deploy.util.StringUtils;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import com.by.bean.People;

public class BeanTest {

    @Test
    public void testBean() {
        InvocationHandler handler = (proxy, method, args) -> {
            // 添加排除方法
            if ("toString".equals(method.getName())) return this.toString();
            Map<String, String> hashMap = new HashMap<>();
            hashMap.put("1", "a");
            hashMap.put("2", "b");
            if (args != null) {
                return "你被代理了 " + method.getName() + "：" + hashMap.get(args[0].toString());
            }
            return "你被代理了 " + method.getName();
        };
        People proxy = (People) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{People.class}, handler);
        System.out.println(proxy.work());
        System.out.println(proxy.work("1"));
    }
}
