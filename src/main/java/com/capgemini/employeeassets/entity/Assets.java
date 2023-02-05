package com.capgemini.employeeassets.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


@Entity
public class Assets {
    @Id
    @GeneratedValue
    @Column(unique = true)
    private long itemNum;
    @NotEmpty(message = "itemName should not be Empty")
    private String itemName;

    @Column(unique = true)
    private long serialNumber;

    @NotEmpty(message = "status should not be Empty")
    private String status;
    @ManyToOne(cascade = CascadeType.MERGE)
    //@JoinColumn(name = "employee_user_id", referencedColumnName = "user_id")
    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public long getItemNum() {
        return itemNum;
    }

    public void setItemNum(long itemNum) {
        this.itemNum = itemNum;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Assets{" +
                "itemNum=" + itemNum +
                ", itemName='" + itemName + '\'' +
                ", serialNumber=" + serialNumber +
                ", status='" + status + '\'' +
                '}';
    }
}
