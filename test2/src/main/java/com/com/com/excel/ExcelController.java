package com.com.com.excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.com.com.approval.service.serviceImpl.ApprovalServiceImpl;

@Controller
public class ExcelController {
	@Resource(name = "ApprovalService")
	ApprovalServiceImpl approvalService;
	
	@RequestMapping(value = "/boardListExcel")
	public void boardListExcel(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		
			Map <String, Object> sessionMap = (Map)session.getAttribute("getMember");
	        List<?> listview  = approvalService.selectList(sessionMap);
	        Map<String , Object> beans = new HashMap<String , Object>();
	        beans.put("listview" , listview );
	        
	        MakeExcel me = new MakeExcel();
	        me.download(request, response, beans, me.get_Filename("project9"), "board.xlsx");
	}
	
	@RequestMapping(value = "/excelUpload")
	public String excelUpload() {
		
		return "excel/excelUpload";
	}
	
	@RequestMapping(value = "/excelListBoard")
	public String excelListBoard(MultipartHttpServletRequest mRequest) throws IOException {
		//파일을 읽기위해 엑셀파일을 가져온다
		String fileName = "";
		String filePath = "C:/Users/giant/Downloads/";
		Iterator<String> iterator = mRequest.getFileNames();
		while(iterator.hasNext())
		{
			MultipartFile mFile = mRequest.getFile(iterator.next());
			fileName = mFile.getOriginalFilename();
		}
		FileInputStream fis = new FileInputStream(filePath + fileName);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		int rowindex=0;
		int columnindex=0;
		//시트 수 (첫번째에만 존재하므로 0을 준다)
		//만약 각 시트를 읽기위해서는 FOR문을 한번더 돌려준다
		XSSFSheet sheet=workbook.getSheetAt(0);
		//행의 수
		int rows=sheet.getPhysicalNumberOfRows();
		for(rowindex=1;rowindex<rows;rowindex++){
		    //행을 읽는다
		    XSSFRow row=sheet.getRow(rowindex);
		    if(row !=null){
		        //셀의 수
		        int cells=row.getPhysicalNumberOfCells();
		        for(columnindex=0;columnindex<=cells;columnindex++){
		            //셀값을 읽는다
		            XSSFCell cell=row.getCell(columnindex);
		            String value="";
		            //셀이 빈값일경우를 위한 널체크
		            if(cell==null){
		                continue;
		            }else{
		                //타입별로 내용 읽기
		                switch (cell.getCellType()){
		                case HSSFCell.CELL_TYPE_FORMULA:
		                    value=cell.getCellFormula();
		                    break;
		                case HSSFCell.CELL_TYPE_NUMERIC:
		                    value=cell.getNumericCellValue()+"";
		                    break;
		                case HSSFCell.CELL_TYPE_STRING:
		                    value=cell.getStringCellValue()+"";
		                    break;
		                case HSSFCell.CELL_TYPE_BLANK:
		                    value=cell.getBooleanCellValue()+"";
		                    break;
		                case HSSFCell.CELL_TYPE_ERROR:
		                    value=cell.getErrorCellValue()+"";
		                    break;
		                }
		            }
		            System.out.println("각 셀 내용 :"+value);
		            }
		        }
		}


		return "excel/excelUpload";
	}

}
