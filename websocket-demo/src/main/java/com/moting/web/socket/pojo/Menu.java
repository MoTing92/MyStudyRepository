package com.moting.web.socket.pojo;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table menu
 *
 * @mbggenerated do_not_delete_during_merge
 */
public class Menu {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu.menu_id
     *
     * @mbggenerated
     */
    private Integer menuId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu.menu_desc
     *
     * @mbggenerated
     */
    private String menuDesc;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu.menu_name
     *
     * @mbggenerated
     */
    private String menuName;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu.menu_id
     *
     * @return the value of menu.menu_id
     *
     * @mbggenerated
     */
    public Integer getMenuId() {
        return menuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu.menu_id
     *
     * @param menuId the value for menu.menu_id
     *
     * @mbggenerated
     */
    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu.menu_desc
     *
     * @return the value of menu.menu_desc
     *
     * @mbggenerated
     */
    public String getMenuDesc() {
        return menuDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu.menu_desc
     *
     * @param menuDesc the value for menu.menu_desc
     *
     * @mbggenerated
     */
    public void setMenuDesc(String menuDesc) {
        this.menuDesc = menuDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu.menu_name
     *
     * @return the value of menu.menu_name
     *
     * @mbggenerated
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu.menu_name
     *
     * @param menuName the value for menu.menu_name
     *
     * @mbggenerated
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
}