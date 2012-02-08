/*
 * Master-Level project:                                                           
 *       Demonstrator of composition of heteregenous services                      
 *       with SCA                                                                 
 *                                                                                 
 *  Copyright (C) 2012                                                            
 *  Authors: Mohamed Habib ESSOUSSI                                                
 *           Mohamed Said MOSLI BOUSKIAA                                           
 *                                                                                 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of 
 * this software and associated documentation files (the "Software"), to deal in    
 * the Software without restriction, including without limitation the rights to     
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies    
 * of the Software, and to permit persons to whom the Software is furnished to do   
 * so, subject to the following conditions:                                         
 *                                                                                  
 * The above copyright notice and this permission notice shall be included in all   
 * copies or substantial portions of the Software.                                  
 *                                                                                  
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR       
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,         
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE      
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER           
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,    
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE    
 * SOFTWARE.                                                                        
 *                                                                                  
 *  TELECOM SudParis | Oct 2011-Jan 2012                                            
 */   
 
package sca.client.launcher;
 
import java.io.IOException;

import javax.naming.Context;

import org.apache.tuscany.sca.host.embedded.SCADomain;

/**
 * This class launches the Chat Client.
 * @author Mohamed Habib ESSOUSSI
 *
 */
public final class ChatLauncher {

	/**
	 * Constructor.
	 */
	private ChatLauncher() { }
	
	/**
	 * Launcher of the client.
	 * @param args No need
	 */
	public static void main(final String[] args) {
		
		System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
				"org.apache.openejb.client.RemoteInitialContextFactory");
        System.setProperty(Context.PROVIDER_URL, "ejbd://localhost:4201");
        
		SCADomain scaDomain = SCADomain.newInstance("client.composite");

		Runnable use = scaDomain.getService(Runnable.class,
				"ClientComponent/Runnable");
		use.run();

		try {
			System.out
					.println("client server started (press enter to shutdown)");
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		scaDomain.close();
		System.out.println("client server stopped");
	}

}
