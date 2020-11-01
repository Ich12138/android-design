package org.Dao;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;


public class OutSearch {
    private static Logger logger = Logger.getLogger(OutSearch.class);

    public static String outSearch(String date) {
        logger.info("-------------匹配接口 outSearch 成功！已进入方法-------------------");

        String state;

        Map map = (Map) JSON.parse(date);

        String uuid = String.valueOf(map.get("uuid"));
        String expCode = String.valueOf(map.get("expCode"));
        String expNo = String.valueOf(map.get("expNo"));

        logger.info("uuid" + uuid);
        logger.info("expCode" + expCode);
        logger.info("expNo" + expCode);

        CommonSearch commonSearch = new CommonSearch();
        state = commonSearch.commonSearch(uuid, expCode, expNo);

        logger.info("state------------" + state);
        return state;

    }
}
