package com.hawkingfoo.offheapmap;

public class ExchangeUtils {
	/**
	 * 
	 * @param minCapacity
	 *            当前数量
	 * @param capacity
	 *            容量
	 * @param limit
	 *            限制
	 * @return 0 不用扩，1 已扩，2 扩不了，4 异常
	 */
	public static int grow(int minCapacity, int capacity, int limit) {
		int result = 0;
		if (minCapacity - capacity > 0) {
			if (capacity > limit) {
				result = 2;
			} else {
				result = 1;
			}
		}
		return result;
	}

	/**
	 * 频繁缩库增库会产生震荡，比如库从10,20,40,80，当前小于等于20才缩减库

	 * @param minCapacity	当前数量
	 * @param modCount	当前容量下标
	 * @param mods	容量数组
	 * @return 0 不用扩，1 已扩，2 扩不了，4 异常
	 */
	public static int ungrow(int minCapacity, int modCount, int[] mods) {
		if (minCapacity == 0) {
			return 2;
		}
		int result = 0;
		int x = 2;
		if (modCount >= x) {
			// 20
			if (minCapacity - mods[modCount - x] <= 0) {
				result = 1;
			}
		}
		return result;
	}
}
