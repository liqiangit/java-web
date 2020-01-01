package com.hawkingfoo.dao;

import com.hawkingfoo.ApplicationTest;
import com.hawkingfoo.model.Person;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { ApplicationTest.class, DataSourceAutoConfiguration.class })
public class PersonMapperTest {
	private static final int size = 10;// 000000;
	@Autowired
	private PersonMapper personMapper;

	@Test
	public void testInsert() {
		Person person = new Person();
		person.setName("LiMing");
		person.setCities(Arrays.asList("Beijing", "Shanghai"));

		// insert
		personMapper.insert(person);

		List<Person> personList = personMapper.selectAll();
		Assert.assertEquals(1, personList.size());
	}

	@Test
	public void testInsert0() throws InterruptedException {
		// 10000000耗时27201
		Map<String, Person> map = new HashMap<String, Person>();
		long start = System.currentTimeMillis();
		for (int i = 0; i < size; i++) {
			Person person = new Person();
			person.setId(i);
			person.setName("LiMing");
			person.setCities(Arrays.asList("Beijing", "Shanghai"));

			// insert
			// personMapper.insert(person);
			map.put(i + "", person);
		}
		System.out.println(System.currentTimeMillis() - start);
		Thread.sleep(Integer.MAX_VALUE);
	}

	@Test
	public void testInsert1() throws InterruptedException {
		// 10000000耗时13058
		List<Person> persons = new LinkedList<Person>();
		long start = System.currentTimeMillis();
		for (int i = 0; i < size; i++) {
			Person person = new Person();
			person.setId(i);
			person.setName("LiMing");
			person.setCities(Arrays.asList("Beijing", "Shanghai"));

			// insert
			// personMapper.insert(person);
			persons.add(person);
		}
		System.out.println(System.currentTimeMillis() - start);
		Thread.sleep(Integer.MAX_VALUE);
	}

	@Test
	public void testInsert2() {
		long start = System.currentTimeMillis();
		for (int i = 0; i < size; i++) {
			Person person = new Person();
			person.setId(i);
			person.setName("LiMing");
			person.setCities(Arrays.asList("Beijing", "Shanghai"));

			// insert
			personMapper.insert(person);
		}
		System.out.println(System.currentTimeMillis() - start);
	}

	@Test
	public void testInsert3() {
		long start = System.currentTimeMillis();
		for (int i = 0; i < size; i++) {
			Person person = personMapper.selectById(i);
			if (person == null) {
				person = new Person();
				person.setId(i);
				person.setName("LiMing");
				person.setCities(Arrays.asList("Beijing", "Shanghai"));

				// insert
				personMapper.insert(person);
			}
		}
		System.out.println(System.currentTimeMillis() - start);
	}

	@Test
	public void testInsert4() throws InterruptedException {
		// 10000000耗时173088,166150
		// h2还用的是堆内内存，不适合大数据量
		long start = System.currentTimeMillis();
		for (int i = 0; i < size; i++) {
			Person person = personMapper.selectById(i);
			if (person == null) {
				person = new Person();
				person.setId(i);
				person.setName(
						"LiMing1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
				person.setCities(Arrays.asList("Beijing", "Shanghai"));

				// insert
				personMapper.insert(person);
			} else {
				person.setName("LiMing");
				person.setCities(Arrays.asList("Beijing", "Shanghai"));

				// update
				personMapper.update(person);
			}
			person = null;
		}
		System.err.println(System.currentTimeMillis() - start);
		Thread.sleep(Integer.MAX_VALUE);
	}
}
