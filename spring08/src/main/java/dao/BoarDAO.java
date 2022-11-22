package dao;

import java.util.List;

import dto.BoarDTO;
import dto.PageDTO;

public interface BoarDAO {
	
	public int count();
	public List<BoarDTO> list(PageDTO pv);
	public void readCount(int num);
	public BoarDTO content(int num);
	
	
	public void reStepCount(BoarDTO dto);
	public void save(BoarDTO dto);
	public BoarDTO updateNum(int num);
	public void update(BoarDTO dto);
	public void delete(int num);
	public String getFile(int num);



}
