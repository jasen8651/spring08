package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UrlPathHelper;

import dto.BoarDTO;
import dto.PageDTO;
import service.Boardservic;

//http://localhost:8090/myapp/list.sb
//http://localhost:8090/myapp/list.sb?currentPage=1

@Controller
public class Boardcontroller {
	private Boardservic service;
	private PageDTO pdto;
	private int currentPage;
	
	public Boardcontroller() {
		// TODO Auto-generated constructor stub
	}
	
	public void setService(Boardservic service) {
		this.service = service;
	}
	@RequestMapping("/list.sb")
	public ModelAndView listMethod(PageDTO pv, ModelAndView mav) {
		int totalRecord = service.countProcess();
		if(totalRecord>=1) {
			if(pv.getCurrentPage() == 0)
				this.currentPage =1;
		}else { 
			this.currentPage = pv.getCurrentPage();
	}	
			
			this.pdto = new PageDTO(currentPage, totalRecord);
			List<BoarDTO> aList = service.listProcess(this.pdto);
			mav.addObject("aList", aList);
			mav.addObject("pv", this.pdto);
		
		mav.setViewName("board/list");
		return mav;
	}
	@RequestMapping(value="/write.sb", method = RequestMethod.GET)
	public ModelAndView wriAndView(BoarDTO dto, PageDTO pv, ModelAndView mav) {
		if(dto.getRef()!=0) {
			mav.addObject("currentPage" , pv.getCurrentPage() );
			mav.addObject("dto", dto);
		}
		mav.setViewName("board/write");
		return mav;
	}
	@RequestMapping(value="/write.sb", method = RequestMethod.POST)
	public String writeProMethod(BoarDTO dto, PageDTO pv, HttpServletRequest request) {
		MultipartFile file = dto.getFilename();
		if(!file.isEmpty()) {
			UUID random = saveCopyFile(file, request);
			dto.setUpload(random+"_"+file.getOriginalFilename());
		}
		dto.setIp(request.getRemoteAddr());
		service.insertProcess(dto);
		if(dto.getRef()!=0) {
			return "redirect:/list.sb?currentPage"+ pv.getCurrentPage();
		}else {
			return "redirect:/list.sb";
		}
		
	}
	private UUID saveCopyFile(MultipartFile file, HttpServletRequest request) {
		String fileName = file.getOriginalFilename();
		
		UUID random = UUID.randomUUID();
		File fe = new File(UrlPathHelper(request));
		if(!fe.exists()) {
			fe.mkdir();
		}
		File ff = new File(UrlPathHelper(request), random+ "_"+ fileName);
		try {
			FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(ff));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return random;
	}
	
	private String UrlPathHelper(HttpServletRequest request) {
		String root = request.getSession().getServletContext().getRealPath("/");
		System.out.println("root"+root);
		String saveDirectort = root + "temp" +File.separator;	
		return saveDirectort;
	}
	@RequestMapping("/view.sb")
	public ModelAndView viewMethod(int currentPage, int num, ModelAndView mav) {
		mav.addObject("dto", service.contentprocess(num));
		mav.addObject("currentPage", currentPage);
		mav.setViewName("board/view");
		return mav;
	}
	
	@RequestMapping("/contentdownload.sb")
	public ModelAndView downMethod(int num, ModelAndView mav) {
		mav.addObject("num" , num);
		mav.setViewName("download");
		return mav;
	}
}
