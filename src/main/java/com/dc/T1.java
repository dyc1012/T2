package com.dc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import redis.clients.jedis.Jedis;

public class T1
{

	public static void main11(String[] args)
	{
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "text/html");
		//headers.add("Accept", "text/xml");
		
		org.springframework.http.HttpEntity<Object> entity = new org.springframework.http.HttpEntity<Object>(headers);

		ResponseEntity<String> re = restTemplate.exchange("http://localhost:8080/z2/user/12x123", HttpMethod.GET,
				entity, String.class);

		System.out.println(re.getBody());
		System.out.println(re.getStatusCode());
		System.out.println(re.getHeaders());
	}

	public static void main6(String[] args)
	{
		// System.out.println(new
		// RestTemplate().getForObject("http://localhost:8080/z2/user/12x123",
		// String.class));

		ResponseEntity<String> re = new RestTemplate().getForEntity("http://localhost:8080/z2/user/12x123",
				String.class);

		System.out.println(re.getBody());
		System.out.println(re.getStatusCode());
		System.out.println(re.getHeaders());
	}

	public static void main3(String[] args) throws Exception
	{
		CloseableHttpClient hc = HttpClients.createDefault();
		HttpGet get = new HttpGet("http://localhost:8080/z2/user/12");
		// get.setHeader("Accept", "application/json");
		HttpResponse resp = hc.execute(get);
		HttpEntity he = resp.getEntity();

		if (he != null)
		{
			System.out.println(EntityUtils.toString(he, "utf-8"));
		}
	}

	public static void main4(String[] args) throws Exception
	{
		URL url = new URL("http://localhost:8080/z2/user/12");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// conn.setRequestProperty("Accept", "application/json");
		conn.connect();

		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String s = br.readLine();
		while (s != null)
		{
			System.out.println(s);
			s = br.readLine();
		}

		conn.disconnect();

	}

	public static void main(String[] args)
	{
		// 连接本地的 Redis 服务
		// Jedis jedis = new Jedis("192.168.20.136");xxxxx
		// Jedis jedis = new Jedis("192.168.20.136", 16379);
		Jedis jedis = new Jedis("192.168.20.136", 16379);
		// Jedis jedis = new Jedis("172.17.0.11", 6379);

		// jedis.auth("1234");
		System.out.println("连接成功");
		// 查看服务是否运行
		System.out.println("服务正在运行: " + jedis.ping());

		// 设置 redis 字符串数据
		jedis.set("runoobkey", "www.runoob.com");
		// 获取存储的数据并输出
		System.out.println("redis 存储的字符串为: " + jedis.get("runoobkey"));

		// 存储数据到列表中
		jedis.lpush("site-list", "Runoob");
		jedis.lpush("site-list", "Google");
		jedis.lpush("site-list", "Taobao");
		// 获取存储的数据并输出
		List<String> list = jedis.lrange("site-list", 0, 2);
		for (int i = 0; i < list.size(); i++)
		{
			System.out.println("列表项为: " + list.get(i));
		}

		// 获取数据并输出
		Set<String> keys = jedis.keys("*");
		Iterator<String> it = keys.iterator();
		while (it.hasNext())
		{
			String key = it.next();
			System.out.println(key);
		}

		jedis.close();
	}

	public static void main1(String[] args)
	{
		T2 x1 = new T2(10000);
		T2 x2 = new T2(20000);
		T2 x3 = new T2(50000);

		x1.start();
		x2.start();
		x3.start();
	}

	static class T2 extends Thread
	{
		int x = 1000;
		double y = 0;
		int z = 1;

		public T2(int x)
		{
			this.x = x;
		}

		public void run()
		{
			while (true)
			{
				try
				{
					for (int i = 0; i < 10000000; i++)
					{
						y = Math.random();

						String s = "";
						s = s + y;
					}

					if (y > 0)
					{
						z = 123;
					}

					Thread.sleep(x);
				}
				catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
