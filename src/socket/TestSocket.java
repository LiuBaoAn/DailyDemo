package socket;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class TestSocket {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		try {
			System.out.println(System.getProperty("line.separator"));
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.1.221:3306/liubcm", "root", "123456");
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select bpmnXml from drill_process where id='062762d6-804c-4851-af5a-3ad6d59574d0' ");
			String bpmnXml = "";
			if(resultSet.next()){//或者while(rs.next()) 
				bpmnXml = resultSet.getString("bpmnXml");
			}
			byte[] xmlByte = bpmnXml.getBytes();
			
			System.out.println("原始bpmnXml的长度: " + bpmnXml.length());
			System.out.println("原始bpmnXml byte的长度: " + xmlByte.length);
			
			System.out.println(bpmnXml);
			
			FileOutputStream fileOutputStream = new FileOutputStream(new File("E:/1.txt"));
			fileOutputStream.write(xmlByte);
			fileOutputStream.flush();
			fileOutputStream.close();
			
			BufferedReader reader = null;
			PrintWriter writer = null;
			Socket socket = new Socket("localhost", 11111);
			// socket的输入输出
			OutputStream outputStream = socket.getOutputStream();
			outputStream.write(xmlByte);
			outputStream.flush();
//			writer = new PrintWriter(outputStream);
//			writer.println(UTTaskCTLEUtil.formatMessage(bpmnXml, "10111"));
//			writer.flush();
//			writer.close();
			socket.shutdownOutput();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
		}
	}

}
