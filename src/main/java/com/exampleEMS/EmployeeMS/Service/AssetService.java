package com.exampleEMS.EmployeeMS.Service;

import com.exampleEMS.EmployeeMS.Models.Asset;

import java.util.List;

public interface AssetService {
//    Asset saveAsset(Asset asset);
    Asset saveAsset(Asset asset,int o_id);
    List<Asset> getAllAssets();
    Asset getAssetById(int a_id);
    Asset updateAsset(Asset asset, int a_id);
    void deleteAsset(int a_id);
}
