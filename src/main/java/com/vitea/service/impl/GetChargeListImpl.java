package com.vitea.service.impl;

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

import com.vitea.dao.IPortDao;
import com.vitea.domain.InterFace;
import com.vitea.model.ListQryBSN;
import com.vitea.service.IGetChargeList;
import com.vitea.util.PropertiesUtil;
import com.vitea.util.SocketTools;
/**
 * 处理socket请求
 * @author liushahe
 *
 */
public class GetChargeListImpl implements IGetChargeList {
	@Autowired
	private IPortDao iPortDao;
	private Logger logger = LoggerFactory.getLogger(GetChargeListImpl.class);
	private String resultcode;
	public String endpointReset(String accNmr) throws IOException, DocumentException {
		InterFace inter=iPortDao.getPortById(3);
		ListQryBSN listQryBSN = parseInput(accNmr);
		if (listQryBSN != null) {
			this.logger.info("清单查询：号码{}，账期：{}，本地网：{}，清单类型：{}：",
					new Object[] { listQryBSN.getAccNbr(), listQryBSN.getBillMonth(), listQryBSN.getAreaCode(),
							listQryBSN.getListTypeId() });
			try {
				Socket sock = new Socket(inter.getUrl(), Integer.parseInt(inter.getPort()));
				this.logger.info("连接开始:{}", sock.toString());
				if (sock.isConnected()) {
					this.logger.info("连接成功!");
					String[] arr = getArrayForReq(listQryBSN);
					byte[] reqBytes = SocketTools.arraYtoBytes(arr);
					DataOutputStream dataOutputStream = new DataOutputStream(
							new BufferedOutputStream(sock.getOutputStream(), 2048));
					dataOutputStream.write(reqBytes);
					dataOutputStream.flush();
					this.logger.info("请求发送完毕!");
					DataInputStream dataInputStream = new DataInputStream(
							new BufferedInputStream(sock.getInputStream()));
					byte[] byteSrc = SocketTools.getBytesFromStream(dataInputStream);
					this.logger.info("解析接口返回开始!");
					StringBuffer sbout = new StringBuffer();
					sbout.append("<root><public>");
					String totalInfo = "";
					if (byteSrc.length == 116) {
						sbout.append("<result>0</result><desc>查询成功</desc></public><data/>");
						this.logger.info("查询成功，但是未查询到数据!");
					} else {
						sbout.append("<result>0</result><desc>查询成功</desc></public><data>");
						int packetNum = SocketTools.getRealTrueInt(byteSrc, 8, 4);
						int x = 0;
						for (int i = 0; i < packetNum; i++) {
							printInfo(byteSrc, x);
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
					this.logger.info("解析接口返回成功!");
					sock.close();
				} else {
					this.logger.error("连接失败!");
					
					this.resultcode = "<root><public><result>-2</result><desc>连接失败</desc></public><data/></root>";
				}
			} catch (ConnectException e) {
				this.resultcode = "<root><public><result>-2</result><desc>连接失败</desc></public><data/></root>";
			}
		} else {
			this.resultcode = "<root><public><result>-1</result><desc>入参格式非法</desc></public><data/></root>";
		}
		return getCleanXML(this.resultcode);
	}

	private ListQryBSN parseInput(String accNmr) {
		ListQryBSN lqb = null;
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(accNmr);
			Element rootElt = doc.getRootElement();
			String listTypeId = rootElt.elementTextTrim("listTypeId");
			String accNbr = rootElt.elementTextTrim("accNbr");
			String billMonth = rootElt.elementTextTrim("billMonth");
			String areaCode = rootElt.elementTextTrim("areaCode");
			lqb = new ListQryBSN(listTypeId, accNbr, billMonth, areaCode);
		} catch (DocumentException e) {
			this.logger.error("入参解析失败，格式有误！");
		}
		return lqb;
	}

	public void printInfo(byte[] byteSrc, int x) {
		this.logger.debug("packetId:{}", SocketTools.getRealTrueValue(byteSrc, x, 2));
		this.logger.debug("length:{}", Integer.valueOf(SocketTools.getRealTrueShort(byteSrc, x + 2, 2)));
		this.logger.debug("id:{}", Integer.valueOf(SocketTools.getRealTrueInt(byteSrc, x + 4, 4)));
		this.logger.debug("packetNum:{}", Integer.valueOf(SocketTools.getRealTrueInt(byteSrc, x + 8, 4)));
		this.logger.debug("packetType:{}", SocketTools.getRealTrueValue(byteSrc, x + 12, 2));
		this.logger.debug("isEnd:{}", SocketTools.getRealTrueValue(byteSrc, x + 14, 1));
		this.logger.debug("success:{}", SocketTools.getRealTrueValue(byteSrc, x + 15, 1));
		this.logger.debug("nItem:{}", Integer.valueOf(SocketTools.getRealTrueShort(byteSrc, x + 16, 2)));
		this.logger.debug("listHead:{}", SocketTools.getRealTrueValue(byteSrc, x + 18, 160));
		this.logger.debug("totalInfo:{}", SocketTools.getRealTrueValue(byteSrc, x + 178, 100));
	}

	private String[] getArrayForReq(ListQryBSN lqb) {
		String[] arr = { "CX", "60", "111111", "RI", "0300005", lqb.getAreaCode(), lqb.getAccNbr(), "",
				lqb.getListTypeId(), lqb.getBillMonth(), "   " };
		return arr;
	}

	private int getListTypeId(ListQryBSN lqb) {
		return Integer.parseInt(lqb.getListTypeId());
	}

	private String getCleanXML(String inputStr) {
		String cleanXMLString = null;
		Pattern pattern = null;
		Matcher matcher = null;
		pattern = Pattern.compile("[\\000]*");
		matcher = pattern.matcher(inputStr);
		if (matcher.find()) {
			cleanXMLString = matcher.replaceAll("");
		}
		return cleanXMLString;
	}


	
}
