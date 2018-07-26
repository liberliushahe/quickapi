package com.vitea.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
 * @author liushahe
 *
 */
public class SocketTools
{
	  
  private static Logger logger = LoggerFactory.getLogger(SocketTools.class);
  private static String FILL_ALIGN_LEFT = "left";
  private static char FILL_CHARBLANK = '\000';
  private static int TYPEID6=6;
  private static int TYPEID7=7;
  private static int TYPEID8=8;
  private static int TYPEID91=91;
  private static int TYPEID80=80;
  private static int TYPEID89=89;
  private static int TYPEID1=1;
  public static int getRealTrueShort(byte[] bytes, int pos, int len)
  {
    byte[] newBytes = new byte[len];
    for (int i = pos; i < len + pos; i++) {
      newBytes[(i - pos)] = bytes[i];
    }
    return (newBytes[0] << 8) + (
      (newBytes[1] & 0xFF) << 0);
  }
  
  public static int getRealTrueInt(byte[] bytes, int pos, int len)
  {
    byte[] newBytes = new byte[len];
    for (int i = pos; i < len + pos; i++) {
      newBytes[(i - pos)] = bytes[i];
    }
    return (newBytes[0] << 24) + (
      (newBytes[1] & 0xFF) << 16) + (
      (newBytes[2] & 0xFF) << 8) + (
      (newBytes[3] & 0xFF) << 0);
  }
  
  public static String joinItemInfo(String[] temp, int listTypeId)
  {
    String resStr = "";
    if (listTypeId == TYPEID6) {
      resStr = "<item><time>" + temp[1] + "</time>" + "<roamPlace>" + temp[6] + "</roamPlace>" + "<callType>" + temp[7] + "</callType>" + "<callWay>" + temp[2] + "</callWay>" + "<userNo>" + temp[3] + "</userNo>" + "<duration>" + temp[4] + "</duration>" + "<fee>" + temp[5] + "</fee></item>";
    } else if (listTypeId ==TYPEID7) {
      resStr = "<item><time>" + temp[1] + "</time><flowKb>" + temp[2] + "</flowKb><duration>" + temp[3] + "</duration><flow>" + temp[4] + "</flow><fee>" + temp[5] + "</fee><roam>" + temp[6] + "</roam><way>" + temp[7] + "</way></item>";
    } else if (listTypeId ==TYPEID8) {
      resStr = "<item><time>" + temp[3] + "</time>" + "<userNo>" + temp[2] + "</userNo>" + "<fee>" + temp[4] + "</fee></item>";
    } else if (listTypeId == TYPEID91)
    {
    	int length=7;
      if (temp.length == length) {
        resStr = "<itemContainFee><userNo>" + temp[1] + "</userNo>" + "<name1>" + temp[2] + "</name1>" + "<name2>" + temp[3] + "</name2>" + "<name3>" + temp[4] + "</name3>" + "<time>" + temp[5] + "</time>" + "<fee>" + temp[6] + "</fee></itemContainFee>";
      }
    }
    else if (listTypeId == TYPEID80)
    {
    	int length=5;
      if (temp.length == length) {
        resStr = "<item><macAddr>" + temp[1] + "</macAddr>" + "<startTime>" + temp[2] + "</startTime>" + "<endTime>" + temp[3] + "</endTime>" + "<duration>" + temp[4] + "</duration>" + "</item>";
      }
    }
    else if (listTypeId == TYPEID89)
    {
    	int length=6;
      if (temp.length == length) {
        resStr = "<feeInfo><CallerNo>" + temp[1] + "</CallerNo>" + "<CalledNo>" + temp[2] + "</CalledNo>" + "<startingTime>" + temp[3] + "</startingTime><lastingTime>" + temp[4] + "</lastingTime><callingFee>" + temp[5] + "</callingFee></feeInfo>";
      }
    }
    else if ((listTypeId == TYPEID1) && 
      (temp.length == 7)) {
      resStr = "<feeInfo><CallerNo>" + temp[1] + "</CallerNo><CalledNo>" + temp[2] + "</CalledNo><startingTime>" + temp[3] + "</startingTime><lastingTime>" + temp[4] + "</lastingTime><callingFee>" + temp[5] + "</callingFee><eventType>" + temp[6] + "</eventType></feeInfo>";
    }
    return resStr;
  }
  
  public static byte[] getBytesFromStream(DataInputStream dataInputStream)
    throws IOException
  {
    byte[] retBytes = new byte[0];
    byte[] newBytes = (byte[])null;
    logger.info("reading response started!");
    try
    {
      for (;;)
      {
        byte tempByte = dataInputStream.readByte();
        byte[] tempBytes = { tempByte };
        newBytes = new byte[1 + retBytes.length];
        
        System.arraycopy(retBytes, 0, newBytes, 0, retBytes.length);
        
        System.arraycopy(tempBytes, 0, newBytes, retBytes.length, 1);
        retBytes = newBytes;
      }
    }
    catch (EOFException localEOFException) {}
    return newBytes;
  }
  
