package com.dadao.user.entity;

/**
 * Created by NFY on 2017-07-16.
 */
public class UserRole {
    private Integer roleId;//int(11) NOT NULL AUTO_INCREMENT,
    private Long userId;//int(11) NOT NULL COMMENT '用户ID',
    private String roleName;//varchar(64) NOT NULL DEFAULT '' COMMENT '角色名称',
    private Integer roleStatus;//int(11) NOT NULL DEFAULT '1' COMMENT '角色状态，0无效、1有效',

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getRoleStatus() {
        return roleStatus;
    }

    public void setRoleStatus(Integer roleStatus) {
        this.roleStatus = roleStatus;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "roleId=" + roleId +
                ", userId=" + userId +
                ", roleName='" + roleName + '\'' +
                ", roleStatus=" + roleStatus +
                '}';
    }
}
