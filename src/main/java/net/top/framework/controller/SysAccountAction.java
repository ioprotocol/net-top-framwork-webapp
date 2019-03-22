package net.top.framework.controller;

import net.top.framework.domain.Account;
import net.top.framework.service.AccountService;
import net.top.framework.util.JsonUtil;
import net.top.framework.util.Md5Utils;
import net.top.framework.webform.DataTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Project:net-top-framwork
 * @Package net.top.framework.controller
 * @Description: 帐号管理
 * @author: xsy
 * @date： 2016/6/28
 * @version： V1.0
 */
@Controller
public class SysAccountAction {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "system/account/list")
    @ResponseBody
    public String list(Integer roleNumber, String account, String mobile, String email, Integer draw, Integer start, Integer length) {
        if(start == null)
            start = 0;
        if(length == null)
            length = 10;
        List<Account> accountList = accountService.listAccount(account, mobile, email, roleNumber, length, start);
        long totalRows = accountService.countAccount(account, mobile, email, roleNumber);
        return JsonUtil.objectToJsonString(new DataTable(draw, totalRows, accountList));
    }

    @RequestMapping(value = "system/account/changeAccountStatus")
    @ResponseBody
    public String changeAccountStatus(Integer id) {
        Account account = accountService.getAccountById(id);

        if(account.getRoleNumber() == 1) {
            return "-1";
        }
        if(account.getIsClose() == 0) {
            account.setIsClose(1);
        } else {
            account.setIsClose(0);
        }
        accountService.saveOrUpdate(account);
        return "0";
    }

    @RequestMapping(value = "system/account/resetpwd")
    @ResponseBody
    public String resetpwd(Integer id) {
        try {
            Account account = accountService.getAccountById(id);
            account.setPassword(Md5Utils.MD5("123456"));
            accountService.saveOrUpdate(account);
            return "0";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "-1";
    }

    @RequestMapping(value = "system/account/del")
    @ResponseBody
    public String del(Integer id) {
        Account account = accountService.getAccountById(id);

        if(account.getRoleNumber() == 1) {
            return "-1";
        }
        accountService.del(id);
        return "0";
    }

    @RequestMapping(value = "system/account/get")
    @ResponseBody
    public String get(Integer id) {
        if(id == null)
            return "";
        Account account = accountService.getAccountById(id);
        return JsonUtil.objectToJsonString(account);
    }

    @RequestMapping(value = "system/account/getCurrentLogin")
    @ResponseBody
    public String getCurrentLogin(HttpServletRequest request) {
        Account account = accountService.getLoginAccount(request);
        return JsonUtil.objectToJsonString(account);
    }

    @RequestMapping(value = "system/account/editpwd")
    @ResponseBody
    public String editpwd(String oldPwd, String newPwd, String newPwdCfm, HttpServletRequest request) {
        Account account = accountService.getLoginAccount(request);
        if(!account.getPassword().equalsIgnoreCase(Md5Utils.MD5(oldPwd))) {
            return "-1";
        }
        if(!newPwd.equalsIgnoreCase(newPwdCfm)) {
            return "-1";
        }
        account.setPassword(Md5Utils.MD5(newPwd));
        accountService.saveOrUpdate(account);
        return "0";
    }

    @RequestMapping(value = "system/account/save")
    @ResponseBody
    public String save(String json, HttpServletRequest request) {
        try {
            Account account = JsonUtil.jsonToObject(json, Account.class);
            if(account.getId() != null) {
                Account loginAccount = accountService.getAccount(account.getAccount());
                if(account.getId() == 1)
                    account.setRoleNumber(1);
                account.setPassword(loginAccount.getPassword());
            }  else {
                account.setPassword(Md5Utils.MD5("123456"));
            }
            accountService.saveOrUpdate(account);
            return "0";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "-1";
    }

    @RequestMapping(value = "system/account/editPersonalAccount")
    @ResponseBody
    public String editPersonalAccount(String json, HttpServletRequest request) {
        try {
            Account account = JsonUtil.jsonToObject(json, Account.class);
            if(account.getId() != null) {
                Account loginAccount = accountService.getLoginAccount(request);
                if(!loginAccount.getId().equals(account.getId())) {
                    return "-1";
                }
                if(account.getId() == 1)
                    account.setRoleNumber(1);
                account.setPassword(loginAccount.getPassword());
                accountService.saveOrUpdate(account);
                accountService.updateLoginAccount(request);
                return "0";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "-1";
    }
}
