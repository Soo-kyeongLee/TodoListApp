package com.todo.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TodoItem {
	private String category;
    private String title;
    private String desc;
    private String current_date;
    private String due_date;

    public TodoItem(String category, String title, String desc, String due_date){
    	this.category=category;
        this.title=title;
        this.desc=desc;
        SimpleDateFormat f=new SimpleDateFormat("yyy/MM/dd");
        this.current_date=f.format(new Date());
        this.due_date=due_date;
    }
    
    public TodoItem(){
    }
    
    public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDue_date() {
		return due_date;
	}

	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}

	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }
    
    @Override
	public String toString() {
		return "TodoItem [category=" + category + ", title=" + title + ", desc=" + desc + ", current_date="
				+ current_date + ", due_date=" + due_date + "]";
	}

	public String toSaveSTring() {
    	return category+"##"+title+"##"+desc+"##"+current_date+"##"+due_date+"\n";
    }
}
