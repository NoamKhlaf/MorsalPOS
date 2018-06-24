package sd.code.morsalpos.common;

import com.vanstone.base.interfaces.StructInterface;

/*************************************************************************************
��          ��: ���
��          Ȩ: �����������Ӽ���(����)���޹�˾
��          ��: 
*************************************************************************************/
public class LogStrc implements StructInterface{
	public int ucRecFalg; 		// ���ʽ����Ƿ�������
	public int EccTrans; 		// �����ֽ���
	public byte EccOnline; 		// ICC online falg
	public int IccFallBack; 	// TRUE, FALSE
	public int nIccDataLen; 		// ��Ƭ���ݳ���
	public int Trans_id; 		// ����id
	// u8 SecondTransId; //ǰ�潻�״��������С���࣬���������ƽ���
	public int ucSwipedFlag; 	// ˢ������
	public byte[] MainAcc = new byte[19]; 		// #2 ���ʺ� n..19
	public byte[] TradeAmount = new byte[7]; 		// #4 ���׽�� BCD6
	public byte[] TradeDate = new byte[4]; 		// #13 �������� n4(YYYYMMDD)(4BCD�� ��:19700101
									// 1970-01-01)
	public byte[] TradeTime = new byte[3]; 		// #12 ����ʱ�� n3(hhmmss)(3λBCD�� ��: 111231 11:12:31)
	public int OperatorNo; 		// ����Ա��
	public int lTraceNo; 			// #11 ��ˮ�� n6
	public int lNowBatchNum; 		// #9 ���κ� n8
	public byte[] szRespCode = new byte[3]; 		// #39 ���׷�����

	// 52�ֽ�

	public byte[] bitmapsend = new byte[8]; 		// ���͵�λͼ 2
	public byte[] ResProcCode = new byte[4]; 	// ������6λ�������3��4λ���ֱ�ʶ���ʻ�����(����������)
	public byte[] TipAmount = new byte[6]; 		// #6 С�ѽ��n12
	public byte[] TradeDateAndTime = new byte[5];// #7 ����ʱ�� n5(MMDDhhmmss)
	public byte[] ExpDate = new byte[4]; 		// #14 ��Ч�� ��/��/��
	public byte[] EntryMode = new byte[4]; 			// #22 POS����㷽ʽ ��n3
	public byte[] field_26 = new byte[2]; 		// #26 ���ͱ�־n2
	public int DesAndFrCardFlag_28; // #28 DES/�⿨��־n1

	public byte[] CenterId = new byte[9]; 		// #32 ������ʶ��
	public byte[] SysReferNo = new byte[13]; 		// #37 ϵͳ�ο��� an12
	public byte[] AuthCode = new byte[7]; 		// #38 ��Ȩ�� an6
	public byte[] TerminalNo = new byte[9]; 		// #41 �ն˺� ans8
	public byte[] MerchantNo = new byte[16]; 		// #42 �̻��� ans15
	public byte[] szIssuerBankId = new byte[9];
	public byte[] szRecvBankId = new byte[9];
	public byte[] SecondAmount = new byte[6]; 		// �ڶ�����������Ҫ�ڶ�������ĳ���
	public byte[] SecondAcc = new byte[21]; 		// #48 �ڶ��ʺ� //�����õڶ��˺ű��潻�״���
	public byte[] HoldCardName = new byte[20]; 	// �ֿ�������
	public byte[] cardType = new byte[17]; 		// ����58���ǰ16λ
	public byte[] IccSn = new byte[2]; 			// #23 IC��ϵ�к� n3
	public byte[] AddInfo = new byte[123]; 		// #54��62��� ������Ϣ��������Ϣ�ȵȸ�����Ϣ
	public int OldTraceNo; 		// ԭ������ˮ �������ѳ���
	public int OldBatchNum; 	// #ԭ�������κ�
	public byte[] OldTransDate = new byte[9]; 	// #15 ԭ��������
	public byte[] OldSysRefNo = new byte[13]; 	// ԭϵͳ�ο���
	// EMV��������
	public byte[] IccData = new byte[256]; 		// #55 IC�������� var up to 255
	public byte[] szCardUnit = new byte[4]; 		// ����֯��CUP VIS MAS
	public int bPanSeqNoOk; 	// ADVT case 43 [3/31/2006 Tommy]
	public byte ucPanSeqNo; 		// App. PAN sequence number
	public byte[] sAppCrypto = new byte[8]; 		// app. cryptogram
	public byte[] sAuthRspCode = new byte[2];
	public byte[] sTVR = new byte[5];
	public byte[] szAID = new byte[33];
	public byte[] szAppLable = new byte[17];
	public byte[] sTSI = new byte[2];
	public byte[] sATC = new byte[2];
	public byte[] szAppPreferName = new byte[17];
	public byte[] Ec_Balance = new byte[6]; 		// �����ֽ����
	public byte[] szCardTypeName = new byte[20]; 	// ��̨���صĿ��������
	public byte[] szAcquirer = new byte[7]; 		// �յ���
	public byte[] szIssuerResp = new byte[21]; 	// #63.2���ڴ�ӡ��ע
	public byte[] szCenterResp = new byte[21]; 	// #63.3
	public byte[] szRecvBankResp = new byte[21]; 	// #63.4
	public byte[] szTransCode = new byte[7]; 	// ������ ���ڽ����п���
	public byte[] IssueBankName = new byte[41]; 	// ���صķ���������
	public byte[] OldTransCode = new byte[7]; 	// ԭ���״�����(��Ҫ���ڽű��������)

