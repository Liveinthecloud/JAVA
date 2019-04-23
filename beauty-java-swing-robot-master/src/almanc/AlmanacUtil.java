package almanc;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author ��ˬ
 * @Description: ${TODO}(��ȡ��ҳ����) 
 */
public class AlmanacUtil {

	/**
	 * ����������
	 */
	private AlmanacUtil() {
	}

	public static Almanac getAlmanac() {
		String url = "http://tools.2345.com/rili.htm";
		String html = pickData(url);
		Almanac almanac = analyzeHTMLByString(html);
		return almanac;
	}

	/*
	 * ��ȡ��ҳ��Ϣ
	 */
	private static String pickData(String url) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpget = new HttpGet(url);
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				// ��ȡ��Ӧʵ��
				HttpEntity entity = response.getEntity();
				// ��ӡ��Ӧ״̬
				if (entity != null) {
					return EntityUtils.toString(entity);
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// �ر�����,�ͷ���Դ
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/*
	 * ʹ��jsoup������ҳ��Ϣ
	 */
	private static Almanac analyzeHTMLByString(String html) {
		String solarDate, lunarDate, chineseAra, should, avoid, festival = " ";
		Document document = Jsoup.parse(html);
		// ��ȡ����ʱ��
		Element solar = document.getElementById("info_all");
		solarDate = solar.text().toString();
		// ��ȡũ��ʱ��
		Element eLunarDate = document.getElementById("info_nong");
		lunarDate = eLunarDate.child(0).html().substring(1, 3) + " " + eLunarDate.html().substring(11);
		//��ȡ��ɵ�֧���귨
		Element eChineseAra = document.getElementById("info_chang");
		chineseAra = eChineseAra.text().toString();
		// ��ȡ��
		should = getSuggestion(document, "yi");
		// ��
		avoid = getSuggestion(document, "ji");
		// ����
		festival = getSuggestion(document, "festival");
		Almanac almanac = new Almanac(solarDate, lunarDate, chineseAra, should, avoid, festival);
		return almanac;
	}

	/*
	 * ��ȡ��/��
	 */
	private static String getSuggestion(Document doc, String id) {
		Element element = doc.getElementById(id);
		Elements elements = element.getElementsByTag("a");
		StringBuffer sb = new StringBuffer();
		for (Element e : elements) {
			sb.append(e.text() + "  ");
		}
		return sb.toString();
	}


}