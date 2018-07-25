package com.vitea.endpoint;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.vitea.dao.InterfaceDao;
import com.vitea.domain.InterFace;
import com.vitea.util.HttpClientUtil;
import com.vitea.util.XmlJsonParseUtil;

/**
 * 查询volte信息实现类
 * 
 * @author liushahe
 *
 */
public class QueryVolteInfoImpl implements IQueryVolteInfo {
	@Autowired
	private InterfaceDao iPortDao;
	private Logger logger = LoggerFactory.getLogger("com.vitea.endpoint.QueryVolteInfoImpl");
	@Override
	public String queryVolteInfo(String xml) {
		InterFace inter = iPortDao.getPortById(201804);
		Element rootElt = null;
		String retStr = null;
		try {
			rootElt = parseXML(xml);
			String busivalue = rootElt.elementTextTrim("busivalue");
			String type = rootElt.elementTextTrim("type");
			// 拼接请求报文
			String json = "{\r\n" + 
					"	\"Envelope\": {\r\n" + 
					"		\"Header\": {\r\n" + 
					"			\"Esb\": {\r\n" + 
					"				\"Router\": {\r\n" + 
					"					\"Sender\": \"62.1176.01\",\r\n" + 
					"					\"AuthCode\": \"\",\r\n" + 
					"					\"Time\": \"2018-06-31 04:35:48.778\",\r\n" + 
					"					\"ServTestFlag\": \"\",\r\n" + 
					"					\"CarryType\": \"\",\r\n" + 
					"					\"TransId\": \"62.1176.01201707310435481043548778\",\r\n" + 
					"					\"MsgId\": \"62.1176.01201707310435481043548778\",\r\n" + 
					"					\"MsgType\": \"\",\r\n" + 
					"					\"EsbId\": \"\",\r\n" + 
					"					\"AuthType\": \"\",\r\n" + 
					"					\"ServCode\": \"10.1160.VOLTEYHQYXXCX_subscriptioninfo.SynReq\",\r\n" + 
					"					\"Version\": \"V0.1\"\r\n" + 
					"				}\r\n" + 
					"			}\r\n" + 
					"		},\r\n" + 
					"		\"Body\": {\r\n" + 
					"			\"mdn\": \"18202822858\",\r\n" + 
					"			\"type\": \"hss\"\r\n" + 
					"		}\r\n" + 
					"	}\r\n" + 
					"}";
			this.logger.info("volte信息查询,号码：{},编码：{}", new Object[] { busivalue, type });
			retStr = HttpClientUtil.execute(inter.getUrl(), json,"application/json");
            //json数据解析为xml数据
			retStr=XmlJsonParseUtil.JsonToXml(retStr);
		} catch (DocumentException e) {
			retStr = "<response><code>-1<code><msg>入参格式非法<msg></response>";
		}
		return retStr;
	}

	/**
	 * 将获取到的xml数据解析
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

}