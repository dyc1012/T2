package com.dc;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import redis.clients.jedis.Jedis;

@Controller
public class UserController
{
	@RequestMapping(value = "getAllUsers", method = RequestMethod.GET)
	@ResponseBody
	public void getAllUsers(HttpServletRequest request, HttpServletResponse response)
	{
		response.setContentType("text/json; charset=UTF-8");

		JSONObject result = new JSONObject();
		JSONArray arr = new JSONArray();

		for (int i = 0; i < 10; i++)
		{
			JSONObject obj = new JSONObject();

			obj.put("id", "id_" + i);
			obj.put("name", "name_" + i);
			obj.put("age", "age_" + i);

			arr.add(obj);
		}

		result.put("total", Integer.valueOf(10));
		result.put("rows", arr);

		try
		{
			response.getOutputStream().write(result.toString().getBytes("UTF-8"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, headers =
	{ "Accept=text/xml, application/json" })
	public @ResponseBody String getUser(@PathVariable String id)
	{
		Jedis jedis = new Jedis("192.168.20.136", 16379);

		// 设置 redis 字符串数据
		String s = jedis.get(id);
		jedis.close();

		return s + "xx";
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public String getUser(@PathVariable String id, Model model)
	{
		Jedis jedis = new Jedis("192.168.20.136", 16379);

		// 设置 redis 字符串数据
		String s = jedis.get(id);
		jedis.close();

		model.addAttribute("user", "user-->" + s);
		return "user";
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody String createUser(@PathVariable String id, HttpServletResponse resp)
	{
		// equivalent to "update"

		// 连接本地的 Redis 服务
		// Jedis jedis = new Jedis("192.168.20.136", 16379);
		Jedis jedis = new Jedis("192.168.20.136", 16379);

		// 设置 redis 字符串数据
		jedis.set(id, "user_" + id);
		jedis.close();

		resp.setHeader("Location", "/user/" + id);
		return "test~~~" + jedis.get(id);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void putUser(@PathVariable String id)
	{
		// equivalent to "update"

		// 连接本地的 Redis 服务
		// Jedis jedis = new Jedis("192.168.20.136", 16379);
		Jedis jedis = new Jedis("192.168.20.136", 16379);

		// 设置 redis 字符串数据
		jedis.set(id, "user_" + id);
		jedis.close();
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable String id)
	{
		// equivalent to "update"

		// 连接本地的 Redis 服务
		// Jedis jedis = new Jedis("192.168.20.136", 16379);
		Jedis jedis = new Jedis("192.168.20.136", 16379);

		// 设置 redis 字符串数据
		jedis.del(id);
		jedis.close();
	}
}
