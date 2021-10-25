package com.hdq.crowd.mvc.controller;

import com.github.pagehelper.PageInfo;
import com.hdq.crowd.entity.Admin;
import com.hdq.crowd.service.api.AdminService;
import com.hdq.crowd.util.CrowdConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @author hdq
 * @create 2021-10-21 15:01
 */
@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/toLogin")
    public String toLogin(Admin admin, Model model, HttpSession session){
        return "admin-login";
    }

    @RequestMapping("/do/logout")
    public String logout(Admin admin, Model model, HttpSession session){
        session.invalidate();
        return "forward:/admin-login";
    }

    @RequestMapping("/to/login")
    public String login(Admin admin, Model model, HttpSession session){
        return adminService.login(admin,model,session);
    }

    @RequestMapping("/to/keyword")
    public String keyword(@RequestParam(value = "keyword",defaultValue = "") String keyword,
                          @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                          @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                          Model model, HttpSession session){
        PageInfo<Admin> adminPageInfo = adminService.selectAdminKeyword(keyword, pageNum, pageSize);
        model.addAttribute(CrowdConstant.ATTR_NAME_PAGE_INFO, adminPageInfo);
        return "admin-page";
    }
//    admin/to/selectAllUser

    /**
     * 查询你所有用户
     * @param pageNum
     * @param pageSize
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/to/selectAllUser")
    public String selectAllUser( @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                 @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                 Model model, HttpSession session){
        PageInfo<Admin> adminPageInfo = adminService.selectAllUser(session, pageNum, pageSize);
        model.addAttribute(CrowdConstant.ATTR_NAME_PAGE_INFO, adminPageInfo);
        return "admin-page";
    }
}
