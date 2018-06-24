/**
 * 
 */
package sd.code.morsalpos.common;

/*************************************************************************************
 * �� ��: ��� �� Ȩ: �����������Ӽ���(����)���޹�˾ �� ��:
 *************************************************************************************/

public class EmvCommonConstants {

	 public static final boolean BANK_ICBC = true; //��ǰ�ǹ��е�Ӧ��
	public static final int MAX_APPNAME_LEN = 33; 				// AppName����󳤶�
	public static final String CAPK_FILE = "Emv_Capk.dat";
	public static final String APP_FILE = "Emv_AppLst.dat";
	public static final String CLAPP_FILE = "Emv_CLAppLst.dat";
	public static final String TERM_AIDLIST = "termaid.lst";
	public static final String ICC_SCRIPT_FILE = "iccscript";
	public static final String FILE_TRADE_ATT = "trade.att";
	public static final int DE55_MUST_SET = 1; 					// �������
	public static final int DE55_OPT_SET = 2; 					// ��ѡ�����
	public static final int DE55_COND_SET = 3; 					// ������������
	public static final int TAG_NULL_1 = 0x00; 					// null tag
	public static final int TAG_NULL_2 = 0xFF; 					// null tag
	public static final int E_NEED_FALLBACK = 91; 				// 51 ��ҪFALLBACK
	public static final int ONLINE_REQUEST = 55; 				// IC����Ҫ������
	// public static final _MIN(a, b) ( (a)<(b) ? (a) : (b) )
	// public static final _MAX(a, b) ( (a)>(b) ? (a) : (b) )
	public static final int TAGMASK_CLASS = 0xC0; 				// tag mask of tag class
	public static final int TAGMASK_CONSTRUCTED = 0x20; 		// tag mask of
	public static final int TAGMASK_FIRSTBYTE = 0x1F; 			// tag mask of first byte
	public static final int TAGMASK_NEXTBYTE = 0x80; 			// tag mask of next byte
	public static final int LENMASK_NEXTBYTE = 0x80; 			// length mask
	public static final int LENMASK_LENBYTES = 0x7F; 			// mask of bytes of lenght
	public static final int ICC_EMV = 0; 						// emv����
	public static final int PARAM_OPEN = 1;
	public static final int PARAM_CLOSE = 0;
	public static final int ENGLISH = 0;
	public static final int CHINESE = 1;
	public static final int TYPE_CASH = 0x01; 					// ��������(�ֽ�)
	public static final int TYPE_GOODS = 0x02; 					// ��������(����)
	public static final int TYPE_SERVICE = 0x04; 				// ��������(����)
	public static final int TYPE_CASHBACK = 0x08; 				// ��������(����)
	public static final int TYPE_INQUIRY = 0x10; 				// ��������(��ѯ)
	public static final int TYPE_PAYMENT = 0x20; 				// ��������(֧��)
	public static final int TYPE_ADMINISTRATIVE = 0x40; 		// ��������(����)
	public static final int TYPE_TRANSFER = 0x80; 				// ��������(ת��)
	public static final int MAX_APP_NUM = 32; 					// Ӧ���б����ɴ洢��Ӧ����
	public static final int MAX_CAPK_NUM = 64; 					// ��֤��Կ�����ɴ洢�Ĺ�Կ��
	public static final int MAX_CAPKREVOKE_NUM = 96; 			// ��֤��Կ�����б��������32*3
	public static final int PART_MATCH = 0x00; 					// ASI(����ƥ��)
	public static final int FULL_MATCH = 0x01; 					// ASI(��ȫƥ��)
	public static final int EMV_GET_POSENTRYMODE = 0;
	public static final int EMV_GET_BATCHCAPTUREINFO = 1;
	public static final int EMV_GET_ADVICESUPPORTINFO = 2;

