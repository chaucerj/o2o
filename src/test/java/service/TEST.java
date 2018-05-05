package service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import redis.clients.jedis.Jedis;

public class TEST {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		List<String> newlist = new LinkedList<String>();
		List<String> ve = new Vector<String>();
		newlist.add(0, "a");
		newlist.add(0, "nn");
		// newlist.add(1, "kkk");
		// newlist.add("ooo");
		// newlist.remove(0);
		System.out.println(newlist.toString());
		list.add(0, "a");
		list.add(1, "b");
		list.add(0, "nn");
		list.add("mm");
		System.out.println(list.toString());
		Jedis jedis = new Jedis("122.114.233.192", 6379);
		System.out.println(jedis.ping());
	}

}
