package kr.spring.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.spring.guestbook.GPageVo;
import kr.spring.guestbook.GuestbookVo;


@Repository
@Mapper
public interface GuestbookMapper {
	public int totSize(GPageVo vo);
	public List<GuestbookVo> list(GPageVo vo);
	public int insert(GuestbookVo vo); // 메소드명 insert xml file id명 
	public int delete(GuestbookVo vo); 
	public int update(GuestbookVo vo); 
	public List<GuestbookVo>select10();
}
