package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javaBean.Person;

public class FindTool {
	public static final int MAX_RESULT_NUM = 5;   // 定义检索记录最大限制
	public static List<Person> database;
	
	// 静态加载，导入所有person信息
	static {
		database = DataBase.get();
	}
	
	
	public static List<Person> find(String data){
		List<Person> persons = null;
		//System.out.println("data="+data);
		// 遍历database模糊匹配信息
		for(int i=0;i<database.size();i++) {
			Person p = database.get(i);
			if(p.toString().contains(data)) {
				if(persons==null) {
					persons = new ArrayList<Person>();
				}
				persons.add(p);
			}
		}
		
		return persons;
	}
	
	
//	public static Person searchId(String id) {
//		System.out.println("id="+id);
//		
//		// 遍历database，匹配id
//		for(int i=0;i<database.size();i++) {
//			Person p = database.get(i);
//			System.out.println(p);
//			/*  注意：txt文件另存为utf编码时会自动带上BOM头部，导致第一条记录的id匹配
//			 * 即使看起来学号一样，但读取txt时读取了前面三个字节的头部。
//			 * 处理方式：1，使用notepad,utf+无BOM格式编码；2，程序中进行头部处理
//			System.out.println(Arrays.toString(id.getBytes()));
//			System.out.println(Arrays.toString(p.getId().getBytes()));
//			*/
//			if(p.getId().equals(id)) {
//				return p;
//			}
//		}
//		return null;
//	}
//	
//	public static List<Person> searchName(String name) {
//		System.out.println("name="+name);
//		List<Person> list = null;
//		
//		// 遍历database，模糊匹配name
//		for(int i=0;i<database.size();i++) {
//			Person p = database.get(i);
//			System.out.println(p);
//			if(p.getName().contains(name)) {
//				if(list==null) {
//					list = new ArrayList<Person>();
//				}
//				list.add(p);
//			}
//			// 检索结果的最大个数
//			if(list.size()==MAX_RESULT_NUM) {
//				break;
//			}
//		}
//		//查询结果显示优化-将更加精确的匹配结果放在更前面
//		int flag = 0;
//		if(list!=null) {
//			for(int i=0;i<list.size();i++) {
//				if(list.get(i).getName().equals(name)) {
//					Person temp = list.get(flag);
//					list.set(flag, list.get(i));
//					list.set(i, temp);
//					flag++;
//				}
//			}
//		}
//		return list;
//	}
//	
//	public static Person searchPhone(String phone) {
//		System.out.println("phone="+phone);
//		
//		// 遍历database，匹配phone
//		for(int i=0;i<database.size();i++) {
//			Person p = database.get(i);
//			System.out.println(p);
//			if(p.getPhone().equals(phone)) {
//				return p;
//			}
//		}
//		return null;
//	}
//	
//	public static Person searchQQ(String qq) {
//		System.out.println("qq="+qq);
//		
//		// 遍历database，匹配qq
//		for(int i=0;i<database.size();i++) {
//			Person p = database.get(i);
//			System.out.println(p);
//			if(p.getQq().equals(qq)) {
//				return p;
//			}
//		}
//		return null;
//	}
//	
//	public static Person searchEmail(String email) {
//		System.out.println("email="+email);
//		
//		// 遍历database，匹配email
//		for(int i=0;i<database.size();i++) {
//			Person p = database.get(i);
//			System.out.println(p);
//			if(p.getEmail().equals(email)) {
//				return p;
//			}
//		}
//		return null;
//	}

}
