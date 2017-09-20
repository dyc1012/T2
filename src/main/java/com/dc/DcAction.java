package com.dc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import redis.clients.jedis.Jedis;

public class DcAction extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public DcAction()
	{
		super(); 
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.println("369123123123------->" + Math.random());
		pw.println("<hr/>");
		pw.println("Served at: " + request.getContextPath());
		pw.println("<hr/>");

		// 连接本地的 Redis 服务
		//Jedis jedis = new Jedis("192.168.20.136", 16379);
		Jedis jedis = new Jedis("172.17.0.11", 6379);
		//jedis.auth("1234");
		pw.println("连接成功xxdddddf");
		// 查看服务是否运行
		pw.println("服务正在运行: " + jedis.ping());
		pw.println("<hr/>");

		// 设置 redis 字符串数据
		jedis.set("runoobkey", "www.runoob.com");
		// 获取存储的数据并输出
		pw.println("redis 存储的字符串为: " + jedis.get("runoobkey"));
		pw.println("<hr/>");

		// 存储数据到列表中
		jedis.lpush("site-list", "Runoob");
		jedis.lpush("site-list", "Google");
		jedis.lpush("site-list", "Taobao");
		// 获取存储的数据并输出
		List<String> list = jedis.lrange("site-list", 0, 2);
		for (int i = 0; i < list.size(); i++)
		{
			pw.println("列表项为: " + list.get(i));
		}
		pw.println("<hr/>");

		// 获取数据并输出
		Set<String> keys = jedis.keys("*");
		Iterator<String> it = keys.iterator();
		while (it.hasNext())
		{
			String key = it.next();
			pw.println(key);
		}
		pw.println("<hr/>");

		pw.flush();
		jedis.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

}
