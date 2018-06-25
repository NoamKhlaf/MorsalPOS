package sd.code.morsalpos.common;

/*************************************************************************************
 * �� ��: ��� �� Ȩ: �����������Ӽ���(����)���޹�˾ �� ��:
 *************************************************************************************/
public class PosMacroConstants {
	//��Ϣ����
	//�ն˸��ݽ���ID�������׽ű��������ݽű��������ݽ��н��״���;
	/*---------------------����ID����----------------------------*/
	public static final int TRADE_ID_SALE				= 1001;		//����
	public static final int TRADE_ID_AUTH_SALE			= 1002;		//��Ȩ����
	public static final int TRADE_ID_INSTALLMENT		= 1003;		//���ڸ���
	public static final int TRADE_ID_OFFLINE			= 1004;		//�⿨���߽���
	public static final int TRADE_ID_BONUS_SALE			= 1005;		//��������
	public static final int TRADE_ID_CHECK_SALE			= 1006;		//֧Ʊ����
	public static final int TRADE_ID_TIP				= 1007;		//С�ѽ���
	public static final int TRADE_ID_FINANCE_TRANSFER_IN= 1008;		//����ת��
	public static final int TRADE_ID_FINANCE_TRANSFER_OUT= 1009;		//����ת��
	public static final int TRADE_ID_TRANSFER			= 1010;		//����ת��
	public static final int TRADE_ID_PRE_AUTH			= 1011;		//Ԥ��Ȩ
	public static final int TRADE_ID_PREAUTHACK			= 1012;		//Ԥ��Ȩȷ��
	public static final int TRADE_ID_BONUS_PREAUTHACK	= 1013;		//����Ԥ��Ȩȷ��
	public static final int TRADE_ID_PREAUTHADD			= 1014;		//׷��Ԥ��Ȩ
	public static final int TRADE_ID_PAYPASS			= 1015;		//��������
	public static final int TRADE_ID_RELOAD				= 1016;		//�ֹ�Ȧ��
	public static final int TRADE_ID_PREPAY_SALES		= 1017;		//Ԥ������
	public static final int TRADE_ID_PRESALE			= 1018;		//Ԥ������
	public static final int TRADE_ID_STOCK				= 1019;		//�չ�����
	public static final int TRADE_ID_AIRPORTVIP			= 1020;		//���ʶ��
	public static final int TRADE_ID_CREDIT_DEBIT		= 1021;		//���ÿ����� ---���нű�����

	public static final int TRADE_ID_SMSK				= 1030;		//�����տ�

	//���¹淶��û�и���
	public static final int TRADE_ID_LOANADJUST			= 1031;		//POS_LOANADJUST�����Ŵ��޸�
	/*public static final int TRADE_ID_LOANSALE			= 1032;		//POS_LOANSALE�����Ŵ�����
	public static final int TRADE_ID_LOANOPEN			= 1033;		//POS_LOANOPEN�����Ŵ���ͨ
	public static final int TRADE_ID_LOANQUERY			= 1034;		//POS_LOANOPEN�����Ŵ���ѯ
	public static final int TRADE_ID_OPENACOUNT			= 1035;		//POS_OPENACOUNT����*/

	public static final int TRADE_ID_LOANSALE			= 1024;        //POS_LOANSALE�������ô�������         
	public static final int TRADE_ID_LOANOPEN			= 1025;       //POS_LOANOPEN�������ô��ͨ 
	public static final int TRADE_ID_LOANQUERY			= 1026;       //POS_LOANOPEN�������ô����ѯ 
	public static final int TRADE_ID_FIXPAYPASS			= 1027;      //POS_SALE�̶����������� 
	public static final int TRADE_ID_CTLSRELOAD			= 1028;       //POS_RELOAD�ǽ��ֹ�Ȧ�� 
	public static final int TRADE_ID_CTLSPREAUTH		= 1029;       //POS_PREAUTH�ǽ�Ԥ��Ȩ 
	//end


