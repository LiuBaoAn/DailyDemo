import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class TestSocket {

	public static void main(String[] args) {
		try {
			BufferedReader reader = null;
			PrintWriter writer = null;
			Socket socket = new Socket("localhost", 12000);
			// socket的输入输出
			writer = new PrintWriter(socket.getOutputStream());
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			// 发送xml数据
			writer.println(UTTaskCTLEUtil.formatMessage("5dc58705-a284-461a-91cd-a1afca190f9a|1|0|自动化执行sb了！", "10111"));
			writer.flush();
			socket.shutdownOutput();
			
			// 获取响应信息
			StringBuffer responseMsg = new StringBuffer();
			String readLine = null;
			while ((readLine = reader.readLine()) != null) {
				responseMsg.append(readLine);
			}
			System.out.println(responseMsg);
			writer.close();
			reader.close();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
