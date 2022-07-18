package com.exampleEMS.EmployeeMS.Controllers;

import com.exampleEMS.EmployeeMS.Exception.AssetNotFoundException;
import com.exampleEMS.EmployeeMS.Models.Asset;
import com.exampleEMS.EmployeeMS.Service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/EMS/asset")
public class AssetController {
    @Autowired
    private AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

//    @PostMapping
//    public ResponseEntity<Asset> saveAsset(@RequestBody Asset asset)
//    {
//        return new ResponseEntity<Asset>(assetService.saveAsset(asset), HttpStatus.CREATED);
//    }

    //
    @PostMapping("/add/WithO_Id:{o_id}")
    public ResponseEntity<?> saveAsset(@RequestBody Asset asset, @PathVariable("o_id") int o_id )
    {
        if(asset.getAssetName().isEmpty())
            return new ResponseEntity<String>("Asset name cannot be empty.",HttpStatus.NOT_ACCEPTABLE);
        else if(asset.getAssetPrice()==0)
            return new ResponseEntity<String>("Asset price cannot be empty nor Zero.",HttpStatus.NOT_ACCEPTABLE);
        else if(asset.getAssetPrice()<0)
            return new ResponseEntity<String>("Asset price cannot be negative.",HttpStatus.NOT_ACCEPTABLE);

        return new ResponseEntity<Asset>(assetService.saveAsset(asset,o_id), HttpStatus.CREATED);
    }
    //

    @GetMapping("/getAll")
    public List<Asset> getAllAssets()
    {
        return assetService.getAllAssets();
    }

    @GetMapping("/get/ById:{a_id}")
    public ResponseEntity<?> getAssetById(@PathVariable ("a_id") int a_id)
    {
        try{
            return new ResponseEntity<Asset>(assetService.getAssetById(a_id), HttpStatus.OK);
        }
        catch (AssetNotFoundException e){
            return new ResponseEntity<String>("Asset does not exist.",HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/update/ById:{a_id}")
    public ResponseEntity<?> updateAsset(@PathVariable("a_id") int a_id, @RequestBody Asset asset)
    {
        try{
            if(asset.getAssetName().isEmpty())
                return new ResponseEntity<String>("Asset name cannot be empty.",HttpStatus.NOT_ACCEPTABLE);
            else if(asset.getAssetPrice()==0)
                return new ResponseEntity<String>("Asset price cannot be empty nor Zero.",HttpStatus.NOT_ACCEPTABLE);
            else if(asset.getAssetPrice()<0)
                return new ResponseEntity<String>("Asset price cannot be negative.",HttpStatus.NOT_ACCEPTABLE);


            return new ResponseEntity<Asset>(assetService.updateAsset(asset,a_id),HttpStatus.OK);
        }
        catch (AssetNotFoundException e){
            return new ResponseEntity<String>("Asset does not exist.",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/ById:{a_id}")
    public ResponseEntity<String> deleteAsset(@PathVariable("a_id") int a_id)
    {
        try{
            assetService.deleteAsset(a_id);
            return new ResponseEntity<String> ("Asset delete successfully", HttpStatus.OK);
        }
        catch (AssetNotFoundException e){
            return new ResponseEntity<String>("Asset does not exist.",HttpStatus.NOT_FOUND);
        }
    }
}