	public static final int TRADE_ID_VOID				= 1101;		//���ճ���
	public static final int TRADE_ID_REFUND				= 1102;		//�����˻�
	public static final int TRADE_ID_PREAUTHCEL			= 1103;		//Ԥ��Ȩ����ȡ��
	public static final int TRADE_ID_CHECK_VOID			= 1104;		//֧Ʊ���ѳ���
	public static final int TRADE_ID_PRE_REFUND			= 1105;		//Ԥ���˿�
	public static final int TRADE_ID_PRE_RETURN			= 1106;		//Ԥ���˻�
	public static final int TRADE_ID_STOCK_VOID			= 1107;		//�տ��




	public static final int TRADE_ID_CHK_BLACK				= 2001;		//��ֹ��
	public static final int TRADE_ID_INQUIRY				= 2002;		//����ѯ
	public static final int TRADE_ID_REVIEW					= 2003;		//����־
	public static final int TRADE_ID_LOYALTY_INQUIRY		= 2004;		//���ѻ��ֲ�ѯ
	public static final int TRADE_ID_BONUS_INQUIRY			= 2005;		//��ѯ�������
	public static final int TRADE_ID_SMSK_INQUIRY			= 2006;		//�����տ��ѯ
	public static final int TRADE_ID_PRE_INQUIRY			= 2008;		//��ѯԤ�����	
	public static final int TRADE_ID_INQ_ORI_PREAUTHTRANS	= 2009;		//��ѯԭ��Ȩ����
	public static final int TRADE_ID_INQ_ACCOUNT_DETAIL		= 2010;		//��ѯ�ʻ���ϸ


	public static final int TRADE_ID_INQUERYIC				= 2012;       //�Ӵ������ֽ���� 
	public static final int TRADE_ID_INQUERYCTLS			= 2013;       //�ǽӵ����ֽ���� 
	public static final int TRADE_ID_JICUNJINQUERY		   	= 2014;	   //������ѯ
	public static final int TRADE_ID_KUIYIDAIQUERY		  	= 2015;	   //��ѯ�ɴ����

	//���нű�
	public static final int TRADE_ID_ORDERAGREECON		= 1041;        //ǩ������Э��
	public static final int TRADE_ID_REALTIMECON		= 1042;        //ʵʱ����
	public static final int TRADE_ID_ORDERSALE			= 1044;        //��������
	public static final int TRADE_ID_ORDERPREAUTH		= 1045;        //����Ԥ��Ȩ
	public static final int TRADE_ID_ORDERPREAUTHEND	= 1046;        //����Ԥ��Ȩȷ��
	public static final int TRADE_ID_JICUNJINEXCHANGE	= 1047;		//�����һ�
	public static final int TRADE_ID_KUIYIDAISALE		= 1048;		//���ݴ�
	public static final int TRADE_ID_SALEDISCOUNT		= 1049;		//�����ۿ۽���
	public static final int TRADE_ID_ORDERREFUND		= 1109;		//�����˻�

	//public static final int TRADE_ID_INQ_TMS_PARAMETER	2011;		//��ѯ������Ϣ
	public static final int TRADE_ID_INQ_TMS_PARAMETER	= 10001;		//��ѯ������Ϣ


	public static final int TRADE_ID_AWARD_REDEEM			= 3001;		//�н����Ѷҽ�
	public static final int TRADE_ID_VOID_AWARD_REDEEM		= 3002;		//�������Ѷҽ�
	public static final int TRADE_ID_LOYALTY_REDEEM			= 3003;		//���ѻ��ֶҽ�

	public static final int TRADE_ID_POINT_SALE				= 3004;		//��������

	public static final int TRADE_ID_WASHCARDMENU			= 3005;		//ϴ��ҵ��

	public static final int TRADE_ID_OPENACOUNT				= 3335;		//POS_OPENACOUNT����

	public static final int TRADE_ID_LOGON					= 4001;		//�ն�ǩ��
	public static final int TRADE_ID_SETTLE					= 4002;		//����
	public static final int TRADE_ID_PRINT_DETAIL			= 4003;		//��ӡ������ϸ
	public static final int TRADE_ID_PRINT_REPORT			= 4004;		//��ӡ���׻���
	public static final int TRADE_ID_REPRINT				= 4005;		//��ӡ�ϱ�ƾ��
	public static final int TRADE_ID_LOGOUT					= 4006;		//ǩ��
	public static final int TRADE_ID_EMV_OFFLINE_UPLOAD		= 4007;		//�ѻ���������
	public static final int TRADE_ID_POS_UPESIGN			= 4009;		//�ϴ�����ǩ����


