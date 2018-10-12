package org.paasta.sample.app.controller;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.paasta.sample.app.entity.mysql.MysqlSample;
import org.paasta.sample.app.entity.postgresql.Sample;
import org.paasta.sample.app.mysql.MysqlSampleRepository;
import org.paasta.sample.app.postgresql.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@Controller
public class SampleController {
	
	@Autowired
	MysqlSampleRepository mysqlRepo;
	
	@Autowired
	SampleRepository postRepo;
	
	@Autowired
	Gson gson;
	
	@RequestMapping("/")
    public String home() {
		return "index";
    }
	
	@RequestMapping("/postgres")
	@ResponseBody
    public String postList() {
		System.out.println("여기로 오는 것 체크! 포스트 ");
		if(postRepo.count() != 0) {
			return gson.toJson(postRepo.findAll());
		}
		
		try {
			List<Sample> postList = (List<Sample>) sampleSetter("postgresql", Sample.class);
			postList.forEach(sample -> postRepo.save(sample));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return gson.toJson(postRepo.findAll());
    }
	
	@RequestMapping("/mysql")
	@ResponseBody
    public String mysqlList() {
		System.out.println("여기로 오는 것 체크! 마이에수 " + mysqlRepo.count());
		if(mysqlRepo.count() != 0) {
			return gson.toJson(mysqlRepo.findAll());
		}
		
		try {
			List<MysqlSample> mysqlList = (List<MysqlSample>) sampleSetter("mysql", MysqlSample.class);
			mysqlList.forEach(mysqlSample -> mysqlRepo.save(mysqlSample)); //mysqlRepo.save(mysqlSample)
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return gson.toJson(mysqlRepo.findAll());
    }
	
	private List<?> sampleSetter(String DBName, Object type) throws Exception {
		List<Object> resultList = new ArrayList<>();
		Object resultObject = null;
		Class<?> aClass = (Class<?>) type;
      
		Method methodSetId = aClass.getMethod("setId", Long.TYPE);
        Method methodSetName = aClass.getMethod("setName", String.class);
        Method methodSetEmail = aClass.getMethod("setEmail", String.class);

        
		for(int index=1; index<6; index++) {
			resultObject = ((Class) type).newInstance();
			methodSetId.invoke(resultObject, index);
			methodSetName.invoke(resultObject, DBName + "-sample" + index);
			methodSetEmail.invoke(resultObject, "sample" + index + "@sample.com");
			resultList.add(resultObject);
		}
		return resultList;
	}
	
}
