package com.vitea.model;

public class ListQryBSN
{

  //账本编号
  private String listTypeId;
  //障碍号码
  private String accNbr;
  //账期
  private String billMonth;
  //区域编码
  private String areaCode;
  
  public String getAreaCode()
  {
    return this.areaCode;
  }
  
  public void setAreaCode(String areaCode)
  {
    this.areaCode = areaCode;
  }
  
  public ListQryBSN() {}
  
  public ListQryBSN(String _listTypeId, String _accNbr, String _billMonth, String _areaCode)
  {
    this.listTypeId = _listTypeId;
    this.accNbr = _accNbr;
    this.billMonth = _billMonth;
    this.areaCode = _areaCode;
  }
  
  public String getListTypeId()
  {
    return this.listTypeId;
  }
  
  public void setListTypeId(String listTypeId)
  {
    this.listTypeId = listTypeId;
  }
  
  public String getAccNbr()
  {
    return this.accNbr;
  }
  
  public void setAccNbr(String accNbr)
  {
    this.accNbr = accNbr;
  }
  
  public String getBillMonth()
  {
    return this.billMonth;
  }
  
  public void setBillMonth(String billMonth)
  {
    this.billMonth = billMonth;
  }
}

