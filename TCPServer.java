import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class TCPServer
{
	public static void main(String[] inargs)
	{

		Socket wai = null;
		int	port = 8570;
		InputStream in = null;
		OutputStream out = null;
		byte []	inbuf = new byte[100];
		byte [] outbuf = new byte[100];

		try
		{
			ServerSocket serverSocket = new ServerSocket(port);
			
			System.out.println("wait the client message");
			try
			{
				wai = serverSocket.accept();
				while(true) {

						in = wai.getInputStream();
						in.read(inbuf);
						int inbufInt = ByteBuffer.wrap(inbuf).getInt();
						System.out.println("Receive number: " + inbufInt);

					if(inbufInt == 0){

						out = wai.getOutputStream();
						outbuf = ByteBuffer.allocate(100).putInt(0).array();
						out.write(outbuf);
						break;

					}else {

						out = wai.getOutputStream();
						inbufInt -= 1;
						System.out.println("Send numbers: " + inbufInt);
						outbuf = ByteBuffer.allocate(100).putInt(inbufInt).array();
						out.write(outbuf);
					}
				}
				wai.close();
			}
			catch(IOException e)
			{
				System.err.println(e);
			}
			finally
			{
				serverSocket.close();
			}
		}
		catch(IOException e)
		{
			System.err.println(e);
		}
	}
}
