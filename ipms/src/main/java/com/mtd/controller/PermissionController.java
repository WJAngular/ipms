package com.mtd.controller;

/**
 * Created by Wooce Yang on 2015/12/19.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mtd.common.AJAXResult;
import com.mtd.common.utils.UserUtil;
import com.mtd.entity.Sys_Permission;
import com.mtd.entity.Sys_Role;
import com.mtd.entity.Sys_Users;
import com.mtd.service.PermissionService;
import com.mtd.service.RolePermissionService;

/**
 * 权限controller
 */
@Controller
@RequestMapping("/permission")
public class PermissionController{

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @RequiresPermissions("sys:perm:view")
    @RequestMapping("/getList")
    public String getList(Integer id,HttpServletRequest request){
        Sys_Users user = (Sys_Users) request.getSession().getAttribute("sysuser");
        if (user != null) {
            Sys_Permission menu = permissionService.get(id);
            if(menu==null || !menu.getType().equals("F"))
                return "/error";
            List<Sys_Permission> permissionList = permissionService.listByHQL("from Sys_Permission t where t.pid='"+id+"'");
            request.setAttribute("menu", menu);
            request.setAttribute("list", permissionList);
            return "admin/admin_permission";
        } else {
            return "/login";
        }
    }

    /**
     * 添加权限/菜单
     */
    @RequiresPermissions("sys:perm:add")
    @RequestMapping(value = "/addPerm", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public AJAXResult addPermission(Sys_Permission perm, HttpServletRequest request) {
        AJAXResult result = new AJAXResult();
        result.code = 1;
        try {
            if (perm.getType().equals("F")) {
                List<Sys_Permission> list = permissionService.listByHQL("from Sys_Permission t where t.pid='" + perm.getPid() + "' and t.sort=" + perm.getSort());
                if (list.size() > 0) {
                    result.msg = "当前父菜单下已存在此排序号!";
                    return result;
                }
            } else {
            }
            permissionService.save(perm);
            result.code = 0;
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
        /* if(perm.isMenu())
            return "redirect:/permission/menuList.do";
        else
            return "redirect:/permission/getList.do?id="+perm.getPid(); */
    }

    @RequiresPermissions("sys:perm:view")
    @RequestMapping("/getPerm")
    public String getPermission(Integer id,HttpServletRequest request) {
        request.setAttribute("perm", permissionService.get(id));
        return "admin/admin_permission_edit";
    }

    /**
     * 修改权限/菜单
     */
    @RequiresPermissions("sys:perm:update")
    @RequestMapping(value = "updatePerm", method = RequestMethod.POST)
    @ResponseBody
    public AJAXResult update(@Valid @ModelAttribute("permission") Sys_Permission permission,Model model) {
        AJAXResult result = new AJAXResult();
        result.code = 1;
        try {
            if (permission.getType().equals("F") && permission.getPid()!=null) {
                //判断同一父菜单下排序号唯一性
                List<Sys_Permission> list = permissionService.listByHQL("from Sys_Permission t where t.id!="+permission.getId()+" and t.pid='" + permission.getPid() + "' and t.sort=" + permission.getSort());
                if (list.size() > 0) {
                    result.msg = "当前父菜单下已存在此排序号!";
                    return result;
                }
            } else {
            }
            if(permission.getType()==null)
                permission.setType("O");
            permissionService.update(permission);
            result.code = 0;
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
        /*if(permission.getType().equals("F"))
            return "redirect:/permission/menuList.do";
        else
            return "redirect:/permission/getList.do";
            */
    }

    /**
     * 删除权限
     */
    @RequiresPermissions("sys:perm:delete")
    @RequestMapping("/delete")
    @ResponseBody
    public AJAXResult delete(Integer id, HttpServletRequest request, HttpServletResponse response) {
        AJAXResult result = new AJAXResult();
        result.code = 1;
        Sys_Users user = (Sys_Users) request.getSession().getAttribute("sysuser");
        if (user != null) {
            try {
                Sys_Permission permission = permissionService.get(id);
                if(permission==null) {
                    result.msg = "不存在此权限!";
                    return result;
                }
                List<Sys_Role> roles = rolePermissionService.findRolesWithPermission(id);
                if( roles.size()>0 ){
                    result.msg = "已有角色采用了本权限,不能删除!";
                    return result;
                }
                permissionService.delete(permission);
                result.code = 0;
            }
            catch (Exception ex){
                ex.printStackTrace();
                result.msg = ex.getMessage();
            }
        }
        else{
            result.msg = "您已超时";
        }
        return result;
    }

    /**
     * 菜单页面
     */
    @RequiresPermissions("sys:perm:menu:list")
    @RequestMapping("/menuList")
    public String menuList(HttpServletRequest request){
        Sys_Users user = (Sys_Users) request.getSession().getAttribute("sysuser");
        if (user != null) {
            List<Sys_Permission> menus = permissionService.getMenus();

            request.setAttribute("list", menus);
            return "admin/admin_menu";
        } else {
            return "/login";
        }
    }

    @RequiresPermissions("sys:perm:view")
    @RequestMapping("/getMenu")
    public String getMenu(Integer id,HttpServletRequest request) {
        request.setAttribute("menu", permissionService.get(id));
        return "admin/admin_menu_edit";
    }

    /**
     * 权限集合(JSON)
     */
    @RequiresPermissions("sys:perm:view")
    @RequestMapping("/getData")
    public void getData(HttpServletResponse response) {
        List<Sys_Permission> permissionList = permissionService.getAll();
        net.sf.json.JSONArray object = net.sf.json.JSONArray.fromObject(permissionList);
        String reust = object.toString();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            PrintWriter out = response.getWriter();
            out.write(reust);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取菜单下的操作
     */
    @RequiresPermissions("sys:perm:view")
    @RequestMapping(value="/menuData",method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Sys_Permission> menuData(){
        List<Sys_Permission> menuList = permissionService.getMenus();
        return menuList;
    }

    /**
     * 获取菜单下的操作
     */
    @RequiresPermissions("sys:perm:view")
    @RequestMapping("ope/json")
    @ResponseBody
    public Map<String, Object> menuOperationDate(Integer pid){
        List<Sys_Permission> menuOperList = permissionService.getMenuOperation(pid);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", menuOperList);
        map.put("total",menuOperList.size());
        return map;
    }

    /**
     * 当前登录用户的权限集合
     */
    @RequestMapping("i/json")
    @ResponseBody
    public List<Sys_Permission> myPermissionDate() {
        List<Sys_Permission> permissionList = permissionService.getPermissions(UserUtil.getCurrentUser().getId());
        return permissionList;
    }

    /**
     * 某用户的权限集合
     */
    @RequiresPermissions("sys:perm:view")
    @RequestMapping("{userId}/json")
    @ResponseBody
    public List<Sys_Permission> otherPermissionDate(@PathVariable("userId") Integer userId) {
        List<Sys_Permission> permissionList=permissionService.getPermissions(userId);
        return permissionList;
    }

    /**
     * 添加权限跳转
     */
    @RequestMapping("/add")
    public String add(Integer menu_id,HttpServletRequest request) {
        try {
            Sys_Permission menu = permissionService.get(menu_id);
            if (menu != null) {
                request.setAttribute("menu", menu);
                return "admin/admin_permission_add";
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "/login";
    }

    /**
     * 添加菜单跳转
     */
    @RequestMapping(value = "menu/create", method = RequestMethod.GET)
    public String menuCreateForm(Model model) {
        model.addAttribute("permission", new Sys_Permission());
        model.addAttribute("action", "create");
        return "system/menuForm";
    }

    /**
     * 添加菜单基础操作
     * @return
     */
    @RequiresPermissions("sys:perm:add")
    @ResponseBody
    public String addMenuBase(Sys_Permission perm, HttpServletRequest request){
        Integer pid = Integer.parseInt(request.getParameter("pid"));
        String pname = request.getParameter("pname");
        permissionService.addBaseOpe(pid, pname);
        return "success";
    }

    /**
     * 修改权限跳转
     */
    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("permission", permissionService.get(id));
        model.addAttribute("action", "update");
        return "system/permissionForm";
    }

}
