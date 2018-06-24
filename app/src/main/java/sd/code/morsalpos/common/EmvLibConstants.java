/**
 * 
 */
package sd.code.morsalpos.common;


/*************************************************************************************
��          ��: ���
��          Ȩ: �����������Ӽ���(����)���޹�˾
��          ��: 
*************************************************************************************/

public class EmvLibConstants {
//	#ifdef __cplusplus
//	extern "C"
//	{
//	#endif
	
	public final static int ENGLISH = 0;
	public final static int CHINESE = 1;

	public final static int TYPE_CASH	= 0x01;				//��������(�ֽ�)
	public final static int TYPE_GOODS = 0x02;				//��������(����)
	public final static int TYPE_SERVICE = 0x04;			//��������(����)
	public final static int TYPE_CASHBACK	= 0x08;			//��������(����)
	public final static int TYPE_INQUIRY = 0x10;			//��������(��ѯ)
	public final static int TYPE_PAYMENT = 0x20;			//��������(֧��)
	public final static int TYPE_ADMINISTRATIVE = 0x40;		//��������(����)
	public final static int TYPE_TRANSFER	= 0x80;			//��������(ת��)

	public final static int MAX_APP_NUM = 32;				//Ӧ���б����ɴ洢��Ӧ����
	public final static int MAX_CAPK_NUM = 64;				//��֤��Կ�����ɴ洢�Ĺ�Կ��
	public final static int MAX_CAPKREVOKE_NUM = 96;		//��֤��Կ�����б��������32*3

	public final static int PART_MATCH = 0x00;				//ASI(����ƥ��)
	public final static int FULL_MATCH = 0x01;				//ASI(��ȫƥ��)

	public final static int EMV_GET_POSENTRYMODE = 0; 
	public final static int EMV_GET_BATCHCAPTUREINFO = 1; 
	public final static int EMV_GET_ADVICESUPPORTINFO = 2; 

	//���״����������붨��
	public final static int EMV_OK = 0;     				//�ɹ�  
	public final static int ERR_EMVRSP = (-1);      		//���������
	public final static int ERR_APPBLOCK = (-2);      		//Ӧ������
	public final static int ERR_NOAPP = (-3);      			//��Ƭ��û��EMVӦ��
	public final static int ERR_USERCANCEL = (-4);      	//�û�ȡ����ǰ��������
	public final static int ERR_TIMEOUT = (-5);      		//�û�������ʱ
	public final static int ERR_EMVDATA = (-6);      		//��Ƭ���ݴ���
	public final static int ERR_NOTACCEPT = (-7);      		//���ײ�����
	public final static int ERR_EMVDENIAL = (-8);      		//���ױ��ܾ�
	public final static int ERR_KEYEXP = (-9);      		//��Կ����
	public final static int ERR_NOPINPAD = (-10);     		//û��������̻���̲�����
	public final static int ERR_NOPIN = (-11);     			//û��������û���������������
	public final static int ERR_CAPKCHECKSUM = (-12);     	//��֤������ԿУ��ʹ���
	public final static int ERR_NOTFOUND = (-13);     		//û���ҵ�ָ�������ݻ�Ԫ��
	public final static int ERR_NODATA = (-14);     		//ָ��������Ԫ��û������
	public final static int ERR_OVERFLOW = (-15);     		//�ڴ����
	public final static int ERR_NOTRANSLOG = (-16);     	//�޽�����־
	public final static int ERR_NORECORD = (-17);     		//�޼�¼
	public final static int ERR_NOLOGITEM = (-18);     		//Ŀ־��Ŀ����
	public final static int ERR_ICCRESET = (-19);     		//IC����λʧ��
	public final static int ERR_ICCCMD = (-20);     		//IC����ʧ��
	public final static int ERR_ICCBLOCK = (-21);     		//IC������ 
	public final static int ERR_ICCNORECORD = (-22);     	//IC���޼�¼
	public final static int ERR_GENAC1_6985 = (-23);     	//GEN AC�����6985
	public final static int ERR_USECONTACT = (-24);     	//�ǽ�ʧ�ܣ����ýӴ�����
	public final static int ERR_APPEXP = (-25);    	 		//qPBOC��Ӧ�ù���
	public final static int ERR_BLACKLIST = (-26);     		//qPBOC��������
	public final static int ERR_GPORSP = (-27);     		//err from GPO
	//public final static int ERR_TRANSEXCEEDED  (-28)    	//�ǽӽ��׳���
	public final static int ERR_TRANSEXCEEDED = (-28);    	//�ǽӽ��׳���
	public final static int ERR_QPBOCFDDAFAIL = (-30);    	//�ǽ�qPBOC fDDAʧ��

	public final static int REFER_APPROVE	= 0x01;			//�ο�������(ѡ����׼)
	public final static int REFER_DENIAL = 0x02;			//�ο�������(ѡ��ܾ�)
	public final static int ONLINE_APPROVE = 0x00;			//����������(������׼)     
	public final static int ONLINE_FAILED	= 0x01;			//����������(����ʧ��) 
	public final static int ONLINE_REFER = 0x02;			//����������(�����ο�)
	public final static int ONLINE_DENIAL	= 0x03;			//����������(�����ܾ�)
	public final static int ONLINE_ABORT = 0x04;			//����PBOC(������ֹ)
	public final static int ONLINE_REFERANDFAIL = 0x05;		//�����ܾ��������ο�
	
	public final static int PATH_PBOC = 0x00;				//Ӧ��·������׼PBOC
	public final static int PATH_QPBOC = 0x01;				//Ӧ��·����qPBOC
	public final static int PATH_MSD = 0x02;				//Ӧ��·����MSD
	public final static int PATH_ECash = 0x03;				//Ӧ��·���������ֽ�
	
//	public static int EmvLib_GetTLV(int Tag, byte[] DataOut, byte[] OutLen)
//	{
//		return 0;
//	}
//	
//	public static int EmvLib_SetTLV(int Tag, byte[] DataOut, int OutLen)
//	{
//		return 0;
//	}
//	
//
//	
//	public static void EmvLib_GetParam(EmvParam tParam)
//	{
//		
//	}
//	
//	public static void EmvLib_SetParam(EmvParam tParam)
//	{
//		
//	}
//
//	public static int EmvLib_Init()
//	{
//		return 0;
//	}
//	
//	public static int EmvLib_AppSel(int Slot, long TransNo)
//	{
//		return 0;
//	}
//	
//	public static int EmvLib_ReadAppData()
//	{
//		return 0;
//	}
//	
//	public static int EmvLib_CardAuth()
//	{
//		return 0;
//	}
//	
//	public static int  EmvLib_GetBalance(byte[] BcdBalance)
//	{
//		return 0;
//	}
//	
//	public static int EmvLib_GetScriptResult(byte[] Result, byte[] RetLen)
//	{
//		return 0;
//	}
//	
//	public static int EmvLib_SetIcCardType(int Mode)
//	{
//		return 0;
//	}
	
//	#ifdef __cplusplus
//	}
//	#endif
}
