/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfs2x.extension.bacay.src;

import com.smartfoxserver.v2.api.LoginErrorHandler;
import com.smartfoxserver.v2.core.SFSEventType;
import com.smartfoxserver.v2.extensions.SFSExtension;

/**
 *
 * @author ToanNB
 */
public class LoginExtension extends SFSExtension {

    @Override
    public void init() {
        trace("Login extension starting.");
        // Register the login event
        addEventHandler(SFSEventType.USER_LOGIN, LoginHandler.class);
    }

    @Override
    public void destroy() {
        trace("Login extension stopped.");
        super.destroy();
    }
    
}
