package com.capgemini.employeeassets.services;

import com.capgemini.employeeassets.entity.Assets;
import com.capgemini.employeeassets.entity.Employee;
import com.capgemini.employeeassets.exception.SerialNumberNotFoundException;
import com.capgemini.employeeassets.exception.UserIdNotFoundException;
import com.capgemini.employeeassets.repository.AssetRepository;
import com.capgemini.employeeassets.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssetService implements IAssetService{
    @Autowired
    AssetRepository assetRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Override
    public Assets addAssets(Assets asset)
    {
        Optional<Employee> opt = employeeRepository.findById(asset.getEmployee().getUserId());
        long srNo = asset.getSerialNumber();
        if(opt.isPresent() && !assetRepository.findBySerialNumber(srNo).isPresent())
        {
            Employee existingEmployee= opt.get();
            asset.getEmployee().setEmpName(existingEmployee.getEmpName());
            asset.getEmployee().setEmpAddress(existingEmployee.getEmpAddress());
            asset.getEmployee().setEmpPhoneNumber(existingEmployee.getEmpPhoneNumber());
            asset.getEmployee().setEmpDesignation(existingEmployee.getEmpDesignation());
            asset.getEmployee().setUsername(existingEmployee.getUsername());
            asset.getEmployee().setPassword(existingEmployee.getPassword());
            asset.getEmployee().setUserRole(existingEmployee.getUserRole());
            return assetRepository.save(asset);
        }
       else
           return null;
    }
    @Override
    public List<Assets> getAllAssetsByUserId(long userId)
    {
        if(!assetRepository.findAllByEmployeeUserId(userId).isEmpty())
            return assetRepository.findAllByEmployeeUserId(userId);
        else
            throw new UserIdNotFoundException("No such user Id is present.");
    }

    @Override
    public String deleteAssetByUserId(long userId) {
        //System.out.println("hi");
        if(!assetRepository.findAllByEmployeeUserId(userId).isEmpty())
        {
            List<Assets> assetsList =assetRepository.findAllByEmployeeUserId(userId);
            for(Assets a:assetsList)
            {
                assetRepository.deleteById(a.getItemNum());
            }
            //assetRepository.deleteByEmployeeUserId(userId);
            return "Asset deleted Successfully";
            //assetRepository.deleteAllByEmployeeUserId(userId);;
        }
        return "No asset is available with this userId";
    }

    @Override
    public Assets updateAssetStatus(long serialNumber,String status)
    {
        Optional<Assets> opt =assetRepository.findBySerialNumber(serialNumber);
        if(opt.isPresent())
        {
            Assets existingAsset=opt.get();
            existingAsset.setStatus(status);
            return assetRepository.save(existingAsset);
        }
        else
                throw new SerialNumberNotFoundException("Asset with provided serial number is not available");
    }
}
