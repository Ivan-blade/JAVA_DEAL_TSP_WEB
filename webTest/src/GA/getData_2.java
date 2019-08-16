package GA;
//�ߵ�api
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Base64.Decoder;

 //����Ч�ص�������
public class getData_2 {
 
	private static final String GAODE_AK = "0b6b6ed2e01de1b84a393fd4faa9ae84";
	
	/**
	 * ���������ַ�ľ�γ������ key lng(����),lat(γ��)
	 */
	public static Map<String, String> getLatitude(String address) {
		try {
			// ����ַת����utf-8��16����
			address = URLEncoder.encode(address, "UTF-8");
			URL resjson = new URL("https://restapi.amap.com/v3/geocode/geo?address="
					+ address + "&output=JSON&key=" + GAODE_AK);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					resjson.openStream()));
			String res;
			StringBuilder sb = new StringBuilder("");
			while ((res = in.readLine()) != null) {
				sb.append(res.trim());
			}
			in.close();
			String str = sb.toString();
			//System.out.println(str);
			if(str!=null&&!str.equals("")){
				Map<String, String> map = null;
				int infoStart = str.indexOf("location\":");
				int infoEnd = str.indexOf(",\"level");
				if (infoStart > 0 && infoEnd > 0) {
					String info= str.substring(infoStart + 10, infoEnd);
					//System.out.println(info);
					int lngEnd=info.indexOf(",");
					String lng = info.substring(1, lngEnd);
					String lat = info.substring(lngEnd + 1,info.length()-1);
					map = new HashMap<String, String>();
					map.put("lng", lng);
					map.put("lat", lat);
					return map;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//���������ݸĳ�double���͵�λ�ĳ�ǧ��
	private static double format(String temp) {
		Double one=Double.parseDouble(temp);
		return (one/1000);
	}
	
	//�������ؾ���
	@SuppressWarnings("null")
	public static double backDis(Map<String, String> A,Map<String, String> B) {
		//System.out.println("Ŀ�������㿪ʼ...");
		try {
			URL resjson = new URL
					("https://restapi.amap.com/v3/direction/driving?&output=JSON&extensions=all&origin="+A.get("lng")+","+A.get("lat")+"&destination="+B.get("lng")+","+B.get("lat")+"&key="+GAODE_AK);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					resjson.openStream()));
			String res;
			StringBuilder sb = new StringBuilder("");
			while ((res = in.readLine()) != null) {
				sb.append(res.trim());
			}
			in.close();
			String str = sb.toString();
			//System.out.println(str);
			if(str!=null&&!str.equals("")){
				double temp=0;
				int disStart = str.indexOf("distance\":");
				//System.out.println(disStart);
				int disEnd = str.indexOf(",\"duration");
				//System.out.println(disEnd);
				if (disStart > 0  && disEnd > 0) {
					//System.out.println("������㿪ʼ...");
					String dis = str.substring(disStart + 11, disEnd-1);
					//System.out.println(dis);
					temp=format(dis);
					//System.out.println(temp);
					if(temp<=1.5)
					{
						temp=0;
						//System.out.println("����=0�Ѿ�����");
						return temp;
					}else {
						//System.out.println("�����Ѿ�����");
						return temp;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("error happens!");
		return (Double) null;
	}

}