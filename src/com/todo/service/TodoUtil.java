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
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "#�� �� �߰��ϱ�\n"
				+ "##�� �� ����:\n");
		
		title = sc.nextLine();
		if (list.isDuplicate(title)) {
			System.out.printf("��ġ�� ���� ����\n");
			return;
		}
		
		System.out.println("##�� �� ��ü���� ����:");
		desc = sc.nextLine();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "#��Ͽ��� �� �� �����ϱ�\n"
				+ "##������ �� �� ����:");
		String title = sc.nextLine();
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "#�� �� ���� �����ϱ�\n"
				+ "##������Ʈ�� �� ���� ����:");
		String title = sc.nextLine().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("�������� �ʴ� ����\n");
			return;
		}

		System.out.println("##�������� ���ο� ����:");
		String new_title = sc.nextLine().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("��ġ�� ���� ����\n");
			return;
		}
		
		System.out.println("##"+new_title+"�� ���ο� ����:");
		String new_description = sc.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("������Ʈ �Ϸ�");
			}
		}

	}

	public static void listAll(TodoList l) {
		
		System.out.println("��ü �� �� ���\n���� , ����\n");
		for (TodoItem item : l.getList()) {
			System.out.println( item.getTitle() + " , " + item.getDesc()+" , "+item.getCurrent_date());
		}
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
				newItem.setTitle(st.nextToken("##"));
				newItem.setDesc(st.nextToken("##"));
				newItem.setCurrent_date(st.nextToken());
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
			System.out.println(num+"���� ���Ϸκ��� �о��\n");
		else 
			System.out.println("�ؾ� �� ���� �������� ����\n");
	
	}
}
