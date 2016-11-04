package com.leanit.subway.location.wifi;

import com.alibaba.fastjson.JSON;
import com.leanit.subway.common.bean.Position;
import com.leanit.subway.common.bean.Result;
import com.leanit.subway.common.bean.WifiInfoPosition;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by admin on 2016/11/3.
 */
@Controller
@RequestMapping("/location/wifi")
public class WifiLocationController {
    @Autowired
    WifiLocationService wifiLocationService;

    /**
     * 增加签名
     *
     * @param wifiInfo
     * @param x
     * @param y
     * @return
     */
    @RequestMapping(value = "/addSign", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Result addSign(@Param("wifiInfo") String wifiInfo, @Param("x") Double x, @Param("y") Double y, @Param("sense") String sense) {
        List<WifiInfoPosition> listWifiInfo = JSON.parseArray(wifiInfo, WifiInfoPosition.class);
        return wifiLocationService.saveWifiPosition(listWifiInfo, x, y, sense);
    }

    /**
     * 获取定位
     *
     * @param wifiInfo
     * @return
     */
    @RequestMapping(value = "/getPosition", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Position getPosition(@Param("wifiInfo") String wifiInfo) {
        return wifiLocationService.getPosition(wifiInfo);
    }
}
