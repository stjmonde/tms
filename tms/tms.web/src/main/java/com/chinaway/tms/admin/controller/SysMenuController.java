package com.chinaway.tms.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinaway.tms.admin.model.SysMenu;
import com.chinaway.tms.admin.service.SysMenuService;
import com.chinaway.tms.utils.json.JsonUtil;
import com.chinaway.tms.utils.page.PageBean;
import com.chinaway.tms.vo.Result;

@Controller
@RequestMapping(value = "/sysMenu")
public class SysMenuController {

	@Autowired
	private SysMenuService sysMenuService;

	
	/**
	 * 用户登录<br>
	 * 返回用户的json串
	 * 
	 * @param userInfo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/ajaxLoginGetMenuList")
	@ResponseBody
	public String AjaxLoginGetMenuList(HttpServletRequest request) {
		//连表查询角色信息
		List<SysMenu> sysMenuList = (List<SysMenu>)request.getSession().getAttribute("sysMenuList");

		String ret = JsonUtil.obj2JsonStr(sysMenuList);
		return ret;
	}
	
	/**
	 * 根据所有菜单信息<br>
	 * 返回用户的json串
	 * 
	 * @param deptInfo
	 * @return
	 */
	@RequestMapping(value = "/queryAllMenu")
	@ResponseBody
	public Result queryAllMenu(@RequestParam(value="page", defaultValue="1") int pageNo, 
			@RequestParam(value="rows", defaultValue="10") int pageSize , @RequestParam(value="sysMenu") String sysMenu) {
		SysMenu menu = (SysMenu)JsonUtil.jsonStr2Obj(sysMenu, SysMenu.class);
		
		Map<String, Object> argsMap = new HashMap<String, Object>();
		argsMap.put("pageNo", pageNo);
		argsMap.put("pageSize", pageSize);
		if(null != menu){
			argsMap.put("name", menu.getName());
			argsMap.put("levels", menu.getLevels());
			argsMap.put("menutype", menu.getMenutype());
			argsMap.put("pid", menu.getPid());
			argsMap.put("requesturl", menu.getRequesturl());
			argsMap.put("sotid", menu.getSotid());
		}
		
		Map<String, Object> resultMap = new HashMap<>();
		int code = 1;
		String msg = "查询所有菜单操作失败!";
		int ret = 0;
		try {
			PageBean<SysMenu> sysMenuPgBn = sysMenuService.selectMenu2PageBean(argsMap);
			if(null != sysMenuPgBn && null != sysMenuPgBn.getResult()){
				ret = sysMenuPgBn.getResult().size();
			}
			
			if (ret > 0) {
				code = 0;
				msg = "查询所有菜单操作成功!";
				resultMap.put("sysMenuList", sysMenuPgBn.getResult());
			}

		} catch (Exception e) {
			e.getStackTrace();
		}

		resultMap.put("code", code);
		resultMap.put("msg", msg);
		Result result = new Result(code, resultMap, msg);

		return result;
	}
	
	/**
	 * 根据条件查询单个部门信息<br>
	 * 返回用户的json串
	 * 
	 * @param deptInfo
	 * @return
	 */
	@RequestMapping(value = "/queryOneById")
	@ResponseBody
	public Result queryOneById(@RequestParam(value="id") String id) {
		Map<String, Object> resultMap = new HashMap<>();
		int code = 1;
		String msg = "根据id查询菜单操作失败!";

		try {
			SysMenu sysMenu = sysMenuService.selectById(id == "" ? 0 : Integer.parseInt(id));

			if (null != sysMenu) {
				code = 0;
				msg = "根据id查询菜单操作成功!";
				resultMap.put("sysMenu", sysMenu);
			}

		} catch (Exception e) {
			e.getStackTrace();
		}

		resultMap.put("code", code);
		resultMap.put("msg", msg);
		Result result = new Result(code, resultMap, msg);

		return result;
	}
	
	/**
	 * 添加菜单信息<br>
	 * 返回用户的json串
	 * 
	 * @param menuInfo
	 * @return
	 */
	@RequestMapping(value = "/addMenu")
	@ResponseBody
	public Result addMenu(@RequestParam(value="sysMenu") String sysMenu) {
		SysMenu menu = (SysMenu)JsonUtil.jsonStr2Obj(sysMenu, SysMenu.class);
		
		Map<String, Object> resultMap = new HashMap<>();
		int code = 1;
		String msg = "添加菜单操作失败!";

		int ret = 0;
		try {
			ret = sysMenuService.insert(menu);
			if (ret > 0) {
				code = 0;
				msg = "添加菜单操作成功!";
			}

		} catch (Exception e) {
			e.getStackTrace();
		}

		resultMap.put("code", code);
		resultMap.put("msg", msg);
		Result result = new Result(code, resultMap, msg);

		return result;
	}
	
	/**
	 * 批量删除用户信息<br>
	 * 返回用户的json串
	 * 
	 * @param userInfo
	 * @return
	 */
	@RequestMapping(value = "/bathDelMenu")
	@ResponseBody
	public Result bathDelMenu(@RequestParam(value="ids") String ids) {
		Map<String, Object> resultMap = new HashMap<>();
		int code = 1;
		String msg = "批量删除操作失败!";

		int ret = 0;
		try {
			String idsArray[] = ids.split(",");
			ret = sysMenuService.deleteByIds(idsArray);

			if (ret > 0) {
				code = 0;
				msg = "批量删除操作成功!";
			}
		} catch (Exception e) {
			e.getStackTrace();
		}

		resultMap.put("code", code);
		resultMap.put("msg", msg);
		Result result = new Result(code, resultMap, msg);

		return result;
	}
	
	/**
	 * 删除菜单信息<br>
	 * 返回用户的json串
	 * 
	 * @param userInfo
	 * @return
	 */
	@RequestMapping(value = "/delMenu")
	@ResponseBody
	public Result delMenu(@RequestParam(value="id") String id) {
		Map<String, Object> resultMap = new HashMap<>();
		int code = 1;
		String msg = "删除操作失败!";

		int ret = 0;
		try {
			ret = sysMenuService.deleteById(id);

			if (ret > 0) {
				code = 0;
				msg = "删除操作成功!";
			}
		} catch (Exception e) {
			e.getStackTrace();
		}

		resultMap.put("code", code);
		resultMap.put("msg", msg);
		Result result = new Result(code, resultMap, msg);

		return result;
	}
	
	/**
	 * 修改菜单信息<br>
	 * 返回用户的json串
	 * 
	 * @param userInfo
	 * @return
	 */
	@RequestMapping(value = "/updateMenu")
	@ResponseBody
	public Result updateMenu(@RequestParam(value="sysMenu") String sysMenu) {
		SysMenu menu = (SysMenu)JsonUtil.jsonStr2Obj(sysMenu, SysMenu.class);
		
		Map<String, Object> resultMap = new HashMap<>();
		int code = 1;
		String msg = "修改菜单操作失败!";

		int ret = 0;
		try {
			ret = sysMenuService.update(menu);

			if (ret > 0) {
				code = 0;
				msg = "修改菜单操作成功!";
			}
		} catch (Exception e) {
			e.getStackTrace();
		}

		resultMap.put("code", code);
		resultMap.put("msg", msg);
		Result result = new Result(code, resultMap, msg);

		return result;
	}
	
}