	public int getUcRecFalg() {
		return ucRecFalg;
	}

	public void setUcRecFalg(int ucRecFalg) {
		this.ucRecFalg = ucRecFalg;
	}

	public int getEccTrans() {
		return EccTrans;
	}

	public void setEccTrans(int eccTrans) {
		EccTrans = eccTrans;
	}

	public byte getEccOnline() {
		return EccOnline;
	}

	public void setEccOnline(byte eccOnline) {
		EccOnline = eccOnline;
	}

	public int getIccFallBack() {
		return IccFallBack;
	}

	public void setIccFallBack(int iccFallBack) {
		IccFallBack = iccFallBack;
	}

	public int getnIccDataLen() {
		return nIccDataLen;
	}

	public void setnIccDataLen(int nIccDataLen) {
		this.nIccDataLen = nIccDataLen;
	}

	public int getTrans_id() {
		return Trans_id;
	}

	public void setTrans_id(int trans_id) {
		Trans_id = trans_id;
	}

	public int getUcSwipedFlag() {
		return ucSwipedFlag;
	}

	public void setUcSwipedFlag(int ucSwipedFlag) {
		this.ucSwipedFlag = ucSwipedFlag;
	}

	public byte[] getMainAcc() {
		return MainAcc;
	}

	public void setMainAcc(byte[] mainAcc) {
		MainAcc = mainAcc;
	}

	public byte[] getTradeAmount() {
		return TradeAmount;
	}

	public void setTradeAmount(byte[] tradeAmount) {
		TradeAmount = tradeAmount;
	}

	public byte[] getTradeDate() {
		return TradeDate;
	}

	public void setTradeDate(byte[] tradeDate) {
		TradeDate = tradeDate;
	}

	public byte[] getTradeTime() {
		return TradeTime;
	}

	public void setTradeTime(byte[] tradeTime) {
		TradeTime = tradeTime;
	}

	public int getOperatorNo() {
		return OperatorNo;
	}

	public void setOperatorNo(int operatorNo) {
		OperatorNo = operatorNo;
	}

	public int getlTraceNo() {
		return lTraceNo;
	}

	public void setlTraceNo(int lTraceNo) {
		this.lTraceNo = lTraceNo;
	}

	public int getlNowBatchNum() {
		return lNowBatchNum;
	}

	public void setlNowBatchNum(int lNowBatchNum) {
		this.lNowBatchNum = lNowBatchNum;
	}

	public byte[] getSzRespCode() {
		return szRespCode;
	}

	public void setSzRespCode(byte[] szRespCode) {
		this.szRespCode = szRespCode;
	}

	public byte[] getBitmapsend() {
		return bitmapsend;
	}

	public void setBitmapsend(byte[] bitmapsend) {
		this.bitmapsend = bitmapsend;
	}

	public byte[] getResProcCode() {
		return ResProcCode;
	}

	public void setResProcCode(byte[] resProcCode) {
		ResProcCode = resProcCode;
	}

	public byte[] getTipAmount() {
		return TipAmount;
	}

