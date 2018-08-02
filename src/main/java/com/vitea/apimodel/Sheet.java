package com.vitea.apimodel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * sheet apimodel
 * @author liushahe
 *
 */
@ApiModel(value = "Sheet", description = "工单号描述")
public class Sheet {
	/**
	 * 工单号
	 */
	@ApiModelProperty("工单号")
	private String sheetid;
	/**
	 * 障碍号码
	 */
	@ApiModelProperty("障碍号码")
	private String busivalue;
	/**
	 * 区域编码
	 */
	@ApiModelProperty("区域编码")
	private String areacode;
	/**
	 * 结单时间
	 */
	@ApiModelProperty("结单时间")
	private String overdate;
	/**
	 * 建单人
	 */
	@ApiModelProperty("建单人")
	private String createoperator;
	/**
	 * 工单描述
	 */
	@ApiModelProperty("工单描述")
	private String remark;
	public String getSheetid() {
		return sheetid;
	}
	public void setSheetid(String sheetid) {
		this.sheetid = sheetid;
	}
	public String getBusivalue() {
		return busivalue;
	}
	public void setBusivalue(String busivalue) {
		this.busivalue = busivalue;
	}
	public String getAreacode() {
		return areacode;
	}
	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}
	public String getOverdate() {
		return overdate;
	}
	public void setOverdate(String overdate) {
		this.overdate = overdate;
	}
	public String getCreateoperator() {
		return createoperator;
	}
	public void setCreateoperator(String createoperator) {
		this.createoperator = createoperator;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "Sheet [sheetid=" + sheetid + ", busivalue=" + busivalue + ", areacode=" + areacode + ", overdate="
				+ overdate + ", createoperator=" + createoperator + ", remark=" + remark + "]";
	}
	

}
