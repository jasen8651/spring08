package service;

import java.util.List;

import dto.BoarDTO;
import dto.PageDTO;

public interface Boardservic {
	public int countProcess();
	public List<BoarDTO> listProcess(PageDTO pv);
	public void insertProcess(BoarDTO dto);
	public BoarDTO contentprocess(int num);
	public void reSteProcess(BoarDTO dto);
	public BoarDTO updateSelectProcess(int num);
	public void updateProcess(BoarDTO dto, String urlpath);
	public void deleteProcess(int num, String urlpath);
	public String fileSelectProcess(int num);
}
