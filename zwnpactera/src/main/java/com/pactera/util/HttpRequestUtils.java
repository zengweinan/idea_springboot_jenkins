package com.pactera.util;

import org.apache.commons.lang.StringUtils;

import javax.net.ssl.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 *
 */
public class HttpRequestUtils {

	private HttpRequestUtils() {
	}

	public static final String POST = "POST";
	public static final String GET = "GET";

	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet(String url, String param) {
		StringBuffer result = new StringBuffer();
		BufferedReader in = null;
		String urlNameString = "";
		try {
			if (StringUtils.isNotBlank(param))
				urlNameString = url + "?" + param;
			else
				urlNameString = url;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			// Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			// for (String key : map.keySet()) {
			// System.out.println(key + "--->" + map.get(key));
			// }
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result.toString();
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		// String result = "";
		StringBuffer result = new StringBuffer();
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				// result += line;
				result.append(line);
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result.toString();
	}
	
	
	public static String doPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		// String result = "";
		StringBuffer result = new StringBuffer();
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				// result += line;
				result.append(line);
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result.toString();
	}

	public static String sendGet(String url) {
		return sendGet(url, null);
	}

	public static InputStream getInputStream(String url) {
		InputStream is = null;
		try {
			URL realUrl = new URL(url);
			// 打开连接
			HttpURLConnection http = (HttpURLConnection) realUrl.openConnection();
			http.setRequestMethod(HttpRequestUtils.GET); // 必须是get方式请求
			// 设置请求属性
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);

			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒

			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒

			http.connect();
			// 获取所有响应头字段
			// Map<String, List<String>> map = http.getHeaderFields();
			// 遍历所有的响应头字段
			// for (String key : map.keySet()) {
			// System.out.println(key + "--->" + map.get(key));
			// }

			// 获取文件转化为byte流
			is = http.getInputStream();

		} catch (Exception e) {
			System.out.println("发送 GET 请求出现异常！" + e);
			e.printStackTrace();
		}
		return is;
	}

	/**
	 * 重定向
	 * 
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @param url
	 */
	public static void redirectUrl(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			String url) {
		try {
			httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 重定向到http://的url
	 * 
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @param url
	 */
	public static void redirectHttpUrl(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			String url) {
		try {
			httpServletResponse.sendRedirect(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getRequestFullUriNoContextPath(HttpServletRequest request) {
		String port = "";
		if (request.getServerPort() != 80) {
			port = ":" + request.getServerPort();
		}
		String fullUri = request.getScheme() + "://" + request.getServerName() + port + request.getRequestURI();
		if (request.getQueryString() != null)
			fullUri += "?" + request.getQueryString();
		return fullUri;
	}

	/**
	 * 发送中文参数
	 * 
	 * @param requestUrl
	 * @param requestMethod:GET/POST
	 * @param param
	 * @return
	 */
	public static String send(String requestUrl, String requestMethod, String param) {
		String result = "";
		try {
			TrustManager[] tm = { new WeiXinX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);

			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod(requestMethod);
			if (null != param) {
				OutputStream outputStream = conn.getOutputStream();
				outputStream.write(param.getBytes("UTF-8"));
				outputStream.close();
			}
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			conn.disconnect();
			result = buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 发送中文参数
	 * 
	 * @param requestUrl
	 * @param requestMethod:GET/POST
	 * @param param
	 * @return
	 */
	public static String send(String requestUrl, String requestMethod, String param, String token) {
		String result = "";
		try {
			TrustManager[] tm = { new WeiXinX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);
			conn.setRequestProperty("Authorization", token);

			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod(requestMethod);
			if (null != param) {
				OutputStream outputStream = conn.getOutputStream();
				outputStream.write(param.getBytes("UTF-8"));
				outputStream.close();
			}
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			conn.disconnect();
			result = buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static byte[] requestByte(String requestUrl, String requestMethod) {
		return requestByte(requestUrl, requestMethod, null);
	}

	public static byte[] requestByte(String requestUrl, String requestMethod, String param) {
		try {
			TrustManager[] tm = { new WeiXinX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);

			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod(requestMethod);
			if (null != param) {
				OutputStream outputStream = conn.getOutputStream();
				outputStream.write(param.getBytes("UTF-8"));
				outputStream.close();
			}
			InputStream inputStream = conn.getInputStream();
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] buffer = new byte[4096];
			int n = 0;
			while (-1 != (n = inputStream.read(buffer))) {
				output.write(buffer, 0, n);
			}
			return output.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


}
class WeiXinX509TrustManager implements X509TrustManager {
	public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	}

	public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	}

	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}
}


