package com.vitea.endpoint.service.impl;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.vitea.dao.InterfaceDao;
import com.vitea.domain.InterFace;
import com.vitea.endpoint.service.ISendMessage;
import com.vitea.util.HttpClientUtil;

/**
 * 发送指令短信 实时短信 查询短信日志
 * 
 * @author jijiuxue
 *
 */
public class SendMessageImpl implements ISendMessage {
	@Autowired
	private InterfaceDao iPortDao;

	private Logger logger = LoggerFactory.getLogger("MONGODB");
	
	/**
	 * 将获取到的xml数据解析到集合中
	 * 
	 * @param xml
	 * @return
	 */
	public Element parseXML(String xml) throws DocumentException {
		Document doc = null;
		doc = DocumentHelper.parseText(xml.trim());
		Element rootElt = doc.getRootElement();
		return rootElt;
	}

	/**
	 * 发送短信并且返回状态码 发送实时短信 发送指令短信 查询短信记录
	 * 
	 * @param xml
	 * @param retStr
	 * @return
	 */
	@Override
	public String sendMessage(String xml) {
		InterFace inter1 = iPortDao.getPortById(201801);
		String retStr = "";
		Element rootElt=null;
		try {
			rootElt = parseXML(xml);
			String msisdn = rootElt.elementTextTrim("msisdn");
			String actionCode = rootElt.elementTextTrim("actionCode");
			String msgcontent = rootElt.elementTextTrim("msgcontent");
				// 拼接请求报文
				String reqStr = "smsmsg=<sms><systemid>10000</systemid><smstype>smspush</smstype><msisdn>" + msisdn
						+ "</msisdn><actionCode>" + actionCode + "</actionCode><msgcontent>" + msgcontent
						+ "</msgcontent></sms>";
		        long startTime = System.currentTimeMillis();
				retStr = HttpClientUtil.execute(inter1.getUrl(), reqStr,inter1.getType(),inter1.getTimeout());
				this.logger.info("代发短信,号码：{},编码：{},其他内容：{},返回报文:{},调用时间:{}", new Object[] { msisdn, actionCode, msgcontent,retStr,(System.currentTimeMillis() - startTime) });

		} catch (DocumentException e) {

			retStr = "<response><code>-1<code><msg>入参格式非法<msg></response>";

		}
		return retStr;
	}

	@Override
	public String queryMessageLog(String xml) {

		InterFace inter2 = iPortDao.getPortById(201802);
		String retStr = "";
		Element rootElt=null;
		try {
			rootElt = parseXML(xml);
			    String systemID = rootElt.elementTextTrim("systemID");
		        String smsType = rootElt.elementTextTrim("smsType");
		        String queryType = rootElt.elementTextTrim("queryType");
		        String serviceNbr = rootElt.elementTextTrim("serviceNbr");
		        String beginDate = rootElt.elementTextTrim("beginDate");
		        String endDate = rootElt.elementTextTrim("endDate");
		        String index = rootElt.elementTextTrim("index");
		        String pageSize = rootElt.elementTextTrim("pageSize");
		        String reqStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><sms><systemID>" + 
		          systemID + "</systemID><smsType>" + 
		          smsType + "</smsType><queryType>" + queryType + 
		          "</queryType><serviceNbr>" + serviceNbr + 
		          "</serviceNbr><beginDate>" + beginDate + 
		          "</beginDate><endDate>" + endDate + 
		          "</endDate><index>" + index + "</index><pageSize>" + 
		          pageSize + "</pageSize></sms>";
		     long startTime = System.currentTimeMillis();
			 retStr = HttpClientUtil.execute(inter2.getUrl(), reqStr.trim(),inter2.getType(),inter2.getTimeout());
		     this.logger.info("短厅日志查询报文：号码：{},开始时间：{},结束时间：{},返回报文：{},调用时间:{}", new Object[] { serviceNbr, beginDate, endDate,retStr,(System.currentTimeMillis() - startTime)});

		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retStr;
	}

	@Override
	public String sendRealTimeMsg(String xml) {
		InterFace inter1 = iPortDao.getPortById(1);
		String retStr = "";
		Element rootElt=null;
		try {
			rootElt = parseXML(xml);
			String msisdn = rootElt.elementTextTrim("msisdn");
			String srctermid = rootElt.elementTextTrim("srctermid");
			String msgcontent = rootElt.elementTextTrim("msgcontent");
			// 拼接请求报文
			String reqStr = "smsmsg=<sms><systemid>wss_wap</systemid><smstype>test</smstype><msisdn>" + msisdn
					+ "</msisdn><srctermid>" + srctermid + "</srctermid><msgcontent>" + msgcontent
					+ "</msgcontent></sms>";
		     long startTime = System.currentTimeMillis();
			retStr = HttpClientUtil.execute(inter1.getUrl(), reqStr,inter1.getType(),inter1.getTimeout());
			this.logger.info("代发短信,号码：{},内容：{},调用时间:{}", new Object[] { msisdn, msgcontent,(System.currentTimeMillis() - startTime)});

		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retStr;
	}

}
