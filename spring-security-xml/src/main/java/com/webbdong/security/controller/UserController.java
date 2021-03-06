package com.webbdong.security.controller;

import com.webbdong.security.domain.SysUser;
import com.webbdong.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor(onConstructor_={@Autowired})
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @RequestMapping("/findAll")
    public String findAll(Model model) {
        List<SysUser> list = userService.findAll();
        model.addAttribute("list", list);
        return "user-list";
    }

    @RequestMapping("/save")
    public String save(SysUser user) {
        userService.save(user);
        return "redirect:findAll";
    }

    @RequestMapping("/toAddRolePage")
    public String toAddRolePage(Model model, Integer id, boolean success) {
        Map<String, Object> map = userService.toAddRolePage(id);
        model.addAttribute("uid", id);
        model.addAttribute("allRoles", map.get("allRoles"));
        model.addAttribute("myRoles", map.get("myRoles"));
        if (success) {
            model.addAttribute("success", "修改成功");
        }
        return "user-role-add";
    }

    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(Integer[] ids, Integer userId) {
        userService.addRoleToUser(userId, ids);
        return "redirect:toAddRolePage?success=" + true + "&id=" + userId;
    }

}
