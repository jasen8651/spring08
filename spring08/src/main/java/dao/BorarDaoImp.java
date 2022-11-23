package dao;


import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import dto.BoarDTO;
import dto.PageDTO;

public class BorarDaoImp implements BoarDAO{
	private SqlSessionTemplate sqlSession;
	
	public BorarDaoImp() {
		// TODO Auto-generated constructor stub
	}
	
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public int count() {
		
		return sqlSession.selectOne("board.count");
	}

	@Override
	public List<BoarDTO> list(PageDTO pv) {
		
		return sqlSession.selectList("board.list", pv);
	}

	@Override
	public void readCount(int num) {
		sqlSession.update("board.readCount", num);
	}
	
	@Override
	public BoarDTO content(int num) {
		return sqlSession.selectOne("board.view", num);
	}

	@Override
	public void reStepCount(BoarDTO dto) {
		sqlSession.update("board.reStepCount", dto);
		
	}

	@Override
	public void save(BoarDTO dto) {
		sqlSession.insert("board.save", dto);
		
	}

	@Override
	public BoarDTO updateNum(int num) {
		return null;
	}

	@Override
	public void update(BoarDTO dto) {
		sqlSession.update("board.update", dto);
	}

	@Override
	public void delete(int num) {
		
	}
	
	@Override
	public String getFile(int num) {
		return sqlSession.selectOne("board.uploadFile",num);
	}

	
	
	
}
