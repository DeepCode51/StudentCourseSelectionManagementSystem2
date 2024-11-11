/**
 * 
 */
package org.lhp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lhp.service.ProvinceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

/**
 * @author rcx
 * @date   2020年4月9日  上午10:28:04
 * @class  org.lhp.controller.ProvintesController
 * 
 * 
 */
@Controller
public class ProvintesController {
	
	@Resource(name="provinceService")
	ProvinceService service;
	
	@RequestMapping("QueryProvintes.do")
	public void lpgin(HttpServletResponse response,HttpServletRequest request) throws IOException{
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		List<?> list = service.queryPrivates();
		
		Gson gson=new Gson();
		String json = gson.toJson(list);
		System.out.println(list);
		PrintWriter out = response.getWriter();
		out.write(json);
	}
	@RequestMapping("QueryCity.do")
	public void querycity(HttpServletResponse response,HttpServletRequest request) throws IOException{
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String provinte = request.getParameter("provinte");
		List<?> list = service.queryCity(provinte);
		
		Gson gson=new Gson();
		String json = gson.toJson(list);
		System.out.println(list);
		PrintWriter out = response.getWriter();
		out.write(json);
	}
	@RequestMapping("QueryArea.do")
	public void QueryArea(HttpServletResponse response,HttpServletRequest request) throws IOException{
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String city = request.getParameter("city");
		List<?> list = service.QueryArea(city);
		
		Gson gson=new Gson();
		String json = gson.toJson(list);
		System.out.println(list);
		PrintWriter out = response.getWriter();
		out.write(json);
	}
}
