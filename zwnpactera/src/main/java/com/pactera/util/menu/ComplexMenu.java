package com.pactera.util.menu;/**
 * Created by bo on 2019/4/18.
 * 一级菜单包含二级菜单的封装
 */
public class ComplexMenu extends BasicButton {

    private BasicButton[] sub_button;

    public BasicButton[] getSub_button() {
        return sub_button;
    }

    public void setSub_button(BasicButton[] sub_button) {
        this.sub_button = sub_button;
    }

}
