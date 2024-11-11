/**
 * 
 */
package org.lhp.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @author rcx
 * @date   2020年4月9日  下午1:52:55
 * @class  org.lhp.util.ExportWord
 * 
 * 
 */
public class ExportWord {
	public ExportWord(){
	}
	public void createDoc(Map<String,Object> dataMap,String fileName){
		
		Configuration configuration=null;
		configuration = new Configuration();
		configuration.setDefaultEncoding("utf-8");
		configuration.setClassForTemplateLoading(this.getClass(), "");
		Template t=null;
		try {
			t=configuration.getTemplate("ScoreMould.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		File outFile = new File(fileName);
		Writer out=null;
		FileOutputStream fos=null;
		try {
			fos=new FileOutputStream(outFile);
			OutputStreamWriter oWriter = new OutputStreamWriter(fos, "UTF-8");
			out=new BufferedWriter(oWriter);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			t.process(dataMap, out);
			out.close();
			fos.close();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		Map<String,Object> map1=new HashMap<String, Object>();
		List<Map<String,Object>> list=new ArrayList<>();
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("id", 1);
			map.put("name", "数学建模");
			map.put("info", "学习数学思维");
			map.put("teacher", "李辉霞");
			map.put("score", 89);
			Map<String,Object> map3=new HashMap<String, Object>();
			map3.put("id", 2);
			map3.put("name", "语文");
			map3.put("info", "学习语文思维");
			map3.put("teacher", "朱允炆");
			map3.put("score", 89);
			Map<String,Object> map4=new HashMap<String, Object>();
			map4.put("id", 2);
			map4.put("name", "语文");
			map4.put("info", "学习语文思维");
			map4.put("teacher", "朱允炆");
			map4.put("score", 89);
		list.add(map);
		list.add(map3);
		list.add(map4);
		
		map1.put("tr1", list);
		ExportWord t=new ExportWord();
		t.createDoc(map1, "File/score.doc");
	}
	
}