	public static final int TRADE_ID_OPER_CHANGE_PWD		= 4010;		//����Ա�޸�����
	public static final int TRADE_ID_MODIFY_AUTH_PWD		= 4011;		//�޸���Ȩ����
	public static final int TRADE_ID_TMS_DOWNLOAD			= 4012;		//Զ������
	public static final int TRADE_ID_TMS_PARAMS_UPDATE		= 4013;		//TMS��������
	public static final int TRADE_ID_CHANGE_READER			= 4014;		//����������

	public static final int TRADE_ID_RECEIVE_LOYAL_PARAM	= 4021;		//���ջ��ֲ���
	public static final int TRADE_ID_UPDATE_CAPK			= 4022;		//���¹�Կ
	public static final int TRADE_ID_UPLOAD_TC				= 4023;		//TC����
	public static final int TRADE_ID_UPDATE_EMV_PARAM		= 4024;		//����EMV����
	public static final int TRADE_ID_UPDATE_PAYPASS_PARAME	= 4025;		//���·ǽӴ�����
	public static final int TRADE_ID_UPLOAD_SCRIPT_RESULT	= 4026;		//�ű��������


	//public static final int TRADE_ID_SHOW_HIDDEN_MENU		4027;		//��ʾ���ز˵�
	//public static final int TRADE_ID_PRT_LAST_TXN			4028;		//��ӡ�ϱ�оƬ����Ϣ

	public static final int TRADE_ID_CHECK_APPID			= 4027;		//��ѯӦ�ø���
	public static final int TRADE_ID_UPDATE_SCRIPT			= 4028;		//����Ӧ��

	public static final int TRADE_ID_DOWN_JICUNJINPARA		= 4029;		//���»�������

	public static final int TRADE_ID_SMSK_ID_PAP			= 4030;		//�����տ������֤

	public static final int TRADE_ID_REMOVE_UPLOADFAIL		= 4031;		//ɾ���ѻ�����ʧ�ܽ���
	public static final int TRADE_ID_DOWNLOAD_TMS_PARAM		= 4033  ;		//���ز�����Ϣ

	public static final int POS_LOGON = 1		;		//POSǩ��
	public static final int POS_SALE  = 2			;		//1001����
	public static final int POS_AUTHSALE = 3		;		//1002��Ȩ����
	public static final int POS_PARTDISSALE = 4	;		//1049�����ۿ�����
	public static final int POS_BONUSSALE = 5		;		//1005��������
	public static final int POS_POINTSALE = 6		;		//��������
	public static final int POS_VOID = 7		;		//���ճ���
	public static final int POS_QUE = 8			;		//�����
	public static final int POS_TIPAMOUNT = 9		;		//1007С�ѽ���
	public static final int POS_OFFLINE = 10		;		//1004���߽���
	public static final int EMVCAPK_DOWN = 	11	;		//EMV��Կ����
	public static final int EMVPARAM_DOWN = 12		;		//EMV��������
	public static final int EMVPARAM_OFFLINE_DOWN = 13;		//���½Ӵ�ʽ����(�ն˲�֧���ѻ�)
	public static final int EMVPARAM_NOTOUCH_DOWN = 14;		//���·ǽӴ�ʽ����(�ն�֧���ѻ�)
	public static final int JICUNJINPARA_DOWN = 15	;		//������������
	public static final int RECV_JFPRM = 16			;		//���ջ��ֲ���
	public static final int POS_REVERSE = 17		;		//���� 
	public static final int ICC_SCRSEND = 18		;		//EMV�ű�����
	public static final int ICC_TCBATCHUP = 19		;		//EMV TC������
	//ICC_OFFLINEBATCHUP = ;		//EMV�����ֽ�������
	public static final int OFFLINE_UP = 20			;		//�ѻ�����������

	public static final int POS_TERAPPSCR_DOWN = 21	;		//�ն�Ӧ�á��˵��ű�����

	//����Ӧ��
	public static final int POS_PREAUTH = 22		;		//1011Ԥ��Ȩ
	public static final int POS_PREAUTH_VOID = 23   ;		//Ԥ��Ȩ���ճ���
	public static final int POS_AUTH_DONE = 24		;		//1012Ԥ��Ȩȷ��
	public static final int POS_AUTH_VOID = 25		;		//Ԥ��Ȩȷ�ϳ���
	public static final int POS_PREAUTH_ADD = 26	;		//1014׷��Ԥ��Ȩ