	public void setTipAmount(byte[] tipAmount) {
		TipAmount = tipAmount;
	}

	public byte[] getTradeDateAndTime() {
		return TradeDateAndTime;
	}

	public void setTradeDateAndTime(byte[] tradeDateAndTime) {
		TradeDateAndTime = tradeDateAndTime;
	}

	public byte[] getExpDate() {
		return ExpDate;
	}

	public void setExpDate(byte[] expDate) {
		ExpDate = expDate;
	}

	public byte[] getEntryMode() {
		return EntryMode;
	}

	public void setEntryMode(byte[] entryMode) {
		EntryMode = entryMode;
	}

	public byte[] getField_26() {
		return field_26;
	}

	public void setField_26(byte[] field_26) {
		this.field_26 = field_26;
	}

	public int getDesAndFrCardFlag_28() {
		return DesAndFrCardFlag_28;
	}

	public void setDesAndFrCardFlag_28(int desAndFrCardFlag_28) {
		DesAndFrCardFlag_28 = desAndFrCardFlag_28;
	}

	public byte[] getCenterId() {
		return CenterId;
	}

	public void setCenterId(byte[] centerId) {
		CenterId = centerId;
	}

	public byte[] getSysReferNo() {
		return SysReferNo;
	}

	public void setSysReferNo(byte[] sysReferNo) {
		SysReferNo = sysReferNo;
	}

	public byte[] getAuthCode() {
		return AuthCode;
	}

	public void setAuthCode(byte[] authCode) {
		AuthCode = authCode;
	}

	public byte[] getTerminalNo() {
		return TerminalNo;
	}

	public void setTerminalNo(byte[] terminalNo) {
		TerminalNo = terminalNo;
	}

	public byte[] getMerchantNo() {
		return MerchantNo;
	}

	public void setMerchantNo(byte[] merchantNo) {
		MerchantNo = merchantNo;
	}

	public byte[] getSzIssuerBankId() {
		return szIssuerBankId;
	}

	public void setSzIssuerBankId(byte[] szIssuerBankId) {
		this.szIssuerBankId = szIssuerBankId;
	}

	public byte[] getSzRecvBankId() {
		return szRecvBankId;
	}

	public void setSzRecvBankId(byte[] szRecvBankId) {
		this.szRecvBankId = szRecvBankId;
	}

	public byte[] getSecondAmount() {
		return SecondAmount;
	}

	public void setSecondAmount(byte[] secondAmount) {
		SecondAmount = secondAmount;
	}

	public byte[] getSecondAcc() {
		return SecondAcc;
	}

	public void setSecondAcc(byte[] secondAcc) {
		SecondAcc = secondAcc;
	}

	public byte[] getHoldCardName() {
		return HoldCardName;
	}

	public void setHoldCardName(byte[] holdCardName) {
		HoldCardName = holdCardName;
	}

	public byte[] getCardType() {
		return cardType;
	}

	public void setCardType(byte[] cardType) {
		this.cardType = cardType;
	}

	public byte[] getIccSn() {
		return IccSn;
	}

	public void setIccSn(byte[] iccSn) {
		IccSn = iccSn;
	}

	public byte[] getAddInfo() {
		return AddInfo;
	}

	public void setAddInfo(byte[] addInfo) {
		AddInfo = addInfo;
	}

	public int getOldTraceNo() {
		return OldTraceNo;
	}

	public void setOldTraceNo(int oldTraceNo) {
		OldTraceNo = oldTraceNo;
	}

	public int getOldBatchNum() {
		return OldBatchNum;
	}

	public void setOldBatchNum(int oldBatchNum) {
		OldBatchNum = oldBatchNum;
	}

	public byte[] getOldTransDate() {
		return OldTransDate;
	}

	public void setOldTransDate(byte[] oldTransDate) {
		OldTransDate = oldTransDate;
	}

	public byte[] getOldSysRefNo() {
		return OldSysRefNo;
	}

	public void setOldSysRefNo(byte[] oldSysRefNo) {
		OldSysRefNo = oldSysRefNo;
	}

	public byte[] getIccData() {
		return IccData;
	}

	public void setIccData(byte[] iccData) {
		IccData = iccData;
	}

	public byte[] getSzCardUnit() {
		return szCardUnit;
	}

	public void setSzCardUnit(byte[] szCardUnit) {
		this.szCardUnit = szCardUnit;
	}

