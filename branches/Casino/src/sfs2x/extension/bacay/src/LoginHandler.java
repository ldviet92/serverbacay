/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfs2x.extension.bacay.src;

/**
 *
 * @author ToanNB
 */
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import com.smartfoxserver.bitswarm.sessions.ISession;
import com.smartfoxserver.v2.entities.data.SFSArray;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.variables.UserVariable;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.smartfoxserver.v2.exceptions.SFSLoginException;
import com.smartfoxserver.v2.exceptions.SFSErrorData;
import com.smartfoxserver.v2.exceptions.SFSErrorCode;
import com.smartfoxserver.v2.extensions.BaseServerEventHandler;
import com.smartfoxserver.v2.extensions.ExtensionLogLevel;
import com.smartfoxserver.v2.util.MD5;

public class LoginHandler extends BaseServerEventHandler {

    @Override
    public void handleServerEvent(ISFSEvent event) throws SFSException {
        String username = (String) event.getParameter(SFSEventParam.LOGIN_NAME);
        String password = (String) event.getParameter(SFSEventParam.LOGIN_PASSWORD);
        ISFSObject outData = (ISFSObject) event.getParameter(SFSEventParam.LOGIN_IN_DATA);
        ISession session = (ISession) event.getParameter(SFSEventParam.SESSION);
        try {
//            String accessToken = outData.getUtfString(Const1.ACCESS_TOKEN);
//            String sessionId = outData.getUtfString(Const1.SESSION_ID);
//            Integer userId = outData.getInt(Const1.USER_ID);
            String accessToken = outData.getUtfString("access_token");
            String sessionId = outData.getUtfString("session_id");
            String userId = outData.getUtfString("user_id");
            
            String compare = userId + Const1.SECRET_KEY + sessionId;
            compare = MD5.getInstance().getHash(compare);
            trace("user_id: "+userId+"  session: "+sessionId);
            trace(" compare: "+compare+" access: "+ accessToken);
            boolean check = compare.equals(accessToken);
            trace("check: "+check);
            if (check == true) {
                //get a connection to the database
                Connection conn = getParentExtension().getParentZone().getDBManager().getConnection();

                //This will strip potential SQL injections
                PreparedStatement sql = conn.prepareStatement("SELECT id,password FROM user WHERE username = ?");
                sql.setString(1, username);

                // Obtain ResultSet
                ResultSet result = sql.executeQuery();

                //Put the result into an SFSobject array
                SFSArray row = SFSArray.newFromResultSet(result);

                //make sure there is a password before you try to use the checkSecurePassword function
                if (password.equals("")) {
                    SFSErrorData data = new SFSErrorData(SFSErrorCode.LOGIN_BAD_PASSWORD);
                    data.addParameter(username);
                    throw new SFSLoginException("You must enter a password.", data);
                }

                //SFS always encrypts passwords before sending them so you need to decrypt the password
                //received from the database and compare that to what they entered in flash
                if (!getApi().checkSecurePassword(session, row.getSFSObject(0).getUtfString("password"), password.toLowerCase())) {
                    
                    SFSErrorData data = new SFSErrorData(SFSErrorCode.LOGIN_BAD_PASSWORD);

                    data.addParameter(username);

                    throw new SFSLoginException("Login failed for user: " + username, data);
                }

                //this was in one of the SFS examples so I left it in there for testing purposes
                if (username.equals("Gonzo") || username.equals("Kermit")) {

                    // Create the error code to send to the client
                    SFSErrorData errData = new SFSErrorData(SFSErrorCode.LOGIN_BAD_USERNAME);
                    errData.addParameter(username);

                    // Fire a Login exception
                    throw new SFSLoginException("Gonzo and Kermit are not allowed in this Zone!", errData);
                }
                                
                //make sure you close the database connection when you're done with it, especially if you've
                //set a low number of maximum connections
                conn.close();

                //at this point you could trigger an joinRoom request if you wanted to, otherwise
                //this will return success to your LOGIN event listener
                trace("Login successful, joining room!");
                // create object people
//                String name = username;
//                Float money = 1.0f;
//                Person name1  = new Person(userId, username, money);
                    
                
            } else {
                SFSErrorData data = new SFSErrorData(SFSErrorCode.LOGIN_BAD_PASSWORD);

                     throw new SFSLoginException("login failed"+ data);
            }

        } catch (SQLException e) {
            trace(ExtensionLogLevel.WARN, " SQL Failed: " + e.toString());
        }
    }
}
