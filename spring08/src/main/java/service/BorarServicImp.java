package service;

import java.io.File;
import java.util.List;

import dao.BoarDAO;
import dto.BoarDTO;
import dto.PageDTO;
import oracle.net.aso.p;

public class BorarServicImp implements Boardservic {
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
		if (dto.getRef()!= 0) {
			dao.reStepCount(dto);
			dto.setRe_step(dto.getRe_step() + 1);
			dto.setRe_level(dto.getRe_level() + 1);

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
		return dao.content(num);
	}

	@Override
	public void updateProcess(BoarDTO dto, String urlpath) {
		String filename = dto.getUpload();
		if(filename != null) {
			String path = dao.getFile(dto.getNum());
			if(path!=null) {
				File file = new File(urlpath, path);
				file.delete();
			}
		}
			dao.update(dto);

	}

	@Override
	public void deleteProcess(int num, String urlpath) {
		String path = dao.getFile(num);
		if(path!=null) {
			File fe = new File(urlpath, path);
			fe.delete();
		}
		dao.delete(num);
	}

	@Override
	public String fileSelectProcess(int num) {

		return dao.getFile(num);
	}

}
