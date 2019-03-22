package net.top.framework.service;

import net.top.framework.dao.BaseDao;
import net.top.framework.domain.Resource;
import net.top.framework.domain.Role;
import net.top.framework.domain.RolePopedom;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Project:net-top-framwork
 * @Package net.top.framework.service
 * @Description:
 * @author: xsy
 * @date： 2016/6/27
 * @version： V1.0
 */
public class RoleServiceImpl implements RoleService{

    @Autowired
    private BaseDao baseDao;

    @Override
    public List<Role> listAll() {
        return baseDao.loadAll(Role.class);
    }

    @Override
    public void saveOrUpdate(Role role) {
        baseDao.saveOrUpdate(role);
    }

    @Override
    public void del(Integer number) {
        String query = " delete from Role where number=?";
        baseDao.bulkUpdate(query, new Integer[]{number});
        query = " delete from RolePopedom where roleNumber=?";
        baseDao.bulkUpdate(query, new Integer[]{number});
    }

    @Override
    public Role load(Integer number) {
        return baseDao.load(Role.class, number);
    }

    @Override
    public List<RolePopedom> listPrivilege(Integer roleNumber) {
        String query = "select p from RolePopedom p where p.roleNumber=?";
        return (List<RolePopedom>) baseDao.find(query, new Integer[]{roleNumber});
    }

    @Override
    public List<Resource> listResource() {
        String query = "select p from Resource p order by p.resourceTypeId asc,p.order asc";
        return (List<Resource>) baseDao.find(query, null);
    }

    @Override
    public void batchSaveRolePopedom(List<RolePopedom> list, Integer roleNumber) {
        String query = " delete from RolePopedom r where r.roleNumber=?";
        baseDao.bulkUpdate(query, new Integer[]{roleNumber});
        try {
            baseDao.batchInsert(list.toArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
