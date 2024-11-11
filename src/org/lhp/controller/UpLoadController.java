/**
 * 
 */
package org.lhp.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.lhp.bean.Student_Course;
import org.lhp.service.StudentCourseService;

/**
 * @author rcx
 * @date 2020年4月9日 下午8:03:47
 * @class org.lhp.controller.UpLoadController
 * 
 * 
 */
@Controller
public class UpLoadController {
	@Resource(name = "studentCourseService")
	StudentCourseService service;

	@RequestMapping("UploadWord.do")
	public void lpgin(HttpServletResponse response, HttpServletRequest request) throws IOException, ServletException {
		// 得到要下载的文件名
		String fileName = "score.doc"; // 23239283-92489-阿凡达.avi
		// 上传的文件都是保存在/WEB-INF/upload目录下的子目录当中
		String fileSaveRootPath = request.getServletContext().getRealPath("/WEB-INF/File");
		// 通过文件名找出文件的所在目录
		String path = fileSaveRootPath;
		// 得到要下载的文件
		// 处理文件名
		String realname = fileName.substring(fileName.indexOf("_") + 1);
		// 设置响应头，控制浏览器下载该文件
		response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
		// 读取要下载的文件，保存到文件输入流
		FileInputStream in = new FileInputStream(path + "\\" + fileName);
		// 创建输出流
		OutputStream out = response.getOutputStream();
		// 创建缓冲区
		byte buffer[] = new byte[1024];
		int len = 0;
		// 循环将输入流中的内容读取到缓冲区当中
		while ((len = in.read(buffer)) > 0) {
			// 输出缓冲区的内容到浏览器，实现文件下载
			out.write(buffer, 0, len);
		}
		// 关闭文件输入流
		in.close();
		// 关闭输出流
		out.close();
	}



}
