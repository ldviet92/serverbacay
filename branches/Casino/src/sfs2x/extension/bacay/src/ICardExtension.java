/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfs2x.extension.bacay.src;

import com.smartfoxserver.v2.core.SFSEventType;
import com.smartfoxserver.v2.extensions.SFSExtension;

/**
 *
 * @author MT405
 */
public class ICardExtension extends SFSExtension{
     @Override
    public void init() {
        trace("Join Room");
        // Register the login event
        addEventHandler(SFSEventType.USER_JOIN_ROOM, UserJoinedRoomEventHandler.class);
        // gui thong tin user
         addRequestHandler("sendInformationUser", SendInformationUser.class);
//         addRequestHandler("countUserInListRoom", countUserInListRoomEventHandler.class);
         //bat dau choi game
         addRequestHandler("startGame", StartGameEventHandler.class);
         //Gui dat cuoc
         addRequestHandler("betGame", BetGameEventHandler.class);
         //Vao ga
         addRequestHandler("vaoGa", ChickenGameEventHandler.class);
         //ke cua
         addRequestHandler("keCua", PlusGameEventHandler.class);
         //dat bien
         addRequestHandler("danhBien", DatBienGameEventHandler.class);
         //nhan bien
         addRequestHandler("nhanBien", nhanbien.class);
        addRequestHandler("getCard", UserStartPlayEventHandler.class);
        
    }

    @Override
    public void destroy() {
        trace("Room extension stopped.");
        super.destroy();
    }
}
