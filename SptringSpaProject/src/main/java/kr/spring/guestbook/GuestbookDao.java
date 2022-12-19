package kr.spring.guestbook;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import kr.spring.mybatis.GuestbookMapper;

//하나의 빈으로 올라갈 수 있게끔 조치를 해야함
@Component
@Transactional
public class GuestbookDao {
	GPageVo gVo;
	
	@Autowired
	GuestbookMapper mapper;
	
	
	@Autowired
	PlatformTransactionManager manager;
	TransactionStatus status;
public int getTotSize(GPageVo gVo) {
	   int totSize=0;
	   totSize = mapper.totSize(gVo);
	    		
	   return totSize;
	 }
 public List<GuestbookVo>select(GPageVo gVo){
	 List<GuestbookVo> list = null;
	 int totSize = getTotSize(gVo);
	
	
	 this.gVo = gVo;
	 gVo.setTotSize(totSize);
	 list = mapper.list(gVo);
	 return list;
 }

 public List<GuestbookVo>select10(){
	 List<GuestbookVo> list = null;
	 list = mapper.select10();
	 return list;
 }

 

 public boolean insert(GuestbookVo vo) {
		boolean b = false;
		status = manager.getTransaction(new DefaultTransactionDefinition());
		Object savePoint = status.createSavepoint();
		System.out.println("id:" +vo.getId());
		int cnt = mapper.insert(vo);
		if(cnt>0) {
			b=true;
			manager.commit(status);
		}else {
			status.rollbackToSavepoint(savePoint);
		}
		return b;
	}
	public boolean delete(GuestbookVo vo) {
		boolean b = false;
		status = manager.getTransaction(new DefaultTransactionDefinition());
		Object savePoint = status.createSavepoint();
		
		int cnt = mapper.delete(vo);
		if(cnt>0) {
			b=true;
			manager.commit(status);
		}else {
			status.rollbackToSavepoint(savePoint);
		}
		return b;
	}
	public boolean update(GuestbookVo vo) {
		boolean b = false;
		status = manager.getTransaction(new DefaultTransactionDefinition());
		Object savePoint = status.createSavepoint();
		
		int cnt = mapper.update(vo);
		if(cnt>0) {
			b=true;
			manager.commit(status);
		}else {
			status.rollbackToSavepoint(savePoint);
		}
		return b;
	}
	
	public GPageVo getgVo() {
		return gVo;
	}

}