package com.capgemini.employeeassets.services;

import com.capgemini.employeeassets.entity.Assets;

import java.util.List;

public interface IAssetService {
    Assets addAssets(Assets asset);
    List<Assets> getAllAssetsByUserId(long userId);

    String deleteAssetByUserId(long itemNumber);

    Assets updateAssetStatus(long serialNumber, String status);
}
