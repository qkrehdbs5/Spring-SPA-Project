package kr.spring.board;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;


import kr.spring.mybatis.BoardMapper;



@Service
@Transactional
public class BoardService {
	PageVo pVo;
		@Autowired
	   BoardMapper boardMapper;
		Object savePoint;
		
		@Autowired
		PlatformTransactionManager manager;
		TransactionStatus status;
		
		public boolean insertR(BoardVo vo) {
			status = manager.getTransaction(new DefaultTransactionDefinition());
			Object savePoint = status.createSavepoint();
			boolean flag = true;
			int cnt = boardMapper.insertR(vo);
			if(cnt<1) {
				status.rollbackToSavepoint(savePoint);
				flag=false;
			}
			
		
			
			return flag;
		}
		
		public void insertAttList(List<AttVo> attList) {
			int cnt = boardMapper.insertAttList(attList);
			if(cnt>0) {
				manager.commit(status);
			}else {
				status.rollbackToSavepoint(savePoint);
			}
		}
		
		
		
	   public List<BoardVo> board10(){
		   List<BoardVo> list = boardMapper.board10();
		   return list;
	   }


	public List<BoardVo> select(PageVo pVo){
		int totSize = boardMapper.totList(pVo);
		pVo.setTotSize(totSize);
		this.pVo = pVo;
		
		List<BoardVo> list = boardMapper.select(pVo);
		return list;
	}

	public BoardVo view(int sno, String up) {
		BoardVo bVo = null;
		if(up !=null &&up.equals("up")) {//상세보기인 경우만 
			boardMapper.hitUp(sno);
		}
		bVo = boardMapper.view(sno);
		List<AttVo> attlist = boardMapper.attList(sno);
		bVo.setAttList(attlist);
		return bVo;
	}
	public boolean delete(BoardVo bVo) {
		boolean b = true;
		
		int replCnt = boardMapper.replCheck(bVo);
		
		if(replCnt>0) {
			b=false;
			return b;
		}
		status = manager.getTransaction(new DefaultTransactionDefinition());
		Object savePoint = status.createSavepoint();
		
		int cnt = boardMapper.delete(bVo);
		if(cnt<1) {
			b=false;
		}else {
			List<String> attList = boardMapper.delFileList(bVo.getSno());
			
			if(attList.size()>0) {
				cnt = boardMapper.attDeleteAll(bVo.getSno());
				if(cnt>0) {
					if(attList.size()>0) {
						String[] delList =attList.toArray(new String[0]);
						fileDelete(delList);
					}
				}else {
					b=false;
				}
			}
		}
		if(b) manager.commit(status);
		else status.rollbackToSavepoint(savePoint);
		
		return b;
	}
	 public boolean updateR(BoardVo bVo, String[] delFiles) {
	      status = manager.getTransaction(new DefaultTransactionDefinition());
	       this.savePoint = status.createSavepoint(); //필드에 있는거 쓸래
	         
	      boolean b = true;
	      int cnt = boardMapper.update(bVo);
	      if(cnt<1) {
	         b=false;
	      }else if(bVo.getAttList().size()>0){
	         int attCnt = boardMapper.attUpdate(bVo);
	         if(attCnt<1) b= false;
	      }
	      
	      if(b) {
	         manager.commit(status);
	         if(delFiles != null && delFiles.length>0) {
	            //펌부파일 데이터 삭제
	            cnt = boardMapper.attDelete(delFiles);
	            if(cnt>0) {
	               fileDelete(delFiles);
	            }else {
	               b = false;
	            }
	         }
	      }else {
	         status.rollbackToSavepoint(savePoint);
	      }
	      
	      return b;
	   }


	
		
	
	public void fileDelete(String[] delFiles) {
		

		for (String f : delFiles) {
			
			File file = new File(FileUploadController.path + f);
			if (file.exists())
				file.delete();
		}
	}
	
	
	public PageVo getpVo() {
		return pVo;
	}

	
}
