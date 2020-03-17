package test.spring.csv;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.sun.imageio.plugins.common.InputStreamAdapter;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
// 실제로 CSV 파일로 저장하고 읽어들이는 클래스 작성
// 즉, CSV 파일관련 비지니스로직 클래스 만들기
import au.com.bytecode.opencsv.bean.CsvToBean;

public class CsvServiceBean {

		// #1. 파일 쓰기 메서드 ( 리스트 타입의 데이터, 파일경로 받기)
		public void writeCSV(List<String[]> data, String filename) {	// 이 메서드를 호출하는 곳에서
			// OpenCSV 라이브러리에 있는 CSWriter 클래스 객체 생성
			CSVWriter cw = null;
			
			try{
				// 새파일명으로 FileWriter객체, 구분자, 따옴표문자(quotechar) 지정
				cw = new CSVWriter(new FileWriter(filename),',','"');
				
				// 한번에 전체 데이터 작성 해주는 방법
				cw.writeAll(data);
				cw.flush();
				cw.close();
				
//				// 리스트 요소 하나씩 꺼내서 일일히 작성 해주는 방법
//				Iterator<String[]> it = data.iterator();
//				try {
//					while(it.hasNext()){
//						String[] s = (String[])it.next();
//						cw.writeNext(s);
//					}
//				}finally {
//					cw.close();
//				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
		
		// #2. csv 파일 읽기 메서드
		public List<String[]> readCSV(String filename){
			
			List<String[]> data = new ArrayList<String[]>();
			try {
				// new CSVReader(InputStramReader객체, 구분자, 예외구문, 삭제할열)
				// 구분자 : csv 구분자 , 이므로 ,로 지정, 탭등 다른표식일 경우 다른것으로 지정
				// 예외구분 : "는 예외 구분으로 ""로 표시된 부분이 있다면 해당부분을 하나의 구분으로 보고 읽게함
				// 삭제할열 :
				CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"),',','"', 0);
				
				// 한번에 csv 파일 읽기
				data = reader.readAll();
				
				reader.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			return data;
		}
		
		
		
		// #3. csv -> Bean 매핑해서 List 담아 리턴
		public List<TestCsvVO> readCsvToBean(String filename){
			List<TestCsvVO> data = null;
			try {
				CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"),',','"', 0);
				
				// CSV를 VO에 매핑해주는 매퍼 역활을 할 클래스 객체 생성
				ColumnPositionMappingStrategy<TestCsvVO> mapper = new ColumnPositionMappingStrategy<TestCsvVO>();
				mapper.setType(TestCsvVO.class);		// VO파일 매필
				String[] colums = new String[] {"num","name","mobile"};	// 각컬럼을 정의할 배열
				mapper.setColumnMapping(colums);	// 각 컬럼명을 매퍼에 설정
				
				CsvToBean<TestCsvVO> csv = new CsvToBean<TestCsvVO>();
				data = csv.parse(mapper, reader);	// (매핑방법, csv파일;
				
				reader.close();
				
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			
			return data;
		}
		
		
		
		
		
}
