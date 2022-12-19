package kr.spring.board;


import java.io.File;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {
	static String path ="/Users/a11/eclipse-workspace/SptringSpaProject/src/main/webapp/WEB-INF/upload/";
	

	@Autowired
	BoardService service;
	
	@RequestMapping("/board/board_insertR")
	public synchronized String upload(@RequestParam("attFile") List<MultipartFile> mul, 
			  			 @ModelAttribute BoardVo vo) {
		try {
				System.out.println("id :" + vo.getId());
				System.out.println("subject:" + vo.getSubject());
				List<AttVo> attList = new ArrayList<AttVo>();
				
				
				//	본문을 저장 
				boolean flag= service.insertR(vo);
				if(!flag) return "저장중 오류 발생";
				//---
				attList = fileupload(mul);
				
				
				
				service.insertAttList(attList);
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		
		
		return "redirect:/board/board_select";   //파일이 업로드되고나서 다시 셀렉트로 간다. 
	}
	@RequestMapping("/board/board_updateR")
	public String updateR(@RequestParam("attFile") List<MultipartFile> mul,
						  @ModelAttribute BoardVo bVo ,@ModelAttribute PageVo pVo, //객체가 있는 get set가 있
						  @RequestParam(name="delFile", required= false) String[] delFiles ){   // 기본형은 Param 으로 받아야한
	
		
		String msg = " ";
		try {
			
			List<AttVo> attList = fileupload(mul);
			bVo.setAttList(attList);

			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		boolean flag = service.updateR(bVo, delFiles);
		if(!flag) msg = "수정중 오류 발생";

		
		return msg;
	}

	//file upload 공통부분 (insertR, updateR, repleR)
	   //file upload 공통부분(입력, 수정, 댓글)
	   public List<AttVo> fileupload(List<MultipartFile> mul)throws Exception{
	      List<AttVo> attList = new ArrayList<AttVo>();
	      
	      for(MultipartFile m : mul) {
	         if(m.isEmpty()) continue;
	         
	         UUID uuid = UUID.randomUUID(); //랜덤 숫자. 이름중복 방지
	         String oriFile = m.getOriginalFilename();
	         String sysFile = "";
	         File temp = new File(path + oriFile);//임시 저장
	         m.transferTo(temp); // 데이터를 지정한 파일로 저장
	         
	         sysFile = (uuid.getLeastSignificantBits()*-1) + "-" + oriFile; //경로+랜덤문자+파일명
	         File f = new File(path + sysFile); //파일객체 만들고
	         temp.renameTo(f); //이름 바꿔줌
	         
	         AttVo attVo = new AttVo();//boardVo가 먼저 만들어져서 sno가 있어야 pSno도 추가되어야겠찌
	         attVo.setOriFile(oriFile);
	         attVo.setSysFile(sysFile);
	         //attVo.setpSno(pVo.getSno()); //미확정
	         
	         attList.add(attVo); //이거 있어야 넘어감
	         
	         System.out.println(uuid.toString());
	      }
	      
	      return attList;
	   }



}
