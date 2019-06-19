package ecommerce.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ecommerce.repository.PurchaseRepo;
import ecommerce.repository.UserRepo;
import ecommerce.repository.WishlistRepo;
import model.Purchase;
import model.User;
import model.Wishlist;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping(value = "report")
public class ReportController {

	@Autowired
	private PurchaseRepo purRepo;
	@Autowired
	private WishlistRepo wishRepo;
	@Autowired
	private UserRepo userRepo;

	@RequestMapping(value = "admin/orders", method = RequestMethod.GET)
	public void ordersReport(String date, HttpServletResponse resp, Model model) throws Exception {
		if (date.equals("")) {
			model.addAttribute("errMsg", "Date is not valid.");
			return;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateFmtd = sdf.parse(date);
		List<Purchase> orders = purRepo.findAllByDate(dateFmtd);

		resp.setContentType("text/html");
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(orders);
		InputStream inputStream = this.getClass().getResourceAsStream("/jasper/OrdersReport.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("date", dateFmtd);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
		inputStream.close();

		resp.setContentType("application/x-download");
		resp.addHeader("Content-disposition", "attachment; filename=Orders.pdf");
		OutputStream out = resp.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, out);
	}
	
	@RequestMapping(value = "user/wishlist", method = RequestMethod.GET)
	public void wishlistReport(HttpServletResponse resp, Model model) throws Exception {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepo.findById(userDetails.getUsername()).get();
		
		List<Wishlist> wishlist = wishRepo.findByUser(user);
		
		resp.setContentType("text/html");
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(wishlist);
		InputStream inputStream = this.getClass().getResourceAsStream("/jasper/WishListReport.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("firstName", user.getFirstName());
		params.put("lastName", user.getLastName());
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
		inputStream.close();

		resp.setContentType("application/x-download");
		resp.addHeader("Content-disposition", "attachment; filename=Orders.pdf");
		OutputStream out = resp.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, out);
	}

}
