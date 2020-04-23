package lhc.controller.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lhc.model.dao.LhcMemberDAOImpl;
import lhc.model.dao.LhcMyCharDAOImpl;
import lhc.model.dao.LhcPcDAOImpl;

import lhc.model.vo.LhcMemberVO;
import lhc.model.vo.LhcPcVO;

import lhc.model.vo.LhcMyCharVO;

@Controller
@RequestMapping("/lhcMyChar/")
public class LhcMyCharBean {

	//Autowired
	@Autowired
	private LhcMyCharDAOImpl lhcMyCharDAO = null;
	@Autowired
	private LhcPcDAOImpl lhcPcDAO = null;
	@Autowired
	private LhcMemberDAOImpl lhcMemberDAO = null;
	
	@RequestMapping("lhcMyCharSeatsForm.lhc")
	public String seats(Integer pageNum, Integer lhc_num, Model model) throws Exception {
		
		List intList = null;
		List list = null;
		LhcPcVO pc = null;
		int[] newReservedArray = null;
		if(lhc_num == null && pageNum == null) {
			lhc_num = 0;
			pageNum = null;
		}else {
			pc = lhcPcDAO.getPc(lhc_num);
			// 각 PC방 당 예약되어 있는지 좌석 숫자 세기
			int countReserved = lhcMyCharDAO.countReservation(lhc_num);
			if(countReserved > 0){
				// pcinfo테이블에 reserved가 있는지 확인하기 
				String reserved = pc.getLhc_reserved();
				
				String[] rArray = reserved.split(",");
				list = new ArrayList();
				for(String s : rArray) { //rArray에 있는 객체를 차례로 s에 넣음.
					list.add(s);
				}
				// 스트링 배열 된거 쉼표(",") 구분해서 스트링 배열로 만든 다음에 int 배열로 바꾸는것
				String[] reservedArray = reserved.split(",");
				newReservedArray = Arrays.stream(reservedArray).mapToInt(Integer::parseInt).toArray();
				
				// pc방 좌석 번호 정렬 (삽입정렬)
				int n = newReservedArray.length;
				for(int i = 1; i < n; ++i) {
					int key = newReservedArray[i];
					int j = i - 1;
					
					/*key값보다 크면 newReservedArray[0..i-1]자리의 
					 * 현재 자리 에서 한칸 움직인다*/
					while(j >= 0 && newReservedArray[j] > key) {
						newReservedArray[j+1] = newReservedArray[j];
						j = j - 1;
					}
					newReservedArray[j + 1] = key;
				}
			}else {
				newReservedArray = new int[1];
				newReservedArray[0] = 0;
			}		
			// 숫자배열을 list에다가 담기
			intList = new ArrayList();
			for(int i : newReservedArray) {
				intList.add(i);
			}
		}
		if(pageNum == null) {
			pageNum = 0;
		}
		model.addAttribute("newReservedArray", newReservedArray);
		model.addAttribute("pc", pc);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("reservedList", list);
		model.addAttribute("intReservedList", intList);
		
		return "lhcMyChar/lhcMyCharSeatsForm";
	}
	
