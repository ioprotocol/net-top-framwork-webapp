package net.top.framework.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Project:net-top-framwork
 * @Package net.top.framework.service
 * @Description:
 * @author: xsy
 * @date： 2016/6/3
 * @version： V1.0
 */
@Configuration
public class SystemConfig {

    @Bean
    AccountService accountService() {
        return new AccountServiceImpl();
    }

    @Bean
    ResourceTypeService resourceTypeService() {
        return new ResourceTypeServiceImpl();
    }

    @Bean
    ResourceService resourceService() {
        return new ResourceServiceImpl();
    }

    @Bean
    RoleService roleService() {
        return new RoleServiceImpl();
    }

    @Bean
    LogService logService() {
        return new LogServiceImpl();
    }

    @Bean
    BaseService baseService() {
        return new BaseServiceImpl();
    }
}
