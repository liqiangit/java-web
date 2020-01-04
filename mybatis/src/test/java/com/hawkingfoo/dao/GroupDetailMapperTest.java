package com.hawkingfoo.dao;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hawkingfoo.ApplicationTest;
import com.hawkingfoo.model.GroupDetail;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { ApplicationTest.class, DataSourceAutoConfiguration.class })
public class GroupDetailMapperTest {
//	10000000
//	85857
	private static final int size = 1000000;
	@Autowired
	private GroupDetailMapper mapper;

	@Test
	public void testInsert2() throws InterruptedException {
		long start = System.currentTimeMillis();
		for (long i = 0; i < size; i++) {
			GroupDetail detail = new GroupDetail();
			detail.setId(i);
			detail.setCategory("category");
			detail.setPeopleId("peopleId"+i);
			detail.setFeatureId("featureId"+i);
			detail.setChn("chn");
			detail.setLibraryName1("libraryName1");
			detail.setLibraryName2("libraryName2");
			detail.setPeopleId("peopleId"+i);
			detail.setX(1);
			detail.setY(1);
			// insert
			mapper.insert(detail);
		}
		System.out.println(System.currentTimeMillis() - start);
		Thread.sleep(Integer.MAX_VALUE);
	}
}