	// ���״����������붨��
	public static final int EMV_OK = (0); 						// �ɹ�
	public static final int ERR_EMVRSP = (-2); 					// ���������
	public static final int ERR_APPBLOCK = (-3); 				// Ӧ������
	public static final int ERR_NOAPP = (-4); 					// ��Ƭ��û��EMVӦ��
	public static final int ERR_USERCANCEL = (-5); 				// �û�ȡ����ǰ��������
	public static final int ERR_TIMEOUT = (-6); 				// �û�������ʱ
	public static final int ERR_EMVDATA = (-7); 				// ��Ƭ���ݴ���
	public static final int ERR_NOTACCEPT = (-8); 				// ���ײ�����
	public static final int ERR_EMVDENIAL = (-9); 				// ���ױ��ܾ�
	public static final int ERR_KEYEXP = (-10); 					// ��Կ����
	public static final int ERR_NOPINPAD = (-11); 				// û��������̻���̲�����
	public static final int ERR_NOPIN = (-12); 					// û��������û���������������
	public static final int ERR_CAPKCHECKSUM = (-13); 			// ��֤������ԿУ��ʹ���
	public static final int ERR_NOTFOUND = (-14); 				// û���ҵ�ָ�������ݻ�Ԫ��
	public static final int ERR_NODATA = (-15); 				// ָ��������Ԫ��û������
	public static final int ERR_OVERFLOW = (-16); 				// �ڴ����
	public static final int ERR_NOTRANSLOG = (-17); 			// �޽�����־
	public static final int ERR_NORECORD = (-18); 				// �޼�¼
	public static final int ERR_NOLOGITEM = (-19); 				// Ŀ־��Ŀ����
	public static final int ERR_ICCRESET = (-20); 				// IC����λʧ��
	public static final int ERR_ICCCMD = (-21); 				// IC����ʧ��
	public static final int ERR_ICCBLOCK = (-22); 				// IC������
	public static final int ERR_ICCNORECORD = (-23); 			// IC���޼�¼
	public static final int ERR_GENAC1_6985 = (-24); 			// GEN AC�����6985
	public static final int ERR_USECONTACT = (-25); 			// �ǽ�ʧ�ܣ����ýӴ�����
	public static final int ERR_APPEXP = (-26); 				// qPBOC��Ӧ�ù���
	public static final int ERR_BLACKLIST = (-27); 				// qPBOC��������
	public static final int ERR_GPORSP = (-28); 				// err from GPO
	// public static final ERR_TRANSEXCEEDED =(-29); 			//�ǽӽ��׳���
	public static final int ERR_TRANSEXCEEDED		=(-30);	//�ǽӽ��׳���
	public static final int ERR_QPBOCFDDAFAIL		=(-31);
	///////////////140910����
	public static final int ERR_UNSUPPORTED			=(-32);	//��֧��
	public static final int ERR_NOFILE				=(-33);	//û���ļ�
	public static final int ERR_DENIALFORECC		=(-34);	//�ܾ��������ֽ𿨵���������
	public static final int ERR_RMAC				=(-35);	//��ȫ����macУ���
	public static final int ERR_RFNOTAG9F66			=(-36);	//�ǽӵ�PDOL��û��9f66
	public static final int ERR_MACCHECK			=(-37);
	public static final int ERR_MEMERR				=(-38);	//6581 �ڴ�ʧ��
	public static final int ERR_LENERR				=(-39);	//6700 ���ȴ���
	public static final int ERR_CONDNOTSATISFIED	=(-40);	//6985 ����������
	public static final int ERR_CMDNOEXIST			=(-41);	//6d00 �������
	public static final int ERR_CMDNOTSUPPORT		=(-42);	// 6e00 �������Ͳ�֧��
	public static final int ERR_TCUNAVAILABLE		=(-43);	//9406 ����TC������
	public static final int ERR_READ_LAST_REC		=-44;		//�����һ����¼ʧ��
	public static final int ERR_ECBALANCE_SHORT		=-45;		//�������ֽ�����
	////////////////


	
	
	
	public static final int REFER_APPROVE = 0x01; 				// �ο�������(ѡ����׼)
	public static final int REFER_DENIAL = 0x02; 				// �ο�������(ѡ��ܾ�)
	public static final int ONLINE_APPROVE = 0x00; 				// ����������(������׼)
	public static final int ONLINE_FAILED = 0x01; 				// ����������(����ʧ��)
	public static final int ONLINE_REFER = 0x02; 				// ����������(�����ο�)
	public static final int ONLINE_DENIAL = 0x03; 				// ����������(�����ܾ�)
	public static final int ONLINE_ABORT = 0x04; 				// ����PBOC(������ֹ)
	public static final int ONLINE_REFERANDFAIL = 0x05; 		// �����ܾ��������ο�
	public static final int PATH_PBOC = 0x00; 					// Ӧ��·��:��׼PBOC
	public static final int PATH_QPBOC = 0x01; 					// Ӧ��·��:qPBOC
	public static final int PATH_MSD = 0x02; 					// Ӧ��·��:MSD
	public static final int PATH_ECash = 0x03; 					// Ӧ��·��:�����ֽ�

}
