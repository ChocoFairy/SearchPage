package ai;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.tomcat.jni.File;

import com.baidu.aip.util.Base64Util;

import utils.GsonUtils;
import utils.HttpUtil;

public class FaceSearch {
	
	public static String search(String imgFile) {
		
		// 读取一张图片，以二进制形式，再转化为BASE64编码
		//String imgFile = "D:\\Program\\J2EE\\Projects\\SearchPage\\WebContent\\img\\zzm2.jpg";
		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		// 请求url
		String url = "https://aip.baidubce.com/rest/2.0/face/v3/search";
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("image", Base64Util.encode(data));     // BASE64字符串
			map.put("image_type", "BASE64");
			map.put("liveness_control", "NONE");
			map.put("group_id_list", "group_1");
			map.put("quality_control", "LOW");
			
			String param = GsonUtils.toJson(map);
			String accessToken = AuthService.getAuth();
			
			String result = HttpUtil.post(url, accessToken,"application/json" ,param);
			System.out.println(result);
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
