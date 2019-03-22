package net.top.framework.service;

import net.top.framework.dao.BaseDao;
import net.top.framework.domain.Account;
import net.top.framework.domain.Resource;
import net.top.framework.domain.RolePopedom;
import net.top.framework.util.DesUtils;
import net.top.framework.util.Md5Utils;
import net.top.framework.util.MyUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Project:net-top-framwork
 * @Package net.top.framework.service
 * @Description:
 * @author: xsy
 * @date： 2016/6/3
 * @version： V1.0
 */
@Service
public class AccountServiceImpl implements AccountService{
    private static final String SESSION_ACCOUNT = "account";
    private static final String SESSION_ACCOUNT_NUMBER = "account.account";
    private static final String SESSION_ACCOUNT_IPADDR = "account.ipaddress";
    private static final String SESSION_ACCOUNT_LOGINTIME = "account.logintime";

    @Autowired
    private BaseDao baseDao;

    @Override
    public boolean authLogin(String account, String password, HttpServletRequest request) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Account.class);
        criteria.add(Restrictions.eq("account", account));
        criteria.add(Restrictions.eq("password", Md5Utils.MD5(password)));
        criteria.add(Restrictions.eq("isClose", 0));
        List<Account> list = (List<Account>) baseDao.findByCriteria(criteria);
        if(list == null || list.size() != 1)
            return false;

        HttpSession session = request.getSession(false);
        if(session == null) {
            session = request.getSession(true);
        }

        session.setAttribute(SESSION_ACCOUNT, list.get(0));
        session.setAttribute(SESSION_ACCOUNT_NUMBER, account);
        session.setAttribute(SESSION_ACCOUNT_IPADDR, request.getRemoteAddr());
        session.setAttribute(SESSION_ACCOUNT_LOGINTIME, new Date().getTime());

        return true;
    }

    @Override
    public boolean isLogin(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if ((session!=null)&&(session.getAttribute(SESSION_ACCOUNT_NUMBER)!=null)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Account getLoginAccount(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if ((session!=null)&&(session.getAttribute(SESSION_ACCOUNT_NUMBER)!=null)){
            return (Account) session.getAttribute(SESSION_ACCOUNT);
        }
        return null;
    }

    @Override
    public void updateLoginAccount(HttpServletRequest request) {
        Account sa = getLoginAccount(request);
        Account dba = getAccount(sa.getAccount());
        HttpSession session = request.getSession(false);
        if ((session!=null)&&(session.getAttribute(SESSION_ACCOUNT_NUMBER)!=null)){
            session.setAttribute(SESSION_ACCOUNT, dba);
        }
    }

    @Override
    public Account getAccount(String account) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Account.class);
        criteria.add(Restrictions.eq("account", account));
        List<Account> list = (List<Account>) baseDao.findByCriteria(criteria);
        if(list == null || list.size() != 1)
            return null;
        return list.get(0);
    }

    @Override
    public List<Resource> getResourceByLoginAccount(HttpServletRequest request) {
        Account account = getLoginAccount(request);

        String queryStr = "select r from Resource"
                + " r join RolePopedom p on p.resourceNumber=r.number join ResourceType t on t.id=r.resourceTypeId where r.isShow=1 and p.roleNumber=? order by t.order asc, r.order asc ";
        return (List<Resource>) baseDao.find(queryStr,new Integer[]{account.getRole().getNumber()});
    }

    @Override
    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null)
            session.invalidate();
    }

    @Override
    public List<Account> listAccount(String account, String mobile, String email, Integer roleNumber, Integer pageSize, Integer pageStartNo) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Account.class);
        if(!MyUtils.isBlank(account)) {
            criteria.add(Restrictions.eq("account", account));
        }
        if(!MyUtils.isBlank(mobile)) {
            criteria.add(Restrictions.eq("mobile", mobile));
        }
        if(!MyUtils.isBlank(email)) {
            criteria.add(Restrictions.eq("email", email));
        }
        if(!MyUtils.isBlank(roleNumber)) {
            criteria.add(Restrictions.eq("roleNumber", roleNumber));
        }
        return (List<Account>) baseDao.findByCriteria(criteria, pageStartNo, pageSize);
    }

    @Override
    public long countAccount(String account, String mobile, String email, Integer roleNumber) {
        String query = "select count(*) from Account ";
        String whereStr = "";
        ArrayList<Object> params = new ArrayList<>();
        if(!MyUtils.isBlank(account)) {
            whereStr += "account=?";
            params.add(account);
        }
        if(!MyUtils.isBlank(mobile)) {
            if(whereStr.length() > 0)
                whereStr += " and ";
            whereStr += "mobile=?";
            params.add(mobile);
        }
        if(!MyUtils.isBlank(email)) {
            if(whereStr.length() > 0)
                whereStr += " and ";
            whereStr += "email=?";
            params.add(email);
        }
        if(!MyUtils.isBlank(roleNumber)) {
            if (whereStr.length() > 0)
                whereStr += " and ";
            whereStr += "roleNumber=?";
            params.add(roleNumber);
        }
        if (whereStr.length() > 0)
            whereStr = " where " + whereStr;

        return baseDao.count(query + whereStr, params.toArray());
    }

    @Override
    public void saveOrUpdate(Account account) {
        baseDao.saveOrUpdate(account);
    }

    @Override
    public void del(Integer accountId) {
        String query = " delete from Account where id=?";
        baseDao.bulkUpdate(query, new Integer[]{accountId});
    }

    @Override
    public Account getAccountById(Integer id) {
        return baseDao.load(Account.class, id);
    }

    @Override
    public boolean isPermitOperation(String resourceNumber, HttpServletRequest request) {
        Account account = getLoginAccount(request);
        if(account == null)
            return false;

        DetachedCriteria criteria = DetachedCriteria.forClass(RolePopedom.class);
        criteria.add(Restrictions.eq("roleNumber", account.getRoleNumber()));
        criteria.add(Restrictions.eq("resourceNumber", resourceNumber));

        if(baseDao.findByCriteria(criteria).size() != 1)
            return false;
        return true;
    }
}
