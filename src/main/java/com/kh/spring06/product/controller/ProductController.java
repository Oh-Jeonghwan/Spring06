package com.kh.spring06.product.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.spring06.member.model.vo.Member;
import com.kh.spring06.product.model.service.ProductService;
import com.kh.spring06.product.model.vo.Product;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	//메인화면 띄우기=> 상품관리 메인페이지
	@GetMapping("/")
	public String productHome() {
		
		return "product/home"; //"WEB-INF/views/product/home.jsp"
	}
	
	//상품추가화면
	@GetMapping("/add.do")
	public String add(HttpSession session) {
		Member loginUser = (Member)session.getAttribute("loginUser");
		
		if(loginUser==null||!loginUser.getMemberId().equals("admin")) {
			session.setAttribute("alertMsg", "관리자가 아닙니다.");
			return "redirect:/";
			
		}
		else {
			return "product/add";
		}
		
	}
	
	//상품추가 처리 메소드
	@PostMapping("/add.do")
	public String add(@ModelAttribute Product p) {
		//service 단으로 토스
		int result = productService.addProduct(p);
		
		if(result>0) {
			return "redirect:list.do";
		}
		else {
			return "redirect:add.do?error";
		}
		
	}
	//상품목록화면
	@GetMapping("/list.do")
	public String productList(Model model
							, @RequestParam(required = false, defaultValue="PRODUCT_NO") String col
							, @RequestParam(required = false, defaultValue="ASC") String order) {
		
		//@RequestParam의 defaultValue 옵션: 해당 요청값의 기본값을 지정할 수 있는 옵션
		//=> 만약 어느 항목에 어느 순서로 정렬한 건지 쿼리스트링으로 지정이 안된 상태라면
		//String col은 product_no, String order 은 asc라는 값으로 지정하겠다.
		//요청일 들어올 때 눈에 보이는 주소: list.do
		//기본값이 들어간 주소: list.do?col=PRODUCT_NO&order=ASC
		//=>정렬 기준을 지정하지 않은 경우는 상품 번호를 오름차순으로 정렬해서 보여주겠다.
		
		//정렬 기능이 포함된 목록 조회용
		//가공=> 개별값들이 vo로 가공 못 할 경우 map타입으로
		Map<String, String> param = new HashMap<>();
		param.put("col", col);
		param.put("order",order);
		
		//조회
		List<Product> list=productService.selectProductList(param);
		
		//수하물을 붙여서 =? =>model 소포상
		model.addAttribute("list",list);
		//응답뷰 지정
		return "product/list";
	}
	//상품 상세조회 메소드
	//요청 파라미터 방식
	
	//상품 상세조회
	//@GetMapping("/detail.do")
	//public String detail(@RequestParam int pno
	//					, Model model) {
	
	//주소변수 방식
	@GetMapping("/detail/{pno}")
	public String detail(@PathVariable int pno
						, Model model) {
		
		//@PathVariable: 주소 변수를 읽어들이겠다.
		
		//조회
		Product product = productService.detailProduct(pno);
		//조회결과 수하물로 부치기
		model.addAttribute("product",product);
		
		return "product/detail"; // /WEB-INF/views/product/detail.jsp
	}
	
	@GetMapping("/delete/{pno}")
	public String delete(@PathVariable int pno) {
		
		//Service 단으로 pno 토
		int result = productService.deleteProduct(pno);
		
		if(result>0) {
			
			return "redirect:/product/list.do"; //절대경로
		}
		else {
			return "redirect:../list.do?error"; //상대경로
		}
		/*
		 * *Path Variable 방식에서 삭제는 잘 되지만
		 * redirect 요청 시 나의 요청 위치를 기준으로 경로가 잡혀 버림
		 * => 해결방법: 한겹 나와서 요청 또는 절대 경로로 요청
		 * 
		 * *Path Varialbe 방식에서의 해당 이슈 원인
		 * => spring06/product/delete/x 에서 x가 지워지면서 
		 * 	  spring06/product/delete/"list.do" 가 추가됨
		 *    해당 경로로 redirect 됨 
		 */
	}
}
