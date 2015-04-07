/**
 *====================================================
 * 文件名称: MemberDaoImpl.java
 * 修订记录：
 * No    日期				作者(操作:具体内容)
 * 1.    2014年12月18日			Administrator(创建:创建文件)
 *====================================================
 * 类描述：(说明未实现或其它不应生成javadoc的内容)
 * 
 */
package com.nit.dao.impl;

import org.springframework.stereotype.Repository;

import com.nit.dao.MemberDao;
import com.nit.entity.Member;

/**
 * @ClassName: MemberDaoImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @date 2014年12月18日 下午4:46:42
 */
@Repository("memberDaoImpl")
public class MemberDaoImpl extends BaseDaoImpl<Member, Long> implements MemberDao {

}
