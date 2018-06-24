package sd.code.morsalpos.common;

/*************************************************************************************
��          ��: ���
��          Ȩ: �����������Ӽ���(����)���޹�˾
��          ��: 
*************************************************************************************/
public  class CardConstants {
	
	public final static int MASK_INCARDNO_HANDIN = 0x01;	//���俨��
	public final static int MASK_INCARDNO_MAGCARD =	0x02;
	public final static int MASK_INCARDNO_ICC = 0x04;
	public final static int MASK_INCARDNO_PICC = 0x08;		//���ǽӿ�
	public final static int MASK_INCARDNO_USECARD= MASK_INCARDNO_MAGCARD|MASK_INCARDNO_ICC|MASK_INCARDNO_PICC;
	public final static int MASK_INCARDNO_ALL = MASK_INCARDNO_HANDIN|MASK_INCARDNO_USECARD;
//	public final static int MASK_INCARDNO_KEYAGAIN = 0x10;	//���俨���������ȷ��
//	public final static int MASK_INCARDNO_PICCMSD = 0x20;	//�ǽӿ�����MSD��ʽ
	public final static int CARD_PROCNULL = 0; //����Ҫ����(оƬ��Ϊ��ҵ��)
	public final static int CARD_EMVFULL = 0x01;	//�忨��ʱ�������������
	public final static int CARD_EMVSIMPLE = 0x02;	//�忨��ʱ����߼�������
	public final static int CARD_EMVFULLNOCASH = 0x04;	//�忨���߲��ǵ����ֽ����������
	public final static int CARD_EMVFULLCASH = 0x08;	//�忨���ߵ����ֽ����������
	public final static int CARD_EMVFULLCASHSALE=	0x10; //�忨���ߵ����ֽ�����������������ѻ��ܾ�
	public final static int CARD_QPASS = 0x20; //qpboc
	public final static int CARD_QPASSONLINE = 0x40; //qpboc online
	public final static int CARD_QPASSOFFLINE = 0x80; //qpboc offline
	public final static int CARD_HANDINCONFIRM=	0x100 ;//�ֹ�����Ҫ���ٴ�ȷ��
	
	public final static int CARD_TIP_COMMON = 0;  //��ͨ����ʾ
	public final static int CARD_TIP_OUT = 1; //ת����
	public final static int CARD_TIP_IN = 2; //ת�뿨
	public final static int CARD_TIP_FINANCE = 3; //����
	public final static int CARD_TIP_WITH_AMT = 0x80; //�������ʾ����ͨ��ʾ
	
	
	public final static int LED_BLUE =	0X01;//led����
	public final static int LED_YELLOW =	0X02;//led�Ƶ�
	public final static int LED_GREEN =	0X04;//led�̵�
	public final static int LED_RED	=	0X08;//led���
	public final static int LED_ALL	=	0X0F;//�ĸ���
}