	public static final int POS_BONUS_AUTH_DONE = 27;		//1013Ԥ��Ȩ����ȷ��
	public static final int POS_REFUND = 28			;		//�����˻�
	public static final int POS_LOGOFF = 29			;		//ǩ��
	public static final int POS_SETTLE = 30 			;		//����
	public static final int POS_BATCH_UP = 31		;		//����  �����Ϳ�ʼ�봫��
	public static final int POS_BATCHUP_ECC = 32	;		//���������ֽ𿨽��׵�����
	public static final int POS_BATCHUP_TC = 	33	;		//����IC�����׵�TC����
	//��������ֵӦ�ý���
	//ECC_SALE = 			;		//�����ֽ�����
	public static final int POS_CLEARCAR = 34		;		//ϴ����ֵ����
	public static final int ECC_BALANCE = 35		;		//�����ֽ��ѯ���
	public static final int ECC_REFUND = 36			;		//�����ֽ��˻�
	public static final int ECC_LOAD = 37			;		//�����ֽ�ָ���˻�Ȧ��

	public static final int PICC_BALANCE = 38		;		//�ǽӵ����ֽ����
	public static final int POS_PAYPASS = 39		;		//1015��������
	public static final int POS_FIXPAYPASS = 40		;		//1027�̶�����������

	public static final int POS_HFARMER_GC =  41;            //��ũȡ��
	public static final int INST_SALE = 42 			;		//1003���ڸ���
	public static final int INST_SALE_VOID = 43		;		//���ڸ���� 

	public static final int POINTS_QUERY = 	44	;		//���ֲ�ѯ
	public static final int POS_QUE_STOP_PAYMENT = 45;		//���ÿ�ֹ����ѯ
	public static final int POS_HAND_EARMARK = 46	;		//1016�ֹ�Ȧ��
	public static final int POS_SPECIAL = 47		;		//��ɫ����

	public static final int POS_MENULOGON = 48		;		//�ű��˵������ǩ��
	public static final int POS_PRNLAST = 49		;		//��ӡ���һ��
	public static final int POS_PRNDETAIL = 50		;		//��ӡ������ϸ
	public static final int POS_PRNSETTLE = 51		;		//��ӡ���׻���
	public static final int POS_SHOWLOG = 52		;		//����־

	//֧ƱPOS����
	public static final int CHEQUE_SALE = 53		;		//1006֧Ʊ����
	//ת��POS����
	public static final int POS_CARDCARD = 54		;		//1010����ת�˽���
	public static final int FINANCE_TRANSFER_IN = 55;		//1008����ת��
	public static final int FINANCE_TRANSFER_OUT = 56;		//1009����ת��
	//�������ô���
	public static final int LOAN_SALE = 57			;		//�������ô�������
	public static final int LOAN_OPEN = 58			;		//�������ô��ͨ
	public static final int LOAN_QUERY = 59			;		//�������ô����ѯ
	public static final int POS_BUY = 60			;		//1019�չ�����
	public static final int POS_SMSKCX = 61			;		//�����տ��ѯ
	public static final int POS_SMSK = 62			;		//1107�����տ�
	public static final int SMSK_AFFIRM = 63		;		//1020�����տ������֤
	public static final int POS_SALEPOINT_DJ = 64	;		//���ѻ��ֶҽ�
	public static final int POS_OPENACOUNT = 65		;		//����������

	public static final int POS_PREPAY = 66			;		//1017Ԥ������
	public static final int POS_PRESALE = 67		;		//1018Ԥ������
	public static final int POS_PREBALANCE = 68	;		//��ѯԤ�����
	public static final int POS_PRERETURN = 69		;		//Ԥ���˻�
	public static final int POS_PREREFUND = 70		;		//Ԥ���˿�
	public static final int POS_CREDIT_DEBIT = 71	;		//1021���ÿ�����
	public static final int POS_GATHERING = 72		;		//1107�տ�
	public static final int POS_REALTIMECON = 73    ;			//1042        //ʵʱ����
	public static final int OPER_RESPED = 74        ;			//4010����Ա����
	public static final int POS_UPESIGN = 75;				//4009�ϴ�����ǩ����
	public static final int POS_DOWNTERMPARAM = 76;			//�����ն˲���
	public static final int POS_DOWNSTRATEGY = 77;			//������������




