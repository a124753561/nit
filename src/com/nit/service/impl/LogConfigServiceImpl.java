/**
 *====================================================
 * 文件名称: LogConfigServiceImpl.java
 * 修订记录：
 * No    日期				作者(操作:具体内容)
 * 1.    2014年12月19日			Administrator(创建:创建文件)
 *====================================================
 * 类描述：(说明未实现或其它不应生成javadoc的内容)
 * 
 */
package com.nit.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nit.CommonAttributes;
import com.nit.LogConfig;
import com.nit.service.LogConfigService;

/**
 * 日志配置
 * @ClassName: LogConfigServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @date 2014年12月19日 下午1:09:40
 */
@Service("logConfigServiceImpl")
@Transactional(readOnly=true)
public class LogConfigServiceImpl implements LogConfigService {

	/*
	 * (non-Javadoc)
	 * @see com.nit.service.LogConfigService#getAll()
	 */
	@SuppressWarnings("unchecked")
	public List<LogConfig> getAll() {
		try {
			File xmlFile = new ClassPathResource(CommonAttributes.WIT_XML_PATH).getFile();
			Document doc = new SAXReader().read(xmlFile);
			List<Element> nodes = doc.selectNodes("/nit/logConfig");
			List<LogConfig> logConfigs = new ArrayList<LogConfig>();
			for(Element element:nodes){
				String name=element.attributeValue("name");
				String value=element.attributeValue("value");
				LogConfig logConfig = new LogConfig();
				logConfig.setOperation(name);
				logConfig.setUrlPattern(value);
				logConfigs.add(logConfig);
			}
			return logConfigs;
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
