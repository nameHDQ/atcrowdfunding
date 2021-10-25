package com.hdq.crowd.mvc.controller;

import com.hdq.crowd.entity.Admin;
import com.hdq.crowd.entity.Student;
import com.hdq.crowd.service.api.AdminService;
import com.hdq.crowd.util.CrowdUtil;
import com.hdq.crowd.util.ResultEntity;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author hdq
 * @create 2021-10-18 16:07
 */
@Controller
public class testController {
    private final Logger logger = LoggerFactory.getLogger(testController.class);
    @Autowired
    private AdminService adminService;

    @ResponseBody
    @RequestMapping("/send/compose/object.do")
    public ResultEntity<Student> testReceiveComplicatedObject(@RequestBody Student student,HttpServletRequest request){
        System.out.println(student.toString());

        return ResultEntity.successWithoutData();
    }



    @ResponseBody
    @RequestMapping("/send/array1.do")
    public String testReceiveArrayOne(@RequestBody List<Integer> array){
        // 接收参数时需要在参数名后面加[]
        for (Integer number : array){
            System.out.println(number);
        }
        return "success";
    }
    @RequestMapping("/test/ssm.html")
    public String testSSM(ModelMap modelMap, HttpServletRequest request){
        boolean judgeResult = CrowdUtil.judgeRequestType(request);
        System.out.println(judgeResult);
        String name = null;
        System.out.println(name.length());
        
        List<Admin> adminList = adminService.getAll();
        modelMap.addAttribute("adminList",adminList);
        return "target";
    }
}
