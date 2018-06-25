/**
 * 
 */
package sd.code.morsalpos.common;


/*************************************************************************************
��          ��: ���
��          Ȩ: �����������Ӽ���(����)���޹�˾
��          ��: 
*************************************************************************************/
public final class DefConstants {
	public final static int MENU_FOOT_PAY = 100000;//����
	public final static int MENU_FOOT_OTHERPAY = 200000;//��������
	public final static int MENU_FOOT_MANAGE = 300000;//���� 
	public final static int MENU_FOOT_BRANCH = 400000;//������ɫ
	public final static int MENU_FOOT_OTHER = 500000;//����
	public final static int TELNUMLEN = 20; 			// �������뻺��ĳ���
	public final static int KEY_BUF_SIZE = 128; 			// �������뻺��ĳ���
	public final static int ITWELLSYSINFOLEN = 40;
	public final static int SWIPEDANDINICEVENT = 0xFF; 		// ���������дſ��Ͳ���IC�Ĳ���
	public final static int XGDOK = 0X01;
//	public final static int BEEPERROR = R.raw.error; 					// �������쳣��
//	public final static int BEEPNORMAL = R.raw.success; 				// ������������
	public final static int DECRYP = 0; 					// ����
	public final static int ENCRYP = 1; 					// ����
	// pos�Ĺ���״̬�����������͵Ĵ���
	public final static int WORK_STATUS = 0; 				// ��������״̬������״̬
	public final static int UPLOGRMB = 1; 					/* ����������ʱѡ��ͬ�Ľ��׼�¼ */
	public final static int UPLOGFRN = 2;
	public final static int UPLOGALL = 3;
	public final static int UPLOGDETAILTYPE1 = 4; 			// ����ƽ��̵ĵ�������
	public final static int UPLOGDETAILTYPE2 = 4; 			// ���˲�ƽ��̵ĵ�������
	public final static int PIN_PED = 0x00; 				// ����
	public final static int PIN_PP = 0x01; 					// ����
	public final static int TIMEOUT = -2; 					// ��ʱ
	// ���ڶ���
	public final static int COM_PAD_NO = 0x00;      //rs232
	public final static int PINPADCOM = 0x01;			//pinpad

	public final static int MAX_LCD_LINENUM = 5; 			// LCD����ʾ���������
	public final static int MAX_LCDWIDTH = 30;
	public final static int MAX_MENUNAME_LEN = 14; 			// �˵�����󳤶�
	public final static int SEARCH_DIRECTION_NEXT = 1;
	public final static int SEARCH_DIRECTION_PREV = 0;
	public final static int MCARDNO_MAX_LEN = 19; 			// ���ŵ���󳤶�
	public final static int MAX_AMT_LEN = 12; 				// ������
	public final static int MAX_PWD_LEN = 8; 				// ������볤��
	public final static int BASE_YEAR = 2000; 				// ��׼��
	public final static int INPUT_TIMEOUT_VAL = 30; 		// 30s ���볬ʱֵ
	public final static int INPUT_INFINITE = -1;
	public final static int INPUT_TIMEOUT = -2; 			// ���볬ʱ����
	public final static int SCRIPT_SUCCESS = 0; 			// �ű����гɹ�
	public final static int SCRIPT_CANCEL = -1; 			// �ű���ȡ��
	public final static int SCRIPT_ERROR = -3; 				// �ű���ʽ����
	public final static int SCRIPT_NONEXIST = -4;
	public final static int SCRIPT_CHECKERR = -5;
	public final static int SCRIPT_MEMLACK = -6;
	public final static int LAST_REC_LOG = 0xffffffff;
	public final static String RECORDLOG =  "record"; 		// ��־�ļ�
	public final static String DUPFILE =  "dup_file"; 		// �����ļ�
	public final static String FILE_OPER_LOG = "oper.log"; // ����Ա�ļ�
	public final static String GOODSMENU_NAME = "ICBC_GDM"; //�������� �ű�ָ���в�������ZZZ��ʱ����ʾ��Ʒ�˵����ؽ��׷��ص�����
	public final static String SIGNFILE	="signpadrec";		//ǩ����¼�ļ�
  

	public static int RECORDNORMAL = 0x00;						 					//��������
	public static int RECORDVOID = 0x01; 											//�������ѳ���
	public static int RECORDHAVEUP = 0x02; 											//����������
	public static int RECORDUPFAIL	=	0x03;	//�ѻ�����3������ʧ��

	
	
	// EMV������ʼ�������Ի��� = true
	public static boolean EMVDEBUG = true;  
	public static int LOG_SIZE = new LogStrc().size();
	
}
