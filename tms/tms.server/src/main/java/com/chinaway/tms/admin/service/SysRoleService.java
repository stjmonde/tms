package com.chinaway.tms.admin.service;

import java.util.Map;
import com.chinaway.tms.admin.model.SysRole;
import com.chinaway.tms.core.BaseService;
import com.chinaway.tms.utils.page.PageBean;

public interface SysRoleService extends BaseService<SysRole, Integer> {

	/**
	 * 查询角色根据用户id
	 * @param integer
	 * @return
	 */
	SysRole queryRoleByUserId(int userId);

	/**
	 * 查询角色分页根据条件
	 * @param argsMap
	 * @return
	 */
	PageBean<SysRole> queRolByCtnPgBn(Map<String, Object> argsMap);

	/**
	 * 批量删除角色
	 * @param idsArray
	 * @return
	 */
	int deleteByIds(String[] idsArray);

}