	public int getbPanSeqNoOk() {
		return bPanSeqNoOk;
	}

	public void setbPanSeqNoOk(int bPanSeqNoOk) {
		this.bPanSeqNoOk = bPanSeqNoOk;
	}

	public byte getUcPanSeqNo() {
		return ucPanSeqNo;
	}

	public void setUcPanSeqNo(byte ucPanSeqNo) {
		this.ucPanSeqNo = ucPanSeqNo;
	}

	public byte[] getsAppCrypto() {
		return sAppCrypto;
	}

	public void setsAppCrypto(byte[] sAppCrypto) {
		this.sAppCrypto = sAppCrypto;
	}

	public byte[] getsAuthRspCode() {
		return sAuthRspCode;
	}

	public void setsAuthRspCode(byte[] sAuthRspCode) {
		this.sAuthRspCode = sAuthRspCode;
	}

	public byte[] getsTVR() {
		return sTVR;
	}

	public void setsTVR(byte[] sTVR) {
		this.sTVR = sTVR;
	}

	public byte[] getSzAID() {
		return szAID;
	}

	public void setSzAID(byte[] szAID) {
		this.szAID = szAID;
	}

	public byte[] getSzAppLable() {
		return szAppLable;
	}

	public void setSzAppLable(byte[] szAppLable) {
		this.szAppLable = szAppLable;
	}

	public byte[] getsTSI() {
		return sTSI;
	}

	public void setsTSI(byte[] sTSI) {
		this.sTSI = sTSI;
	}

	public byte[] getsATC() {
		return sATC;
	}

	public void setsATC(byte[] sATC) {
		this.sATC = sATC;
	}

	public byte[] getSzAppPreferName() {
		return szAppPreferName;
	}

	public void setSzAppPreferName(byte[] szAppPreferName) {
		this.szAppPreferName = szAppPreferName;
	}

	public byte[] getEc_Balance() {
		return Ec_Balance;
	}

	public void setEc_Balance(byte[] ec_Balance) {
		Ec_Balance = ec_Balance;
	}

	public byte[] getSzCardTypeName() {
		return szCardTypeName;
	}

	public void setSzCardTypeName(byte[] szCardTypeName) {
		this.szCardTypeName = szCardTypeName;
	}

	public byte[] getSzAcquirer() {
		return szAcquirer;
	}

	public void setSzAcquirer(byte[] szAcquirer) {
		this.szAcquirer = szAcquirer;
	}

	public byte[] getSzIssuerResp() {
		return szIssuerResp;
	}

	public void setSzIssuerResp(byte[] szIssuerResp) {
		this.szIssuerResp = szIssuerResp;
	}

	public byte[] getSzCenterResp() {
		return szCenterResp;
	}

	public void setSzCenterResp(byte[] szCenterResp) {
		this.szCenterResp = szCenterResp;
	}

	public byte[] getSzRecvBankResp() {
		return szRecvBankResp;
	}

	public void setSzRecvBankResp(byte[] szRecvBankResp) {
		this.szRecvBankResp = szRecvBankResp;
	}

	public byte[] getSzTransCode() {
		return szTransCode;
	}

	public void setSzTransCode(byte[] szTransCode) {
		this.szTransCode = szTransCode;
	}

	public byte[] getIssueBankName() {
		return IssueBankName;
	}

	public void setIssueBankName(byte[] issueBankName) {
		IssueBankName = issueBankName;
	}

	public byte[] getOldTransCode() {
		return OldTransCode;
	}

	public void setOldTransCode(byte[] oldTransCode) {
		OldTransCode = oldTransCode;
	}

