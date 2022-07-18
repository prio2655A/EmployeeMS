package com.exampleEMS.EmployeeMS.Exception;

public class AssetNotFoundException extends RuntimeException{
    int asset_id;
    public AssetNotFoundException(int assetId) {
        super("Asset not found");
        this.asset_id =assetId;
    }
}
