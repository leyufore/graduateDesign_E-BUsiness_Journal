package com.leyufore.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class StartupListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent contextEvent) {
		System.out.println("leyufore destroy 应用销毁");
	}

	@Override
	public void contextInitialized(ServletContextEvent contextEvent) {
		System.out.println("leyufore initialize 应用启动");
	 
	}

}
