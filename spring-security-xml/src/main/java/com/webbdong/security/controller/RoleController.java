package com.webbdong.security.controller;

import com.webbdong.security.domain.SysRole;
import com.webbdong.security.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor(onConstructor_={@Autowired})
@Controller
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    @RequestMapping("/findAll")
    public String findAll(Model model) {
        List<SysRole> list = roleService.findAll();
        model.addAttribute("list", list);
        return "role-list";
    }

    @RequestMapping("/save")
    public String save(SysRole role) {
        roleService.save(role);
        return "redirect:findAll";
    }
    
}