	public byte[] toBytes() {
		byte[] msgByte=new byte[size()];
		byte[]tmp=null;
		int len=0;
		tmp=new byte[4];
		tmp=com.vanstone.utils.CommonConvert.intToBytes(ucRecFalg);
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=4;
		tmp=new byte[4];
		tmp=com.vanstone.utils.CommonConvert.intToBytes(EccTrans);
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=4;
		tmp=new byte[1];
		tmp[0]=EccOnline;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[4];
		tmp=com.vanstone.utils.CommonConvert.intToBytes(IccFallBack);
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=4;
		tmp=new byte[4];
		tmp=com.vanstone.utils.CommonConvert.intToBytes(nIccDataLen);
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=4;
		tmp=new byte[4];
		tmp=com.vanstone.utils.CommonConvert.intToBytes(Trans_id);
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=4;
		tmp=new byte[4];
		tmp=com.vanstone.utils.CommonConvert.intToBytes(ucSwipedFlag);
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=4;
		tmp=new byte[MainAcc.length];
		tmp=MainAcc;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[TradeAmount.length];
		tmp=TradeAmount;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[TradeDate.length];
		tmp=TradeDate;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[TradeTime.length];
		tmp=TradeTime;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[4];
		tmp=com.vanstone.utils.CommonConvert.intToBytes(OperatorNo);
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=4;
		tmp=new byte[4];
		tmp=com.vanstone.utils.CommonConvert.intToBytes(lTraceNo);
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=4;
		tmp=new byte[4];
		tmp=com.vanstone.utils.CommonConvert.intToBytes(lNowBatchNum);
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=4;
		tmp=new byte[szRespCode.length];
		tmp=szRespCode;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[bitmapsend.length];
		tmp=bitmapsend;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[ResProcCode.length];
		tmp=ResProcCode;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[TipAmount.length];
		tmp=TipAmount;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[TradeDateAndTime.length];
		tmp=TradeDateAndTime;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[ExpDate.length];
		tmp=ExpDate;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[EntryMode.length];
		tmp=EntryMode;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[field_26.length];
		tmp=field_26;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[4];
		tmp=com.vanstone.utils.CommonConvert.intToBytes(DesAndFrCardFlag_28);
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=4;
		tmp=new byte[CenterId.length];
		tmp=CenterId;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[SysReferNo.length];
		tmp=SysReferNo;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[AuthCode.length];
		tmp=AuthCode;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[TerminalNo.length];
		tmp=TerminalNo;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[MerchantNo.length];
		tmp=MerchantNo;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[szIssuerBankId.length];
		tmp=szIssuerBankId;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[szRecvBankId.length];
		tmp=szRecvBankId;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[SecondAmount.length];
		tmp=SecondAmount;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[SecondAcc.length];
		tmp=SecondAcc;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[HoldCardName.length];
		tmp=HoldCardName;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[cardType.length];
		tmp=cardType;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[IccSn.length];
		tmp=IccSn;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[AddInfo.length];
		tmp=AddInfo;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[4];
		tmp=com.vanstone.utils.CommonConvert.intToBytes(OldTraceNo);
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=4;
		tmp=new byte[4];
		tmp=com.vanstone.utils.CommonConvert.intToBytes(OldBatchNum);
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=4;
		tmp=new byte[OldTransDate.length];
		tmp=OldTransDate;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[OldSysRefNo.length];
		tmp=OldSysRefNo;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[IccData.length];
		tmp=IccData;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[szCardUnit.length];
		tmp=szCardUnit;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[4];
		tmp=com.vanstone.utils.CommonConvert.intToBytes(bPanSeqNoOk);
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=4;
		tmp=new byte[1];
		tmp[0]=ucPanSeqNo;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[sAppCrypto.length];
		tmp=sAppCrypto;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[sAuthRspCode.length];
		tmp=sAuthRspCode;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[sTVR.length];
		tmp=sTVR;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[szAID.length];
		tmp=szAID;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[szAppLable.length];
		tmp=szAppLable;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[sTSI.length];
		tmp=sTSI;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[sATC.length];
		tmp=sATC;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[szAppPreferName.length];
		tmp=szAppPreferName;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[Ec_Balance.length];
		tmp=Ec_Balance;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[szCardTypeName.length];
		tmp=szCardTypeName;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[szAcquirer.length];
		tmp=szAcquirer;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[szIssuerResp.length];
		tmp=szIssuerResp;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[szCenterResp.length];
		tmp=szCenterResp;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[szRecvBankResp.length];
		tmp=szRecvBankResp;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[szTransCode.length];
		tmp=szTransCode;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[IssueBankName.length];
		tmp=IssueBankName;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[OldTransCode.length];
		tmp=OldTransCode;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		if(len%4!=0)
		{
		tmp = new byte[4-len%4];
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		}
		return msgByte;
	}

