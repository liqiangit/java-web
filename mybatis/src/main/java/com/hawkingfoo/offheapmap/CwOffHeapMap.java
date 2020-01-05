package com.hawkingfoo.offheapmap;

import net.openhft.chronicle.map.ChronicleMap;
import net.openhft.chronicle.map.ChronicleMapBuilder;

public class CwOffHeapMap<K, V> extends AbstractOffHeapMap<K, V> {
	Class<K> keyClass;
	Class<V> valueClass;

	public CwOffHeapMap(Class<K> keyClass, Class<V> valueClass) {
		this.keyClass = keyClass;
		this.valueClass = valueClass;
		CwMap<K, V> cwMap = newMap(0);
		list.add(cwMap);
	}

	@Override
	public CwMap<K, V> newMap(int modCount) {
		ChronicleMapBuilder<K, V> mapBuilder = ChronicleMapBuilder.of(getKClass(), getVClass()).entries(mods[modCount]);
		ChronicleMap<K, V> cache = mapBuilder.create();
		CwMap<K, V> cwMap = new CwMap<K, V>(modCount, mods[modCount], cache);
		return cwMap;
	}

	public Class<K> getKClass() {
		return keyClass;
	}

	public Class<V> getVClass() {
		return valueClass;
	}
}
