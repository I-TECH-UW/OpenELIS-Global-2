package org.openelisglobal.menu.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.openelisglobal.menu.service.MenuService;
import org.openelisglobal.menu.util.MenuItem;
import org.openelisglobal.menu.util.MenuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/rest/menu")
    public List<MenuItem> getMenuTree() {
        return MenuUtil.getMenuTree();
    }

    @GetMapping("/rest/menu/{elementId}")
    public Optional<MenuItem> getMenuTree(@PathVariable String elementId) {
        return MenuUtil.getMenuTree()//
                .stream().filter(menuItem -> elementId.equals(menuItem.getMenu().getElementId()))
                .findFirst();
    }

    @PostMapping("/rest/menu/{elementId}")
    public MenuItem postMenuTree(@PathVariable String elementId, @RequestBody MenuItem menuItem) {
        return menuService.save(menuItem);
    }

}
