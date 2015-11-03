package com.epizza;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.epizza.item.ItemFactory;
import com.epizza.item.ItemObject;
/**
 * 
 * Epizza company scale main class
 * @author SUSHIL
 *
 */
public class EpizzaScale {
	
	
	public static void main(String[] str){
		try {
			if(str.length < 2)
				throw new EpizzaException("Please run the program as arg as : java EpizzaScale <input_file_with_path> <outputfile_with_path>");
			
			EpizzaScale epizzaEscale = new EpizzaScale(str[0], str[1]);
			epizzaEscale.calculateAndPrintTotalBill();
			
		} catch (EpizzaException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	
	public EpizzaScale(String inputFile, String outputFile){
		this.orderInputFile = inputFile;
		this.orderOutputFile = outputFile;
	}

	/**
	 * Calculate and print total Bill amount
	 * @param itemOrderList
	 * @param outputFile
	 * @throws EpizzaException 
	 */
	public Double calculateAndPrintTotalBill() throws EpizzaException {
		List<String> output = new ArrayList<String>();
		Double totalItemAmt = 0.0, totalBillAmt=0.0, newCustDiscAmt=0.0, vatAmt=0.0;
		//Read input file
		List<ItemObject> itemOrderList = readInputFile(orderInputFile);
		
		//Pizza item bill amount
		for(ItemObject item : itemOrderList){
			totalItemAmt += item.getItemBillAmt();
			output.add(EpizzaUtill.formatBillOutput(item.getItemString(),item.getItemBillAmt()));
		}
			
		// 10% discount for new customer
		if(isNewCustomer(getOrderEmail())){
			newCustDiscAmt = totalItemAmt*(Constant.NEW_CUST_DIS_PERCENT/100);
			output.add(EpizzaUtill.formatBillOutput("New customer discount ("+Constant.NEW_CUST_DIS_PERCENT+"%)", -newCustDiscAmt));
		}
		totalBillAmt = totalItemAmt - newCustDiscAmt;
		output.add(EpizzaUtill.formatBillOutput("Order total ",totalBillAmt));
			
		//VAT % application
		vatAmt =  totalBillAmt * (Constant.VAT_PERCENT/100);
		output.add(EpizzaUtill.formatBillOutput("VAT ("+Constant.VAT_PERCENT +"%)",vatAmt));
			
		//Total
		totalBillAmt +=vatAmt;
		output.add(EpizzaUtill.formatBillOutput("Total ",totalBillAmt));
		
		writeOutputFile(output);
		return totalBillAmt;
	}

	/**
	 * Check if it is new customer email or existing. If it does not exist,
	 * persist in the file email.txt.
	 * @param orderEmail
	 * @return
	 * @throws EpizzaException
	 */
	public boolean isNewCustomer(String orderEmail) throws EpizzaException {
		boolean isNewCustomer = false;
		if(orderEmail == null || orderEmail.trim().length() == 0)
			return isNewCustomer;
		Properties prop = new Properties();
		File emailFileBase = new File(Constant.EMAIL_FILE_BASE);
		try {
			if(emailFileBase.exists())	
				prop.load(new FileReader(emailFileBase));
			if(!prop.containsKey(orderEmail)){
					isNewCustomer = true;
					prop.put(orderEmail, Constant.EMAIL_IDENTIFIER);
					prop.store(new FileWriter(Constant.EMAIL_FILE_BASE), null);
			}
			
		} catch (IOException e) {
			e.printStackTrace();	
			throw new EpizzaException(e.getMessage());
		}
		return isNewCustomer;
	}

	private String orderEmail;
	private String orderInputFile;
	private String orderOutputFile;
	
	/**
	 * 
	 * @param output
	 * @throws EpizzaException
	 */
	private void writeOutputFile(List<String> output) throws EpizzaException{
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(orderOutputFile));
			for(String out : output){
				writer.write(out);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new EpizzaException(e.getMessage());
			}
		}
	
	}
	
	/**
	 * read input file and return ordered Pizza object
	 */
	private List<ItemObject> readInputFile(String fileName) throws EpizzaException{
		List<ItemObject> orderList =  new ArrayList<ItemObject>();
		BufferedReader reader=null;
		try {
			reader = new BufferedReader(new FileReader(fileName));
			String line =null;
			while((line = reader.readLine()) !=null && line.length() >0){
				if(EpizzaUtill.isInputEmail(line))
					setOrderEmail(line);
				else{
					orderList.add(ItemFactory.getItem(line));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new EpizzaException(e.getMessage());
		}finally{
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new EpizzaException(e.getMessage());
			}
		}
		return orderList;
	}
	
	public void setOrderEmail(String email){
		this.orderEmail = email.substring(email.indexOf(':')+1).trim();
	}
	
	public String getOrderEmail(){
		return orderEmail;
	}
	
	
	
}
