/**
 *====================================================
 * 文件名称: CommonController.java
 * 修订记录：
 * No    日期				作者(操作:具体内容)
 * 1.    2015年1月4日			Administrator(创建:创建文件)
 *====================================================
 * 类描述：(说明未实现或其它不应生成javadoc的内容)
 * 
 */
package com.nit.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ClassName: CommonController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @date 2015年1月4日 下午1:39:17
 */
@RequestMapping("/common")
@Controller("commonController")
public class CommonController {

	@RequestMapping(value = "/resource_not_found", method = RequestMethod.GET)
	public String resource_not_found(Model model) {
		return "/common/resource_not_found";
	}
}
