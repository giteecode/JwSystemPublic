package com.zxw;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zxw.jwxt.service.CollegeService;
import com.zxw.jwxt.vo.BaseQueryParam;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;


/**
 * @author zxw
 * @date 2019/11/10 16:05
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestController {

    @Autowired
    private CollegeService collegeService;

    @Test
    public void query(){
        IPage iPage = collegeService.pageQuery(new BaseQueryParam());
        System.out.println(iPage.toString());
    }
    @Test
    public void memory() {
        System.out.println(Runtime.getRuntime().totalMemory() / (1024 * 1024));
        System.out.println(Runtime.getRuntime().freeMemory() / (1024 * 1024));
        System.out.println(Runtime.getRuntime().maxMemory() / (1024 * 1024));
        System.out.println((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024));


        MemoryMXBean mem = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemoryUsage = mem.getHeapMemoryUsage();
        long init = heapMemoryUsage.getInit() / 1024 / 1024;//初始的总内存
        long max = heapMemoryUsage.getMax() / 1024 / 1024; //最大可用内存
        long used = heapMemoryUsage.getUsed() / 1024 / 1024;//已使用的内存
        long l = heapMemoryUsage.getCommitted() / 1024 / 1024;
        System.out.println("max:" + max);
        System.out.println("used:" + used);
        System.out.println("init:" + init);
        System.out.println("commit:" + l);
        //totalMemory( ) 返回Java虚拟机中的总内存。这个方法返回的值可能随时间而变化，这取决于宿主操作系统环境和JVM的内存占用情况。 需要注意的是， Note that the amount of memory required to hold an object of any given type may be implementation-dependent 。不同依赖实现的虚拟机需要的能Hold住任何类型对象所需的内存大小都不太一样。因为这取决于对象在JVM运行时是如何建立在内存中的，不同的虚拟机实现都不太一样，拿最常用的HotSpot来说，一个对象包括：对象头（Header）、实例数据（Instance Data）、对齐填充（Padding），而且在JAVA中需要检测对象占用的内存大小，不像C中那么简单sizeof( ) 就完事了。
        long vmFree = 0;
        long vmUse = 0;
        long vmTotal = 0;
        long vmMax = 0;
        int byteToMb = 1024 * 1024;
        Runtime rt = Runtime.getRuntime();
        vmTotal = rt.totalMemory() / byteToMb;
        vmFree = rt.freeMemory() / byteToMb;
        vmMax = rt.maxMemory() / byteToMb;
        vmUse = vmTotal - vmFree;
        System.out.println("JVM总内存空间为：" + vmTotal + " MB");
        System.out.println("JVM内存的空闲空间为：" + vmFree + " MB");
        System.out.println("JVM总内存空间为：" + vmMax + " MB");
        System.out.println("JVM内存已用的空间为：" + vmUse + " MB");
    }
}
