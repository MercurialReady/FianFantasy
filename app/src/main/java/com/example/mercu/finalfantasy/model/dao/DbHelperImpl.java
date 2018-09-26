package com.example.mercu.finalfantasy.model.dao;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Mercu on 2018/8/22.
 */

public class DbHelperImpl implements DbHelper
{
    @Inject
    public DbHelperImpl()
    {
    }

    @Override
    public List<HistoryData> addHistoryData(String data)
    {
        return null;
    }

    @Override
    public void clearAllData()
    {

    }

    @Override
    public List<HistoryData> loadAllData()
    {
        return null;
    }
}
