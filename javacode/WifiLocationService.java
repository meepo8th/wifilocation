package com.leanit.subway.location.wifi;

import com.alibaba.fastjson.JSON;
import com.leanit.subway.common.bean.Position;
import com.leanit.subway.common.bean.Result;
import com.leanit.subway.common.bean.WifiInfo;
import com.leanit.subway.common.bean.WifiInfoPosition;
import com.leanit.subway.common.dao.CommonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2016/11/3.
 */
@Service
public class WifiLocationService {
    @Autowired
    CommonDao commonDao;
    private static final String POSITION_SPLIT = "#@#";
    private static HashMap.SimpleEntry<Long, List<WifiInfoPosition>> cacheEntry;

    /**
     * 保存wifi标签信息
     *
     * @param wifiInfos
     * @param x
     * @param y
     * @param sense
     * @return
     */
    public Result saveWifiPosition(List<WifiInfoPosition> wifiInfos, Double x, Double y, String sense) {
        if (null != wifiInfos) {
            for (WifiInfoPosition wifiInfoPosition : wifiInfos) {
                wifiInfoPosition.setX(x);
                wifiInfoPosition.setY(y);
                wifiInfoPosition.setSense(sense);
                commonDao.insertCommon("T_WIFI_POSITION", wifiInfoPosition);
            }
        }
        return new Result();
    }

    /**
     * 获取当前位置
     *
     * @param wifiInfo
     * @return
     */
    public Position getPosition(String wifiInfo) {
        List<WifiInfo> listWifiInfo = JSON.parseArray(wifiInfo, WifiInfo.class);
        List<WifiInfoPosition> listWifiInfoPosition = null;
        if (null == cacheEntry || (System.currentTimeMillis() - cacheEntry.getKey()) > 1000 * 1800) {
            listWifiInfoPosition = commonDao.qryAllCommon("T_WIFI_POSITION", new WifiInfoPosition(), "");
            cacheEntry = new HashMap.SimpleEntry<>(System.currentTimeMillis(), listWifiInfoPosition);
        } else {
            listWifiInfoPosition = cacheEntry.getValue();
        }

        Map<String, List<WifiInfo>> mapWifiPosition = new HashMap<>();
        String mapKey = "";
        for (WifiInfoPosition infoPosition : listWifiInfoPosition) {
            mapKey = infoPosition.getSense() + POSITION_SPLIT + infoPosition.getX() + POSITION_SPLIT + infoPosition.getY();
            WifiInfo info = new WifiInfo(infoPosition.getSsid(), infoPosition.getBssid(), infoPosition.getLevel());
            if (!mapWifiPosition.containsKey(mapKey)) {
                List<WifiInfo> listWifi = new ArrayList<>();
                listWifi.add(info);
                mapWifiPosition.put(mapKey, listWifi);
            } else {
                mapWifiPosition.get(mapKey).add(info);
            }
        }
        String minKey = "";
        Double minValue = Double.MAX_VALUE;
        for (Map.Entry<String, List<WifiInfo>> entry : mapWifiPosition.entrySet()) {
            Double value = calcWifiDifference(entry.getValue(), listWifiInfo);
            System.out.println(entry.getKey() + ":" + value);
            if (value < minValue) {
                minValue = value;
                minKey = entry.getKey();
            }
        }
        return new Position(minKey.split(POSITION_SPLIT));
    }

    /**
     * 计算两个信息之间的差异
     *
     * @param fromInfo
     * @param toInfo
     * @return
     */
    private Double calcWifiDifference(List<WifiInfo> fromInfo, List<WifiInfo> toInfo) {
        double rtn = Double.MAX_VALUE;
        Map<String, WifiInfo> toMap = new HashMap<>();
        for (WifiInfo info : toInfo) {
            toMap.put(info.getBssid(), info);
        }
        double difference = 0d;
        int count = 0;
        for (WifiInfo info : fromInfo) {
            if (toMap.containsKey(info.getBssid())) {
                difference += Math.abs(info.getLevel() - toMap.get(info.getBssid()).getLevel()) / Math.abs(info.getLevel());
                count++;
            }
        }
        if (count > 0) {
            rtn = difference / count;
        }
        return rtn;
    }

}