  public static byte[] arraYtoBytes(String[] arr)
  {
    byte[] retBytes = new byte[0];
    retBytes = writeString(retBytes, arr[0], 2, FILL_ALIGN_LEFT, FILL_CHARBLANK);
    retBytes = writeShort(retBytes, Integer.parseInt(arr[1]));
    retBytes = writeInt(retBytes, Integer.parseInt(arr[2]));
    retBytes = writeString(retBytes, arr[3], 2, FILL_ALIGN_LEFT, FILL_CHARBLANK);
    retBytes = writeString(retBytes, arr[4], 10, FILL_ALIGN_LEFT, FILL_CHARBLANK);
    retBytes = writeString(retBytes, arr[5], 11, FILL_ALIGN_LEFT, FILL_CHARBLANK);
    retBytes = writeString(retBytes, arr[6], 25, FILL_ALIGN_LEFT, FILL_CHARBLANK);
    retBytes = writeString(retBytes, arr[7], 11, FILL_ALIGN_LEFT, FILL_CHARBLANK);
    retBytes = writeInt(retBytes, Integer.parseInt(arr[8]));
    retBytes = writeString(retBytes, arr[9], 6, FILL_ALIGN_LEFT, FILL_CHARBLANK);
    retBytes = writeString(retBytes, arr[10], 3, FILL_ALIGN_LEFT, FILL_CHARBLANK);
    return retBytes;
  }
  
  public static byte[] writeInt(byte[] bufBytes, int i)
  {
    byte[] bytes = new byte[4];
    bytes[0] = ((byte)(i >>> 24));
    bytes[1] = ((byte)(i >>> 16));
    bytes[2] = ((byte)(i >>> 8));
    bytes[3] = ((byte)(i >>> 0));
    
    byte[] newBufBytes = new byte[bufBytes.length + 4];
    
    System.arraycopy(bufBytes, 0, newBufBytes, 0, bufBytes.length);
    
    System.arraycopy(bytes, 0, newBufBytes, bufBytes.length, bytes.length);
    
    return newBufBytes;
  }
  
  public static byte[] writeString(byte[] bufBytes, String str, int length)
  {
    byte[] thisStrBytes = str.getBytes();
    
    byte[] stdStrByte = new byte[length];
    
    System.arraycopy(thisStrBytes, 0, stdStrByte, 0, thisStrBytes.length > length ? 
      length : thisStrBytes.length);
    
    byte[] newBufBytes = new byte[bufBytes.length + length];
    
    System.arraycopy(bufBytes, 0, newBufBytes, 0, bufBytes.length);
    
    System.arraycopy(stdStrByte, 0, newBufBytes, bufBytes.length, stdStrByte.length);
    
    return newBufBytes;
  }
  
  public static byte[] writeShort(byte[] bufBytes, int i)
  {
    byte[] bytes = new byte[2];
    bytes[0] = ((byte)(i >>> 8));
    bytes[1] = ((byte)(i >>> 0));
    
    byte[] newBufBytes = new byte[bufBytes.length + 2];
    
    System.arraycopy(bufBytes, 0, newBufBytes, 0, bufBytes.length);
    
    System.arraycopy(bytes, 0, newBufBytes, bufBytes.length, bytes.length);
    
    return newBufBytes;
  }
  
  public static byte[] writeString(byte[] bufBytes, String str, int length, String align, char blank)
  {
    String writeStr = formatVarByBlank(str, align, blank, length);
    return writeString(bufBytes, writeStr, length);
  }
  
  public static String formatVarByBlank(String var, String align, char blank, int varLen)
  {
    int len = var.getBytes().length;
    StringBuffer fillStr = new StringBuffer();
    int fillStrLen = varLen - len;
    for (int i = 0; i < fillStrLen; i++) {
      fillStr.append(blank);
    }
    if (align.equals(FILL_ALIGN_LEFT)) {
      return var + fillStr.toString();
    }
    return fillStr.toString() + var;
  }
  
  public static String getRealTrueValueWidthOutBlank(byte[] bytes, int pos, int len)
    throws Exception
  {
    byte[] tempBytes = new byte[len];
    for (int i = 0; i < len; i++) {
      tempBytes[i] = 1;
    }
    return null;
  }
  
  public static String getRealTrueValue(byte[] bytes, int pos, int len)
  {
    byte[] newbytes = new byte[len];
    for (int i = 0; i < len; i++) {
      newbytes[i] = bytes[(pos + i)];
    }
    BufferedInputStream is = new BufferedInputStream(new ByteArrayInputStream(newbytes));
    BufferedReader read = null;
    try
    {
      read = new BufferedReader(new InputStreamReader(is, "GB2312"));
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException) {}
    StringBuffer sb = new StringBuffer();
    int c = 0;
    try
    {
      while ((c = read.read()) != -1) {
        sb.append((char)c);
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    return sb.toString().trim();
  }

}
