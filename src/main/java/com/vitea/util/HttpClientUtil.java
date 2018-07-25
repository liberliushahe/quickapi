package com.vitea.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 处理http请求 优化请求流程 使用http的连接池，这样不需要每次get都需要3次握手，提高并发能力
 * 
 * @author liushahe
 *
 */
public class HttpClientUtil {

	private static Logger logger = LoggerFactory.getLogger("com.vitea.util.HttpClientUtil");

	public static PoolingHttpClientConnectionManager cm = null;
	public static CloseableHttpClient httpClient = null;

	/**
	 * 默认content 类型由于http主要是提交数据所以设置类型为表单类型，根据具体要求设置
	 */
	private static final String DEFAULT_CONTENT_TYPE = "application/x-www-form-urlencoded";

	/**
	 * 默认请求超时时间30s
	 */
	private static final int DEFAUL_TTIME_OUT =Integer.parseInt(PropertiesUtil.getProperties("defaulttimeout"));
	private static final int count = Integer.parseInt(PropertiesUtil.getProperties("defaultMaxPerRoute"));
	private static final int totalCount = Integer.parseInt(PropertiesUtil.getProperties("maxTotal"));
	private static final int Http_Default_Keep_Time = Integer.parseInt(PropertiesUtil.getProperties("Http_Default_Keep_Time"));

	/**
	 * 初始化连接池
	 */
	public static synchronized void initPools() {
		if (httpClient == null) {
			cm = new PoolingHttpClientConnectionManager();
			cm.setDefaultMaxPerRoute(count);
			cm.setMaxTotal(totalCount);
			httpClient = HttpClients.custom().setKeepAliveStrategy(Strategy).setConnectionManager(cm).build();
		}
	}

	 /**
     * Http connection keepAlive 设置
     */
    public static ConnectionKeepAliveStrategy Strategy = new ConnectionKeepAliveStrategy() {
        public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
            HeaderElementIterator it = new BasicHeaderElementIterator(response.headerIterator(HTTP.CONN_KEEP_ALIVE));
            int keepTime = Http_Default_Keep_Time;
            while (it.hasNext()) {
                HeaderElement he = it.nextElement();
                String param = he.getName();
                String value = he.getValue();
                if (value != null && param.equalsIgnoreCase("timeout")) {
                    try {
                        return Long.parseLong(value) * 1000;
                    } catch (Exception e) {
                        e.printStackTrace();
                        logger.error("format KeepAlive timeout exception, exception:" + e.toString());
                    }
                }
            }
            return keepTime * 1000;
        }
    };

    public static CloseableHttpClient getHttpClient() {
        return httpClient;
    }

    public static PoolingHttpClientConnectionManager getHttpConnectionManager() {
        return cm;
    }
	/**
	 * 根据参数类型进行相应的请求 返回相应的
	 * @param url
	 * @param methodName
	 * @param contentType
	 * @param timeout
	 * @return
	 */
	public static HttpRequestBase getRequest(String url,String methodName, String contentType,int timeout){
		
		if(httpClient==null){
			initPools();
		}
		HttpRequestBase method = null;
        if (timeout <= 0) {
            timeout = DEFAUL_TTIME_OUT;
        }
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout * 1000).setConnectTimeout(timeout * 1000)
                .setConnectionRequestTimeout(timeout * 1000).setExpectContinueEnabled(false).build();

        if (HttpPut.METHOD_NAME.equalsIgnoreCase(methodName)) {
            method = new HttpPut(url);
        } else if (HttpPost.METHOD_NAME.equalsIgnoreCase(methodName)) {
            method = new HttpPost(url);
        } else if (HttpGet.METHOD_NAME.equalsIgnoreCase(methodName)) {
            method = new HttpGet(url);
        } else {
            method = new HttpPost(url);
        }
        if (StringUtils.isBlank(contentType)) {
            contentType = DEFAULT_CONTENT_TYPE;
        }
        method.addHeader("Content-Type", contentType);
        method.addHeader("Accept", contentType);
        method.setConfig(requestConfig);
        return method;
		
	}
	/**
	 * 处理POST请求
	 * @param url
	 * @param data
	 * @return
	 */
    public static String execute(String url, String data,String contentType) {
        long startTime = System.currentTimeMillis();
        HttpEntity httpEntity = null;
        HttpEntityEnclosingRequestBase method = null;
        String responseBody = "";
        try {
            if (httpClient == null) {
                initPools();
            }
            method = (HttpEntityEnclosingRequestBase) getRequest(url, HttpPost.METHOD_NAME, contentType, 0);
            method.setEntity(new StringEntity(data,"UTF-8"));
            HttpContext context = HttpClientContext.create();
            CloseableHttpResponse httpResponse = httpClient.execute(method, context);
           
            
            httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                responseBody = EntityUtils.toString(httpEntity, "UTF-8");
            }

        } catch (Exception e) {
            if(method != null){
                method.abort();
            }
            e.printStackTrace();
            logger.error(
                    "execute post request exception, url:" + url + ", exception:" + e.toString() + ", cost time(ms):"
                            + (System.currentTimeMillis() - startTime));
        } finally {
            if (httpEntity != null) {
                try {
                    EntityUtils.consumeQuietly(httpEntity);
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error(
                            "close response exception, url:" + url + ", exception:" + e.toString() + ", cost time(ms):"
                                    + (System.currentTimeMillis() - startTime));
                }
            }
        }
        return responseBody;
    }
	
    /**
     * 执行GET 请求
     *
     * @param uri
     * @return
     */
    public static String execute(String url) {
        long startTime = System.currentTimeMillis();
        HttpEntity httpEntity = null;
        HttpRequestBase method = null;
        String responseBody = "";
        try {
            if (httpClient == null) {
                initPools();
            }
            method = getRequest(url, HttpGet.METHOD_NAME, DEFAULT_CONTENT_TYPE, 0);
            HttpContext context = HttpClientContext.create();
            CloseableHttpResponse httpResponse = httpClient.execute(method, context);
            httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                responseBody = EntityUtils.toString(httpEntity, "UTF-8");
                logger.info("请求URL: "+url+"+  返回状态码："+httpResponse.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            if(method != null){
                method.abort();
            }
            e.printStackTrace();
            logger.error("execute get request exception, url:" + url + ", exception:" + e.toString() + ",cost time(ms):"
                    + (System.currentTimeMillis() - startTime));
        } finally {
            if (httpEntity != null) {
                try {
                    EntityUtils.consumeQuietly(httpEntity);
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error("close response exception, url:" + url + ", exception:" + e.toString() + ",cost time(ms):"
                            + (System.currentTimeMillis() - startTime));
                }
            }
        }
        return responseBody;
    }
	
}
