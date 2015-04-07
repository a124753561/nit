/**
 *====================================================
 * 文件名称: ProductController.java
 * 修订记录：
 * No    日期				作者(操作:具体内容)
 * 1.    2014年12月19日			Administrator(创建:创建文件)
 *====================================================
 * 类描述：(说明未实现或其它不应生成javadoc的内容)
 * 
 */
package com.nit.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ClassName: ProductController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @date 2014年12月19日 下午4:08:08
 */
@Controller("adminProductController")
@RequestMapping("/admin/product")
public class ProductController {

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		return "/admin/list";

	}

}
