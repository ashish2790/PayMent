package com.awl.tch.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.awl.tch.bean.Payment;
import com.awl.tch.exceptions.TCHServiceException;
import com.awl.tch.externalentityImpl.IrctcBridgeServiceImpl;

public class TestIrctcService {

	ApplicationContext ctx;
	Map<String, String> map = null;
	@Before
	public void init(){
		ctx = new ClassPathXmlApplicationContext("spring.xml");
		map = new HashMap<String, String>();
		map.put("URL","https://testcpg.irctc.co.in/cpg/service/pos");
		map.put("USERNAME","admin");
		map.put("PASSWORD","admin");
		map.put("KEY","S33lk7YaWtwNK2HXG8UUpQ==");
	}
	
	@Test
	public void getAmount(){
		IrctcBridgeServiceImpl irctc = ctx.getBean(IrctcBridgeServiceImpl.class);
		Payment input = new Payment();
		input.setTerminalSerialNumber("17067WL25866508");
		input.setTerminalId("27001649");
		try {
			irctc.getAmount(input);
		} catch (TCHServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*@Test
	public void saleConfirmation(){
		System.setProperty("https.proxyHost","10.10.15.200");
		System.setProperty("https.proxyPort","8080");
		IrctcSaleConfService saleConfservice = (IrctcSaleConfirmSerivceImpl) ctx.getBean(IrctcConstant.TCH_IRCTC_SALE_CONF_SERVICE);
		SaleConfirm saleConf = new SaleConfirm();
		//{"tid":"WER34678","assetSerialNo":"423434343","serviceProvider":"WL",
		//	"appTxnId":"1000000001","amount":"100.50","invoiceNo":"6742354345","rrn":"00454654655","cardNo":"5667","cardType":"C"
		//	,"cardProvider":"vi","responseTimeStamp":"20-11-2016 18:30:30"}

		saleConf.setTid("WER34678");
		saleConf.setAssetSrNo("423434343");
		saleConf.setServiceProvider("WL");
		saleConf.setAppTxnId("1000000001");
		saleConf.setAmount("100.50");
		saleConf.setInvoiceNumber("6742354345");
		saleConf.setRrn("00454654655");
		saleConf.setCardDigit("5667");
		saleConf.setCardType("C");
		saleConf.setCardProvider("vi");
		saleConf.setTimestamp("20-11-2016 18:30:30");
		
		try {
			saleConf = saleConfservice.cosumeWS(map, saleConf);
			System.out.println(saleConf);
			System.out.println(saleConf.getAmount());
		} catch (IRCTCException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertEquals("", "");
	}*/
	
	
	/*@Test
	public void voidTxn(){
		System.setProperty("https.proxyHost","10.10.15.200");
		System.setProperty("https.proxyPort","8080");
		IrctcVoidService voidService = (IrctcVoidServiceImpl) ctx.getBean(IrctcConstant.TCH_IRCTC_SALE_VOID_SERVICE);
		VoidTxn voidtxn = new VoidTxn();
		// {"tid":"WER34678","assetSerialNo":"423434343","serviceProvider":"WL","amount":"100.50" }

		voidtxn.setTid("WER34678");
		voidtxn.setAssetSrNo("423434343");
		voidtxn.setServiceProvider("WL");
		voidtxn.setAmount("100.50");
		
		try {
			voidtxn = voidService.cosumeWS(map, voidtxn);
			System.out.println(voidtxn);
			System.out.println(voidtxn.getAmount());
		} catch (IRCTCException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertEquals("", "");
	}*/
	
	
	/*@Test
	public void TxnEnquiry(){
		System.setProperty("https.proxyHost","10.10.15.200");
		System.setProperty("https.proxyPort","8080");
		IrctcTransactionEnquiryService tranEnquiry = (IrctcTransactionEnquiryServiceImpl) ctx.getBean(IrctcConstant.TCH_IRCTC_TRANSACTION_ENQUIRY_SERVICE);
		TransactionEnquiry txnEnq = new TransactionEnquiry();
		// {"tid":"WER34678","assetSerialNo":"423434343","serviceProvider":"WL","amount":"100.50" }

		txnEnq.setTid("WER34678");
		txnEnq.setAssetSrNo("423434343");
		txnEnq.setServiceProvider("WL");
		txnEnq.setAmount("100.50");
		
		try {
			txnEnq = tranEnquiry.cosumeWS(map, txnEnq);
			System.out.println(txnEnq);
			System.out.println(txnEnq.getAmount());
		} catch (IRCTCException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertEquals("", "");
	}*/
	
	/*@Test
	public void getEnquiry(){
		IrctcDaoImpl irctcDao= (IrctcDaoImpl) ctx.getBean(Constants.TCH_IRCTC_DAO);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		IrctcDTO irctcDTO=new IrctcDTO();
		irctcDTO.setTid("WER34678");
		irctcDTO.setAsstSrNo("423434343");
		irctcDTO.setServiceProvider("WL");
		irctcDTO.setAppTxnId("1000000001");
		irctcDTO.setAmount("100.50");
		irctcDTO.setInvoiceNo("6742354345");
		irctcDTO.setRrn("00454654655");
		irctcDTO.setCardigit("5667");
		irctcDTO.setCardProvider("vi");
		irctcDTO.setCardType("C");
		irctcDTO.setTimeStamp("20-11-2016 18:30:30");
		irctcDTO.setTimeStamp(dateFormat.format(date));
		irctcDTO.setReqType("Enquiry");
		irctcDao.save(irctcDTO);
		
	}*/
}
