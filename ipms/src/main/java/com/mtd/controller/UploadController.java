package com.mtd.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.wechat.WeChat;
import com.wechat.api.MediaApi;
import com.wechat.bean.MediaRespone;
import com.wechat.bean.WeChatMedia;

@Controller
@RequestMapping("/upload")
public class UploadController {

	@RequestMapping("/upload_image")
	public String addContent(
			@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request) throws IOException {
		{

			if (file.getSize() != 0) {
				String path = request.getSession().getServletContext()
						.getRealPath("upload/userphoto");
				String url = "http://" + request.getServerName() // ��������ַ
						+ ":" + request.getServerPort() // �˿ں�
						+ request.getContextPath() // ��Ŀ����
						+ "/upload/wechat";// ����ҳ���������ַ

				String fileName = System.currentTimeMillis() + ".jpg";
				File targetFile = new File(path, fileName);
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				// ����
				try {
					file.transferTo(targetFile);
					// user.setPicUrl(url + "\\" + fileName);
					WeChatMedia a=new MediaApi().uploadMedia(WeChat.getAccessToken(), url
							+ "/" + fileName);
//					MediaRespone r=new MediaApi().uploadImage(WeChat.getAccessToken(), url
//							+ "/" + fileName);
					
					System.out.println(fileName);
					System.out.println(url);
					System.out.println(path);
					System.out.println("img mediaid:"+a.getMedia_id());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
		return "index";
	}

}
