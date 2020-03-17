package test.spring.csv;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/csv/")
public class TestCsvBean {
	
	@Autowired
	private CsvServiceBean csvService = null;
	
	@RequestMapping("writeCsv.do")
	public String writeCsv(HttpServletRequest request) {
		
		List<String[]> data = new ArrayList<String[]>();
		data.add(new String[] {"1","아이언맨","010-1111-2222"});
		data.add(new String[] {"2","캡틴아메리카","010-3333-2222"});
		data.add(new String[] {"3","토르","010-2222-2222"});
		
		// 실제 서버에서 서비스 해주는 폴더의 경로 가져오기
		String path = request.getRealPath("resource/csv");
		path += "/test.csv";	// 실제 폴더 경로명에 파일(확장자명포함)명 붙히기
		System.out.println("path : " + path);
		
		// 위의 List에 들어있는 정보를 CSV비지니스로직처리하는 메서드에 전달하여
		// CSV 파일을 만들게 시켜야함
		csvService.writeCSV(data, path);
		
		
		return "csv/testCsv";
	}
	
	@RequestMapping("readCsv.do")
	public String readCsv(HttpServletRequest request) {
		List<String[]> data = new ArrayList<String[]>();
		String path = request.getRealPath("resource/csv");
		path += "//test.csv";
		data = csvService.readCSV(path);	// 경로주고 csv파일 읽어와라
		
		Iterator<String[]> it = data.iterator();
		while(it.hasNext()) {
			String[] arr = (String[])it.next();
			for(String s : arr) {
				System.out.println(s +" ");
			}
			System.out.println();
		}
		
		return "csv/testCsv";
	}

	
	// #3. CSV -> Bean
	@RequestMapping("csvToBean.do")
	public String csvToBean(HttpServletRequest request) {
		
		// 파일이 위치한 실제 경로 차기
		String path = request.getRealPath("resource/csv");
		path += "//test.csv";
		// 파일 경로 던져주면, 데이터가 VO에 담긴 VO들을 리스트로 리턴 받겠다.
		List<TestCsvVO> data = csvService.readCsvToBean(path);
		
		Iterator<TestCsvVO> it = data.iterator();
		while(it.hasNext()) {
			TestCsvVO vo = (TestCsvVO)it.next();
			System.out.println("num : " + vo.getNum());
			System.out.println("name : " + vo.getName());
			System.out.println("mobile : " + vo.getMobile());
		}
		
		
		
		return "csv/testCSv";
	}
	
	
	
	
	
	
	
}
