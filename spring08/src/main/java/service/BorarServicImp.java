package service;

import java.util.List;

import dao.BoarDAO;
import dto.BoarDTO;
import dto.PageDTO;

public class BorarServicImp implements Boardservic{
	private BoarDAO dao;
	
	
	public BorarServicImp() {
		// TODO Auto-generated constructor stub
	}
	
	public void setDao(BoarDAO dao) {
		this.dao = dao;
	}

	@Override
	public int countProcess() {
		// TODO Auto-generated method stub
		return dao.count();
	}

	@Override
	public List<BoarDTO> listProcess(PageDTO pv) {
		// TODO Auto-generated method stub
		return dao.list(pv);
	}

	@Override
	public void insertProcess(BoarDTO dto) {
		if(dto.getRef()!=0) {
			dto.setRe_step(dto.getRe_step()+1);
			dto.setRe_level(dto.getRe_level()+1);
		}
		dao.save(dto);
		
	}

	@Override
	public BoarDTO contentprocess(int num) {
		dao.readCount(num);
		return dao.content(num);
	}

	@Override
	public void reSteProcess(BoarDTO dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BoarDTO updateSelectProcess(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateProcess(BoarDTO dto, String urlpath) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteProcess(int num, String urlpath) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String fileSelectProcess(int num) {
		
		return dao.getFile(num);
	}

	
}
