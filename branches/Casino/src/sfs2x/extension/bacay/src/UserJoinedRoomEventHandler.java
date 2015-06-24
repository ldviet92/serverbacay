/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfs2x.extension.bacay.src;

/**
 *
 * @author MT405
 */
import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.smartfoxserver.v2.extensions.BaseServerEventHandler;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.exceptions.SFSErrorCode;
import com.smartfoxserver.v2.exceptions.SFSErrorData;
import com.smartfoxserver.v2.exceptions.SFSJoinRoomException;

public class UserJoinedRoomEventHandler extends BaseServerEventHandler {

	@Override
	public void handleServerEvent(ISFSEvent event) throws SFSException {
		
		Room room = (Room) event.getParameter(SFSEventParam.ROOM);	

		// Get user that joined
		User user = (User) event.getParameter(SFSEventParam.USER);

		trace("icard: user " + user.getName() + " entered the game room '" + room.getName() + "' - game room id: " + room.getId());
//                ISFSObject obj = new SFSObject();
//                obj.putUtfString("user", "test");
//                SFSErrorData error = new SFSErrorData(SFSErrorCode.JOIN_BAD_ROOM);
//                error.addParameter(user.getName());
//                throw new SFSJoinRoomException("No!", error);
//		net.sf.json.JSONObject j = new net.sf.json.JSONObject();
//		CardUser cardUser = CardUserManager.getInstance().GetUser(user.getId());
//		CardRoom cardRoom = CardRoomManager.getInstance().GetRoom(room.getId());
//		cardRoom.OnPlayerJoinRoom(cardUser);
		
	}

}