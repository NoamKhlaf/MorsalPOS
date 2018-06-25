package sd.code.morsalpos.common;

import com.vanstone.base.interfaces.StructInterface;

/*************************************************************************************
��          ��: ���
��          Ȩ: �����������Ӽ���(����)���޹�˾
��          ��: 
*************************************************************************************/
public class Pos8583PacketStrc implements StructInterface{
	// �������׵Ĵ�����ӡƾ�����Ƿ��¼�������Ƿ��¼������־��������ˮ���Ƿ����ӣ�
	public int iTransNo; 			/* ���״��� */
	public byte[] TransCode = new byte[6]; 		/* ������ */
	public  int action;
	public byte[] SevCode = new byte[2]; 		// ����������� f25
	public byte[] MessCode = new byte[2]; 		// ��Ϣ������ f60.1
	public byte[] NetInfoCode = new byte[3]; 	// ���������Ϣ�� f60.3
	public byte[] SendMsgId = new byte[4]; 		// S(4) ���͵�Msgid
	public byte[] RecvMsgId = new byte[4]; 		// S(4) ���պ�Msgid
	public byte[] ReversedMsgId = new byte[4]; 	// S(4) �˽��׷�������ʱҪ���͵�MSGID
	public byte[] BitmapSend = new byte[8]; 	// 8583 Packet Bit Map S(8)
	public byte[] BitmapReverse = new byte[8]; 	// ������BitMap
	
	

	public Pos8583PacketStrc() {
	}

	public Pos8583PacketStrc(int iTransNo, byte[] transCode, int action,
			byte[] sevCode, byte[] messCode, byte[] netInfoCode,
			byte[] sendMsgId, byte[] recvMsgId, byte[] reversedMsgId,
			byte[] bitmapSend, byte[] bitmapReverse) {
		
		this.iTransNo = iTransNo;
		TransCode = transCode;
		this.action = action;
		SevCode = sevCode;
		MessCode = messCode;
		NetInfoCode = netInfoCode;
		SendMsgId = sendMsgId;
		RecvMsgId = recvMsgId;
		ReversedMsgId = reversedMsgId;
		BitmapSend = bitmapSend;
		BitmapReverse = bitmapReverse;
	}



	/**
	 * @return the iTransNo
	 */
	public int getiTransNo() {
		return iTransNo;
	}

	/**
	 * @param iTransNo the iTransNo to set
	 */
	public void setiTransNo(int iTransNo) {
		this.iTransNo = iTransNo;
	}

	/**
	 * @return the transCode
	 */
	public byte[] getTransCode() {
		return TransCode;
	}

	/**
	 * @param transCode the transCode to set
	 */
	public void setTransCode(byte[] transCode) {
		TransCode = transCode;
	}

	/**
	 * @return the action
	 */
	public int getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(int action) {
		this.action = action;
	}

	/**
	 * @return the sevCode
	 */
	public byte[] getSevCode() {
		return SevCode;
	}

	/**
	 * @param sevCode the sevCode to set
	 */
	public void setSevCode(byte[] sevCode) {
		SevCode = sevCode;
	}

	/**
	 * @return the messCode
	 */
	public byte[] getMessCode() {
		return MessCode;
	}

	/**
	 * @param messCode the messCode to set
	 */
	public void setMessCode(byte[] messCode) {
		MessCode = messCode;
	}

	/**
	 * @return the netInfoCode
	 */
	public byte[] getNetInfoCode() {
		return NetInfoCode;
	}

	/**
	 * @param netInfoCode the netInfoCode to set
	 */
	public void setNetInfoCode(byte[] netInfoCode) {
		NetInfoCode = netInfoCode;
	}

	/**
	 * @return the sendMsgId
	 */
	public byte[] getSendMsgId() {
		return SendMsgId;
	}

