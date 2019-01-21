package org.Dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.ocr.AuthService;
import org.ocr.PictureUtil;

import java.util.Map;


public class OCRSearch {
    private static Logger logger = Logger.getLogger(OCRSearch.class);
    public static String ocrSearch(String date){

        Map map = (Map) JSON.parse(date);
        String base64 = String.valueOf(map.get("base64"));
        String uuid = String.valueOf(map.get("uuid"));

        logger.info("base64----------"+base64);
        logger.info("uuid------------"+uuid);

        String message=null;
        String state;

        String httpUrl = " https://aip.baidubce.com/rest/2.0/ocr/v1/accurate_basic?access_token=" + AuthService.getAuth();
        String httpArg = "image=" + base64;
        String jsonResult = PictureUtil.request(httpUrl, httpArg);
        System.out.println("返回的结果---------> " + jsonResult);


        JSONObject jsonObject = JSONObject.parseObject(jsonResult);
        String words_result = jsonObject.getString("words_result");
        JSONArray result_array = JSON.parseArray(words_result);
        for (int i = 0; i < result_array.size(); i++) {

            System.out.println(result_array.getJSONObject(i).getString("words"));
            message+= result_array.getJSONObject(i).getString("words")+" ";

        }

        //识别OCR翻译后的关键信息(待写)
        String expCode="";
        String expNo="";
        CommonSearch commonSearch = new CommonSearch();
        state = commonSearch.commonSearch(uuid,expCode,expNo);

        logger.info("state=----------"+state);
        return state;
    }
}
