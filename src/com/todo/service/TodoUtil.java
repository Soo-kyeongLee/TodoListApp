package com.todo.service;

import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String cate, title, desc, end;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "#�� �� �߰��ϱ�\n"
				+ "##�� �� ī�װ�:\n");
		
		cate = sc.nextLine();
		
		System.out.println("\n"
				+ "##�� �� ����:\n");
		
		title = sc.nextLine();
		if (list.isDuplicate(cate, title)) {
			System.out.printf("���� ī�װ��� ��ġ�� ���� ����\n");
			return;
		}
		
		System.out.println("##�� �� ��ü���� ����:");
		desc = sc.nextLine();
		
		System.out.println("##������_yyy/mm/dd:");
		end = sc.nextLine();
		
		TodoItem t = new TodoItem(cate, title, desc, end);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		listAll(l);
		System.out.println("\n"
				+ "#��Ͽ��� �� �� �����ϱ�\n"
				+ "##������ �� �׸� ��ȣ:");
		int Num = sc.nextInt();
		 sc.nextLine();
		int cnt=0;
		for (TodoItem item : l.getList()) {
			cnt++;
			if(cnt==Num) {
				System.out.println(item.toString()+"�� ������ �����Ͻðڽ��ϱ�?(y)\n");
				String check=sc.nextLine();
				if(!check.equalsIgnoreCase("y")) {
					break;
				}
				l.deleteItem(item);
				break;
			}
		}
		if (cnt<Num||cnt<=0) {
			System.out.println("�������� �ʴ� �׸�\n");
			return;
		}
		
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		listAll(l);
		System.out.println("\n"
				+ "#�� �� ���� �����ϱ�\n"
				+ "##������Ʈ�� �׸� ��ȣ");
		int num = sc.nextInt();
		 sc.nextLine();
		int cnt=0;
		for (TodoItem item : l.getList()) {
			cnt++;
			if(cnt==num) {
				String cate=item.getCategory();
				System.out.println("##"+item.getTitle()+"�� ���ο� ����:");
				String new_title = sc.nextLine().trim();
				if (l.isDuplicate(cate,new_title)) {
					System.out.println("�ϳ��� ī�װ��� ��ġ�� ���� ����\n");
					return;
				}
				System.out.println("##"+new_title+"�� ���ο� ����:");
				String new_description = sc.nextLine().trim();
				System.out.println("##������ ��¥:");
				String end = sc.nextLine().trim();
				l.deleteItem(item);
				TodoItem t=new TodoItem(cate,new_title,new_description,end);
				l.addItem(t);
				System.out.println("������Ʈ �Ϸ�");
				break;
			}
		}
		if (cnt<num||cnt<=0) {
			System.out.println("�������� �ʴ� �׸�\n");
			return;
		}
	}

	public static void listAll(TodoList l) {
		
		System.out.println("��ü �� �� ���\n�о�, ���� , ����, ���� ��¥, ������ ��¥\n");
		int i=0;
		for (TodoItem item : l.getList()) {
			i++;
			System.out.println( i+"�� "+item.getCategory() + " , " +item.getTitle() + " , " + item.getDesc()+" , "+item.getCurrent_date()+", "+item.getDue_date());
		}
	}
	
	public static void find(TodoList l, String key) {
		int cnt=0;
		for (TodoItem item : l.getList()) {
			String title=item.getTitle();
			String desc=item.getDesc();
			if(find_(title, key)||find_(desc,key)) {
				cnt++;
				System.out.println(item.toString());
			}
		}
		System.out.println(cnt+"���� �� ���� �߰�");
	}
	
	public static boolean find_(String s,String key) {
		StringTokenizer st=new StringTokenizer(s);
		while(st.hasMoreTokens()) {
			if(st.nextToken().equals(key)) {
				return true;	
			}
		}
		return false;
	}
	
	public static void saveList(TodoList l,String file) throws IOException {
		//file open
		FileWriter f = new FileWriter(file,false);
		for (TodoItem item : l.getList()) {
			f.write(item.toSaveSTring());
			//f.flush();
		}
		//file close
		f.close();
	}
	
	public static void loadList(TodoList l,String file) {
		int num=0;
		BufferedReader bf;
		try {
			bf = new BufferedReader(new FileReader(file));
			String s;
			while((s=bf.readLine())!=null) {
				StringTokenizer st=new StringTokenizer(s);
				TodoItem newItem=new TodoItem();
				newItem.setCategory(st.nextToken("##"));
				newItem.setTitle(st.nextToken("##"));
				newItem.setDesc(st.nextToken("##"));
				newItem.setCurrent_date(st.nextToken("##"));
				newItem.setDue_date(st.nextToken());
				l.addItem(newItem);
				num++;
			}
			
			bf.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("todoutil::loadList");
			e.printStackTrace();
		}
		
	
		if(num!=0)
			System.out.println(num+"���� �ؾ��� ���� ����\n");
		else 
			System.out.println("�ؾ� �� ���� �������� ����\n");
	
	}
}
