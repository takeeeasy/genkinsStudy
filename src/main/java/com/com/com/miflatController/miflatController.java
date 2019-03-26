package com.com.com.miflatController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tobesoft.platform.PlatformRequest;
import com.tobesoft.platform.PlatformResponse;
import com.tobesoft.platform.data.ColumnInfo;
import com.tobesoft.platform.data.Dataset;
import com.tobesoft.platform.data.DatasetList;
import com.tobesoft.platform.data.VariableList;

@Controller
public class miflatController {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@RequestMapping("testst")
	public void miflatformSend(HttpServletResponse response) throws IOException {
		 System.out.println("test");
		
		/****** Service API �ʱ�ȭ ******/
		VariableList vl = new VariableList();
		DatasetList  dl = new DatasetList();

		List <Map <String, Object>> list = new ArrayList<Map <String, Object>>();
		list = sqlSession.selectList("mapper.mflist");
		
		
		/********* Dataset ���� ************/
		Dataset ds = new Dataset("ds_sawon");
		ds.addColumn("seq", ColumnInfo.COLTYPE_STRING, 20);
		ds.addColumn("memName", ColumnInfo.COLTYPE_STRING, 20);
		ds.addColumn("memId", ColumnInfo.COLTYPE_STRING, 20);
		ds.addColumn("boardSubject", ColumnInfo.COLTYPE_STRING, 20);
		ds.addColumn("boardContent", ColumnInfo.COLTYPE_STRING, 20);
		ds.addColumn("regDate", ColumnInfo.COLTYPE_STRING, 20);
		ds.addColumn("uptDate", ColumnInfo.COLTYPE_STRING, 20);
		ds.addColumn("viewCnt", ColumnInfo.COLTYPE_STRING, 20);
		
		for (int i = 0 ; i < list.size() ; i++)
		{
			String seq =  list.get(i).get("seq").toString();
			String memName = list.get(i).get("memName").toString();
			String memId = list.get(i).get("memId").toString();
			String boardSubject = list.get(i).get("boardSubject").toString();
			String boardContent = list.get(i).get("boardContent").toString();
			String regDate = list.get(i).get("regDate").toString(); 
			
			int row = ds.appendRow();
			ds.setColumn(row, "seq", seq);
			ds.setColumn(row, "memName", memName);
			ds.setColumn(row, "memId", memId);
			ds.setColumn(row, "boardSubject", boardSubject);
			ds.setColumn(row, "boardContent", boardContent);
			ds.setColumn(row, "regDate", regDate);
		}

		/********* ������ Dataset�� DatasetList�� �߰� ************/
		dl.addDataset(ds);

		/******** ��� XML ���� �� Web Server�� ���� ******/
		
			PlatformResponse pRes = new PlatformResponse(response, PlatformRequest.XML, "utf-8");
			pRes.sendData(vl, dl);

			System.out.println("testEnd");
	}
	
	@RequestMapping("getmp")
	public void miflatformReceive(HttpServletRequest req, HttpServletResponse response) throws IOException {
		/****** Service API �ʱ�ȭ ******/
		VariableList vlist = new VariableList();
		DatasetList dsList = new DatasetList();
		PlatformRequest pReq = new PlatformRequest(req, "utf-8");
		
		/****** Web Server���� XML���� �� Parsing **/
		pReq.receiveData();
		
		/** Listȹ�� �� Dataset,���� ȹ�� **/
		vlist = pReq.getVariableList();
		dsList = pReq.getDatasetList();
		
		String searchType = vlist.getValueAsString("searchType");
		String searchTxt = vlist.getValueAsString("searchTxt");
		String stDate = vlist.getValueAsString("stDate");
		String enDate = vlist.getValueAsString("enDate");
		
		Map <String ,Object> map = new HashMap<String, Object>();
		  
		map.put("searchType", searchType);
		map.put("searchTxt", searchTxt);
		map.put("stDate", stDate);
		map.put("enDate", enDate);
		System.out.println("map�� "+map.get("searchType"));
		System.out.println("map�� "+map.get("searchTxt"));
		System.out.println("map�� "+map.get("stDate"));
		System.out.println("map�� "+map.get("enDate"));
		List <Map<String, Object>> list = sqlSession.selectList("miplat.search", map);
		
		
		/********* Dataset ���� ************/
		Dataset ds = new Dataset("schList");
		ds.addColumn("seq", ColumnInfo.COLTYPE_STRING, 20);
		ds.addColumn("memName", ColumnInfo.COLTYPE_STRING, 20);
		ds.addColumn("memId", ColumnInfo.COLTYPE_STRING, 20);
		ds.addColumn("boardSubject", ColumnInfo.COLTYPE_STRING, 20);
		ds.addColumn("boardContent", ColumnInfo.COLTYPE_STRING, 20);
		ds.addColumn("regDate", ColumnInfo.COLTYPE_STRING, 20);
		ds.addColumn("uptDate", ColumnInfo.COLTYPE_STRING, 20);
		ds.addColumn("viewCnt", ColumnInfo.COLTYPE_STRING, 20);
		
		for (int i = 0 ; i < list.size() ; i++)
		{
			String seq =  list.get(i).get("seq").toString();
			String memName = list.get(i).get("memName").toString();
			String memId = list.get(i).get("memId").toString();
			String boardSubject = list.get(i).get("boardSubject").toString();
			String boardContent = list.get(i).get("boardContent").toString();
			String regDate = list.get(i).get("regDate").toString(); 
			System.out.println(
							seq+",\n"+
							memName+",\n"+
							memId+",\n"+
							boardSubject+",\n"
							);
			int row = ds.appendRow();
			ds.setColumn(row, "seq", seq);
			ds.setColumn(row, "memName", memName);
			ds.setColumn(row, "memId", memId);
			ds.setColumn(row, "boardSubject", boardSubject);
			ds.setColumn(row, "boardContent", boardContent);
			ds.setColumn(row, "regDate", regDate);
			
			/** ���� rowCount�� �������� **/
			System.out.println("rownum:"+ds.getRowCount());
			/** ���� row�� "seq"�� �������� **/
			System.out.println(ds.getColumnAsString(row, "seq"));
		}
	

		/********* ������ Dataset�� DatasetList�� �߰� ************/
		dsList.addDataset(ds);

		/******** ��� XML ���� �� Web Server�� ���� ******/
		
			PlatformResponse pRes = new PlatformResponse(response, PlatformRequest.XML, "utf-8");
			pRes.sendData(vlist, dsList);

	}
}
