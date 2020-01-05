package com.hawkingfoo.offheapmap;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public abstract class AbstractOffHeapMap<K, V> implements Map<K, V> {
	public static final int DEFAULT_CAPACITY = 10;
	public static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
	public static final int LIMIT = 110000;
	public static int[] mods = new int[64];
	static {
		mods[0] = DEFAULT_CAPACITY;
		int capacity = DEFAULT_CAPACITY;
		for (int i = 1; i < mods.length; i++) {
			capacity = _grow(capacity);
			mods[i] = capacity;
			if (capacity > LIMIT) {
				break;
			}
		}
	}

	private static int _grow(int oldCapacity) {
		int newCapacity = oldCapacity + (oldCapacity >> 1);
		if (newCapacity == oldCapacity) {
			newCapacity = oldCapacity + 1;
		}
		return newCapacity;
	}

	protected List<CwMap<K, V>> list = new LinkedList<CwMap<K, V>>();
	
	public abstract CwMap<K, V> newMap(int modCount);

	@Override
	public int size() {
		int size = 0;
		for (CwMap<K, V> chronicleMap : list) {
			size += chronicleMap.getMap().size();
		}
		return size;
	}

	@Override
	public boolean isEmpty() {
		if (list.size() == 1 && list.get(0).getMap().size() == 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean containsKey(Object key) {
		boolean containsKey = false;
		// 修改
		for (CwMap<K, V> chronicleMap : list) {
			if (chronicleMap.getMap().containsKey(key)) {
				containsKey = true;
				break;
			}
		}
		return containsKey;
	}

	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public V get(Object key) {
		V v = null;
		// 修改
		for (int i = 0; i < list.size(); i++) {
			CwMap<K, V> chronicleMap = list.get(i);
			if (chronicleMap.getMap().containsKey((K) key)) {
				v = chronicleMap.getMap().get(key);
				break;
			}
		}
		return v;
	}

	@Override
	public V put(K key, V value) {
		boolean containsKey = false;
		// 修改
		for (CwMap<K, V> chronicleMap : list) {
			if (chronicleMap.getMap().containsKey(key)) {
				chronicleMap.getMap().put(key, value);
				containsKey = true;
				break;
			}
		}
		// 新增
		if (!containsKey) {
			CwMap<K, V> cwMap = getLastMap();
			int result = ExchangeUtils.grow(cwMap.getMap().size(), cwMap.getCapacity(), LIMIT);
			if (result == 1) {
				int modCount = cwMap.getModCount() + 1;
				CwMap<K, V> newMap = newMap(modCount);
				for (Map.Entry<K, V> entry : cwMap.getMap().entrySet()) {
					newMap.getMap().put(entry.getKey(), entry.getValue());
				}
				list.remove(list.size() - 1);
				list.add(newMap);
			}
			if (result == 2) {
				CwMap<K, V> newMap = newMap(0);
				list.add(newMap);
			}
			// 这里一定要用getLastMap，因为最后一位元素变了
			getLastMap().getMap().put(key, value);
		}
		return value;
	}

	private CwMap<K, V> getLastMap() {
		return list.get(list.size() - 1);
	}

	@Override
	public V remove(Object key) {
		V v = null;
		boolean containsKey = false;
		// 修改
		for (int i = 0; i < list.size(); i++) {
			CwMap<K, V> chronicleMap = list.get(i);

			if (chronicleMap.getMap().containsKey((K) key)) {
				v = chronicleMap.getMap().remove(key);
				containsKey = true;
				if (i < list.size() - 1) {
					CwMap<K, V> last = getLastMap();
					moveData(chronicleMap, last);
				}
				break;
			}
		}
		if (containsKey) {
			CwMap<K, V> last = getLastMap();
			int result = ExchangeUtils.ungrow(last.getMap().size(), last.getModCount(), mods);
			if (result == 2 && list.size() > 1) {
				list.remove(list.size() - 1);
			}
			if (result == 1) {
				CwMap<K, V> newMap = newMap(last.getModCount() - 1);
				for (Map.Entry<K, V> entry : last.getMap().entrySet()) {
					newMap.getMap().put(entry.getKey(), entry.getValue());
				}
				list.remove(list.size() - 1);
				list.add(newMap);
			}
		}
		return v;
	}

	private void moveData(CwMap<K, V> dest, CwMap<K, V> src) {
		int x = 0;
		K k = null;
		for (Map.Entry<K, V> entry : src.getMap().entrySet()) {
			x++;
			dest.getMap().put(entry.getKey(), entry.getValue());
			if (x == 1) {
				k = entry.getKey();
				break;
			}
		}
		if (k != null) {
			src.getMap().remove(k);
		}
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<K> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}
}
