package cn.codeshop.net;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class JsonUtil {

	private static final SimpleDateFormat myFmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final char TYPE_LEFT_BRACE='{';
	public static final char TYPE_RIGHT_BRACE='}';
	public static final char TYPE_LEFT_SQUARE='[';
	public static final char TYPE_RIGHT_SQUARE=']';
	public static final char TYPE_COMMA=',';
	public static final char TYPE_COLON=':';

	public static final String toJSONString(Object v)
	{
		if(v==null) return "null";
		StringBuffer sb=new StringBuffer();
		write(sb,v);
		return sb.toString();
	}

	static class Point
	{
		int p;
	}

	private static final void writeMap(StringBuffer sb,Map v)
	{
		if(v==null)
		{
			sb.append("null");
			return;
		}
		int i=0;
		int size=v.size();
		Set<Entry> entrys=v.entrySet();
		sb.append(TYPE_LEFT_BRACE);
		for(Entry e:entrys)
		{
			i++;
			Object key=e.getKey();
			Object var=e.getValue();
			write(sb,key);
			sb.append(TYPE_COLON);
			write(sb,var);
			if(i<size)
			{
				sb.append(TYPE_COMMA);
			}
		}
		sb.append(TYPE_RIGHT_BRACE);
	}

	private static final void writeObject(StringBuffer sb,Object v)
	{
		if(v!=null)
		{
			Class c=v.getClass();
			Field[] fields=c.getDeclaredFields();
			Map<Object,Object> kv=new LinkedHashMap<Object,Object>();
			for(Field field:fields)
			{
				int modifierType=field.getModifiers();
				if(!Modifier.isStatic(modifierType)&&Modifier.isPublic(modifierType))
				{
					try
					{
						Object key=field.getName();
						Object var=field.get(v);
						if(var!=null)
						{
							kv.put(key,var);
						}
					}
					catch(IllegalArgumentException e)
					{
						e.printStackTrace();
					}
					catch(IllegalAccessException e)
					{
						e.printStackTrace();
					}
				}
			}
			Method[] ma=c.getDeclaredMethods();
			try
			{
				for(int i=0;i<ma.length;i++)
				{
					Method m=ma[i];
					int modifierType=m.getModifiers();
					Class<?>[] ptypes=m.getParameterTypes();
					if(ptypes.length==0)
					{
						if(!Modifier.isStatic(modifierType)&&Modifier.isPublic(modifierType))
						{
							if(m.getName().startsWith("get"))
							{
								String name=lowerFirstChar(m.getName().substring(3));
								if(!kv.containsKey(name))
								{
									Object value=m.invoke(v,new Object[]{});
									kv.put(name,value);
								}
							}
							else if(m.getName().startsWith("is"))
							{
								String name=lowerFirstChar(m.getName().substring(2));
								if(!kv.containsKey(name))
								{
									Object value=m.invoke(v,new Object[]{});
									kv.put(name,value);
								}
							}
						}
					}
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			if(kv.size()>0)
			{
				write(sb,kv);
			}
		}
	}

	public static String lowerFirstChar(String str)
	{
		if(str==null)
		{
			return null;
		}
		char[] ca=str.toCharArray();
		ca[0]=Character.toLowerCase(ca[0]);
		return new String(ca);
	}

	private static final void writeList(StringBuffer sb,List v)
	{
		if(v==null)
		{
			sb.append("null");
			return;
		}
		int size=v.size();
		sb.append(TYPE_LEFT_SQUARE);
		for(int i=0;i<size;i++)
		{
			Object obj=v.get(i);
			write(sb,obj);
			if(i<size-1)
			{
				sb.append(TYPE_COMMA);
			}
		}
		sb.append(TYPE_RIGHT_SQUARE);
	}

	private static final void writeArray(StringBuffer sb,Object[] v)
	{
		if(v==null)
		{
			sb.append("null");
			return;
		}
		int size=v.length;
		sb.append(TYPE_LEFT_SQUARE);
		for(int i=0;i<size;i++)
		{
			Object obj=v[i];
			write(sb,obj);
			if(i<size-1)
			{
				sb.append(TYPE_COMMA);
			}
		}
		sb.append(TYPE_RIGHT_SQUARE);
	}

	private static final void write(StringBuffer sb,Object obj)
	{
		if(obj==null)
		{
			sb.append("null");
		}
		else if(obj instanceof Boolean)
		{
			sb.append(((Boolean)obj).booleanValue());
		}
		else if(obj instanceof Integer)
		{
			sb.append(((Integer)obj).intValue());
		}
		else if(obj instanceof String)
		{
			String s=(String)obj;
			s=s.replace("\\","\\\\");
			s=s.replace("\"","\\\"");
			s=s.replace("/","\\/");
			s=s.replace("\b","\\b");
			s=s.replace("\f","\\f");
			s=s.replace("\n","\\n");
			s=s.replace("\r","\\r");
			s=s.replace("\t","\\t");
			sb.append("\"").append(s).append("\"");
		}
		else if(obj instanceof List)
		{
			writeList(sb,((List)obj));
		}
		else if(obj instanceof Map)
		{
			writeMap(sb,((Map)obj));
		}
		else if(obj instanceof Byte)
		{
			sb.append(((Byte)obj).byteValue());
		}
		else if(obj instanceof Short)
		{
			sb.append(((Short)obj).shortValue());
		}
		else if(obj instanceof Long)
		{
			sb.append(((Long)obj).longValue());
		}
		else if(obj instanceof Float)
		{
			sb.append(((Float)obj).floatValue());
		}
		else if(obj instanceof Double)
		{
			sb.append(((Double)obj).doubleValue());
		}
		else if(obj instanceof Date)
		{
			Date d=(Date)obj;
			sb.append("\"").append(myFmt.format(d)).append("\"");
		}
		else if(obj.getClass().isArray())
		{
			writeArray(sb,(Object[])obj);
		}
		else
		{
			writeObject(sb,obj);
		}
	}

	private static class KV
	{
		boolean inString=false;
		boolean isEmpty=true;
		public static final int STEP_KEY=0;
		public static final int STEP_VALUE=1;
		public static final int V_STRING=0;
		public static final int V_LIST=1;
		public static final int V_MAP=2;
		public int step=STEP_KEY;
		public int vType=V_STRING;
		public StringBuffer key=new StringBuffer();
		public StringBuffer vStr=new StringBuffer();
		public List vList=new ArrayList();
		public Map vMap=new HashMap();

		public void append(char c)
		{
			isEmpty=false;
			if(step==STEP_KEY)
			{
				key.append(c);
			}
			else if(step==STEP_VALUE&&vType==V_STRING)
			{
				vStr.append(c);
			}
		}

		public void putTo(Map map)
		{
			if(isEmpty) return;
			String k=key.toString();
			if(vType==V_STRING)
			{
				String v=vStr.toString();
				map.put(toObject(k),toObject(v));
			}
			else if(vType==V_LIST)
			{
				map.put(toObject(k),vList);
			}
			else if(vType==V_MAP)
			{
				map.put(toObject(k),vMap);
			}
		}

		public String toString()
		{
			if(vType==V_STRING)
			{
				return TYPE_LEFT_SQUARE+key.toString()+TYPE_COLON+vStr.toString()+TYPE_RIGHT_SQUARE;
			}
			else if(vType==V_LIST)
			{
				return TYPE_LEFT_SQUARE+key.toString()+TYPE_COLON+vList+TYPE_RIGHT_SQUARE;
			}
			else if(vType==V_MAP)
			{
				return TYPE_LEFT_SQUARE+key.toString()+TYPE_COLON+vMap+TYPE_RIGHT_SQUARE;
			}
			return "";
		}
	}

	private static KV newKV()
	{
		return new KV();
	}

	private static final Object toObject(String s)
	{
		if(s==null) return s;
		s=s.trim();
		if(s.length() == 0) return s;
		if(s.contains("null")) return null;
		char b1=s.charAt(0);
		char b2=s.charAt(s.length()-1);
		if(b1=='\"'&&b2=='\"')
		{
			s=s.substring(1,s.length()-1);
			s=s.replace("\\\"","\"");
			s=s.replace("\\/","/");
			s=s.replace("\\b","\b");
			s=s.replace("\\f","\f");
			s=s.replace("\\n","\n");
			s=s.replace("\\r","\r");
			s=s.replace("\\t","\t");
			s=s.replace("\\\\","\\");
			return s;
		}
		else
		{
			try
			{
				return Integer.parseInt(s);
			}
			catch(Exception e1)
			{
				try
				{
					return Long.parseLong(s);
				}
				catch(Exception e2)
				{
					try
					{
						return Double.parseDouble(s);
					}
					catch(Exception e3)
					{
						try
						{
							if(s.toLowerCase().equals("true"))
								return true;
							else if(s.toLowerCase().equals("false")) return false;
							throw new Exception();
						}
						catch(Exception e)
						{
						}
					}
				}
			}
		}
		return s;
	}

	public static final Object parse(String s)
	{
		if(s==null||s.length() == 0) throw new RuntimeException("s isEmpty!!");
		Point p=new Point();
		if(s.charAt(0)==TYPE_LEFT_BRACE)
		{
			return parseMap(s,p);
		}
		else if(s.charAt(0)==TYPE_LEFT_SQUARE)
		{
			return parseList(s,p);
		}
//		//by rongxinhua
//		else {
//			return s;
//		}
		throw new RuntimeException("No Support.");
	}

	private static final List parseList(String s,Point p)
	{
		if(s==null||s.length() == 0) throw new RuntimeException("s isEmpty!!");
		boolean F=true;
		boolean isObject=false;
		List list=new ArrayList();
		boolean inString=false;
		StringBuffer buff=new StringBuffer();
		int length=s.length();
		for(;p.p<length;p.p++)
		{
			char c=s.charAt(p.p);
			if(c==TYPE_LEFT_SQUARE&&!inString)
			{
				if(F)
				{
					F=false;
				}
				else
				{
					List list2=parseList(s,p);
					list.add(list2);
				}
			}
			else if(c==TYPE_RIGHT_SQUARE&&!inString)
			{
				if(buff.length()>0)
				{
					String str=buff.toString();
					list.add(toObject(str));
					p.p++;
				}
				return list;
			}
			else if(c==TYPE_COMMA&&!inString)
			{
				String str=buff.toString();
				if(isObject&&str.isEmpty())
				{
					isObject=false;
				}
				else
				{
					list.add(toObject(str));
					buff=new StringBuffer();
				}
			}
			else if(c==TYPE_LEFT_BRACE&&!inString)
			{
				Map map=parseMap(s,p);
				list.add(map);
				isObject=true;
				buff=new StringBuffer();
			}
			else if(c=='\r'||c=='\n')
			{
				continue;
			}
			else
			{
				if(c=='\"') inString=!inString;
				buff.append(c);
			}
		}
		return list;
	}

	private static final Map parseMap(String s,Point p)
	{
		if(s==null||s.length() == 0) throw new RuntimeException("s isEmpty!!");
		boolean F=true;
		Map map=new HashMap();
		KV kv=newKV();
		int length=s.length();
		for(;p.p<length;p.p++)
		{
			char c=s.charAt(p.p);
			if(c==TYPE_LEFT_BRACE&&!kv.inString)
			{
				if(F)
				{
					F=false;
				}
				else
				{
					kv.vType=KV.V_MAP;
					kv.vMap=parseMap(s,p);
					kv.putTo(map);
					kv=newKV();
				}
			}
			else if(c==TYPE_RIGHT_BRACE&&!kv.inString)
			{
				kv.putTo(map);
				return map;
			}
			else if(c==TYPE_COLON&&!kv.inString)
			{
				kv.step=KV.STEP_VALUE;
			}
			else if(c==TYPE_COMMA&&!kv.inString)
			{
				kv.putTo(map);
				kv=newKV();
			}
			else if(c==TYPE_LEFT_SQUARE&&!kv.inString)
			{
				kv.vType=KV.V_LIST;
				kv.vList=parseList(s,p);
				kv.putTo(map);
				kv=newKV();
			}
			else if(c=='\r'||c=='\n')
			{
				continue;
			}
			else
			{
				if(c=='\"') kv.inString=!kv.inString;
				if(kv.step==KV.STEP_KEY)
				{
					kv.append(c);
				}
				else if(kv.step==KV.STEP_VALUE)
				{
					kv.append(c);
				}
			}
		}
		return map;
	}
	
}