	public int size() {
		int len=0;
		len+=4;
		len+=4;
		len+=1;
		len+=4;
		len+=4;
		len+=4;
		len+=4;
		len+=MainAcc.length;
		len+=TradeAmount.length;
		len+=TradeDate.length;
		len+=TradeTime.length;
		len+=4;
		len+=4;
		len+=4;
		len+=szRespCode.length;
		len+=bitmapsend.length;
		len+=ResProcCode.length;
		len+=TipAmount.length;
		len+=TradeDateAndTime.length;
		len+=ExpDate.length;
		len+=EntryMode.length;
		len+=field_26.length;
		len+=4;
		len+=CenterId.length;
		len+=SysReferNo.length;
		len+=AuthCode.length;
		len+=TerminalNo.length;
		len+=MerchantNo.length;
		len+=szIssuerBankId.length;
		len+=szRecvBankId.length;
		len+=SecondAmount.length;
		len+=SecondAcc.length;
		len+=HoldCardName.length;
		len+=cardType.length;
		len+=IccSn.length;
		len+=AddInfo.length;
		len+=4;
		len+=4;
		len+=OldTransDate.length;
		len+=OldSysRefNo.length;
		len+=IccData.length;
		len+=szCardUnit.length;
		len+=4;
		len+=1;
		len+=sAppCrypto.length;
		len+=sAuthRspCode.length;
		len+=sTVR.length;
		len+=szAID.length;
		len+=szAppLable.length;
		len+=sTSI.length;
		len+=sATC.length;
		len+=szAppPreferName.length;
		len+=Ec_Balance.length;
		len+=szCardTypeName.length;
		len+=szAcquirer.length;
		len+=szIssuerResp.length;
		len+=szCenterResp.length;
		len+=szRecvBankResp.length;
		len+=szTransCode.length;
		len+=IssueBankName.length;
		len+=OldTransCode.length;
		if(len%4!=0)
		{
		len+=4-len%4;
		}
		return len;
	}

