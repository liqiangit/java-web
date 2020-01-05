package com.hawkingfoo.offheapmap;

import java.util.Map;

public class CwMap<K, V> {

	private int modCount;
	private int capacity;

	private Map<K, V> map;

	public CwMap(int modCount, int capacity, Map<K, V> map) {
		this.modCount = modCount;
		this.capacity = capacity;
		this.map = map;
	}

	public int getModCount() {
		return modCount;
	}

	public Map<K, V> getMap() {
		return map;
	}

	public int getCapacity() {
		return capacity;
	}
}
