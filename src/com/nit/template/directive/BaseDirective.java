/**
 *====================================================
 * 文件名称: BaseDirective.java
 * 修订记录：
 * No    日期				作者(操作:具体内容)
 * 1.    2015年1月9日			Administrator(创建:创建文件)
 *====================================================
 * 类描述：(说明未实现或其它不应生成javadoc的内容)
 * 
 */
package com.nit.template.directive;

import java.io.IOException;

import com.nit.util.FreemarkerUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * @ClassName: BaseDirective
 * @Description: 模板指令 - 基类
 * @author Administrator
 * @date 2015年1月9日 上午10:35:14
 */
public abstract class BaseDirective implements TemplateDirectiveModel {
	
	
	/**
	 * 设置局部变量
	 * @param name 名称
	 * @param value 变量值
	 * @param env Environment
	 * @param body TemplateDirectiveBody
	 */
	protected void setLocalVariable(String name, Object value, Environment env, TemplateDirectiveBody body) throws TemplateException, IOException {
		TemplateModel sourceVariable = FreemarkerUtils.getVariable(name, env);
		FreemarkerUtils.setVariable(name, value, env);
		body.render(env.getOut());
		FreemarkerUtils.setVariable(name, sourceVariable, env);
	}
}
