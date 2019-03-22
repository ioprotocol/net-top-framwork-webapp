package net.top.framework.service;

import net.top.framework.domain.Account;
import net.top.framework.domain.Resource;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Project:net-top-framwork
 * @Package net.top.framework.service
 * @Description:
 * @author: xsy
 * @date： 2016/6/3
 * @version： V1.0
 */
public interface AccountService {
    /**
     * 登陆验证
     * @param account
     * @param password
     * @return
     */
    boolean authLogin(String account, String password, HttpServletRequest request);

    /**
     * 登录验证，判断用户是否已经登录过平台
     * @param request
     * @return
     */
    boolean isLogin(HttpServletRequest request);

    /**
     *
     * @param request
     */
    void logout(HttpServletRequest request);

    /**
     * 取得当前登录的账号
     * @param request
     * @return
     */
    Account getLoginAccount(HttpServletRequest request);

    /**
     * 更新session中的帐号信息
     * @param request
     */
    void updateLoginAccount(HttpServletRequest request);

    /**
     * 根据账号名称获得账号对象
     * @param account
     * @return
     */
    Account getAccount(String account);

    /**
     * 取得当前登录用户可访问的资源
     * @param request
     * @return
     */
    List<Resource> getResourceByLoginAccount(HttpServletRequest request);

    /**
     * 帐号查找
     * @param account
     * @param mobile
     * @param email
     * @param roleNumber
     * @param pageSize
     * @param pageStartNo
     * @return
     */
    List<Account> listAccount(String account, String mobile, String email, Integer roleNumber, Integer pageSize, Integer pageStartNo);

    /**
     *
     * @param account
     * @param mobile
     * @param email
     * @param roleNumber
     * @return
     */
    long countAccount(String account, String mobile, String email, Integer roleNumber);

    /**
     * 根据主键ID取得帐号
     * @param id
     * @return
     */
    Account getAccountById(Integer id);

    /**
     *
     * @param account
     */
    void saveOrUpdate(Account account);

    /**
     *
     * @param id
     */
    void del(Integer id);

    /**
     * 是否有权限操作系统某项资源
     * @param resourceNumber
     */
    boolean isPermitOperation(String resourceNumber, HttpServletRequest request);
}
