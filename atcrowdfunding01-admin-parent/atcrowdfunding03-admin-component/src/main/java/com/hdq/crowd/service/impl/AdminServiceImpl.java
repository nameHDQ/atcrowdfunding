package com.hdq.crowd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hdq.crowd.entity.Admin;
import com.hdq.crowd.entity.AdminExample;
import com.hdq.crowd.mapper.AdminMapper;
import com.hdq.crowd.service.api.AdminService;
import com.hdq.crowd.util.CrowdConstant;
import com.hdq.crowd.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

/**
 * @author hdq
 * @create 2021-10-18 12:14
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public void saveAdmin(Admin admin) {
        adminMapper.insert(admin);
    }

    @Override
    public List<Admin> getAll() {
        List<Admin> admins = adminMapper.selectByExample(new AdminExample());
        return admins;
    }

    @Override
    public String login(Admin admin, Model model, HttpSession session) {
        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andUserNameEqualTo(admin.getUserName());
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        if (admins.size() > 0) {
            /**
             * 逻辑更严谨的比较
             * return (a == b) || (a != null && a.equals(b));
             */
            if (Objects.equals(admins.get(0).getUserPswd(),MD5Util.string2MD5(admin.getUserPswd()))) {
                session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN, admins.get(0));
                System.out.println(admins.get(0).getUserName());
                return "redirect:/admin-main";
            } else {
                model.addAttribute("message", "密码不正确");
                return "admin-login";
            }
        } else {
            model.addAttribute("message", "账号不存在");
            return "admin-login";
        }



    }

    @Override
    public PageInfo<Admin> selectAdminKeyword(String Keyword, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Admin> adminList = adminMapper.selectAdminKeyword(Keyword);
        PageInfo page = new PageInfo(adminList);
        return page;
    }

    /**
     * 用户管理界面查找所有用户
     * @param model
     * @param session
     * @return
     */
    @Override
    public PageInfo<Admin> selectAllUser(HttpSession session, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Admin> adminList = adminMapper.selectByExample(new AdminExample());
        PageInfo page = new PageInfo(adminList);
        return page;
    }
}
