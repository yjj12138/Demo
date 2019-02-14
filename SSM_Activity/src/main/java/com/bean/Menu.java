package com.bean;

import java.util.List;

public class Menu {
    private Integer menuid;

    private String menuname;

    private Integer upmenuid;

    private String menupath;

    private Integer menustate;

    private String menuremark;

    private List<Role> role;
    //二级菜单
    private List<Menu> second;

    public List<Menu> getSecond() {
        return second;
    }

    public void setSecond(List<Menu> second) {
        this.second = second;
    }

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }

    public Integer getMenuid() {
        return menuid;
    }

    public void setMenuid(Integer menuid) {
        this.menuid = menuid;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname == null ? null : menuname.trim();
    }

    public Integer getUpmenuid() {
        return upmenuid;
    }

    public void setUpmenuid(Integer upmenuid) {
        this.upmenuid = upmenuid;
    }

    public String getMenupath() {
        return menupath;
    }

    public void setMenupath(String menupath) {
        this.menupath = menupath == null ? null : menupath.trim();
    }

    public Integer getMenustate() {
        return menustate;
    }

    public void setMenustate(Integer menustate) {
        this.menustate = menustate;
    }

    public String getMenuremark() {
        return menuremark;
    }

    public void setMenuremark(String menuremark) {
        this.menuremark = menuremark == null ? null : menuremark.trim();
    }
}