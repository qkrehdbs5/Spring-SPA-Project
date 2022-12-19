package kr.spring.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.spring.board.AttVo;
import kr.spring.board.BoardVo;
import kr.spring.board.PageVo;

@Repository
@Mapper
public interface BoardMapper {
	public int totList(PageVo vo);
	public List<BoardVo> select(PageVo vo);
	public List<BoardVo> board10();
	
	public void hitUp(int sno);
	public BoardVo view(int sno);
	public List<AttVo> attList(int sno);

	public int replCheck(BoardVo bVo);
	public int delete (BoardVo bVo);
	public List<String>delFileList(int sno);
	public int attDeleteAll(int sno);
	
	
	public int insertR(BoardVo vo);
	public int insertAttList(List<AttVo> attList);
	
	
	public int update(BoardVo bVo);
	public int attUpdate(BoardVo bVo);
	public int attDelete(String[] delFile);
	

}