	public static final int SALE_POS = 0;	//0��ͨ����POS
	public static final int CHEQUE_POS = 1;		//1֧ƱPOS
	public static final int TRANSFER_POS = 2;	//2ת��POS
	public static final int FINANCE_POS = 3;	//3����POS
	public static final int SMSK_POS = 4;		//4�����տ�
	public static final int STOCK_POS = 5;		//5֤ȯ
	public static final int BUY_POS = 6;		//6�չ�
	public static final int PCLOAN_POS = 7;		//7�������Ѵ���
	public static final int JICUNJIN_POS = 8;   //8�������Ŀ
	public static final int SCRIPT_POS = 9;		//�ű�POS----����


	/*****************���صĴ�����Ϣ����****************************/
	public static final int  E_TRANS_FAIL		= 2;		//����ʧ��     
	public static final int  E_NO_TRANS			= 3;		//�޽���
	public static final int  E_MAKE_PACKET		= 4;		//�����   
	public static final int  E_ERR_CONNECT		= 5;		//����ʧ��
	public static final int  E_SEND_PACKET		= 6;		//��������   
	public static final int  E_RECV_PACKET		= 7;		//�հ�����  
	public static final int  E_RESOLVE_PACKET	= 8;		//�������  
	public static final int  E_REVERSE_FAIL		= 9;		//����ʧ�� 
	public static final int  E_NO_OLD_TRANS	    = 10;		//��ԭʼ���� 
	public static final int  E_TRANS_VOIDED		= 11;		//�����ѱ����� 
	public static final int  E_ERR_SWIPE		= 12;		//ˢ������
	public static final int  E_MEM_ERR			= 13;		//�ļ�����ʧ��
	public static final int  E_PINPAD_KEY		= 14;		//������̻�����Կ����
	public static final int  E_FILE_OPEN		= 15;		//���ļ���
	public static final int  E_FILE_SEEK		= 16;		//��λ�ļ���
	public static final int  E_FILE_READ		= 17;		//���ļ���
	public static final int  E_FILE_WRITE		= 18;		//д�ļ���
	public static final int  E_CHECK_MAC_VALUE	= 19;		//�հ�MACУ���
	public static final int  E_TRANS_CANCEL		= 20;		//���ױ�ȡ��  
	public static final int	 E_MAC				= 21;		//MacУ�����
	public static final int  E_SYS				= 22;		//ϵͳ����
	//public static final int  E_FAIL				23;		//����ʧ��
	public static final int	 E_REVTIMEOUT		= 24;		//���ճ�ʱ
	public static final int	 E_RESPERR			= 25;		//���������
	public static final int	 E_SAMEPACKET		= 26;		//ԭ����������δ����
	/*NO_DISP��Ϊ������ʾ�����ֵ��ע����ʱ����*/
	public static final int  NO_DISP          = 0xBB;


	public static final int  KEYIN_CARD			= 0x01;	//���俨��
	public static final int  SWIPED_CARD		= 0x02;
	public static final int  INSERT_ICCARD		= 0x05;	//IC��
	public static final int  PAYPASS_ICCARD		= 0x07;    //�ǽӿ�������
	public static final int  PAYPASS_MAG		= 0x91;	//�ǽӼ�������



	public static final int  HAVE_INPUT_PIN	  = 0x10;
	public static final int  NOT_INPUT_PIN	  = 0x20;

	
	//PAN���뷽ʽ�궨��
	public static final int  PAN_KEYIN = 0x01;	//���俨��
	public static final int  PAN_MAGCARD =	0x02;	//�ſ�
	public static final int  PAN_ICCARD =	0x05;	//IC��
	public static final int  PAN_PICCQUICK =	0x07;	//PICC��������
	public static final int  PAN_PICCMSD =	0x91;	//PICC�����׽��������
	public static final int  PAN_PICCFULL =	0x98;	//PICC���������������
	
	public static final int PIN_HAVE_INPUT =	0x10;	//������
	public static final int PIN_NOT_INPUT =		0x20;	//������
	
	
	

}
