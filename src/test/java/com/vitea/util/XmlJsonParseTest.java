package com.vitea.util;

import org.junit.Test;

/**
 * 
 * @author liushahe
 *
 */
public class XmlJsonParseTest {
	@Test
	public void object2XMl() {
		XmlJsonParseUtil.xml2Object("t");
		
	}
	
	@Test
	public void xml2json() {
		String xml="<Envelope><Body><type>hss</type><mdn>18202822858</mdn></Body><Header><Esb><Router><MsgId>10.1104.19_201707310435481043548778</MsgId><Sender>44.1174.01</Sender><Time>2017-07-31 04:35:48.778</Time><ServCode>10.1160.VOLTEYHQYXXCX_subscriptioninfo.SynReq</ServCode><AuthCode/><TransId>10.1104.19_201707310435481043548778</TransId><ServTestFlag/><EsbId/><Version>V0.1</Version><AuthType/><CarryType/><MsgType/></Router></Esb></Header></Envelope>\r\n";
		XmlJsonParseUtil.xmlToJson(xml);
		
	}
	
	@Test
	public void json2xml() {
		String json="{\r\n" + 
				"	\"Envelope\": {\r\n" + 
				"		\"Header\": {\r\n" + 
				"			\"Esb\": {\r\n" + 
				"				\"Router\": {\r\n" + 
				"					\"Sender\": \"44.1174.01\",\r\n" + 
				"					\"AuthCode\": \"\",\r\n" + 
				"					\"Time\": \"2017-07-31 04:35:48.778\",\r\n" + 
				"					\"ServTestFlag\": \"\",\r\n" + 
				"					\"CarryType\": \"\",\r\n" + 
				"					\"TransId\": \"10.1104.19_201707310435481043548778\",\r\n" + 
				"					\"MsgId\": \"10.1104.19_201707310435481043548778\",\r\n" + 
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
		String s=XmlJsonParseUtil.jsonToXml(json);
		System.out.println(s);
	}
	
	
}
