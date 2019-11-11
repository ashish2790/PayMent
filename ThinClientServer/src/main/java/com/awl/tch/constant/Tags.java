package com.awl.tch.constant;

public interface Tags {

	
	public String currencyCodeTagLength = "000103";
	public String transactionIDTagLength = "000215";
	public String DCCtransactionIndicatorTagLength = "000301";
	public String amountInLocalCurrencyTagLength = "000412";
	public String localCurrencyCodeTagLength = "000503";
	public String exchangeRateTagLength = "000608";
	public String markupPercentTagLength = "000704";
	public String DCCTransactioncurrencycodeTagLength = "000803";
	public String originalAmountTagLength = "001112";
	public String serviceCodeTagLength = "001703";
	public String invoiceNumberTagLength = "001806";
	public String conveniencefeesTagLength = "003312";
	public String authorizationSourceTagLength = "003501";
	public String hardwareserialnumberTagLength = "005315";
	public String expirydatetagLength = "005504";
	public String transactionCurrencyName = "000903";
	public String numberOfDecimalForDccAmount = "001001";
	public String emiPrefixTagLength = "000301";
	public String emiProgramCodeTagLength = "001906";
	public String emiProductCodeTagLength = "002015";
	public String emiTenureTagLength = "002102";
	public String emidiscflagtypeTagLength = "002201";
	public String emiAmountTagLength = "002312";
	public String emiROITagLength = "002404";
	public String emiPercentageTagLength = "002504";
	public String emiDiscAmountTagLength = "002612";
	public String emiFixedAmountTagLength = "002712";
	public String emiCapAmountTagLength = "002812";
	public String emiprocfeepercentageTagLength = "002904";
	public String emitotalamountTagLength = "003012";
	public String emieffcostcustomerTagLength = "003112";
	public String optoutVoidTransactionTagLength = "004602";
	public String upiIndicator = "005601U";
	public String pinBypassFlag = "005701";
	//Brand emi  // tag changed to 4 length eg. 058 -> 0058
	public String brandEmiManufacture ="005805";
	public String brandEmischeme ="005902";
	public String brandEmiproductCategory ="006002";
	public String brandEmiVelocity ="006102";
	public String brandEmiVelocitydays ="006203";
	public String brandEmiVelocityDWMY ="006301";
}
