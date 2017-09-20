package com.dc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.CharSet;
import org.apache.commons.lang3.CharSetUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.commons.validator.GenericValidator;
import org.apache.commons.lang3.StringEscapeUtils;

/**
 * @author Administrator
 *         http://commons.apache.org/proper/commons-lang/userguide.html
 */
public class LangDemo
{
	public void charSetDemo() 
	{
		System.out.println("**CharSetDemo**");
		CharSet charSet = CharSet.getInstance("aeiou");
		String demoStr = "The quick brown fox jumps over the lazy dogooooo0.";
		int count = 0;
		for (int i = 0, len = demoStr.length(); i < len; i++)
		{
			if (charSet.contains(demoStr.charAt(i)))
			{
				count++;
			}
		}
		System.out.println("count: " + count);
	}

	public void charSetUtilsDemo()
	{
		System.out.println("**CharSetUtilsDemo**");
		System.out.println("�����ַ����а���ĳ�ַ���.");
		System.out.println(CharSetUtils.count("The quick brown fox jumps over the lazy dog.", "aeiou"));

		System.out.println("ɾ���ַ�����ĳ�ַ�.");
		System.out.println(CharSetUtils.delete("The quick brown fox jumps over the lazy dog.", "aeiou"));

		System.out.println("�����ַ�����ĳ�ַ�.");
		System.out.println(CharSetUtils.keep("The quick brown fox jumps over the lazy dog.", "aeiou"));

		System.out.println("�ϲ��ظ����ַ�.");
		System.out.println(CharSetUtils.squeeze("a  bbbbbb    d c dd", "b d"));
	}

	public void objectUtilsDemo()
	{
		System.out.println("**ObjectUtilsDemo**");
		System.out.println("ObjectΪnullʱ��Ĭ�ϴ�ӡĳ�ַ�.");
		Object obj = null;
		System.out.println(ObjectUtils.defaultIfNull(obj, "��"));

		System.out.println("��֤���������Ƿ�ָ���Object�Ƿ����,ȡ����Object��equals()����.");
		Object a = new Object();
		Object b = a;
		Object c = new Object();
		System.out.println(ObjectUtils.equals(a, b));
		System.out.println(ObjectUtils.equals(a, c));

		System.out.println("�ø���Object��toString()�������ض�����Ϣ.");
		Date date = new Date();
		System.out.println(ObjectUtils.identityToString(date));
		System.out.println(date);

		System.out.println("�����౾���toString()�������,����Ϊnullʱ������0�����ַ���.");
		System.out.println(ObjectUtils.toString(date));
		System.out.println(ObjectUtils.toString(null));
		System.out.println(date);
	}

