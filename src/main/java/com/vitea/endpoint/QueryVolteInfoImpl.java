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
import com.vitea.util.DateFormatUtil;
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
			String sender = rootElt.elementTextTrim("sender");
			String transid = rootElt.elementTextTrim("transid");
			String servcode = rootElt.elementTextTrim("servcode");
			String msgid = rootElt.elementTextTrim("msgid");
			String version = rootElt.elementTextTrim("version");
			String time=DateFormatUtil.getFormatDateMill();
			/*
			 * 2018.7.26 14:58
			 * 此处拼接报文为了提高接口查询速度 故而没有保存在数据库，
			 * 将所有变量抽离出来防止以后有变,如果报文有变修改以下报文即可
			 * */
			String json = "{\r\n" + 
					"	\"Envelope\": {\r\n" + 
					"		\"Header\": {\r\n" + 
					"			\"Esb\": {\r\n" + 
					"				\"Router\": {\r\n" + 
					"					\"Sender\": \""+sender+"\",\r\n" + 
					"					\"AuthCode\": \"\",\r\n" + 
					"					\"Time\": \""+time+"\",\r\n" + 
					"					\"ServTestFlag\": \"\",\r\n" + 
					"					\"CarryType\": \"\",\r\n" + 
					"					\"TransId\": \""+transid+"\",\r\n" + 
					"					\"MsgId\": \""+msgid+"\",\r\n" + 
					"					\"MsgType\": \"\",\r\n" + 
					"					\"EsbId\": \"\",\r\n" + 
					"					\"AuthType\": \"\",\r\n" + 
					"					\"ServCode\": \""+servcode+"\",\r\n" + 
					"					\"Version\": \""+version+"\"\r\n" + 
					"				}\r\n" + 
					"			}\r\n" + 
					"		},\r\n" + 
					"		\"Body\": {\r\n" + 
					"			\"mdn\": \""+busivalue+"\",\r\n" + 
					"			\"type\": \""+type+"\"\r\n" + 
					"		}\r\n" + 
					"	}\r\n" + 
					"}";
		     long startTime = System.currentTimeMillis();
			 retStr = HttpClientUtil.execute(inter.getUrl(), json,inter.getType(),inter.getTimeout());
			 this.logger.info("volte信息查询,号码：{},编码：{},调用时间:{}", new Object[] { busivalue, type,(System.currentTimeMillis() - startTime)});
            //JSON数据解析为XML数据
			retStr=XmlJsonParseUtil.jsonToXml(retStr);
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