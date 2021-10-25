package com.hdq.crowd.service.api;

import com.github.pagehelper.PageInfo;
import com.hdq.crowd.entity.Admin;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author hdq
 * @create 2021-10-18 12:14
 */
public interface AdminService {
    void saveAdmin(Admin admin);

    List<Admin> getAll();

    String login(Admin admin, Model model, HttpSession session);
    PageInfo<Admin> selectAdminKeyword(String Keyword, Integer pageNum, Integer pageSize);

    PageInfo<Admin> selectAllUser(HttpSession session, Integer pageNum, Integer pageSize);
}
