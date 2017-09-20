package com.dc;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/code")
public class SecCodeController
{
	public static final int AUTHCODE_LENGTH = 4; // ��֤�볤��
	public static final int SINGLECODE_WIDTH = 12; // ������֤����
	public static final int SINGLECODE_HEIGHT = 25; // ������֤��߶�
	public static final int SINGLECODE_GAP = 3; // ������֤��֮����
	public static final int IMG_WIDTH = AUTHCODE_LENGTH * (SINGLECODE_WIDTH + SINGLECODE_GAP);
	public static final int IMG_HEIGHT = SINGLECODE_HEIGHT;

	@RequestMapping
	public void generate(HttpServletRequest request, HttpServletResponse response)
	{
		// System.out.println(123333);
		String authCode = "";

		for (int i = 0; i < AUTHCODE_LENGTH; i++)
		{
			authCode += (new Random()).nextInt(10);
		}

		// ����ͼƬ�ĸߡ�������
		// RGB���룺red��green��blue
		BufferedImage img = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, BufferedImage.TYPE_INT_BGR);
		// �õ�ͼƬ�ϵ�һ������
		Graphics g = img.getGraphics();
		// ���û��ʵ���ɫ������������ɫ
		g.setColor(Color.YELLOW);
		// �û��������һ�����Σ����ε����Ͻ����꣬����
		g.fillRect(0, 0, IMG_WIDTH, IMG_HEIGHT);
		// ��������ɫ����Ϊ��ɫ������д��
		g.setColor(Color.BLACK);
		// �������壺���塢������ʽ�ġ��ֺ�
		g.setFont(new Font("����", Font.PLAIN, SINGLECODE_HEIGHT + 5));

		// �������
		char c;
		for (int i = 0; i < authCode.toCharArray().length; i++)
		{
			// ȡ����Ӧλ�õ��ַ�
			c = authCode.charAt(i);
			// ����һ���ַ�����Ҫ�������ݣ���ʼ��λ�ã��߶�
			g.drawString(c + "", i * (SINGLECODE_WIDTH + SINGLECODE_GAP) + SINGLECODE_GAP / 2, IMG_HEIGHT);
		}
		Random random = new Random();
		// ������
		for (int i = 0; i < 20; i++)
		{
			int x = random.nextInt(IMG_WIDTH);
			int y = random.nextInt(IMG_HEIGHT);
			int x2 = random.nextInt(IMG_WIDTH);
			int y2 = random.nextInt(IMG_HEIGHT);
			g.drawLine(x, y, x + x2, y + y2);
		}

		// ��ֹ������������ͼƬ
		response.setDateHeader("Expires", -1);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");

		// ֪ͨ�ͻ�����ͼƬ�ķ�ʽ�򿪷��͹�ȥ������
		response.setHeader("Control-Type", "image/jpeg");

		request.getSession().setAttribute("authCode", authCode); // ����֤�뱣�浽session�У������Ժ���֤

		try
		{
			// ����ͼƬ
			ImageIO.write(img, "JPEG", response.getOutputStream());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}
