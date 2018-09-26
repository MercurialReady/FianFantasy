package com.example.mercu.finalfantasy.model.dao;

import java.util.List;

/**
 * Created by Mercu on 2018/8/22.
 */

//用于搜索记录的存取
public interface DbHelper
{
    List<HistoryData> addHistoryData(String data);

    void clearAllData();

    List<HistoryData> loadAllData();
}
