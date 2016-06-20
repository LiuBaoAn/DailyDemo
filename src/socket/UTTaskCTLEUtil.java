package socket;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;


public class UTTaskCTLEUtil {
	public static final String seq = "|";
	public static final String split_seq = "\\|";
	private static final String lineSeparator = System.getProperty("line.separator");

	/**
	 * 构成报文，[长度]|[交易码]|[报文内容]|
	 * @param origMessage 报文内容
	 * @param code 交易码
	 * @return
	 */
	public static String formatMessage(String origMessage, String code) {
		StringBuilder message = new StringBuilder();
		
		// 先构建：|[交易码]|[报文内容]|
		message.append(seq).append(code).append(seq).append(origMessage).append(seq);
		// 已构建的长度
		int length = message.length();
		
		// 在前面加上长度
		message.insert(0, String.format("%09d", length));
		return message.toString();
	}
	
	/**
	 * 解析报文，[长度]|[交易码]|[报文内容]|
	 * <br>
	 * 把响应的三部分取出来，如果为null就没通过响应的规则
	 * 
	 * @param origMessage
	 * @return
	 */
	public static Object[] parseMessage(String origMessage) {
		Object[] objects = new Object[3];
		if (origMessage == null 
				|| origMessage.length() < 16
				|| origMessage.charAt(9) != '|' // 长度码的后面
				|| origMessage.charAt(15) != '|' // 交易码的后面
				|| origMessage.charAt(origMessage.length() - 1) != '|') { //最后一个字符
			// 明显的不合适
			return null;
		}
		
		String[] messages = origMessage.split(split_seq, 3);
		if(messages.length != 3) {
			// 不是
			return null;
		}
		
		String lengthStr = messages[0];
		String codeStr = messages[1];
		String messageStr = messages[2];
		
		if(lengthStr.length() != 9 || codeStr.length() != 5) {
			// 长度的不对
			return null;
		}
		try {// 如果这两个不可以转成int类型，也是错误的，长度值等于后面两个的长度
			int length = Integer.parseInt(lengthStr);
//			if(length != (codeStr.length() + messageStr.length() + 2)) {
//				return null;
//			}
			
			// codeStr里面不含有数字之外的东西
			Integer.parseInt(codeStr);
			objects[0] = length;
			objects[1] = codeStr;
			objects[2] = messageStr.substring(0, messageStr.length() - 1);  //最后一部分还有个 |
		} catch (Exception e) {
			return null;
		}
		
		return objects;
	}

	/**
	 * 构成报文，[长度]|[响应码]|[响应信息]|[报文内容]|
	 * @param code
	 * @param responseMsg
	 * @param content
	 * @return
	 */
	public static String formatResponseMessage(String code, String responseMsg, String content) {
		StringBuilder message = new StringBuilder();
		
		// 先构建：|[响应码]|[响应信息]|[报文内容]|
		message.append(seq).append(code).append(seq).append(responseMsg).append(seq).append(content).append(seq);
		// 已构建的长度
		int length = message.length();
		
		// 在前面加上长度
		message.insert(0, String.format("%09d", length));
		return message.toString();
	}
	/**
	 * 解析响应报文，[长度]|[响应码]|[响应码]|[报文内容]|
	 * <br>
	 * 把响应的各部分取出来，如果为null就没通过响应的规则
	 * 
	 * @param origMessage
	 * @return
	 */
	public static Object[] parseResponseMessage(String origMessage) {
		Object[] objects = new Object[4];
		if (origMessage == null 
				|| origMessage.length() < 12
				|| origMessage.charAt(9) != '|' // 长度码的后面
//				|| origMessage.charAt(15) != '|' // 交易码的后面
				|| origMessage.charAt(origMessage.length() - 1) != '|') { //最后一个字符
			// 明显的不合适
			return null;
		}
		
		String[] messages = origMessage.split(split_seq, 4);
		if(messages.length != 4) {
			// 不是
			return null;
		}
		
		String lengthStr = messages[0];
		String codeStr = messages[1];
		String messageStr = messages[2];
		String contentStr = messages[3];
		
		if(lengthStr.length() != 9 || codeStr.length() != 5) {
			// 长度的不对
			return null;
		}
		try {// 如果这两个不可以转成int类型，也是错误的，长度值等于后面两个的长度
			int length = Integer.parseInt(lengthStr);
//			if(length != (codeStr.length() + messageStr.length() + 2)) {
//				return null;
//			}
			
			// codeStr里面不含有数字之外的东西
			Integer.parseInt(codeStr);
			objects[0] = length;
			objects[1] = codeStr;
			objects[2] = contentStr;
			objects[3] = messageStr.substring(0, messageStr.length() - 1);  //最后一部分还有个 |
		} catch (Exception e) {
			return null;
		}
		
		return objects;
	}
	
	/**
	 * split message
	 * @param origMessage
	 * @return
	 */
	public static String[] splitMessage(String origMessage) {
		return origMessage.split(split_seq);
	}
	
	/**
	 * 从输入中读取信息
	 * @param reader
	 * @return
	 * @throws IOException
	 */
	public static String readMessageFromInput(BufferedReader reader) throws IOException {
		StringBuffer message = new StringBuffer();
		String readLine = null;
		int i = 0;
		while ((readLine = reader.readLine()) != null) {
			// 如果有换行
//			if(message.length() != 0) {
//				message.append(lineSeparator);
//			}
			message.append(readLine);
		}
		return message.toString();
	}
	
	/**
	 * 关闭这些IO对象
	 * @param socket
	 * @param reader
	 * @param writer
	 */
	public static void closeALl(Socket socket, Reader reader, Writer writer) {
		try {
			if(writer != null) {
				writer.close();
				writer = null;
			}
			if(reader != null) {
				reader.close();
				reader = null;
			}
			if(socket != null) {
				socket.close();
				socket = null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// 5个 报文长度是7！
		String message = UTTaskCTLEUtil.formatMessage("报文长度是7！报文长度是7！报文长度是7！报文长度是7！报文长度是7！", "10101");
		System.out.println(message);
		Object[] objects = UTTaskCTLEUtil.parseMessage(message);
		if(objects != null) {
			System.out.println(objects[0]);
			System.out.println(objects[1]);
			System.out.println(objects[2]);
		}
		
	}
}