	public void toBean(byte[] buf) {
		byte[]tmp=null;
		int len=0;
		tmp=new byte[4];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		ucRecFalg=com.vanstone.utils.CommonConvert.bytesToInt(tmp);
		len+=4;
		tmp=new byte[4];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		EccTrans=com.vanstone.utils.CommonConvert.bytesToInt(tmp);
		len+=4;
		tmp=new byte[1];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		EccOnline=tmp[0];
		len+=tmp.length;
		tmp=new byte[4];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		IccFallBack=com.vanstone.utils.CommonConvert.bytesToInt(tmp);
		len+=4;
		tmp=new byte[4];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		nIccDataLen=com.vanstone.utils.CommonConvert.bytesToInt(tmp);
		len+=4;
		tmp=new byte[4];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		Trans_id=com.vanstone.utils.CommonConvert.bytesToInt(tmp);
		len+=4;
		tmp=new byte[4];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		ucSwipedFlag=com.vanstone.utils.CommonConvert.bytesToInt(tmp);
		len+=4;
		tmp=new byte[MainAcc.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		MainAcc=tmp;
		len+=tmp.length;
		tmp=new byte[TradeAmount.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		TradeAmount=tmp;
		len+=tmp.length;
		tmp=new byte[TradeDate.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		TradeDate=tmp;
		len+=tmp.length;
		tmp=new byte[TradeTime.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		TradeTime=tmp;
		len+=tmp.length;
		tmp=new byte[4];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		OperatorNo=com.vanstone.utils.CommonConvert.bytesToInt(tmp);
		len+=4;
		tmp=new byte[4];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		lTraceNo=com.vanstone.utils.CommonConvert.bytesToInt(tmp);
		len+=4;
		tmp=new byte[4];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		lNowBatchNum=com.vanstone.utils.CommonConvert.bytesToInt(tmp);
		len+=4;
		tmp=new byte[szRespCode.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		szRespCode=tmp;
		len+=tmp.length;
		tmp=new byte[bitmapsend.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		bitmapsend=tmp;
		len+=tmp.length;
		tmp=new byte[ResProcCode.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		ResProcCode=tmp;
		len+=tmp.length;
		tmp=new byte[TipAmount.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		TipAmount=tmp;
		len+=tmp.length;
		tmp=new byte[TradeDateAndTime.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		TradeDateAndTime=tmp;
		len+=tmp.length;
		tmp=new byte[ExpDate.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		ExpDate=tmp;
		len+=tmp.length;
		tmp=new byte[EntryMode.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		EntryMode=tmp;
		len+=tmp.length;
		tmp=new byte[field_26.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		field_26=tmp;
		len+=tmp.length;
		tmp=new byte[4];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		DesAndFrCardFlag_28=com.vanstone.utils.CommonConvert.bytesToInt(tmp);
		len+=4;
		tmp=new byte[CenterId.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		CenterId=tmp;
		len+=tmp.length;
		tmp=new byte[SysReferNo.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		SysReferNo=tmp;
		len+=tmp.length;
		tmp=new byte[AuthCode.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		AuthCode=tmp;
		len+=tmp.length;
		tmp=new byte[TerminalNo.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		TerminalNo=tmp;
		len+=tmp.length;
		tmp=new byte[MerchantNo.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		MerchantNo=tmp;
		len+=tmp.length;
		tmp=new byte[szIssuerBankId.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		szIssuerBankId=tmp;
		len+=tmp.length;
		tmp=new byte[szRecvBankId.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		szRecvBankId=tmp;
		len+=tmp.length;
		tmp=new byte[SecondAmount.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		SecondAmount=tmp;
		len+=tmp.length;
		tmp=new byte[SecondAcc.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		SecondAcc=tmp;
		len+=tmp.length;
		tmp=new byte[HoldCardName.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		HoldCardName=tmp;
		len+=tmp.length;
		tmp=new byte[cardType.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		cardType=tmp;
		len+=tmp.length;
		tmp=new byte[IccSn.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		IccSn=tmp;
		len+=tmp.length;
		tmp=new byte[AddInfo.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		AddInfo=tmp;
		len+=tmp.length;
		tmp=new byte[4];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		OldTraceNo=com.vanstone.utils.CommonConvert.bytesToInt(tmp);
		len+=4;
		tmp=new byte[4];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		OldBatchNum=com.vanstone.utils.CommonConvert.bytesToInt(tmp);
		len+=4;
		tmp=new byte[OldTransDate.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		OldTransDate=tmp;
		len+=tmp.length;
		tmp=new byte[OldSysRefNo.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		OldSysRefNo=tmp;
		len+=tmp.length;
		tmp=new byte[IccData.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		IccData=tmp;
		len+=tmp.length;
		tmp=new byte[szCardUnit.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		szCardUnit=tmp;
		len+=tmp.length;
		tmp=new byte[4];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		bPanSeqNoOk=com.vanstone.utils.CommonConvert.bytesToInt(tmp);
		len+=4;
		tmp=new byte[1];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		ucPanSeqNo=tmp[0];
		len+=tmp.length;
		tmp=new byte[sAppCrypto.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		sAppCrypto=tmp;
		len+=tmp.length;
		tmp=new byte[sAuthRspCode.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		sAuthRspCode=tmp;
		len+=tmp.length;
		tmp=new byte[sTVR.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		sTVR=tmp;
		len+=tmp.length;
		tmp=new byte[szAID.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		szAID=tmp;
		len+=tmp.length;
		tmp=new byte[szAppLable.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		szAppLable=tmp;
		len+=tmp.length;
		tmp=new byte[sTSI.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		sTSI=tmp;
		len+=tmp.length;
		tmp=new byte[sATC.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		sATC=tmp;
		len+=tmp.length;
		tmp=new byte[szAppPreferName.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		szAppPreferName=tmp;
		len+=tmp.length;
		tmp=new byte[Ec_Balance.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		Ec_Balance=tmp;
		len+=tmp.length;
		tmp=new byte[szCardTypeName.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		szCardTypeName=tmp;
		len+=tmp.length;
		tmp=new byte[szAcquirer.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		szAcquirer=tmp;
		len+=tmp.length;
		tmp=new byte[szIssuerResp.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		szIssuerResp=tmp;
		len+=tmp.length;
		tmp=new byte[szCenterResp.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		szCenterResp=tmp;
		len+=tmp.length;
		tmp=new byte[szRecvBankResp.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		szRecvBankResp=tmp;
		len+=tmp.length;
		tmp=new byte[szTransCode.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		szTransCode=tmp;
		len+=tmp.length;
		tmp=new byte[IssueBankName.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		IssueBankName=tmp;
		len+=tmp.length;
		tmp=new byte[OldTransCode.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		OldTransCode=tmp;
		len+=tmp.length;
	}

}
