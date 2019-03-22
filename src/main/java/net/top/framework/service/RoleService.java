package net.top.framework.service;

import net.top.framework.domain.Resource;
import net.top.framework.domain.Role;
import net.top.framework.domain.RolePopedom;

import java.util.List;

/**
 * @Project:net-top-framwork
 * @Package net.top.framework.service
 * @Description:
 * @author: xsy
 * @date： 2016/6/27
 * @version： V1.0
 */
public interface RoleService {
    /**
     *
     * @return
     */
    List<Role> listAll();

    /**
     *
     * @param number
     * @return
     */
    Role load(Integer number);
    /**
     *
     * @param role
     */
    void saveOrUpdate(Role role);

    /**
     *
     * @param number
     */
    void del(Integer number);

    /**
     *
     * @param roleNumber
     * @return
     */
    List<RolePopedom> listPrivilege(Integer roleNumber);

    /**
     * 列出系统所有权限控制点
     * @return
     */
    List<Resource> listResource();

    /**
     *
     * @param list
     */
    void batchSaveRolePopedom(List<RolePopedom> list, Integer roleNumber);
}
