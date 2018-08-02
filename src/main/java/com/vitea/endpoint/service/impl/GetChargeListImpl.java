package com.vitea.endpoint.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.vitea.dao.InterfaceDao;
import com.vitea.domain.InterFace;
import com.vitea.endpoint.service.IGetChargeList;
import com.vitea.model.ListQryBsn;
import com.vitea.util.JedisClientUtil;
import com.vitea.util.SocketTools;
/**
 * 处理socket请求
 * @author liushahe
 *
 */
public class GetChargeListImpl implements IGetChargeList {
	@Autowired
	private InterfaceDao iPortDao;
	private Logger logger = LoggerFactory.getLogger(GetChargeListImpl.class);
	private String resultcode;
	private static final int LENGTH=116;
	private static  Pattern pattern = Pattern.compile("[\\000]*");

	@Override
	public String endpointReset(String accNmr) throws IOException, DocumentException {
		InterFace inter=iPortDao.getPortById(201803);
		ListQryBsn listQryBSN = parseInput(accNmr);
		if (listQryBSN != null) {
			try {
				Socket sock = new Socket(inter.getUrl(), Integer.parseInt(inter.getPort()));
				if (sock.isConnected()) {
					JedisClientUtil.setInc("success");
				     long startTime = System.currentTimeMillis();
					String[] arr = getArrayForReq(listQryBSN);
					byte[] reqBytes = SocketTools.arraYtoBytes(arr);
					DataOutputStream dataOutputStream = new DataOutputStream(
							new BufferedOutputStream(sock.getOutputStream(), 2048));
					dataOutputStream.write(reqBytes);
					dataOutputStream.flush();
					DataInputStream dataInputStream = new DataInputStream(
							new BufferedInputStream(sock.getInputStream()));
					byte[] byteSrc = SocketTools.getBytesFromStream(dataInputStream);
					StringBuffer sbout = new StringBuffer();
					sbout.append("<root><public>");
					String totalInfo = "";
					if (byteSrc.length == LENGTH) {
						sbout.append("<result>0</result><desc>查询成功</desc></public><data/>");
						this.logger.info("查询成功，但是未查询到数据!");
					} else {
						sbout.append("<result>0</result><desc>查询成功</desc></public><data>");
						int packetNum = SocketTools.getRealTrueInt(byteSrc, 8, 4);
						int x = 0;
						for (int i = 0; i < packetNum; i++) {
							int nItem = SocketTools.getRealTrueShort(byteSrc, x + 16, 2);
							totalInfo = SocketTools.getRealTrueValue(byteSrc, x + 178, 300);
							int y = x + 478;
							for (int j = 0; j < nItem; j++) {
								this.logger.debug("listInfo:{}", SocketTools.getRealTrueValue(byteSrc, y, 198));
								String[] item = SocketTools.getRealTrueValue(byteSrc, y, 198).split(",");
								sbout.append(SocketTools.joinItemInfo(item, getListTypeId(listQryBSN)));

								y += 200;
							}
							int length = SocketTools.getRealTrueShort(byteSrc, x + 2, 2);
							x = x + length + 200;
						}
						sbout.append("<totalInfo>" + totalInfo + "</totalInfo></data>");
					}
					sbout.append("</root>");
					this.resultcode = sbout.toString();
				    this.logger.info("计费清单：号码：{},账期:{},返回信息:{},调用时间:{}", new Object[] { listQryBSN.getAccNbr(), listQryBSN.getBillMonth(),totalInfo,(System.currentTimeMillis() - startTime)});
				    dataOutputStream.close();
				    dataInputStream.close();
				    sock.close();
				} else {
					this.logger.error("连接失败!");
					JedisClientUtil.setInc("fail");
					this.resultcode = "<root><public><result>-2</result><desc>连接失败</desc></public><data/></root>";
				}
			} catch (ConnectException e) {
				JedisClientUtil.setInc("fail");
				this.resultcode = "<root><public><result>-2</result><desc>连接失败</desc></public><data/></root>";
			}
		} else {
			this.resultcode = "<root><public><result>-1</result><desc>入参格式非法</desc></public><data/></root>";
		}
		return getCleanXML(this.resultcode);
	}

	private ListQryBsn parseInput(String accNmr) {
		ListQryBsn lqb = null;
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(accNmr);
			Element rootElt = doc.getRootElement();
			String listTypeId = rootElt.elementTextTrim("listTypeId");
			String accNbr = rootElt.elementTextTrim("accNbr");
			String billMonth = rootElt.elementTextTrim("billMonth");
			String areaCode = rootElt.elementTextTrim("areaCode");
			lqb = new ListQryBsn(listTypeId, accNbr, billMonth, areaCode);
		} catch (DocumentException e) {
			this.logger.error("入参解析失败，格式有误！");
		}
		return lqb;
	}

	private String[] getArrayForReq(ListQryBsn lqb) {
		String[] arr = { "CX", "60", "111111", "RI", "0300005", lqb.getAreaCode(), lqb.getAccNbr(), "",
				lqb.getListTypeId(), lqb.getBillMonth(), "   " };
		return arr;
	}

	private int getListTypeId(ListQryBsn lqb) {
		return Integer.parseInt(lqb.getListTypeId());
	}

	private String getCleanXML(String inputStr) {
		String cleanXMLString = null;
		Matcher matcher = null;
		matcher = pattern.matcher(inputStr);
		if (matcher.find()) {
			cleanXMLString = matcher.replaceAll("");
		}
		return cleanXMLString;
	}	
}
