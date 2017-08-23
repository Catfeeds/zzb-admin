package com.hcb.zzb.dto;

import java.util.Date;

public class CarModel {
    private Integer id;

    private Date createAt;

    private Date updateAt;

    private Date deleteAt;

    private String carModelUuid;

    private String brandModel;

    private String brand;

    private String carSeries;

    private String modelYear;

    private String transmissionCase;

    private String displacement;
    
    private String clutch;
    
    private int seatNumber;
    
    private String carModel;

    private String applyUserUuid;

    private Integer applyStatus;

    private String operatorUuid;
    
    private Date operationTime;
    
    private String operatorName;//
    
    private String color;
    private String brandUuid;
    private String seriesUuid;

    public String getBrandUuid() {
		return brandUuid;
	}

	public String getSeriesUuid() {
		return seriesUuid;
	}

	public void setBrandUuid(String brandUuid) {
		this.brandUuid = brandUuid;
	}

	public void setSeriesUuid(String seriesUuid) {
		this.seriesUuid = seriesUuid;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Date getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(Date deleteAt) {
        this.deleteAt = deleteAt;
    }

    public String getCarModelUuid() {
        return carModelUuid;
    }

    public void setCarModelUuid(String carModelUuid) {
        this.carModelUuid = carModelUuid == null ? null : carModelUuid.trim();
    }

    public String getBrandModel() {
        return brandModel;
    }

    public void setBrandModel(String brandModel) {
        this.brandModel = brandModel == null ? null : brandModel.trim();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }

    public String getCarSeries() {
        return carSeries;
    }

    public void setCarSeries(String carSeries) {
        this.carSeries = carSeries == null ? null : carSeries.trim();
    }

    public String getModelYear() {
        return modelYear;
    }

    public void setModelYear(String modelYear) {
        this.modelYear = modelYear == null ? null : modelYear.trim();
    }

    public String getTransmissionCase() {
        return transmissionCase;
    }

    public void setTransmissionCase(String transmissionCase) {
        this.transmissionCase = transmissionCase == null ? null : transmissionCase.trim();
    }

    public String getDisplacement() {
        return displacement;
    }

    public void setDisplacement(String displacement) {
        this.displacement = displacement == null ? null : displacement.trim();
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel == null ? null : carModel.trim();
    }

    public String getApplyUserUuid() {
        return applyUserUuid;
    }

    public void setApplyUserUuid(String applyUserUuid) {
        this.applyUserUuid = applyUserUuid == null ? null : applyUserUuid.trim();
    }

    public Integer getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(Integer applyStatus) {
        this.applyStatus = applyStatus;
    }

    public String getOperatorUuid() {
        return operatorUuid;
    }

    public void setOperatorUuid(String operatorUuid) {
        this.operatorUuid = operatorUuid == null ? null : operatorUuid.trim();
    }

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getClutch() {
		return clutch;
	}

	public void setClutch(String clutch) {
		this.clutch = clutch;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	public Date getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
    
}