	public void serializationUtilsDemo()
	{
		System.out.println("*SerializationUtils**");
		Date date = new Date();
		byte[] bytes = SerializationUtils.serialize(date);
		System.out.println(ArrayUtils.toString(bytes));
		System.out.println(date);

		Date reDate = (Date) SerializationUtils.deserialize(bytes);
		System.out.println(reDate);
		System.out.println(ObjectUtils.equals(date, reDate));
		System.out.println(date == reDate);

		FileOutputStream fos = null;
		FileInputStream fis = null;
		try
		{
			fos = new FileOutputStream(new File("d:/test.txt"));
			fis = new FileInputStream(new File("d:/test.txt"));
			SerializationUtils.serialize(date, fos);
			Date reDate2 = (Date) SerializationUtils.deserialize(fis);

			System.out.println(date.equals(reDate2));

		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				fos.close();
				fis.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

	}

	public void randomStringUtilsDemo()
	{
		System.out.println("**RandomStringUtilsDemo**");
		System.out.println("����ָ�����ȵ�����ַ���,����ûʲô��.");
		System.out.println(RandomStringUtils.random(500));

		System.out.println("��ָ���ַ��������ɳ���Ϊn������ַ���.");
		System.out.println(RandomStringUtils.random(5, "abcdefghijk"));

		System.out.println("ָ�����ַ�����������������ַ���.");
		System.out.println(RandomStringUtils.random(5, true, false));
		System.out.println(RandomStringUtils.random(5, false, true));

	}

	public void stringUtilsDemo()
	{
		System.out.println("**StringUtilsDemo**");
		System.out.println("���ַ����ظ�n�Σ������ְ�ĳ��Ⱦ��У����ַ���������ĳ�ַ�������.");
		String[] header = new String[3];
		header[0] = StringUtils.repeat("*", 50);
		header[1] = StringUtils.center("  StringUtilsDemo  ", 50, "^O^");
		header[2] = header[0];
		String head = StringUtils.join(header, "/n");
		System.out.println(head);

		System.out.println("���̵�ĳ����,��...��β.");
		System.out.println(StringUtils.abbreviate("The quick brown fox jumps over the lazy dog.", 10));
		System.out.println(StringUtils.abbreviate("The quick brown fox jumps over the lazy dog.", 15, 10));

		System.out.println("�������ַ�����ͬ��������.");
		System.out.println(StringUtils.indexOfDifference("aaabc", "aaacc"));

		System.out.println("�������ַ�����ͬ����ʼ������.");
		System.out.println(StringUtils.difference("aaabcde", "aaaccde"));

		System.out.println("��ȥ�ַ���Ϊ��ָ���ַ�����β�Ĳ���.");
		System.out.println(StringUtils.chomp("aaabcde", "de"));

		System.out.println("���һ�ַ����Ƿ�Ϊ��һ�ַ������Ӽ�.");
		System.out.println(StringUtils.containsOnly("aad", "aadd"));

		System.out.println("���һ�ַ����Ƿ�����һ�ַ������Ӽ�.");
		System.out.println(StringUtils.containsNone("defg", "aadd"));

		System.out.println("���һ�ַ����Ƿ������һ�ַ���.");
		System.out.println(StringUtils.contains("defg", "ef"));
		System.out.println(StringUtils.containsOnly("ef", "defg"));

		System.out.println("���ؿ��Դ���null��toString().");
		System.out.println(StringUtils.defaultString("aaaa"));
		System.out.println("?" + StringUtils.defaultString(null) + "!");

		System.out.println("ȥ���ַ��еĿո�.");
		System.out.println(StringUtils.deleteWhitespace("aa  bb  cc"));

		System.out.println("�ж��Ƿ���ĳ���ַ�.");
		System.out.println(StringUtils.isAlpha("ab"));
		System.out.println(StringUtils.isAlphanumeric("12"));
		System.out.println(StringUtils.isBlank(""));
		System.out.println(StringUtils.isNumeric("123"));
	}

	public void systemUtilsDemo()
	{
		System.out.println(genHeader("SystemUtilsDemo"));
		System.out.println("���ϵͳ�ļ��ָ���.");
		System.out.println(SystemUtils.FILE_SEPARATOR);

		System.out.println("���Դ�ļ�����.");
		System.out.println(SystemUtils.FILE_ENCODING);

		System.out.println("���extĿ¼.");
		System.out.println(SystemUtils.JAVA_EXT_DIRS);

		System.out.println("���java�汾.");
		System.out.println(SystemUtils.JAVA_VM_VERSION);

		System.out.println("���java����.");
		System.out.println(SystemUtils.JAVA_VENDOR);
	}

	public void classUtilsDemo()
	{
		System.out.println(genHeader("ClassUtilsDemo"));
		System.out.println("��ȡ��ʵ�ֵ����нӿ�.");
		System.out.println(ClassUtils.getAllInterfaces(Date.class));

		System.out.println("��ȡ�����и���.");
		System.out.println(ClassUtils.getAllSuperclasses(Date.class));

		System.out.println("��ȡ������.");
		System.out.println(ClassUtils.getShortClassName(Date.class));

		System.out.println("��ȡ����.");
		System.out.println(ClassUtils.getPackageName(Date.class));

		System.out.println("�ж��Ƿ����ת��.");
		System.out.println(ClassUtils.isAssignable(Date.class, Object.class));
		System.out.println(ClassUtils.isAssignable(Object.class, Date.class));
	}

	public void stringEscapeUtilsDemo()
	{
		System.out.println(genHeader("StringEcsapeUtils"));
		System.out.println("ת�������ַ�.");
		System.out.println("html:" + StringEscapeUtils.escapeHtml4("/n\n"));
		System.out.println("html:" + StringEscapeUtils.unescapeHtml4("<p>"));
	}

	private final class BuildDemo
	{
		String name;

		int age;

		public BuildDemo(String name, int age)
		{
			this.name = name;
			this.age = age;
		}

		public String toString()
		{
			ToStringBuilder tsb = new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE);
			tsb.append("Name", name);
			tsb.append("Age", age);
			return tsb.toString();
		}

		public int hashCode()
		{
			HashCodeBuilder hcb = new HashCodeBuilder();
			hcb.append(name);
			hcb.append(age);
			return hcb.hashCode();
		}

		public boolean equals(Object obj)
		{
			if (!(obj instanceof BuildDemo))
			{
				return false;
			}
			BuildDemo bd = (BuildDemo) obj;
			EqualsBuilder eb = new EqualsBuilder();
			eb.append(name, bd.name);
			eb.append(age, bd.age);
			return eb.isEquals();
		}
	}