	@RequestMapping("lhcMyCharSeatsPro.lhc")
	public String lhcSeatsReserve(HttpSession session, HttpServletRequest request, int lhc_num, Model model) throws Exception {
		
		String id = (String)session.getAttribute("memId");
		
		// 체크박스 체크된 내역들 String[] checkbox에 담아둔다
		String[] checkbox = request.getParameterValues("lhc_reserved");
		
		// 배열에 담겨진거 한개의 String으로 묶기
		String checkboxes = String.join(",", checkbox);
		// 체크박스 된것들 int 배열로 형변환
		int[] checkedboxes = Arrays.stream(checkbox).mapToInt(Integer::parseInt).toArray();
		
		LhcMemberVO vo = lhcMemberDAO.selectMember(id);
		
		// 한개 pc방 정보를 받기
		LhcPcVO pc = lhcPcDAO.getPc(lhc_num);
		
		// 업체명 받기
		String lhc_name = pc.getLhc_name();
		
		// 저장된 내역 없을때랑 있을때랑 구분하기
		int countReserved = lhcMyCharDAO.countReservation(lhc_num);
		int[] combined = null;
		String joinNewCombined = null;
		
		if(countReserved > 0) {
			// 저장된 예약 내역불러오기
			String reserved = pc.getLhc_reserved();
			// 스트링 배열 된거 쉼표(",") 구분해서 스트링 배열로 만든 다음에 int 배열로 바꾸는것
			String[] reservedArray = reserved.split(",");
			int[] newReservedArray = Arrays.stream(reservedArray).mapToInt(Integer::parseInt).toArray();
			
			// pc방 좌석 번호 정렬
			int n = newReservedArray.length;
			for(int i = 1; i < n; ++i) {
				int key = newReservedArray[i];
				int j = i - 1;
				
				/*key값보다 크면 newReservedArray[0..i-1]자리의 
				 * 현재 자리 에서 한칸 움직인다*/
				while(j >= 0 && newReservedArray[j] > key) {
					newReservedArray[j+1] = newReservedArray[j];
					j = j - 1;
				}
				newReservedArray[j + 1] = key;
			}
			
			// 기존에 예약 되어 있는 newReservedArray와 새로 예약한 checkedboxes 배열 예약 합치기 
			combined = new int[checkedboxes.length + newReservedArray.length];
			System.arraycopy(checkedboxes, 0, combined, 0, checkedboxes.length);
			System.arraycopy(newReservedArray, 0, combined, checkedboxes.length, newReservedArray.length);
			
			// int[] combined를 String으로 형변환해서 String 배열로 저장한 다음에, 배열을 한개의 String으로 묶음. 
			String[] newCombined = Arrays.stream(combined).mapToObj(String::valueOf).toArray(String[]::new);
			joinNewCombined = String.join(",", newCombined);
			
			// 새로 합친 배열의 length
			int lhc_rescount = newCombined.length;
			int lhc_totalres = newCombined.length;
			lhcMyCharDAO.updateReservation(joinNewCombined, lhc_num, lhc_rescount);
			
			// 하나씩 고객이 예약한거 집어넣기
			for(int i = 0; i < checkbox.length; i++) {
				lhcMyCharDAO.insertMychar(lhc_num, checkbox[i], id, lhc_name);
			}
			//예약이 추가되면 누적으로 PC방 정보테이블에 숫자 넣기 넣기(예약 개수는 전체 예약 테이블 참조)( if(countReserved > 0) { 안에 있는 내용, for문 뒤에 추가
			lhcMyCharDAO.updateTotalRes(lhc_num);
			//예약이 추가되면 누적으로 PC방 정보테이블에 매출계산하기(데이터 계산하는 것은 전체 예약 테이블)( if(countReserved > 0) { ..}else{ else문에도 추가, lhcMyCharPro 컨트롤러에 추가, 결제하기 누르면 매출 올리기 
			lhcMyCharDAO.updateTotalPrice(lhc_num);
			
		}else {	// 전에 예약된 내역이 없는 경우 
			int lhc_rescount = checkbox.length;
			combined = checkedboxes;
			joinNewCombined = checkboxes;
			lhcMyCharDAO.updateReservation(checkboxes, lhc_num, lhc_rescount);
			
			for(int i = 0; i < checkbox.length; i++) {
				lhcMyCharDAO.insertMychar(lhc_num, checkbox[i], id, lhc_name);
			}
			
			//예약이 추가되면 누적으로 PC방 정보테이블에 숫자 넣기 넣기( if(countReserved > 0) { ..}else{ else문에도 추가
			lhcMyCharDAO.updateTotalRes(lhc_num);
			//예약이 추가되면 누적으로 PC방 정보테이블에 매출계산하기(데이터 계산하는 것은 전체 예약 테이블)( if(countReserved > 0) { ..}else{ else문에도 추가
			lhcMyCharDAO.updateTotalPrice(lhc_num);
		}
				
		model.addAttribute("lhc_name", lhc_name);
		model.addAttribute("checkbox", combined);
		model.addAttribute("checkboxes", joinNewCombined);
		return "lhcMyChar/lhcMyCharSeatsPro";
	}
	
	// 내 예약 테이블
	@RequestMapping("lhcMyCharForm.lhc")
	public String lhcMyCharForm(HttpSession session, Model model, String pageNum) throws Exception {
		// session 회원 id받기
		String id = (String)session.getAttribute("memId");
				
		if(pageNum == null) {pageNum = "1";}
		int pageSize = 10;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;
				
		List resList = null;
		count = lhcMyCharDAO.getReservedCount(id);
		
		// testing
		//System.out.println("count 나오나 테스트: "+count);
		
			// 멤버테이블에서 한 아이디에 소지한 돈 꺼내오기 
			LhcMemberVO member = lhcMemberDAO.selectMember(id);
			int lhc_money = member.getLhc_money();
				
				
			if(count > 0) {
				resList = lhcMyCharDAO.getReservedList(id);
				member = lhcMemberDAO.selectMember(id);
			}else {
					resList = Collections.EMPTY_LIST;
				}
				
				number = count - (currentPage-1) * pageSize;
				
				model.addAttribute("currentPage", currentPage);
				model.addAttribute("startRow", startRow);
				model.addAttribute("endRow", endRow);
				model.addAttribute("pageSize", pageSize);
				model.addAttribute("count", count);
				model.addAttribute("resList", resList);
				model.addAttribute("number", number);
				
				
				return "lhcMyChar/lhcMyCharForm";
			}
			
