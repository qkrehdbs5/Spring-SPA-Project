package kr.spring.guestbook;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class GuestbookController {
   @Autowired //얘로 dao가져올거임 bean으로 만들자
   GuestbookDao dao;

   @RequestMapping("/guestbook/guestbook_select") //이 매핑 정보가 들어왔을 때
   public ModelAndView select(GPageVo gVo) {//매개변수로 pagevo 넣으면 들어옴 신기하다!(RESTCONTROLLER 있기에 가능)
      ModelAndView mv = new ModelAndView();
      // service or dao

   
      //검색어에 따른 TOTSIZE를 가져와 페이지를 재계산
     // int totSize = dao.getTotSize(gVo);
  
     
      //검색어에 따른 LIST 가져옴
      List<GuestbookVo> list = dao.select(gVo);

   
      //LIST를 model & view에 추가
      gVo = dao.getgVo(); //새로 갱신된 페이지 정보
      mv.addObject("gVo",gVo); //페이징 처리 위해 얘도 날려줌
      mv.addObject("list",list);
      mv.setViewName("guestbook/guestbook_select"); //view에 세팅한대로 문장완성
      return mv;
   
   }
   @RequestMapping("/guestbook/guestbook_insert")
   public void insert(GuestbookVo vo, HttpServletResponse resp) {
	   	
	   
	   	try {
	   	   	boolean b =dao.insert(vo);
			PrintWriter out = resp.getWriter();
			if( !b ) {
				out.print("<script>");
				out.print("alert('저장중 오류 발생')");
				out.print("</script>");
			}
	   	
	   	} catch (IOException e) {	
			e.printStackTrace();
		}
   }

   @RequestMapping("/guestbook/guestbook_delete")
   public void delete(GuestbookVo vo, HttpServletResponse resp) {
	   	
	   	boolean b =dao.delete(vo);
	   	try {
			PrintWriter out = resp.getWriter();
			if( !b ) {
				out.print("<script>");
				out.print("alert('삭제중 오류 발생')");
				out.print("</script>");
			}
	   	
	   	} catch (IOException e) {	
			e.printStackTrace();
		}
   }
   @RequestMapping("/guestbook/guestbook_update")
   public String update(GuestbookVo vo, HttpServletResponse resp) {
	   	String msg ="";
	   	boolean b =dao.update(vo);
	   	if(!b) {
	   		msg ="수정중 오류 발생";
	   	}
	   	return msg;
   }
   @RequestMapping("/guestbook/guestbook10")
   public ModelAndView select10() {//매개변수로 pagevo 넣으면 들어옴 신기하다!(RESTCONTROLLER 있기에 가능)
	      ModelAndView mv = new ModelAndView();
	     
	     
	      List<GuestbookVo> list = dao.select10();
	     
	      mv.addObject("list",list);
	      mv.setViewName("guestbook/guestbook_select10"); //view에 세팅한대로 문장완성
	      return mv;
   }
}