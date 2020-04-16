package lhc.controller.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lhc.model.dao.LhcPcDAOImpl;
import lhc.model.dao.LhcSeatsDAOImpl;
import lhc.model.vo.LhcPcVO;
import lhc.model.vo.LhcSeatsVO;

@Controller
@RequestMapping("/lhcSeats/")
public class LhcSeatsBean {

	@Autowired
	private LhcSeatsDAOImpl lhcSeatsDAO = null;
	
	@RequestMapping("lhcSeats.lhc")
	public String seats(int pageNum, int lhc_num, Model model) throws Exception {
		
		LhcPcVO pc = lhcSeatsDAO.getPc(lhc_num);
		
		model.addAttribute("pc", pc);
		model.addAttribute("pageNum", pageNum);
		
		return "lhcSeats/lhcSeats";
	}
	
	@RequestMapping("lhcSeatsReserveForm.lhc")
	public String lhcSeatsReserve(int lhc_num) throws Exception {
		
		LhcSeatsVO seats = lhcSeatsDAO.getMychar(lhc_num);
		
		return "lhcSeats/lhc";
	}
}
