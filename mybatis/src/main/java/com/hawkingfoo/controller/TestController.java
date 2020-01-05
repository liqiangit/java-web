package com.hawkingfoo.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hawkingfoo.dao.GroupDetailMapper;
import com.hawkingfoo.model.GroupDetail;

/**
 * @author yckj 测试类
 */
@RestController
@RequestMapping("/test")
public class TestController {
	@Autowired
	private GroupDetailMapper mapper;
	static AtomicLong idIdx = new AtomicLong();

	@PostMapping(value = { "/testAddData" }, consumes = { "application/json" })
	public ResultResDTO testAddData(@RequestBody TestNameReqDTO nameReqDTO) {
		ResultResDTO result = new ResultResDTO();
		try {
			long start = System.currentTimeMillis();
			String[] libraryName = nameReqDTO.getName().split(",");
			String libraryName1 = libraryName[0];
			String libraryName2 = libraryName[1];
			String chn = nameReqDTO.getChn();
			for (long i = 0; i < nameReqDTO.getSize(); i++) {
				GroupDetail detail = new GroupDetail();
				detail.setId(idIdx.incrementAndGet());
				detail.setCategory("category");
				detail.setPeopleId("peopleId" + i);
				detail.setFeatureId("featureId" + i);
				detail.setChn(chn);
				detail.setLibraryName1(libraryName1);
				detail.setLibraryName2(libraryName2);
				detail.setPeopleId("peopleId" + i);
				detail.setX(1);
				detail.setY(1);
				GroupDetail record = mapper.selectByUK(detail);
				if (record == null) {
					// insert
					mapper.insert(detail);
				} else {
					detail.setId(record.getId());
					mapper.updateByPrimaryKey(detail);
				}
			}
			System.out.println(String.format("渠道%s,人脸库%s,添加%s,耗时%s", nameReqDTO.getChn(),nameReqDTO.getName(), nameReqDTO.getSize(),
					System.currentTimeMillis() - start));
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("500");
			result.setMsg(e.getMessage());
		}
		return result;
	}

	@PostMapping(value = { "/testRemoveData" }, consumes = { "application/json" })
	public ResultResDTO testRemoveData(@RequestBody TestNameReqDTO nameReqDTO) {
		ResultResDTO result = new ResultResDTO();
		try {
			long start = System.currentTimeMillis();
			String[] libraryName = nameReqDTO.getName().split(",");
			String libraryName1 = libraryName[0];
			String libraryName2 = libraryName[1];
			String chn = nameReqDTO.getChn();
			for (long i = 0; i < nameReqDTO.getSize(); i++) {
				GroupDetail detail = new GroupDetail();
				detail.setId(i);
				detail.setCategory("category");
				detail.setPeopleId("peopleId" + i);
				detail.setFeatureId("featureId" + i);
				detail.setChn(chn);
				detail.setLibraryName1(libraryName1);
				detail.setLibraryName2(libraryName2);
				detail.setPeopleId("peopleId" + i);
				detail.setX(1);
				detail.setY(1);
				GroupDetail record = mapper.selectByUK(detail);
				if (record != null) {
					mapper.deleteByPrimaryKey(record.getId());
				}
			}
			System.out.println(String.format("渠道%s,人脸库%s,删除%s,耗时%s", nameReqDTO.getChn(),nameReqDTO.getName(), nameReqDTO.getSize(),
					System.currentTimeMillis() - start));
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("500");
			result.setMsg(e.getMessage());
		}
		return result;
	}
}
