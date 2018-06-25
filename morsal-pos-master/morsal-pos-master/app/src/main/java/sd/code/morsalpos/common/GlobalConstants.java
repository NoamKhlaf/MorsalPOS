package sd.code.morsalpos.common;

import com.vanstone.trans.api.struct.EmvAppList;
import com.vanstone.trans.api.struct.EmvCapk;
import com.vanstone.trans.api.struct.EmvParam;



public class GlobalConstants {

	 public static int isPinPad=1;  //�������
	public static String CurAppDir = "";
	public static  PosCom PosCom = new PosCom();
	public static  byte[] BitMap = new byte[17];
	public static  int g_EmvFullOrSim; //����EMV�������̻��Ǽ������� 0:�������� 1: ��������
	public static  int g_SwipedFlag; //���������Ƿ��⵽ˢ������IC�� 0:û�� 1:ˢ�� 2:��IC��

	public static  EmvParam stEmvParam = new EmvParam();
	public static EmvCapk gltCurCapk = new EmvCapk(); // ��ǰ������CAPKԪ��
	public static EmvAppList gltCurApp = new EmvAppList(); // ��ǰ������APP����
   }
