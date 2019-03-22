package net.top.framework.service;

import net.top.framework.domain.ResourceType;

import java.io.Serializable;
import java.util.List;

/**
 * @Project:net-top-framwork
 * @Package net.top.framework.service
 * @Description:
 * @author: xsy
 * @date： 2016/6/13
 * @version： V1.0
 */
public interface ResourceTypeService {

    /**
     * 列出所有资源类型
     * @return
     */
    List<ResourceType> listAll();

    /**
     *
     * @param id
     * @return
     */
    ResourceType load(Integer id);

    /**
     *
     * @param resourceType
     */
    Serializable save(ResourceType resourceType);

    /**
     *
     * @param resourceType
     */
    void update(ResourceType resourceType);

    /**
     *
     * @param id
     */
    void del(Integer id);
}