		@RequestMapping("lhcMyCharPro.lhc")
		public String lhcMyCharPro(HttpSession session,HttpServletRequest request, String lhc_seats, int lhc_num, Model model) throws Exception {
			String id = (String)session.getAttribute("memId");
			// 멤버테이블에서 한 아이디에 소지한 돈 꺼내오기 
			LhcMemberVO member = lhcMemberDAO.selectMember(id);
			int lhc_money = member.getLhc_money();
			// 한개의 PC방 정보 받기 
			LhcPcVO pc = lhcPcDAO.getPc(lhc_num);
			// 한개의 PC방 예약 번호들 String 타입으로 뽑아오기
			String reserved = pc.getLhc_reserved();
			// 스트링 타입 배열로 분리시키기
			String[] rArray = reserved.split(",");
			// 배열을 list에다 넣기 
			List rList = new ArrayList();
			for(String i : rArray) {
				rList.add(i);
			}
			// 리스트 크기 반복문 돌려서  받았던 데이터랑 일치하면 삭제하기
			for(int i = 0; i < rArray.length; i++) {
				if(lhc_seats.equals(rArray[i])) {
					rList.remove(i);
				}
			}
			// 합체 
			String combined = String.join(",", rList);
			// 예약정보 가지고 오기
			LhcMyCharVO seats = lhcMyCharDAO.getSeats(lhc_seats, lhc_num);
			// Date로 예약한 시간 뽑아온 다음 형변환  하고 
			Date rTime = seats.getLhc_end();
			// 현재 날짜와 시간 뽑아오기 
			Date cTime = new Date(System.currentTimeMillis());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			
			
			
			// 현재시간 빼기 예약한 시간 빼기(밀리초로 계산됨)
			long diff = cTime.getTime() - rTime.getTime();
			// 밀리초 계산한것을 분으로 바꿈
			long diffMin = diff / (1000*60);
			// 분당 15원으로 계산 
			int rawRevenue = (int)diffMin * 15;
			int netRev = (int)((rawRevenue + 50) / 100 * 100) + 1000;
			// testing
			//System.out.println("현재시간 출력: " + sdf.format(cTime));
			//System.out.println("현재시간과 예약했던 시간 차이로 (분으로) " + diffMin);
			//System.out.println("매출: " + rawRevenue);
			//System.out.println("10원 반올림해서 매출: " + netRev);
				
			// 끝내기 후 업데이트
			int lhc_rescount = rList.size();
				
			if(lhc_money >= netRev) {
				lhcMyCharDAO.updateReservation(combined, lhc_num, lhc_rescount);
				lhcMyCharDAO.updateLhc_end(netRev, lhc_num, lhc_seats);
				int newLhc_money = -netRev;
				lhcMemberDAO.updatePoint(id, newLhc_money);
				lhcMyCharDAO.updateTotalPrice(lhc_num);
			}  
			model.addAttribute("lhc_money", lhc_money);
			model.addAttribute("netRev", netRev);
				
			return "lhcMyChar/lhcMyCharPro";
		}
	
	  @RequestMapping("lhcVendorChar.lhc") 
	  public String lhcVendorChar(HttpSession session, Model model,String pageNum)throws Exception{
		  
		  String id = (String)session.getAttribute("memId");
		  LhcPcVO vo = lhcPcDAO.getPc(id);
		  String lhc_name = vo.getLhc_name();
		  //System.out.println(lhc_name + "   lhc_name");
		  int count = 0;
		  count = lhcMyCharDAO.getReservedCount1(lhc_name);
		  //System.out.println(count+"  Vendor count");
		  if(pageNum == null) {pageNum = "1";}
			int pageSize = 10;
			int currentPage = Integer.parseInt(pageNum);
			int startRow = (currentPage - 1) * pageSize + 1;
			int endRow = currentPage * pageSize;
			int number = 0;
					
			List charList = null;
			
			if(count > 0) {
				charList = lhcMyCharDAO.getReservedList1(lhc_name);
			}else {
				charList = Collections.EMPTY_LIST;
				}				
				number = count - (currentPage-1) * pageSize;
				
				model.addAttribute("currentPage", currentPage);
				model.addAttribute("startRow", startRow);
				model.addAttribute("endRow", endRow);
				model.addAttribute("pageSize", pageSize);
				model.addAttribute("count", count);
				model.addAttribute("charList", charList);
				model.addAttribute("number", number);
				
		  return "lhcVendor/lhcVendorChar";
	  }
	  
	  @RequestMapping("lhcAllChar.lhc") 
	  public String lhcAllChar(String pageNum, Model model)throws Exception{
		  
		  int count = 0;
		  count = lhcMyCharDAO.getAllCharCount(); // 예약 건수 전체 개수
		  //System.out.println(count+"   전체 count");
		  
		  if(pageNum == null) {pageNum = "1";}
			int pageSize = 10;
			int currentPage = Integer.parseInt(pageNum);
			int startRow = (currentPage - 1) * pageSize + 1;
			int endRow = currentPage * pageSize;
			int number = 0;
			
			List charList = null;
			
			if(count > 0) {
				charList = lhcMyCharDAO.getReservedAllList();
			}else {
				charList = Collections.EMPTY_LIST;
				}
				
				number = count - (currentPage-1) * pageSize;
			
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("startRow", startRow);
			model.addAttribute("endRow", endRow);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("count", count);
			model.addAttribute("charList", charList);
			model.addAttribute("number", number);
			
		  return "lhcAdmin/lhcAllChar";
	  }
			
}
