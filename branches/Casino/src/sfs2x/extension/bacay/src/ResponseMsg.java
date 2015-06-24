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
public class ResponseMsg {
    public static final int SUCCESS = 1;
    public static final int FAIL = 0;
    public static final int INCORRECT_SIGNATURE = 403;
    public static final int INVALID_PARAMS = 406;
    public static final int API_FORBIDDEN = 405;
    public static final int INSERT_DB_FAIL = 777;
    public static final int EMAIL_EXIST = 300;
    public static final int EMAIL_OR_USERNAME_EXIST = 306;
    public static final int WRONG_METHOD = 301;
    public static final int HTTP_NOT_FOUND = 404;
    public static final int HTTP_UNAUTHORIZED = 503;
    public static final int USER_NOT_FOUND = 407;
    public static final int WRONG_PASSWORD = 408;
    public static final int HTTP_IP_INCORRECT = 409;
    public static final int OLD_PASSWORD_INCORRECT = 501;
    public static final int PASSWORD_EMPTY = 502;
    public static final int PASSWORD_INCORRECT = 504;
    public static final int USER_EXIST = 505;
    public static final int ACCESS_TOKEN_EXPIRE = 506;
    public static final int ACCESS_TOKEN_NOT_MATCH = 507;
    public static final int CAN_NOT_SEND_EMAIL = 508;
    public static final int GAME_NOT_FOUND = 509;
    
    public static String getMsg(int status){
        String msg = "Thành công";
        switch(status){
            case FAIL:
                msg = "Thất bại";
                break;
            case INCORRECT_SIGNATURE:
                msg = "Chữ kí không đúng";
                break;
            case INVALID_PARAMS:
                msg = "Thiếu biến gửi lên";
                break;
            case API_FORBIDDEN:
                msg = "Không được phép truy cập";
                break;
            case INSERT_DB_FAIL:
                msg = "Không lưu được vào CSDL.";
                break;
            case EMAIL_EXIST:
                msg = "Email đã tồn tại.";
                break;
            case EMAIL_OR_USERNAME_EXIST:
                msg = "Tên đăng nhập hoặc email đã tồn tại.";
                break;
            case WRONG_METHOD:
                msg = "Sai phương thức gửi lên";
                break;
            case HTTP_NOT_FOUND:
                msg = "Nội dung yêu cầu không được tìm thấy";
                break;
            case HTTP_UNAUTHORIZED:
                msg = "Người dùng chưa được xác thực";
                break;
            case USER_NOT_FOUND:
                msg = "Không tìm thấy người dùng.";
                break;
            case WRONG_PASSWORD:
                msg = "Mật khẩu sai.";
                break;
            case HTTP_IP_INCORRECT:
                msg = "Địa chỉ IP ko chi phép truy cập";
                break;
            case OLD_PASSWORD_INCORRECT:
                msg = "Mật khẩu cũ không đúng";
                break;
            case PASSWORD_EMPTY:
                msg = "Mật khẩu rỗng";
                break;
            case PASSWORD_INCORRECT:
                msg = "Mật khẩu không đúng";
                break;
            case USER_EXIST:
                msg = "Người dùng đã tồn tại trong hệ thống";
                break;
            case ACCESS_TOKEN_EXPIRE:
                msg = "access token đã quá hạn";
                break;
            case ACCESS_TOKEN_NOT_MATCH:
                msg = "access token không trùng nhau";
                break;
            case CAN_NOT_SEND_EMAIL:
                msg = "Gửi mail không thành công.";
                break;
            case GAME_NOT_FOUND:
                msg = "Game không được tìm thấy hoặc chưa được kích hoạt.";
                break;
        }
        return msg;
    }
    
}