	/**
	 * @param sendMsgId the sendMsgId to set
	 */
	public void setSendMsgId(byte[] sendMsgId) {
		SendMsgId = sendMsgId;
	}

	/**
	 * @return the recvMsgId
	 */
	public byte[] getRecvMsgId() {
		return RecvMsgId;
	}

	/**
	 * @param recvMsgId the recvMsgId to set
	 */
	public void setRecvMsgId(byte[] recvMsgId) {
		RecvMsgId = recvMsgId;
	}

	/**
	 * @return the reversedMsgId
	 */
	public byte[] getReversedMsgId() {
		return ReversedMsgId;
	}

	/**
	 * @param reversedMsgId the reversedMsgId to set
	 */
	public void setReversedMsgId(byte[] reversedMsgId) {
		ReversedMsgId = reversedMsgId;
	}

	/**
	 * @return the bitmapSend
	 */
	public byte[] getBitmapSend() {
		return BitmapSend;
	}

	/**
	 * @param bitmapSend the bitmapSend to set
	 */
	public void setBitmapSend(byte[] bitmapSend) {
		BitmapSend = bitmapSend;
	}

	/**
	 * @return the bitmapReverse
	 */
	public byte[] getBitmapReverse() {
		return BitmapReverse;
	}

	/**
	 * @param bitmapReverse the bitmapReverse to set
	 */
	public void setBitmapReverse(byte[] bitmapReverse) {
		BitmapReverse = bitmapReverse;
	}

	public byte[] toBytes() {
		byte[] msgByte=new byte[size()];
		byte[]tmp=null;
		int len=0;
		tmp=new byte[4];
		tmp=com.vanstone.utils.CommonConvert.intToBytes(iTransNo);
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=4;
		tmp=new byte[TransCode.length];
		tmp=TransCode;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[4];
		tmp=com.vanstone.utils.CommonConvert.intToBytes(action);
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=4;
		tmp=new byte[SevCode.length];
		tmp=SevCode;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[MessCode.length];
		tmp=MessCode;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[NetInfoCode.length];
		tmp=NetInfoCode;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[SendMsgId.length];
		tmp=SendMsgId;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[RecvMsgId.length];
		tmp=RecvMsgId;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[ReversedMsgId.length];
		tmp=ReversedMsgId;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[BitmapSend.length];
		tmp=BitmapSend;
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;
		tmp=new byte[BitmapReverse.length];
		tmp=BitmapReverse;
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
		len+=TransCode.length;
		len+=4;
		len+=SevCode.length;
		len+=MessCode.length;
		len+=NetInfoCode.length;
		len+=SendMsgId.length;
		len+=RecvMsgId.length;
		len+=ReversedMsgId.length;
		len+=BitmapSend.length;
		len+=BitmapReverse.length;
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
		iTransNo=com.vanstone.utils.CommonConvert.bytesToInt(tmp);
		len+=4;
		tmp=new byte[TransCode.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		TransCode=tmp;
		len+=tmp.length;
		tmp=new byte[4];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		action=com.vanstone.utils.CommonConvert.bytesToInt(tmp);
		len+=4;
		tmp=new byte[SevCode.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		SevCode=tmp;
		len+=tmp.length;
		tmp=new byte[MessCode.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		MessCode=tmp;
		len+=tmp.length;
		tmp=new byte[NetInfoCode.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		NetInfoCode=tmp;
		len+=tmp.length;
		tmp=new byte[SendMsgId.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		SendMsgId=tmp;
		len+=tmp.length;
		tmp=new byte[RecvMsgId.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		RecvMsgId=tmp;
		len+=tmp.length;
		tmp=new byte[ReversedMsgId.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		ReversedMsgId=tmp;
		len+=tmp.length;
		tmp=new byte[BitmapSend.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		BitmapSend=tmp;
		len+=tmp.length;
		tmp=new byte[BitmapReverse.length];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		BitmapReverse=tmp;
		len+=tmp.length;
	}

}
