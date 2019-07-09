package com.itany.netClass.filters;

import com.itany.netClass.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.HashMap;
import java.util.Map;

/**
 * 项目应用监听器
 * @author teacher
 * @date 2018-8-22
 */
@WebListener
public class ApplicationLisenter implements ServletContextListener {

	public static ServletContext application;

	@Override
	public void contextDestroyed(ServletContextEvent context) {
	}

	@Override
	public void contextInitialized(ServletContextEvent context) {
		application = context.getServletContext();
		Map<String, User> usersMap = new HashMap<String, User>();
		application.setAttribute("users", usersMap);
	}

	public static Map<String, User> getUsers(){
		Map<String, User> usersMap = (Map<String, User>) ApplicationLisenter.application.getAttribute("users");
		return usersMap;
	}

}