	public void builderDemo()
	{
		System.out.println(genHeader("BuilderDemo"));
		BuildDemo obj1 = new BuildDemo("a", 1);
		BuildDemo obj2 = new BuildDemo("b", 2);
		BuildDemo obj3 = new BuildDemo("a", 1);

		System.out.println("toString()");
		System.out.println(obj1);
		System.out.println(obj2);
		System.out.println(obj3);

		System.out.println("hashCode()");
		System.out.println(obj1.hashCode());
		System.out.println(obj2.hashCode());
		System.out.println(obj3.hashCode());

		System.out.println("equals()");
		System.out.println(obj1.equals(obj2));
		System.out.println(obj1.equals(obj3));
	}

	public void numberUtils()
	{
		System.out.println(genHeader("NumberUtils"));
		System.out.println("�ַ���תΪ����(��֪����ʲô��).");
		System.out.println(NumberUtils.toInt("ba", 33));

		System.out.println("��������ѡ�����ֵ.");
		System.out.println(NumberUtils.max(new int[]
		{ 1, 2, 3, 4 }));

		System.out.println("�ж��ַ����Ƿ�ȫ������.");
		System.out.println(NumberUtils.isDigits("123.1"));

		System.out.println("�ж��ַ����Ƿ�����Ч����.");
		System.out.println(NumberUtils.isNumber("0123.1"));
	}

	public void dateFormatUtilsDemo()
	{
		System.out.println(genHeader("DateFormatUtilsDemo"));
		System.out.println("��ʽ���������.");
		System.out.println(DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"));

		System.out.println("���.");
		StopWatch sw = new StopWatch();
		sw.start();

		for (Iterator iterator = DateUtils.iterator(new Date(), DateUtils.RANGE_WEEK_CENTER); iterator.hasNext();)
		{
			Calendar cal = (Calendar) iterator.next();
			System.out.println(DateFormatUtils.format(cal.getTime(), "yy-MM-dd HH:mm"));
		}

		sw.stop();
		System.out.println("����ʱ:" + sw.getTime());

	}

	private String genHeader(String head)
	{
		String[] header = new String[3];
		header[0] = StringUtils.repeat("*", 50);
		header[1] = StringUtils.center("  " + head + "  ", 50, "^O^");
		header[2] = header[0];
		return StringUtils.join(header, "/n");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		LangDemo langDemo = new LangDemo();
		System.out.println(GenericValidator.isDouble("z1.1"));
		System.out.println(GenericValidator.isEmail("1a@1a.cn"));
		//langDemo.charSetDemo();
	//	langDemo.charSetUtilsDemo();
	//	langDemo.objectUtilsDemo();
//		langDemo.serializationUtilsDemo();
		langDemo.randomStringUtilsDemo();
//		langDemo.stringUtilsDemo();
//		langDemo.systemUtilsDemo();
//		langDemo.classUtilsDemo();
//		langDemo.stringEscapeUtilsDemo();
//		langDemo.builderDemo();
//		langDemo.numberUtils();
//		langDemo.dateFormatUtilsDemo();

	}

}
