package com.jim.json;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

/*
 * 需要定义get方法才能使用tojson,除非成员是public
 */
public class ObjectToJson {
    public static class User {
	private Long id;
	private String name;

	public Long getId() {
	    return id;
	}

	public void setId(Long id) {
	    this.id = id;
	}

	public String getName() {
	    return name;
	}

	public void setName(String name) {
	    this.name = name;
	}
    }

    public static class Group {
	private Long id;
	private String name;
	private List<User> users = new ArrayList<User>();

	public Long getId() {
	    return id;
	}

	public void setId(Long id) {
	    this.id = id;
	}

	public String getName() {
	    return name;
	}

	public void setName(String name) {
	    this.name = name;
	}

	public List<User> getUsers() {
	    return users;
	}

	public void setUsers(List<User> users) {
	    this.users = users;
	}
    }

    public static void main(String[] args) {
	Group group = new Group();
	group.setId(0L);
	group.setName("admin");

	User guestUser = new User();
	guestUser.setId(2L);
	guestUser.setName("guest");

	User rootUser = new User();
	rootUser.setId(3L);
	rootUser.setName("root");

	group.getUsers().add(guestUser);
	group.getUsers().add(rootUser);

	String jsonString = JSON.toJSONString(group);
	System.out.println(jsonString);

	Group group2 = JSON.parseObject(jsonString, Group.class);
	System.out.println(JSON.toJSONString(group2));

    }
}
