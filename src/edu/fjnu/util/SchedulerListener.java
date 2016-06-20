package edu.fjnu.util;


import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 程序启动监听器，用于启动时开启定时器
 * @author zhangzhiyong
 *
 */
public class SchedulerListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
        System.out.println("服务开启时间："+new Date());
        Scheduler scheduler = new Scheduler();
        scheduler.sendScheduler("2016-06-30 01:30:00");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("contextDestroyed");
	}

}
