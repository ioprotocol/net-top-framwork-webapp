package net.top.framework.service;

import net.top.framework.domain.Resource;

import java.util.List;

/**
 * @Project:net-top-framwork
 * @Package net.top.framework.service
 * @Description:
 * @author: xsy
 * @date： 2016/6/26
 * @version： V1.0
 */
public interface ResourceService {
    /**
     * @param id
     * @return
     */
    List<Resource> listByTypeId(Integer id, Integer pageStartNo, Integer pageSize);

    /**
     *
     * @param typeId
     * @return
     */
    long count(Integer typeId);
    /**
     * @param number
     * @return
     */
    Resource load(String number);

    /**
     * @param resource
     * @return
     */
    void saveOrUpdate(Resource resource);

    /**
     *
     * @param number
     */
    void del(String number);
}
