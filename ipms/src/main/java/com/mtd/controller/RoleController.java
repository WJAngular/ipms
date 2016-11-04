package com.mtd.controller;

/**
 * Created by Wooce Yang on 2015/12/21.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

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

import com.alibaba.fastjson.JSONObject;
import com.mtd.entity.Sys_Role;
import com.mtd.entity.Sys_Users;
import com.mtd.page.Page;
import com.mtd.service.PermissionService;
import com.mtd.service.RolePermissionService;
import com.mtd.service.RoleService;
import com.mtd.service.UserRoleService;

/**
 * 
 * @author wocco
 *
 */
@Controller
@RequestMapping("/role")
public class RoleController{

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private PermissionService permissionService;

    /**
     * Ĭ��ҳ��
     * @return
     */
    @RequiresPermissions("sys:role:")
    @RequestMapping("/getList")
    public String getList(HttpServletRequest request){
        Sys_Users user = (Sys_Users) request.getSession().getAttribute("sysuser");
        if (user != null) {
            String hql = "from Sys_Role t";
            String search = request.getParameter("search");

            if (search != null) {
                hql += " where t.name like '%" + search + "%'";
            }
            Page pageModel = new Page();
            String pageinfo = (String) request.getParameter("page");
            if (pageinfo != null) {
                int page = Integer.parseInt(pageinfo);
                if (page != 0) {
                    pageModel.setPage(page);
                }
            } else {
                pageModel.setPage(1);
            }

            pageModel.setPageSize(10);// ����ҳ����ʾ��� ֵ
            pageModel.setTotalCount(roleService.FindAll(hql).size()); // ���������
            pageModel.setNum(2); // ���õ�ǰҳ��ǰ�����,/**ǰ�����ʾ5ҳ**/
            // ͨ��ǰҳ��
            List<Object> list = roleService.findByPage(hql, pageModel.getPageSize(), pageModel.getPage());
            pageModel.setItems(list);
            request.setAttribute("count", list.size());
            request.setAttribute("list", list);
            request.setAttribute("pageModel", pageModel);
            request.setAttribute("page", pageModel.getPage());
            String obj = request.getQueryString();
            if (obj != null) {
                String[] fenge = obj.split("[ = & ]");
                String op = "&";
                for (int i = 0; i < fenge.length; i++) {
                    if ("page".equals(fenge[i])) {
                        op += fenge[i] + "=" + fenge[++i];
                        break;
                    }
                }
                obj = obj.replace(op, "");
            } else {
                obj = "";
            }
            String url = "http://" + request.getServerName() // ��������ַ
                    + ":" + request.getServerPort() // �˿ں�
                    + request.getContextPath() // ��Ŀ���
                    + request.getServletPath() // ����ҳ��������ַ
                    + "?" + (obj); // ���� ;
            request.setAttribute("pageuri", url);
            return "admin/admin_role";
        } else {
            return "/login";
        }
    }

    @RequiresPermissions("sys:role:add")
    @RequestMapping("/addRole")
    public String addRole(Sys_Role role, HttpServletRequest request) {
        roleService.save(role);
        return "redirect:/sysrole/getList.do";
    }

    /**
     * ��ɫ����(JSON)
     */
    /*
    @RequiresPermissions("sys:role:view")
    @RequestMapping(value="json",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getData(HttpServletRequest request) {
        Page<Sys_Role> page=getPage(request);
        List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
        page = roleService.search(page, filters);
        return getEasyUIData(page);
    } */

    /**
     * ��ȡ��ɫӵ�е�Ȩ��ID����
     * @return
     */
    @RequiresPermissions("sys:role:permView")
    @RequestMapping("/getPerms")
    public String getRolePermissions(HttpServletRequest request){
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            List<Integer> permissions = rolePermissionService.getPermissionIds(id);
            Sys_Role role = roleService.get(id);
            request.setAttribute("rolePerms", permissions);
            request.setAttribute("list", permissionService.getAll());
            request.setAttribute("role", role);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "admin/admin_role_permission";
    }

    /**
     * �޸Ľ�ɫȨ��
     * @return
     */
    @RequiresPermissions("sys:role:permUpd")
    @RequestMapping(value = "/updatePermission")
    public void updateRolePermission(HttpServletRequest request,HttpServletResponse resp){
        String result = "{\"result\":\"error\"}";
        resp.setContentType("application/json");
        Sys_Users user = (Sys_Users) request.getSession().getAttribute("sysuser");
        if (user != null) {
            try {
                Integer role_id = Integer.parseInt(request.getParameter("role_id"));
                String[] permIds = request.getParameterValues("perm_id");
                List newList = Arrays.asList(permIds);
                List<Integer> oldPermIdList = rolePermissionService.getPermissionIds(user.getId());
                rolePermissionService.updateRolePermission(role_id, oldPermIdList, newList);
                result = "{\"result\":\"success\"}";
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        try {
            resp.getWriter().write(result);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * ��ӽ�ɫ��ת
     * @param model
     * @return
     */
    @RequiresPermissions("sys:role:add")
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createForm(Model model) {
        model.addAttribute("role", new Sys_Role());
        model.addAttribute("action", "create");
        return "system/roleForm";
    }

    /**
     * ��ӽ�ɫ
     * @param role
     * @param model
     * @return
     */
    @RequiresPermissions("sys:role:add")
    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public String create(@Valid Sys_Role role,Model model) {
        roleService.save(role);
        return "success";
    }

    /**
     * �޸Ľ�ɫ��ת
     * @param id
     * @param model
     * @return
     */
    @RequiresPermissions("sys:role:update")
    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("role", roleService.get(id));
        model.addAttribute("action", "update");
        return "system/roleForm";
    }

    /**
     * �޸Ľ�ɫ
     * @param role
     * @param model
     * @return
     */
    @RequiresPermissions("sys:role:update")
    @RequestMapping("/updateRole")
    public String update(@Valid @ModelAttribute("role") Sys_Role role,Model model) {
        roleService.update(role);
        return "redirect:/role/getList.do";
    }

    /**
     * ɾ���ɫ
     * @param id
     * @return
     */
    @RequiresPermissions("sys:role:delete")
    @RequestMapping("/delRole")
    public void delete(Integer id, HttpServletRequest request, HttpServletResponse response) {
        JSONObject object = new JSONObject();
        int result = 1;
        String msg = "";
        Sys_Users user = (Sys_Users) request.getSession().getAttribute("sysuser");
        if (user != null) {
            try {
                Sys_Role role = roleService.get(id);
                if(role==null){
                    msg = "�����ڴ˽�ɫ";
                }
                else {
                    if (userRoleService.hasRole(id)) {
                        msg = "�����û�ʹ�øý�ɫ";
                    } else {
                        roleService.delete(role);
                        result = 0;
                    }
                }
            }
            catch (Exception ex){
                ex.printStackTrace();
                msg = ex.getMessage();
            }
        }
        object.put("result",result);
        object.put("msg",msg);
        response.setContentType("application/json");

        try {
            PrintWriter out = response.getWriter();
            out.write(object.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequiresPermissions("sys:role:view")
    @RequestMapping("/viewRole")
    public String viewRole(Integer id,HttpServletRequest request) {
        request.setAttribute("role", roleService.get(id));
        return "admin/admin_role_edit";
    }

}
