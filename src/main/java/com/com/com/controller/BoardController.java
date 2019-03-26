package com.com.com.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.com.com.service.serviceImpl.BoardServiceImpl;
import com.tobesoft.platform.data.Dataset;

@Controller
public class BoardController 
{

	@Resource(name = "service")
	private BoardServiceImpl boardService;
	
	@RequestMapping("list")
	public String list(Model model, @RequestParam(required=false, defaultValue="1") int pageNum) 
	{
		System.out.println(pageNum);
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(); 
		list = boardService.list(pageNum);
		int maxPage = boardService.maxPageInBoard();
		
		System.out.println("list.isEmpty:"+list.size());
		
		model.addAttribute("boardList", list);
		model.addAttribute("maxPage", maxPage);
		return "board/list";
	}
	
	@RequestMapping("writeView")
	public String writeView()
	{
		return "board/writeView";
	}
	
	@RequestMapping("detail")
	public String detail(@RequestParam Map<String, Object> seq, Model model)
	{
		Map <String, Object> map = new HashMap<String, Object>();
		map = boardService.detail(seq);
		model.addAttribute("dataMap", map);
		return "board/writeView";
	}
	
	
	@RequestMapping("insert")
	public String insert(Model model , @RequestParam Map<String, Object> map) 
	{
		System.out.println(map);
			boardService.insert(map);
			
		return "redirect:list";
	}
	
	@RequestMapping("modify")
	public String modify(@RequestParam Map<String, Object> map)
	{
		boardService.modify(map);
		return "redirect:list";
	}

	@Autowired
	private SqlSessionTemplate sqlSession;
	@RequestMapping("delete")
	public String delete(Integer[] chkBox)
	{
		List<Integer> list = Arrays.asList(chkBox);// asList = Integer
		boardService.delete(list);

		return "redirect:list";
	}
	
	@RequestMapping("search")
	public String search(@RequestParam Map<String, Object> map, Model model) 
	{

		
		List <Map<String, Object>> list = sqlSession.selectList("mapper.search", map);
		System.out.println("listsize:"+list.size());
		model.addAttribute("boardList", list);
		return "board/list";
	}
	
	@RequestMapping("ajaxSearch")
	public String ajaxSearch(@RequestParam Map<String, Object> map, Model model)
	{
		
		System.out.println("type:"+map.get("searchType"));
		System.out.println("txt:"+map.get("searchTxt"));
		System.out.println("stDate:"+map.get("stDate"));
		System.out.println("enDate:"+map.get("enDate"));
		List <Map<String, Object>> list = sqlSession.selectList("mapper.search", map);
		model.addAttribute("boardList", list);
		return "board/ajaxSearch";
	}
	@RequestMapping("fileUpload")
	public String fileUpload()
	{
		return "board/fileForm";
	}
	
	@RequestMapping("fileUploadProc")
	 public String fileUpload (MultipartHttpServletRequest mRequest) throws Exception, IOException
	{
		String filePath = "\\resources\\upload\\";
		
        Iterator<String> iterator = mRequest.getFileNames();
        
        File file = new File(filePath);

        if(file.exists() == false)
        {

            file.mkdirs();

        }

        while(iterator.hasNext())
        {

        	MultipartFile mFile= mRequest.getFile(iterator.next());

            if(mFile.isEmpty() == false)
            {

            	String fileName = System.currentTimeMillis() + "_" + mFile.getOriginalFilename();
		
                mFile.transferTo(new File(filePath + fileName));     
            }
    
        }
        
        return "board/fileUpload";
        
	}
	
}
