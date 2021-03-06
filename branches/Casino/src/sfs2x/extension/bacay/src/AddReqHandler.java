/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfs2x.extension.bacay.src;

import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;

/**
 *
 * @author ToanNB
 */
public class AddReqHandler extends BaseClientRequestHandler
{
    @Override
    public void handleClientRequest(User sender, ISFSObject params)
    {
        // Get the client parameters
        int n1 = params.getInt("n1");
        int n2 = params.getInt("n2");
         
        // Create a response object
        ISFSObject resObj = SFSObject.newInstance(); 
        resObj.putInt("res", n1 + n2);
         
        // Send it back
        send("add", resObj, sender);
    }
}
