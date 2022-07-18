package com.exampleEMS.EmployeeMS.Service;

import com.exampleEMS.EmployeeMS.Exception.AssetNotFoundException;
import com.exampleEMS.EmployeeMS.Exception.OrganisationNotFoundException;
import com.exampleEMS.EmployeeMS.Models.Asset;
import com.exampleEMS.EmployeeMS.Models.Organisation;
import com.exampleEMS.EmployeeMS.Repository.AssetRepository;
import com.exampleEMS.EmployeeMS.Repository.OrganisationRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@NoArgsConstructor
@Service
public class AssetServiceImp implements AssetService {
    @Autowired
    private AssetRepository assetRepository;


    public AssetServiceImp(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

//    @Override
//    public Asset saveAsset(Asset asset) {
//        return assetRepository.save(asset) ;
//    }

    //
    @Autowired
    private OrganisationRepository organisationRepository;

    public AssetServiceImp(OrganisationRepository organisationRepository) {
        this.organisationRepository = organisationRepository;
    }

    @Override
    public Asset saveAsset(Asset asset, int o_id) {
        Organisation createOrganisation= organisationRepository.findById(o_id).orElseThrow( ()->new OrganisationNotFoundException(o_id) );
        asset.setOrganisation(createOrganisation);
        return assetRepository.save(asset) ;
    }
    //
    @Override
    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    @Override
    public Asset getAssetById(int a_id) {
        return assetRepository.findById(a_id).orElseThrow( ()->new AssetNotFoundException(a_id) );
    }

    @Override
    public Asset updateAsset(Asset asset, int a_id) {
        Asset existingAsset=assetRepository.findById(a_id).orElseThrow( ()->new AssetNotFoundException(a_id) );

        existingAsset.setAssetName(asset.getAssetName());
        existingAsset.setAssetPrice(asset.getAssetPrice());

        assetRepository.save(existingAsset);

        return existingAsset;
    }

    @Override
    public void deleteAsset(int a_id)
    {
        assetRepository.findById(a_id).orElseThrow( ()->new AssetNotFoundException(a_id) );
        assetRepository.deleteById(a_id);
    }
}
