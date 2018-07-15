package com.vitea.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.vitea.dao.IPortDao;
import com.vitea.domain.InterFace;
import com.vitea.service.ISendMessage;
import com.vitea.util.HttpClientUtil;
/**
 * 处理http请求
 * @author jijiuxue
 *
 */
public class SendMessageImpl implements ISendMessage{
	@Autowired
	private IPortDao iPortDao;

    private Logger logger = LoggerFactory.getLogger("com.vitea.service.impl.SendMessageImpl");

/**
 * 将获取到的xml数据解析到集合中
 * @param xml
 * @return
 */
 public Map<String,String> parseXML(String xml) throws DocumentException{
	 Document doc = null;
	 HashMap<String,String> map=new HashMap<String,String>();
			doc = DocumentHelper.parseText(xml.trim());
			Element rootElt = doc.getRootElement();
			 if (rootElt.elementText("srvType").equals("sendMsg")){
				 String srvType = rootElt.elementTextTrim("srvType");
				 String msisdn = rootElt.elementTextTrim("msisdn");
			     String actionCode = rootElt.elementTextTrim("actionCode");
			     String msgcontent = rootElt.elementTextTrim("msgcontent");
			     map.put("srvType", srvType);
			     map.put("msisdn", msisdn);
			     map.put("actionCode", actionCode);
			     map.put("msgcontent", msgcontent);
			     
			 }else if (rootElt.elementText("srvType").equals("qryLog"))
		      {
				    String srvType = rootElt.elementTextTrim("srvType");
			        String systemID = rootElt.elementTextTrim("systemID");
			        String smsType = rootElt.elementTextTrim("smsType");
			        String queryType = rootElt.elementTextTrim("queryType");
			        String serviceNbr = rootElt.elementTextTrim("serviceNbr");
			        String beginDate = rootElt.elementTextTrim("beginDate");
			        String endDate = rootElt.elementTextTrim("endDate");
			        String index = rootElt.elementTextTrim("index");
			        String pageSize = rootElt.elementTextTrim("pageSize");
			        map.put("srvType", srvType);
			        map.put("systemID", systemID);
			        map.put("smsType", smsType);
			        map.put("queryType", queryType);
			        map.put("serviceNbr", serviceNbr);
			        map.put("beginDate", beginDate);
			        map.put("endDate", endDate);
			        map.put("index", index);
			        map.put("pageSize", pageSize);
		      }else if(rootElt.elementText("srvType").equals("sendRealTimeMsg")){
		    	  String srvType = rootElt.elementTextTrim("srvType");
					 String msisdn = rootElt.elementTextTrim("msisdn");
				     String srctermid = rootElt.elementTextTrim("srctermid");
				     String msgcontent = rootElt.elementTextTrim("msgcontent");
				     map.put("srvType", srvType);
				     map.put("msisdn", msisdn);
				     map.put("srctermid", srctermid);
				     map.put("msgcontent", msgcontent); 
		      }
			 
			 
			 
				return map;
			
		} 
	      
 
/**
 * 发送短信并且返回状态码
 * 发送实时短信
 * 发送指令短信
 * 查询短信记录
 * @param xml
 * @param retStr 
 * @return
 */
  public String sendMessage(String xml){
	 InterFace inter1=iPortDao.getPortById(1);
	 InterFace inter2=iPortDao.getPortById(2);
	 String retStr="";
	 Map<String, String> map;
	try {
		map = parseXML(xml);
      if (map.get("srvType").equals("sendMsg"))
      {
        String msisdn =map.get("msisdn");
        String actionCode =map.get("actionCode");
        String msgcontent =map.get("msgcontent");
        //拼接请求报文
        String reqStr = "smsmsg=<sms><systemid>10000</systemid><smstype>smspush</smstype><msisdn>" + 
          msisdn + 
          "</msisdn><actionCode>" + 
          actionCode + 
          "</actionCode><msgcontent>" + 
          msgcontent + 
          "</msgcontent></sms>";
        this.logger.info("代发短信,号码：{},编码：{},其他内容：{}", new Object[] { msisdn, actionCode, msgcontent });
        retStr=HttpClientUtil.execute(inter1.getUrl(), reqStr);
     
      }
      else if (map.get("srvType").equals("qryLog"))
      {
        String systemID = map.get("systemID");
        String smsType = map.get("smsType");
        String queryType = map.get("queryType");
        String serviceNbr =map.get("serviceNbr");
        String beginDate =map.get("beginDate");
        String endDate =map.get("endDate");
        String index =map.get("index");
        String pageSize =map.get("pageSize");
        String reqStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><sms><systemID>" + 
          systemID + "</systemID><smsType>" + 
          smsType + "</smsType><queryType>" + queryType + 
          "</queryType><serviceNbr>" + serviceNbr + 
          "</serviceNbr><beginDate>" + beginDate + 
          "</beginDate><endDate>" + endDate + 
          "</endDate><index>" + index + "</index><pageSize>" + 
          pageSize + "</pageSize></sms>";
        this.logger.info("短厅日志查询报文：号码：{},开始时间：{},结束时间：{}", new Object[] { serviceNbr, beginDate, endDate }); 
		retStr=HttpClientUtil.execute(inter2.getUrl(), reqStr.trim());        
        
      }else if(map.get("srvType").equals("sendRealTimeMsg"))
      {
          String msisdn =map.get("msisdn");
          String srctermid =map.get("srctermid");
          String msgcontent =map.get("msgcontent");
          //拼接请求报文
          String reqStr = "smsmsg=<sms><systemid>wss_wap</systemid><smstype>test</smstype><msisdn>" + 
                  msisdn + 
                  "</msisdn><srctermid>" + 
                  srctermid + 
                  "</srctermid><msgcontent>" + 
                  msgcontent + 
                  "</msgcontent></sms>";
          this.logger.info("代发短信,号码：{},内容：{}", new Object[] { msisdn, msgcontent });
          retStr=HttpClientUtil.execute(inter1.getUrl(), reqStr);
       
        }
	  }catch (DocumentException e) {
  	
 		 retStr = "<response><code>-1<code><msg>入参格式非法<msg></response>";
 		 
 	}
      return retStr;
    }

}
