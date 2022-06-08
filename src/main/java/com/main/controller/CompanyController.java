package com.main.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.main.dao.CompanyDao;
import com.main.model.Company;

@Controller
public class CompanyController {
	
	@Autowired
	private ResourceLoader resourceLoader;

	Company company = new Company();

	@Autowired
	CompanyDao companyDaoImpl;

	@GetMapping("/readQR/{qrImage}")
	public String verifyQR(@PathVariable("qrImage") String qrImage) throws Exception {
		return readQR(qrImage);
	}

	@GetMapping("/")
	public String addData(ModelMap model) {
		model.addAttribute("company", company);
		return "codeGenerator";
	}

	@PostMapping("/savedata")
	public String addData(@ModelAttribute("company") Company company) {
		companyDaoImpl.save(company);
		try {
			writeQR(company);
		} catch (WriterException | IOException e) {
			e.printStackTrace();
		}
		return "redirect:/";
	}

	private String writeQR(@RequestBody Company company) throws WriterException, IOException {
		String qcodePath = "D:/" + company.getCertifyCode() + "-" + company.getCertifyId() + ".png";
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = qrCodeWriter.encode(
				company.getStudImage() + " Certificate Id : " + company.getCertifyCode() + "-" + company.getCertifyId()
						+ "\n Student Name : " + company.getStudentFname() + " " + company.getStudentLname()
						+ "\n Join Date : " + company.getJoinDate() + "\n Exit Date :" + company.getExitDate()
						+ "\n Company Name : " + company.getCompanyName() + "\n Company Email : "
						+ company.getCompanyEmail() + "\n Company Website : " + company.getCompanyWebsite(),
				BarcodeFormat.QR_CODE, 350, 350);
		Path path = FileSystems.getDefault().getPath(qcodePath);
		MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
		return "/images/" + company.getCertifyCode() + "-" + company.getCertifyId() + ".png";
	}

	private String readQR(String qrImage) throws Exception {
		final Resource fileResource = resourceLoader.getResource("classpath:static/images/" + qrImage);
		File QRfile = fileResource.getFile();
		System.out.println("Wowww: " + QRfile);
		BufferedImage bufferedImg = ImageIO.read(QRfile);
		LuminanceSource source = new BufferedImageLuminanceSource(bufferedImg);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		Result result = new MultiFormatReader().decode(bitmap);
		System.out.println("Barcode Format: " + result.getBarcodeFormat());
		System.out.println("Content: " + result.getText());
		return result.getText();

	}
}
