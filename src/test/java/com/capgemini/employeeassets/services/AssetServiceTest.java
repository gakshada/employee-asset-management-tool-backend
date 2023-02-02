package com.capgemini.employeeassets.services;

import com.capgemini.employeeassets.entity.Assets;
import com.capgemini.employeeassets.entity.Employee;
import com.capgemini.employeeassets.exception.SerialNumberNotFoundException;
import com.capgemini.employeeassets.repository.AssetRepository;
import com.capgemini.employeeassets.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class AssetServiceTest {
    @Mock
    private AssetRepository assetRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private AssetService assetService;

    private Assets asset;
    private Employee employee;

    @BeforeEach
    void setup()
    {
        employee= new Employee();
        employee.setEmpName("Akshada");
        employee.setEmpAddress("Shirdi");
        employee.setEmpPhoneNumber("7689905645");
        employee.setEmpDesignation("Software Engineer");
        employee.setUserId(1L);
        employee.setUsername("akshada@gmail.com");
        employee.setPassword("Akshada@1234");
        employee.setUserRole("employee");

        asset = new Assets();
        asset.setItemNum(1);
        asset.setItemName("Laptop");
        asset.setSerialNumber(12345);
        asset.setStatus("Allocated");
        asset.setEmployee(employee);
    }

    @DisplayName("JUnit test for getAllAssetsByUserId method")
    @Test
    void getAllAssetsByUserId()
    {
        //given - precondition or setup
        given(assetRepository.findAllByEmployeeUserId(employee.getUserId())).willReturn(List.of(asset));

        // when -  action or the behaviour that we are going test
        List<Assets> assetsList = assetService.getAllAssetsByUserId(employee.getUserId());
        System.out.println(assetsList);
        // then - verify the output
        assertThat(assetsList).isNotNull();
        assertThat(assetsList.size()).isEqualTo(1);
    }

    @DisplayName("JUnit test for updateAssetStatus method")
    @Test
    public void updateAssetStatus(){
        String newStatus="Returned";
        // given
        given(assetRepository.findBySerialNumber(asset.getSerialNumber())).willReturn(Optional.of(asset));
        given(assetRepository.save(asset)).willReturn(asset);
        asset.setStatus(newStatus);
        // when
        Assets updatedAsset = assetService.updateAssetStatus(asset.getSerialNumber(),newStatus);
        System.out.println(updatedAsset);
        // then
        assertThat(updatedAsset).isNotNull();
    }

    @DisplayName("JUnit test for updateAssetStatus method which throws exception")
    @Test
    public void whenUpdateAssetStatus_thenThrowsException(){
        String newStatus="Returned";
        // given
        given(assetRepository.findBySerialNumber(asset.getSerialNumber())).willReturn(Optional.empty());

        // when
        Exception exception = assertThrows(SerialNumberNotFoundException.class, () -> {
            assetService.updateAssetStatus(asset.getSerialNumber(),newStatus);
        });

        // then
        assertEquals("Asset with provided serial number is not available",exception.getMessage());
    }
}
